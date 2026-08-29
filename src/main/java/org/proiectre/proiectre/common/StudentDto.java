package org.proiectre.proiectre.common;

public class StudentDto {
    private Long id;
    private String fullName;
    private Integer yearOfStudy;
    private String username;
    private Long userId;

    public StudentDto(Long id, String fullName, Integer yearOfStudy, String username, Long userId) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfStudy = yearOfStudy;
        this.username = username;
        this.userId = userId;
    }

    public Long getId() { return id; }
    public String getFullName() { return fullName; }
    public Integer getYearOfStudy() { return yearOfStudy; }
    public String getUsername() { return username; }
    public Long getUserId() { return userId; }
}