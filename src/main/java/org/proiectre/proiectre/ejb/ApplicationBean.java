package org.proiectre.proiectre.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.proiectre.proiectre.common.ApplicationDto;
import org.proiectre.proiectre.entities.Application;

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
            return copyApplicationsToDto(applications);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    private List<ApplicationDto> copyApplicationsToDto(List<Application> applications) {
        List<ApplicationDto> dtos = new ArrayList<>();
        for (Application a : applications) {
            dtos.add(new ApplicationDto(
                    a.getId(), a.getStatus(),
                    a.getStudent() != null ? a.getStudent().getFullName() : null,
                    a.getPosition() != null ? a.getPosition().getTitle() : null));
        }
        return dtos;
    }
}