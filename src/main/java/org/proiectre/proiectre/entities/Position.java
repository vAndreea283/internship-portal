package org.proiectre.proiectre.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "positions")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "number_of_slots", nullable = false)
    private Integer numberOfSlots;

    @Column(name = "year_of_study_target", nullable = false)
    private Integer yearOfStudyTarget;

    @Column(name = "application_deadline", nullable = false)
    private LocalDate applicationDeadline;

    @Column(name = "duration_weeks", nullable = false)
    private Integer durationWeeks;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PositionStatus status; // "PENDING", "APPROVED", "CLOSED"

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "position")
    private List<Application> applications;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getNumberOfSlots() { return numberOfSlots; }
    public void setNumberOfSlots(Integer numberOfSlots) { this.numberOfSlots = numberOfSlots; }
    public Integer getYearOfStudyTarget() { return yearOfStudyTarget; }
    public void setYearOfStudyTarget(Integer yearOfStudyTarget) { this.yearOfStudyTarget = yearOfStudyTarget; }
    public LocalDate getApplicationDeadline() { return applicationDeadline; }
    public void setApplicationDeadline(LocalDate applicationDeadline) { this.applicationDeadline = applicationDeadline; }
    public Integer getDurationWeeks() { return durationWeeks; }
    public void setDurationWeeks(Integer durationWeeks) { this.durationWeeks = durationWeeks; }
    public PositionStatus getStatus() { return status; }
    public void setStatus(PositionStatus status) { this.status = status; }
    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }
    public List<Application> getApplications() { return applications; }
    public void setApplications(List<Application> applications) { this.applications = applications; }
}