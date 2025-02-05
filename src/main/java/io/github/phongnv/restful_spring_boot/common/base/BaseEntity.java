package io.github.phongnv.restful_spring_boot.common.base;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.*;
import java.sql.Timestamp;


@Setter
@Getter
@MappedSuperclass
@SQLDelete(sql = "UPDATE {table_name} SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  private Timestamp created_at;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private Timestamp updated_at;

  @Column(name = "deleted_at")
  private Timestamp deleted_at;
}