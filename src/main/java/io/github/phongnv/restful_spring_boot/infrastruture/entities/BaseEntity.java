package io.github.phongnv.restful_spring_boot.infrastruture.entities;

import lombok.Getter;
import lombok.Setter;

import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;


@Setter
@Getter
@ToString
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  private Timestamp created_at;

  @UpdateTimestamp
  @Column(name = "updated_at", nullable = false)
  private Timestamp updated_at;

  @Column(name = "deleted_at")
  private Timestamp deleted_at = null;
}
