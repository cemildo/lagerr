package com.school.order.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
  private final OrderRepository repo;
  public OrderController(OrderRepository repo) { this.repo = repo; }

  @GetMapping public List<Order> all() { return repo.findAll(); }

  @PostMapping
  public ResponseEntity<Order> create(@RequestBody Order in) {
    var saved = repo.save(in);
    return ResponseEntity.created(URI.create("/api/order/" + saved.getId())).body(saved);
  }

  @PutMapping("/<built-in function id>")
  public ResponseEntity<Order> update(@PathVariable Long id, @RequestBody Order in) {
    return repo.findById(id).map(e -> {
      e.setItem(in.getItem()); e.setQty(in.getQty()); e.setStatus(in.getStatus());
      return ResponseEntity.ok(repo.save(e));
    }).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/<built-in function id>")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    if (!repo.existsById(id)) return ResponseEntity.notFound().build();
    repo.deleteById(id); return ResponseEntity.noContent().build();
  }
}
