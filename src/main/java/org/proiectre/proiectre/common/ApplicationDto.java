package org.proiectre.proiectre.common;

import org.proiectre.proiectre.entities.ApplicationStatus;

public class ApplicationDto {
    private Long id;
    private ApplicationStatus status;
    private String studentName;
    private String positionTitle;
    private Long studentId;
    private Long positionId;
    private String interviewResult;
    private Double gradeValue;

    public ApplicationDto(Long id, ApplicationStatus status, String studentName, String positionTitle, Long studentId, Long positionId, String interviewResult, Double gradeValue) {
        this.id = id;
        this.status = status;
        this.studentName = studentName;
        this.positionTitle = positionTitle;
        this.studentId = studentId;
        this.positionId = positionId;
        this.interviewResult = interviewResult;
        this.gradeValue = gradeValue;
    }

    public Long getId() { return id; }
    public ApplicationStatus getStatus() { return status; }
    public String getStudentName() { return studentName; }
    public String getPositionTitle() { return positionTitle; }
    public Long getStudentId() { return studentId; }
    public Long getPositionId() { return positionId; }
    public String getInterviewResult() { return interviewResult; }
    public Double getGradeValue() { return gradeValue; }
}