package org.proiectre.proiectre.common;

public class TutoringPositionDto {
    private Long id;
    private String title;
    private String description;
    private String studentName;

    public TutoringPositionDto(Long id, String title, String description, String studentName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.studentName = studentName;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getStudentName() { return studentName; }
}