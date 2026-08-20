package org.proiectre.proiectre.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.proiectre.proiectre.common.PositionDto;
import org.proiectre.proiectre.entities.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class PositionBean {

    private static final Logger LOG = Logger.getLogger(PositionBean.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    public List<PositionDto> findAllPositions() {
        LOG.info("findAllPositions");
        try {
            TypedQuery<Position> typedQuery = entityManager.createQuery("SELECT p FROM Position p", Position.class);
            List<Position> positions = typedQuery.getResultList();
            return copyPositionsToDto(positions);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    private List<PositionDto> copyPositionsToDto(List<Position> positions) {
        List<PositionDto> dtos = new ArrayList<>();
        for (Position p : positions) {
            dtos.add(new PositionDto(
                    p.getId(), p.getTitle(), p.getDescription(), p.getNumberOfSlots(), p.getYearOfStudyTarget(),
                    p.getApplicationDeadline(), p.getDurationWeeks(), p.getStatus(),
                    p.getCompany() != null ? p.getCompany().getName() : null));
        }
        return dtos;
    }
}