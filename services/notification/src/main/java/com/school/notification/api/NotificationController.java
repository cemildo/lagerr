package com.school.notification.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
  private final NotificationRepository repo;
  public NotificationController(NotificationRepository repo) { this.repo = repo; }

  @GetMapping public List<Notification> all() { return repo.findAll(); }

  @PostMapping
  public ResponseEntity<Notification> create(@RequestBody Notification in) {
    var saved = repo.save(in);
    return ResponseEntity.created(URI.create("/api/notification/" + saved.getId())).body(saved);
  }

  @PutMapping("/<built-in function id>")
  public ResponseEntity<Notification> update(@PathVariable Long id, @RequestBody Notification in) {
    return repo.findById(id).map(e -> {
      e.setMessage(in.getMessage()); e.setSent(in.isSent());
      return ResponseEntity.ok(repo.save(e));
    }).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/<built-in function id>")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    if (!repo.existsById(id)) return ResponseEntity.notFound().build();
    repo.deleteById(id); return ResponseEntity.noContent().build();
  }
}
