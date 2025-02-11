package io.github.phongnv.restful_spring_boot.entities;

import io.github.phongnv.restful_spring_boot.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "project_user")
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
