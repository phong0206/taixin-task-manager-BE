package io.github.phongnv.restful_spring_boot.entities;

import io.github.phongnv.restful_spring_boot.common.base.BaseEntity;
import io.github.phongnv.restful_spring_boot.common.constants.ImageType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "images")
@NoArgsConstructor
@AllArgsConstructor
public class Image extends BaseEntity {
  @Column(nullable = false)
  private String filename;

  @Column(nullable = false)
  private String path;

  @Enumerated(EnumType.STRING)
  @Column(name = "type", nullable = false)
  private ImageType type;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String mnmnmnmn;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
  private User user;

  @ManyToOne
  @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = true)
  private Project project;

  @ManyToOne
  @JoinColumn(name = "comment_id", referencedColumnName = "id", nullable = true)
  private Comment comment;
}
