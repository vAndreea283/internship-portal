// ejb/TutoringPositionBean.java
package org.proiectre.proiectre.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.proiectre.proiectre.common.TutoringPositionDto;
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

    private List<TutoringPositionDto> copyToDto(List<TutoringPosition> positions) {
        List<TutoringPositionDto> dtos = new ArrayList<>();
        for (TutoringPosition t : positions) {
            dtos.add(new TutoringPositionDto(
                    t.getId(), t.getTitle(), t.getDescription(),
                    t.getStudent() != null ? t.getStudent().getFullName() : null)); // TutoringPosition este o pozitie de practica care poate exista inainte sa fie atribuita unui student
        }
        return dtos;
    }
}