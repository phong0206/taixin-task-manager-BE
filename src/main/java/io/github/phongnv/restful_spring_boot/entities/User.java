package io.github.phongnv.restful_spring_boot.entities;

import io.github.phongnv.restful_spring_boot.common.base.BaseEntity;
import io.github.phongnv.restful_spring_boot.common.constants.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
  @Column(nullable = false, length = 100)
  private String name;

  @Column(nullable = false)
  private String password;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(length = 500)
  private String refresh_token;

  @Column(length = 500)
  private String access_token;

  @Enumerated(EnumType.STRING)
  @Column(name = "role")
  private UserRole role = UserRole.USER;

  @PrePersist
  @PreUpdate
  public void normalizeEmail() {
    this.email = this.email.toLowerCase();
  }

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<ProjectUser> projectUsers;

  @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Task> createdTasks;

  @OneToMany(mappedBy = "assignedPerson", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Task> assignedTasks;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Comment> comments;

  @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Notification> receivedNotifications;

  @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Notification> sentNotifications;
}
