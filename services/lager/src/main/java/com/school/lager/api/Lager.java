package com.school.lager.api;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stocks")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Lager {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false) private String sku;
  @Column(nullable = false) private Integer qty;

}
