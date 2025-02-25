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
@Table(name = "projects")
@SQLDelete(sql = "UPDATE {table_name} SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
@NoArgsConstructor
@AllArgsConstructor
public class Project extends BaseEntity {

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String description;

  @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<ProjectUser> projectUsers;

  @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Milestone> milestones;

  @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Task> tasks;
}
