package com.school.order.api;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Order {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false) private String item;
  @Column(nullable = false) private Integer qty;
  @Column(nullable = false) private String status;

}
