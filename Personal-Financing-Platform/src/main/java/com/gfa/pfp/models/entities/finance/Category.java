package com.gfa.pfp.models.entities.finance;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "category")
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;
  Long userId;
  String name;

    public Category(Long userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public Category(String name) {
        this.name = name;
    }

}