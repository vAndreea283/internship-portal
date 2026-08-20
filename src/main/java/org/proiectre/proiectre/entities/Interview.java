package org.proiectre.proiectre.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "interviews")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "summary", length = 2000)
    private String summary;

    @Enumerated(EnumType.STRING)
    @Column(name = "result", nullable = false)
    private InterviewResult result; // "PENDING", "PASSED", "FAILED"

    @OneToOne // one interview - one application
    @JoinColumn(name = "application_id", nullable = false, unique = true)
    private Application application;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
    public InterviewResult getResult() { return result; }
    public void setResult(InterviewResult result) { this.result = result; }
    public Application getApplication() { return application; }
    public void setApplication(Application application) { this.application = application; }
}