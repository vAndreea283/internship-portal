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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Stateless
public class PositionBean {

    private static final Logger LOG = Logger.getLogger(PositionBean.class.getName());
    private static final int PAGE_SIZE = 3;

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

    public List<PositionDto> findAllPositionsPaged(int page) {
        LOG.info("findAllPositionsPaged " + page);
        try {
            TypedQuery<Position> typedQuery = entityManager.createQuery("SELECT p FROM Position p", Position.class);
            typedQuery.setFirstResult((page - 1) * PAGE_SIZE);
            typedQuery.setMaxResults(PAGE_SIZE);
            return copyPositionsToDto(typedQuery.getResultList());
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public int countAllPositions() {
        TypedQuery<Long> countQuery = entityManager.createQuery("SELECT COUNT(p) FROM Position p", Long.class);
        return (int) Math.ceil(countQuery.getSingleResult() / (double) PAGE_SIZE);
    }

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

    public List<PositionDto> searchPositions(String query) {
        LOG.info("searchPositions " + query);
        try {
            TypedQuery<Position> typedQuery = entityManager.createQuery(
                    "SELECT p FROM Position p WHERE LOWER(p.title) LIKE LOWER(:q) OR LOWER(p.company.name) LIKE LOWER(:q)",
                    Position.class);
            typedQuery.setParameter("q", "%" + query + "%");
            return copyPositionsToDto(typedQuery.getResultList());
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public List<PositionDto> searchPositionsPaged(String query, int page) {
        LOG.info("searchPositionsPaged " + query + " page " + page);
        try {
            TypedQuery<Position> typedQuery = entityManager.createQuery(
                    "SELECT p FROM Position p WHERE LOWER(p.title) LIKE LOWER(:q) OR LOWER(p.company.name) LIKE LOWER(:q)",
                    Position.class);
            typedQuery.setParameter("q", "%" + query + "%");
            typedQuery.setFirstResult((page - 1) * PAGE_SIZE);
            typedQuery.setMaxResults(PAGE_SIZE);
            return copyPositionsToDto(typedQuery.getResultList());
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public int countSearchResults(String query) {
        TypedQuery<Long> countQuery = entityManager.createQuery(
                "SELECT COUNT(p) FROM Position p WHERE LOWER(p.title) LIKE LOWER(:q) OR LOWER(p.company.name) LIKE LOWER(:q)",
                Long.class);
        countQuery.setParameter("q", "%" + query + "%");
        return (int) Math.ceil(countQuery.getSingleResult() / (double) PAGE_SIZE);
    }

    public Map<Integer, Long> countPositionsByYear() {
        LOG.info("countPositionsByYear");
        try {
            TypedQuery<Object[]> typedQuery = entityManager.createQuery(
                    "SELECT p.yearOfStudyTarget, COUNT(p) FROM Position p GROUP BY p.yearOfStudyTarget", Object[].class);
            Map<Integer, Long> counts = new LinkedHashMap<>();
            for (Object[] row : typedQuery.getResultList()) {
                counts.put((Integer) row[0], (Long) row[1]);
            }
            return counts;
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public List<PositionDto> findByCompanyUsername(String username) {
        LOG.info("findByCompanyUsername " + username);
        try {
            TypedQuery<Position> typedQuery = entityManager.createQuery(
                    "SELECT p FROM Position p WHERE p.company.user.username = :username", Position.class);
            typedQuery.setParameter("username", username);
            return copyPositionsToDto(typedQuery.getResultList());
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
}