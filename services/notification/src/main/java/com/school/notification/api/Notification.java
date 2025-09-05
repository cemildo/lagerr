package com.school.notification.api;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notifications")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Notification {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false) private String message;
  @Column(nullable = false) private boolean sent;

}
