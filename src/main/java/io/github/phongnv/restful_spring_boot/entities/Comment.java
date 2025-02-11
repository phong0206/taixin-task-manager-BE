package io.github.phongnv.restful_spring_boot.entities;

import io.github.phongnv.restful_spring_boot.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity {

  @Column( columnDefinition = "TEXT")
  private String content;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "task_id", nullable = false)
  private Task task;

}
