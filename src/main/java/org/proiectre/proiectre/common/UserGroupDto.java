package org.proiectre.proiectre.common;

public class UserGroupDto {
    private Long id;
    private String username;
    private String userGroup;

    public UserGroupDto(Long id, String username, String userGroup) {
        this.id = id;
        this.username = username;
        this.userGroup = userGroup;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getUserGroup() { return userGroup; }
}