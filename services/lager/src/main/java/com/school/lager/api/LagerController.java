package com.school.lager.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/lager")
public class LagerController {
  private final LagerRepository repo;
  public LagerController(LagerRepository repo) { this.repo = repo; }

  @GetMapping public List<Lager> all() { return repo.findAll(); }

  @PostMapping
  public ResponseEntity<Lager> create(@RequestBody Lager in) {
    var saved = repo.save(in);
    return ResponseEntity.created(URI.create("/api/lager/" + saved.getId())).body(saved);
  }

  @PutMapping("/<built-in function id>")
  public ResponseEntity<Lager> update(@PathVariable Long id, @RequestBody Lager in) {
    return repo.findById(id).map(e -> {
      e.setSku(in.getSku()); e.setQty(in.getQty());
      return ResponseEntity.ok(repo.save(e));
    }).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/<built-in function id>")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    if (!repo.existsById(id)) return ResponseEntity.notFound().build();
    repo.deleteById(id); return ResponseEntity.noContent().build();
  }
}
