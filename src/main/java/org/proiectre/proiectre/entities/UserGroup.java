package org.proiectre.proiectre.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "usergroups")
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "usergroup", nullable = false)
    private String userGroup; // "STUDENT", "COMPANY", "DEPARTMENT"

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getUserGroup() { return userGroup; }
    public void setUserGroup(String userGroup) { this.userGroup = userGroup; }
}