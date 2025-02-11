package io.github.phongnv.restful_spring_boot.entities;

import io.github.phongnv.restful_spring_boot.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(name = "milestones")
@NoArgsConstructor
@AllArgsConstructor
public class Milestone extends BaseEntity {

  @Column(nullable = false, length = 200)
  private String name;

  @ManyToOne
  @JoinColumn(name = "project_id", nullable = false)
  private Project project;

  @OneToMany(mappedBy = "milestone", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Task> tasks;
}
