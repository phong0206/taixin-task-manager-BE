package io.github.phongnv.restful_spring_boot.infrastruture.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@ToString
@Table(name = "milestones")
@SQLDelete(sql = "UPDATE {table_name} SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
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
