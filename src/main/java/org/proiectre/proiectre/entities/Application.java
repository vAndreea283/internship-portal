package org.proiectre.proiectre.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ApplicationStatus status; // "APPLIED", "INTERVIEWING", "ACCEPTED", "REJECTED", "CLOSED"

    @ManyToOne // many applications - one student
    @JoinColumn(name = "student_id", nullable = false) // spune cum este reprezentata relatia in baza de date, student_id este foreign key catre tabela students
    private Student student;

    @ManyToOne // many applications - one position
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    @OneToOne(mappedBy = "application")
    private Interview interview;

    @OneToOne(mappedBy = "application")
    private Grade grade;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public Position getPosition() { return position; }
    public void setPosition(Position position) { this.position = position; }
    public Interview getInterview() { return interview; }
    public void setInterview(Interview interview) { this.interview = interview; }
    public Grade getGrade() { return grade; }
    public void setGrade(Grade grade) { this.grade = grade; }
}