// ejb/StudentBean.java
package org.proiectre.proiectre.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.proiectre.proiectre.common.StudentDto;
import org.proiectre.proiectre.entities.Student;

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

    private List<StudentDto> copyStudentsToDto(List<Student> students) {
        List<StudentDto> dtos = new ArrayList<>();
        for (Student s : students) {
            dtos.add(new StudentDto(
                    s.getId(), s.getFullName(), s.getYearOfStudy(), s.getCvPath(), s.getImagePath(),
                    s.getUser() != null ? s.getUser().getUsername() : null));
        }
        return dtos;
    }
}