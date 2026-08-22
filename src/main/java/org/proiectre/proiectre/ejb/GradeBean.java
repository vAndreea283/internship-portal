package org.proiectre.proiectre.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.proiectre.proiectre.common.GradeDto;
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

    private List<GradeDto> copyGradesToDto(List<Grade> grades) {
        List<GradeDto> dtos = new ArrayList<>();
        for (Grade g : grades) {
            dtos.add(new GradeDto(
                    g.getId(), g.getValue(),
                    g.getApplication().getId())); // g.getApplication() != null ? g.getApplication().getId() : null
        }
        return dtos;
    }
}