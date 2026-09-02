package org.proiectre.proiectre.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.proiectre.proiectre.common.StudentCvDto;
import org.proiectre.proiectre.common.StudentDto;
import org.proiectre.proiectre.common.StudentPhotoDto;
import org.proiectre.proiectre.entities.Student;
import org.proiectre.proiectre.entities.StudentCv;
import org.proiectre.proiectre.entities.StudentPhoto;
import org.proiectre.proiectre.entities.User;
import org.proiectre.proiectre.entities.UserGroup;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class StudentBean {

    private static final Logger LOG = Logger.getLogger(StudentBean.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private PasswordBean passwordBean;

    public List<StudentDto> findAllStudents() {
        LOG.info("findAllStudents");
        try {
            TypedQuery<Student> typedQuery = entityManager.createQuery("SELECT s FROM Student s", Student.class);
            List<Student> students = typedQuery.getResultList();
            return copyStudentsToDto(students);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    /*private List<StudentDto> copyStudentsToDto(List<Student> students) {
        List<StudentDto> dtos = new ArrayList<>();
        for (Student s : students) {
            dtos.add(new StudentDto(
                    s.getId(),
                    s.getFullName(),
                    s.getYearOfStudy(),
                    s.getCvPath(),
                    s.getImagePath(),
                    s.getUser().getUsername(), // s.getUser() != null ? s.getUser().getUsername() : null));
                    s.getUser().getId()));
        }
        return dtos;
    }*/

    public StudentDto findById(Long id) {
        LOG.info("findById " + id);
        try {
            Student student = entityManager.find(Student.class, id);
            return student != null ? toDto(student) : null;
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    // creeaza User + UserGroup(APPLY_POSITIONS) + Student intr-o singura tranzactie
    public String createStudent(String username, String email, String password, String fullName, Integer yearOfStudy) {
        LOG.info("createStudent " + username);
        try {
            TypedQuery<Long> countQuery = entityManager.createQuery(
                    "SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class);
            countQuery.setParameter("username", username);
            if (countQuery.getSingleResult() > 0) {
                return "Acest username este deja folosit.";
            }

            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(passwordBean.convertToSha256(password));
            entityManager.persist(user);

            UserGroup group = new UserGroup();
            group.setUsername(username);
            group.setUserGroup("APPLY_POSITIONS");
            entityManager.persist(group);

            Student student = new Student();
            student.setFullName(fullName);
            student.setYearOfStudy(yearOfStudy);
            student.setUser(user);
            entityManager.persist(student);

            return null;
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    // editarea nu mai atinge contul - doar datele studentului
    public void updateStudent(Long id, String fullName, Integer yearOfStudy) {
        LOG.info("updateStudent " + id);
        try {
            Student student = entityManager.find(Student.class, id);
            student.setFullName(fullName);
            student.setYearOfStudy(yearOfStudy);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public void deleteStudentsByIds(List<Long> ids) {
        LOG.info("deleteStudentsByIds " + ids);
        try {
            for (Long id : ids) {
                Student student = entityManager.find(Student.class, id);
                if (student != null) {
                    entityManager.remove(student);
                }
            }
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    private StudentDto toDto(Student s) {
        return new StudentDto(
                s.getId(), s.getFullName(), s.getYearOfStudy(),
                s.getUser() != null ? s.getUser().getUsername() : null,
                s.getUser() != null ? s.getUser().getId() : null);
    }

    private List<StudentDto> copyStudentsToDto(List<Student> students) {
        List<StudentDto> dtos = new ArrayList<>();
        for (Student s : students) {
            dtos.add(toDto(s));
        }
        return dtos;
    }

    public StudentDto findByUsername(String username) {
        LOG.info("findByUsername " + username);
        try {
            TypedQuery<Student> typedQuery = entityManager.createQuery(
                    "SELECT s FROM Student s WHERE s.user.username = :username", Student.class);
            typedQuery.setParameter("username", username);
            List<Student> results = typedQuery.getResultList();
            return results.isEmpty() ? null : toDto(results.get(0));
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    // upsert: daca studentul are deja poza, o actualizeaza in loc; altfel creeaza una noua
    // (evita sterge+adauga, care ar viola constrangerea unique pe student_id daca INSERT-ul ajunge inaintea DELETE-ului)
    public void addPhotoToStudent(Long studentId, String filename, String fileType, byte[] fileContent) {
        LOG.info("addPhotoToStudent " + studentId);
        try {
            Student student = entityManager.find(Student.class, studentId);
            StudentPhoto photo = student.getPhoto();
            if (photo == null) {
                photo = new StudentPhoto();
                photo.setStudent(student);
                student.setPhoto(photo);
                entityManager.persist(photo);
            }
            photo.setFilename(filename);
            photo.setFileType(fileType);
            photo.setFileContent(fileContent);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public StudentPhotoDto findPhotoByStudentId(Long studentId) {
        LOG.info("findPhotoByStudentId " + studentId);
        try {
            TypedQuery<StudentPhoto> typedQuery = entityManager.createQuery(
                    "SELECT p FROM StudentPhoto p WHERE p.student.id = :id", StudentPhoto.class);
            typedQuery.setParameter("id", studentId);
            List<StudentPhoto> photos = typedQuery.getResultList();
            if (photos.isEmpty()) {
                return null;
            }
            StudentPhoto photo = photos.get(0);
            return new StudentPhotoDto(photo.getId(), photo.getFilename(), photo.getFileType(), photo.getFileContent());
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    // upsert, acelasi motiv ca la poza
    public void addCvToStudent(Long studentId, String filename, String fileType, byte[] fileContent) {
        LOG.info("addCvToStudent " + studentId);
        try {
            Student student = entityManager.find(Student.class, studentId);
            StudentCv cv = student.getCv();
            if (cv == null) {
                cv = new StudentCv();
                cv.setStudent(student);
                student.setCv(cv);
                entityManager.persist(cv);
            }
            cv.setFilename(filename);
            cv.setFileType(fileType);
            cv.setFileContent(fileContent);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public StudentCvDto findCvByStudentId(Long studentId) {
        LOG.info("findCvByStudentId " + studentId);
        try {
            TypedQuery<StudentCv> typedQuery = entityManager.createQuery(
                    "SELECT c FROM StudentCv c WHERE c.student.id = :id", StudentCv.class);
            typedQuery.setParameter("id", studentId);
            List<StudentCv> cvs = typedQuery.getResultList();
            if (cvs.isEmpty()) {
                return null;
            }
            StudentCv cv = cvs.get(0);
            return new StudentCvDto(cv.getId(), cv.getFilename(), cv.getFileType(), cv.getFileContent());
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    // format CSV asteptat (cu header pe prima linie): username,email,password,fullName,yearOfStudy
    public String importStudentsFromCsv(byte[] csvContent) {
        LOG.info("importStudentsFromCsv");
        try {
            String content = new String(csvContent, StandardCharsets.UTF_8);
            String[] lines = content.split("\\r?\\n");
            int imported = 0;
            int skipped = 0;

            for (int i = 1; i < lines.length; i++) {
                String line = lines[i].trim();
                if (line.isEmpty()) continue;

                String[] cols = line.split(",");
                if (cols.length < 5) { skipped++; continue; }

                String error = createStudent(cols[0].trim(), cols[1].trim(), cols[2].trim(),
                        cols[3].trim(), Integer.valueOf(cols[4].trim()));

                if (error != null) { skipped++; } else { imported++; }
            }
            return imported + " studenti importati, " + skipped + " sariti (username existent sau linie invalida).";
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
}