package org.proiectre.proiectre.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.proiectre.proiectre.common.GradeDto;
import org.proiectre.proiectre.entities.Application;
import org.proiectre.proiectre.entities.Grade;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class GradeBean {

    private static final Logger LOG = Logger.getLogger(GradeBean.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    public List<GradeDto> findAllGrades() {
        LOG.info("findAllGrades");
        try {
            TypedQuery<Grade> typedQuery = entityManager.createQuery("SELECT g FROM Grade g", Grade.class);
            List<Grade> grades = typedQuery.getResultList();
            return copyGradesToDto(grades);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public GradeDto findByApplicationId(Long applicationId) {
        LOG.info("findByApplicationId " + applicationId);
        try {
            TypedQuery<Grade> typedQuery = entityManager.createQuery(
                    "SELECT g FROM Grade g WHERE g.application.id = :appId", Grade.class);
            typedQuery.setParameter("appId", applicationId);
            List<Grade> results = typedQuery.getResultList();
            return results.isEmpty() ? null : toDto(results.get(0));
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public void saveGrade(Long applicationId, Double value) {
        LOG.info("saveGrade " + applicationId);
        try {
            TypedQuery<Grade> typedQuery = entityManager.createQuery(
                    "SELECT g FROM Grade g WHERE g.application.id = :appId", Grade.class);
            typedQuery.setParameter("appId", applicationId);
            List<Grade> results = typedQuery.getResultList();

            Grade grade;
            if (results.isEmpty()) {
                grade = new Grade();
                Application application = entityManager.find(Application.class, applicationId);
                grade.setApplication(application);
                entityManager.persist(grade);
            } else {
                grade = results.get(0);
            }
            grade.setValue(value);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    private GradeDto toDto(Grade g) {
        return new GradeDto(
                g.getId(), g.getValue(),
                g.getApplication() != null ? g.getApplication().getId() : null);
    }

    private List<GradeDto> copyGradesToDto(List<Grade> grades) {
        List<GradeDto> dtos = new ArrayList<>();
        for (Grade g : grades) {
            dtos.add(toDto(g));
        }
        return dtos;
    }

    /*private List<GradeDto> copyGradesToDto(List<Grade> grades) {
        List<GradeDto> dtos = new ArrayList<>();
        for (Grade g : grades) {
            dtos.add(new GradeDto(
                    g.getId(), g.getValue(),
                    g.getApplication().getId())); // g.getApplication() != null ? g.getApplication().getId() : null
        }
        return dtos;
    }*/
}
