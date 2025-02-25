package io.github.phongnv.restful_spring_boot.infrastruture.entities;

import io.github.phongnv.restful_spring_boot.infrastruture.common.constants.ImageType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Builder
@Entity
@ToString
@Table(name = "images")
@SQLDelete(sql = "UPDATE {table_name} SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
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
