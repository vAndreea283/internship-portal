package org.proiectre.proiectre.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.proiectre.proiectre.common.StudentDto;
import org.proiectre.proiectre.common.TutoringPositionDto;
import org.proiectre.proiectre.entities.Student;
import org.proiectre.proiectre.entities.TutoringPosition;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class TutoringPositionBean {

    private static final Logger LOG = Logger.getLogger(TutoringPositionBean.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    public List<TutoringPositionDto> findAllTutoringPositions() {
        LOG.info("findAllTutoringPositions");
        try {
            TypedQuery<TutoringPosition> typedQuery = entityManager.createQuery("SELECT t FROM TutoringPosition t", TutoringPosition.class);
            List<TutoringPosition> positions = typedQuery.getResultList();
            return copyToDto(positions);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    /* studenti fara nicio pozitie de internship acceptata si fara tutoring deja asignat */
    public List<StudentDto> findStudentsWithoutInternship() {
        LOG.info("findStudentsWithoutInternship");
        try {
            TypedQuery<Student> typedQuery = entityManager.createQuery(
                    "SELECT s FROM Student s WHERE s.id NOT IN " +
                            "(SELECT a.student.id FROM Application a WHERE a.status = 'ACCEPTED') " +
                            "AND s.id NOT IN (SELECT t.student.id FROM TutoringPosition t WHERE t.student IS NOT NULL)",
                    Student.class);
            List<StudentDto> dtos = new ArrayList<>();
            for (Student s : typedQuery.getResultList()) {
                dtos.add(new StudentDto(s.getId(), s.getFullName(), s.getYearOfStudy(), s.getCvPath(), s.getImagePath(),
                        s.getUser() != null ? s.getUser().getUsername() : null,
                        s.getUser() != null ? s.getUser().getId() : null));
            }
            return dtos;
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public void createTutoringPosition(String title, String description) {
        LOG.info("createTutoringPosition");
        try {
            TutoringPosition tutoringPosition = new TutoringPosition();
            tutoringPosition.setTitle(title);
            tutoringPosition.setDescription(description);
            entityManager.persist(tutoringPosition);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public void assignStudent(Long tutoringPositionId, Long studentId) {
        LOG.info("assignStudent " + tutoringPositionId + " -> " + studentId);
        try {
            TutoringPosition tutoringPosition = entityManager.find(TutoringPosition.class, tutoringPositionId);
            Student student = entityManager.find(Student.class, studentId);
            tutoringPosition.setStudent(student);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    private TutoringPositionDto toDto(TutoringPosition t) {
        return new TutoringPositionDto(
                t.getId(), t.getTitle(), t.getDescription(),
                t.getStudent() != null ? t.getStudent().getFullName() : null,
                t.getStudent() != null ? t.getStudent().getId() : null);
    }

    private List<TutoringPositionDto> copyToDto(List<TutoringPosition> positions) {
        List<TutoringPositionDto> dtos = new ArrayList<>();
        for (TutoringPosition t : positions) {
            dtos.add(toDto(t));
        }
        return dtos;
    }

    /*private List<TutoringPositionDto> copyToDto(List<TutoringPosition> positions) {
        List<TutoringPositionDto> dtos = new ArrayList<>();
        for (TutoringPosition t : positions) {
            dtos.add(new TutoringPositionDto(
                    t.getId(), t.getTitle(), t.getDescription(),
                    t.getStudent() != null ? t.getStudent().getFullName() : null)); // TutoringPosition este o pozitie de practica care poate exista inainte sa fie atribuita unui student
        }
        return dtos;
    }*/
}