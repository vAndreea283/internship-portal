package org.proiectre.proiectre.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "year_of_study", nullable = false)
    private Integer yearOfStudy;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @OneToMany(mappedBy = "student")
    private List<Application> applications;

    /* cascade = CascadeType.ALL — daca ștergi studentul, se șterg automat si poza/CV-ul lui
    fetch = FetchType.LAZY — nu se incarca bytes de fiecare data cand citesti un student, doar cand chiar ai nevoie de ei */
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private StudentPhoto photo;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private StudentCv cv;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public Integer getYearOfStudy() { return yearOfStudy; }
    public void setYearOfStudy(Integer yearOfStudy) { this.yearOfStudy = yearOfStudy; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public List<Application> getApplications() { return applications; }
    public void setApplications(List<Application> applications) { this.applications = applications; }
    public StudentPhoto getPhoto() { return photo; }
    public void setPhoto(StudentPhoto photo) { this.photo = photo; }
    public StudentCv getCv() { return cv; }
    public void setCv(StudentCv cv) { this.cv = cv; }
}