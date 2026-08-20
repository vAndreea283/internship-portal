package org.proiectre.proiectre.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tutoring_positions")
public class TutoringPosition { // poziție de practica in facultate, fallback pentru studentii ramasi fara loc
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = 2000)
    private String description;

    @OneToOne // un tutoring position poate fi ocupat de un singur student, iar un student poate ocupa un singur tutoring position
    @JoinColumn(name = "student_id", unique = true)
    private Student student; // null pana e asignat un student

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
}