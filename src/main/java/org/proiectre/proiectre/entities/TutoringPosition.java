package org.proiectre.proiectre.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tutoring_positions")
public class TutoringPosition { // pozitie de practica in facultate, fallback pentru studentii ramasi fara loc
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = 2000)
    private String description;

    @OneToMany(mappedBy = "tutoringPosition") // o pozitie de tutoriat poate avea mai multi studenti asignati
    private List<Student> students;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<Student> getStudents() { return students; }
    public void setStudents(List<Student> students) { this.students = students; }
}