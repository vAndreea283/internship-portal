package org.proiectre.proiectre.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "student_photos")
public class StudentPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "filename", nullable = false)
    private String filename;

    @Column(name = "file_type", nullable = false)
    private String fileType;

    @Lob // large object
    @Column(columnDefinition = "LONGBLOB") // poze de rezolutie mare
    private byte[] fileContent;

    @OneToOne
    @JoinColumn(name = "student_id", nullable = false, unique = true)
    private Student student;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFilename() { return filename; }
    public void setFilename(String filename) { this.filename = filename; }
    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }
    public byte[] getFileContent() { return fileContent; }
    public void setFileContent(byte[] fileContent) { this.fileContent = fileContent; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
}