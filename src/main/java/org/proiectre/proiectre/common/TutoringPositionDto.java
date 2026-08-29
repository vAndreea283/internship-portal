package org.proiectre.proiectre.common;

public class TutoringPositionDto {
    private Long id;
    private String title;
    private String description;
    private String studentName;
    private Long studentId;

    public TutoringPositionDto(Long id, String title, String description, String studentName, Long studentId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.studentName = studentName;
        this.studentId = studentId;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getStudentName() { return studentName; }
    public Long getStudentId() { return studentId; }
}