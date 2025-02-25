package io.github.phongnv.restful_spring_boot.infrastruture.entities;

import io.github.phongnv.restful_spring_boot.infrastruture.common.constants.TypeNotification;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Builder
@Entity
@ToString
@Table(name = "notifications")
@SQLDelete(sql = "UPDATE {table_name} SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
@NoArgsConstructor
@AllArgsConstructor
public class Notification extends BaseEntity {

  @Enumerated(EnumType.STRING)
  @Column(name = "type", nullable = false)
  private TypeNotification type;

  @Column(nullable = false)
  private Boolean is_read = false;

  @ManyToOne
  @JoinColumn(name = "recipient_id", nullable = false)
  private User recipient;

  @ManyToOne
  @JoinColumn(name = "sender_id", nullable = false)
  private User sender;

  @ManyToOne
  @JoinColumn(name = "task_id", nullable = false)
  private Task task;
}
