package org.proiectre.proiectre.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.proiectre.proiectre.common.PositionDto;
import org.proiectre.proiectre.entities.Company;
import org.proiectre.proiectre.entities.Position;
import org.proiectre.proiectre.entities.PositionStatus;

import java.time.LocalDate;
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

    /* 1. parcurge lista de Position; 2. transforma fiecare Position in PositionDto */
    /*private List<PositionDto> copyPositionsToDto(List<Position> positions) {
        List<PositionDto> dtos = new ArrayList<>();
        for (Position p : positions) {
            dtos.add(new PositionDto(
                    p.getId(),
                    p.getTitle(),
                    p.getDescription(),
                    p.getNumberOfSlots(),
                    p.getYearOfStudyTarget(),
                    p.getApplicationDeadline(),
                    p.getDurationWeeks(),
                    p.getStatus(),
                    p.getCompany().getName())); // p.getCompany() != null ? p.getCompany().getName() : null));
        }
        return dtos;
    }*/

    public PositionDto findById(Long id) {
        LOG.info("findById " + id);
        try {
            Position position = entityManager.find(Position.class, id);
            if (position == null) {
                return null;
            }
            return toDto(position);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public void createPosition(String title, String description, Integer numberOfSlots, Integer yearOfStudyTarget,
                               LocalDate applicationDeadline, Integer durationWeeks, PositionStatus status, Long companyId) {
        LOG.info("createPosition");
        try {
            Company company = entityManager.find(Company.class, companyId);

            Position position = new Position();
            position.setTitle(title);
            position.setDescription(description);
            position.setNumberOfSlots(numberOfSlots);
            position.setYearOfStudyTarget(yearOfStudyTarget);
            position.setApplicationDeadline(applicationDeadline);
            position.setDurationWeeks(durationWeeks);
            position.setStatus(status);
            position.setCompany(company);

            entityManager.persist(position);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public void updatePosition(Long id, String title, String description, Integer numberOfSlots, Integer yearOfStudyTarget,
                               LocalDate applicationDeadline, Integer durationWeeks, PositionStatus status, Long companyId) {
        LOG.info("updatePosition " + id);
        try {
            Position position = entityManager.find(Position.class, id);
            Company company = entityManager.find(Company.class, companyId);

            position.setTitle(title);
            position.setDescription(description);
            position.setNumberOfSlots(numberOfSlots);
            position.setYearOfStudyTarget(yearOfStudyTarget);
            position.setApplicationDeadline(applicationDeadline);
            position.setDurationWeeks(durationWeeks);
            position.setStatus(status);
            position.setCompany(company);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public void deletePositionsByIds(List<Long> ids) {
        LOG.info("deletePositionsByIds " + ids);
        try {
            for (Long id : ids) {
                Position position = entityManager.find(Position.class, id);
                if (position != null) {
                    entityManager.remove(position);
                }
            }
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    /* 1. transforma un singur Position intr-un PositionDto */
    private PositionDto toDto(Position p) {
        return new PositionDto(
                p.getId(),
                p.getTitle(),
                p.getDescription(),
                p.getNumberOfSlots(),
                p.getYearOfStudyTarget(),
                p.getApplicationDeadline(),
                p.getDurationWeeks(),
                p.getStatus(),
                p.getCompany().getName(),
                p.getCompany().getId());
    }

    /* 2. transforma o lista de Position intr-o lista de PositionDto */
    private List<PositionDto> copyPositionsToDto(List<Position> positions) {
        List<PositionDto> dtos = new ArrayList<>();
        for (Position p : positions) {
            dtos.add(toDto(p));
        }
        return dtos;
    }
}