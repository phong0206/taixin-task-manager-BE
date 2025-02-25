package io.github.phongnv.restful_spring_boot.infrastruture.entities;

import io.github.phongnv.restful_spring_boot.infrastruture.common.constants.StatusTask;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@ToString
@Table(name = "tasks")
@SQLDelete(sql = "UPDATE {table_name} SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
@NoArgsConstructor
@AllArgsConstructor
public class Task extends BaseEntity {
  @Column(nullable = false)
  private String title;

  @Column( columnDefinition = "TEXT")
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private StatusTask status;

  @Column(nullable = false, length = 100)
  private String priority;

  @Column(nullable = false)
  private Timestamp start_date;

  @Column(nullable = false)
  private Timestamp due_date;

  @ManyToOne
  @JoinColumn(name = "project_id", nullable = false)
  private Project project;

  @ManyToOne
  @JoinColumn(name = "milestone_id", nullable = false)
  private Milestone milestone;

  @ManyToOne
  @JoinColumn(name = "assigned_to", nullable = false)
  private User assignedPerson;

  @ManyToOne
  @JoinColumn(name = "create_by", nullable = false)
  private User creator;

  @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Comment> comments;

  @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Notification> notifications;
}
