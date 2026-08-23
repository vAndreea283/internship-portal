package org.proiectre.proiectre.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.proiectre.proiectre.common.StudentDto;
import org.proiectre.proiectre.entities.Student;
import org.proiectre.proiectre.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class StudentBean {

    private static final Logger LOG = Logger.getLogger(StudentBean.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

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

    public void createStudent(String fullName, Integer yearOfStudy, String cvPath, String imagePath, Long userId) {
        LOG.info("createStudent");
        try {
            User user = entityManager.find(User.class, userId);

            Student student = new Student();
            student.setFullName(fullName);
            student.setYearOfStudy(yearOfStudy);
            student.setCvPath(cvPath);
            student.setImagePath(imagePath);
            student.setUser(user);

            entityManager.persist(student);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public void updateStudent(Long id, String fullName, Integer yearOfStudy, String cvPath, String imagePath, Long userId) {
        LOG.info("updateStudent " + id);
        try {
            Student student = entityManager.find(Student.class, id);
            User user = entityManager.find(User.class, userId);

            student.setFullName(fullName);
            student.setYearOfStudy(yearOfStudy);
            student.setCvPath(cvPath);
            student.setImagePath(imagePath);
            student.setUser(user);
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
                s.getId(), s.getFullName(), s.getYearOfStudy(), s.getCvPath(), s.getImagePath(),
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
}