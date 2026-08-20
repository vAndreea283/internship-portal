package org.proiectre.proiectre.common;

import org.proiectre.proiectre.entities.PositionStatus;

import java.time.LocalDate;

public class PositionDto {
    private Long id;
    private String title;
    private String description;
    private Integer numberOfSlots;
    private Integer yearOfStudyTarget;
    private LocalDate applicationDeadline;
    private Integer durationWeeks;
    private PositionStatus status;
    private String companyName;

    public PositionDto(Long id, String title, String description, Integer numberOfSlots, Integer yearOfStudyTarget,
                       LocalDate applicationDeadline, Integer durationWeeks, PositionStatus status, String companyName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.numberOfSlots = numberOfSlots;
        this.yearOfStudyTarget = yearOfStudyTarget;
        this.applicationDeadline = applicationDeadline;
        this.durationWeeks = durationWeeks;
        this.status = status;
        this.companyName = companyName;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Integer getNumberOfSlots() { return numberOfSlots; }
    public Integer getYearOfStudyTarget() { return yearOfStudyTarget; }
    public LocalDate getApplicationDeadline() { return applicationDeadline; }
    public Integer getDurationWeeks() { return durationWeeks; }
    public PositionStatus getStatus() { return status; }
    public String getCompanyName() { return companyName; }
}