package io.github.phongnv.restful_spring_boot.infrastruture.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Builder
@Entity
@ToString
@Table(name = "project_user")
@SQLDelete(sql = "UPDATE {table_name} SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
@NoArgsConstructor
@AllArgsConstructor
public class ProjectUser extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "project_id", nullable = false)
  private Project project;
}
