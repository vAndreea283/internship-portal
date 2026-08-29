package org.proiectre.proiectre.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.proiectre.proiectre.common.InterviewDto;
import org.proiectre.proiectre.entities.Application;
import org.proiectre.proiectre.entities.Interview;
import org.proiectre.proiectre.entities.InterviewResult;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class InterviewBean {

    private static final Logger LOG = Logger.getLogger(InterviewBean.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    public List<InterviewDto> findAllInterviews() {
        LOG.info("findAllInterviews");
        try {
            TypedQuery<Interview> typedQuery = entityManager.createQuery("SELECT i FROM Interview i", Interview.class);
            List<Interview> interviews = typedQuery.getResultList();
            return copyInterviewsToDto(interviews);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public InterviewDto findByApplicationId(Long applicationId) {
        LOG.info("findByApplicationId " + applicationId);
        try {
            TypedQuery<Interview> typedQuery = entityManager.createQuery(
                    "SELECT i FROM Interview i WHERE i.application.id = :appId", Interview.class);
            typedQuery.setParameter("appId", applicationId);
            List<Interview> results = typedQuery.getResultList();
            return results.isEmpty() ? null : toDto(results.get(0));
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public void saveInterview(Long applicationId, String summary, InterviewResult result) {
        LOG.info("saveInterview " + applicationId);
        try {
            TypedQuery<Interview> typedQuery = entityManager.createQuery(
                    "SELECT i FROM Interview i WHERE i.application.id = :appId", Interview.class);
            typedQuery.setParameter("appId", applicationId);
            List<Interview> results = typedQuery.getResultList();

            Interview interview;
            if (results.isEmpty()) {
                interview = new Interview();
                Application application = entityManager.find(Application.class, applicationId);
                interview.setApplication(application);
                entityManager.persist(interview);
            } else {
                interview = results.get(0);
            }
            interview.setSummary(summary);
            interview.setResult(result);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    private InterviewDto toDto(Interview i) {
        return new InterviewDto(
                i.getId(), i.getSummary(), i.getResult(),
                i.getApplication() != null ? i.getApplication().getId() : null);
    }

    private List<InterviewDto> copyInterviewsToDto(List<Interview> interviews) {
        List<InterviewDto> dtos = new ArrayList<>();
        for (Interview i : interviews) {
            dtos.add(toDto(i));
        }
        return dtos;
    }

    /*private List<InterviewDto> copyInterviewsToDto(List<Interview> interviews) {
        List<InterviewDto> dtos = new ArrayList<>();
        for (Interview i : interviews) {
            dtos.add(new InterviewDto(
                    i.getId(), i.getSummary(), i.getResult(),
                    i.getApplication().getId())); // i.getApplication() != null ? i.getApplication().getId() : null));
        }
        return dtos;
    }*/
}