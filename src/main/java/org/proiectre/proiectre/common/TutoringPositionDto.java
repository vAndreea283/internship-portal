package org.proiectre.proiectre.common;

public class TutoringPositionDto {
    private Long id;
    private String title;
    private String description;
    private String studentNames;

    public TutoringPositionDto(Long id, String title, String description, String studentNames) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.studentNames = studentNames;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getStudentNames() { return studentNames; }
}