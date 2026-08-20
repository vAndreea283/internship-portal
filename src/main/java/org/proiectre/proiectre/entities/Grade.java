package org.proiectre.proiectre.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "grades")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "value", nullable = false)
    private Double value;

    @OneToOne // one grade - one application
    @JoinColumn(name = "application_id", nullable = false, unique = true)
    private Application application;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Double getValue() { return value; }
    public void setValue(Double value) { this.value = value; }
    public Application getApplication() { return application; }
    public void setApplication(Application application) { this.application = application; }
}