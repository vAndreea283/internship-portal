package org.proiectre.proiectre.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.proiectre.proiectre.common.ApplicationDto;
import org.proiectre.proiectre.entities.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class ApplicationBean {

    private static final Logger LOG = Logger.getLogger(ApplicationBean.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    public List<ApplicationDto> findAllApplications() {
        LOG.info("findAllApplications");
        try {
            TypedQuery<Application> typedQuery = entityManager.createQuery("SELECT a FROM Application a", Application.class);
            List<Application> applications = typedQuery.getResultList();
            return copyToDto(applications);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public List<ApplicationDto> findByStudentUsername(String username) {
        LOG.info("findByStudentUsername " + username);
        try {
            TypedQuery<Application> typedQuery = entityManager.createQuery(
                    "SELECT a FROM Application a WHERE a.student.user.username = :username", Application.class);
            typedQuery.setParameter("username", username);
            return copyToDto(typedQuery.getResultList());
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    // compania vede doar aplicatiile primite la propriile pozitii
    public List<ApplicationDto> findByCompanyUsername(String username) {
        LOG.info("findByCompanyUsername " + username);
        try {
            TypedQuery<Application> typedQuery = entityManager.createQuery(
                    "SELECT a FROM Application a WHERE a.position.company.user.username = :username", Application.class);
            typedQuery.setParameter("username", username);
            return copyToDto(typedQuery.getResultList());
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
    // verificare de proprietate: apartine aceasta aplicatie companiei userului dat?
    public boolean isOwnedByCompany(Long applicationId, String companyUsername) {
        try {
            TypedQuery<Long> countQuery = entityManager.createQuery(
                    "SELECT COUNT(a) FROM Application a WHERE a.id = :appId AND a.position.company.user.username = :username",
                    Long.class);
            countQuery.setParameter("appId", applicationId);
            countQuery.setParameter("username", companyUsername);
            return countQuery.getSingleResult() > 0;
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    // nu se mai poate aplica dupa deadline, evita aplicarea de doua ori la aceeasi pozitie
    public String createApplication(Long studentId, Long positionId) {
        LOG.info("createApplication student=" + studentId + " position=" + positionId);
        try {
            Position position = entityManager.find(Position.class, positionId);
            if (position == null) {
                return "Pozitia nu exista!";
            }
            if (position.getStatus() != PositionStatus.APPROVED || position.getCompany().getStatus() != CompanyStatus.APPROVED) {
                return "Aceasta pozitie nu este disponibila pentru aplicare.";
            }
            if (position.getApplicationDeadline() != null
                    && position.getApplicationDeadline().isBefore(LocalDate.now())) {
                return "Termenul limita de aplicare a expirat!";
            }

            TypedQuery<Long> countQuery = entityManager.createQuery(
                    "SELECT COUNT(a) FROM Application a WHERE a.student.id = :studentId AND a.position.id = :positionId",
                    Long.class);
            countQuery.setParameter("studentId", studentId);
            countQuery.setParameter("positionId", positionId);
            if (countQuery.getSingleResult() > 0) {
                return "Ai aplicat deja la aceasta pozitie!";
            }

            Student student = entityManager.find(Student.class, studentId);

            Application application = new Application();
            application.setStudent(student);
            application.setPosition(position);
            application.setStatus(ApplicationStatus.APPLIED);
            entityManager.persist(application);

            return null;
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    // la acceptare, celelalte aplicatii/interviuri ale studentului se inchid
    public void updateStatus(Long id, ApplicationStatus newStatus) {
        LOG.info("updateStatus " + id + " -> " + newStatus);
        try {
            Application application = entityManager.find(Application.class, id);
            application.setStatus(newStatus);

            if (newStatus == ApplicationStatus.ACCEPTED) {
                Long studentId = application.getStudent().getId();
                TypedQuery<Application> otherAppsQuery = entityManager.createQuery(
                        "SELECT a FROM Application a WHERE a.student.id = :studentId AND a.id <> :thisId " +
                                "AND a.status NOT IN (:accepted, :rejected, :closed)", Application.class);
                otherAppsQuery.setParameter("studentId", studentId);
                otherAppsQuery.setParameter("thisId", id);
                otherAppsQuery.setParameter("accepted", ApplicationStatus.ACCEPTED);
                otherAppsQuery.setParameter("rejected", ApplicationStatus.REJECTED);
                otherAppsQuery.setParameter("closed", ApplicationStatus.CLOSED);
                for (Application other : otherAppsQuery.getResultList()) {
                    other.setStatus(ApplicationStatus.CLOSED);
                }
            }
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public void deleteApplicationsByIds(List<Long> ids) {
        LOG.info("deleteApplicationsByIds " + ids);
        try {
            for (Long id : ids) {
                Application application = entityManager.find(Application.class, id);
                if (application != null) {
                    entityManager.remove(application);
                }
            }
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public ApplicationDto findById(Long id) {
        LOG.info("findById " + id);
        try {
            Application application = entityManager.find(Application.class, id);
            return application != null ? toDto(application) : null;
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
    private ApplicationDto toDto(Application a) {
        return new ApplicationDto(
                a.getId(), a.getStatus(),
                a.getStudent() != null ? a.getStudent().getFullName() : null,
                a.getPosition() != null ? a.getPosition().getTitle() : null,
                a.getStudent() != null ? a.getStudent().getId() : null,
                a.getPosition() != null ? a.getPosition().getId() : null,
                a.getInterview() != null ? a.getInterview().getResult().name() : null,
                a.getGrade() != null ? a.getGrade().getValue() : null);
    }

    private List<ApplicationDto> copyToDto(List<Application> applications) {
        List<ApplicationDto> dtos = new ArrayList<>();
        for (Application a : applications) {
            dtos.add(toDto(a));
        }
        return dtos;
    }

    /*private List<ApplicationDto> copyApplicationsToDto(List<Application> applications) {
        List<ApplicationDto> dtos = new ArrayList<>();
        for (Application a : applications) {
            dtos.add(new ApplicationDto(
                    a.getId(), a.getStatus(),
                    a.getStudent().getFullName(), // a.getStudent() != null ? a.getStudent().getFullName() : null,
                    a.getPosition().getTitle())); // a.getPosition() != null ? a.getPosition().getTitle() : null));
        }
        return dtos;
    }*/
}