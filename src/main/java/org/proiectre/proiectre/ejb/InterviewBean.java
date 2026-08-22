package org.proiectre.proiectre.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.proiectre.proiectre.common.InterviewDto;
import org.proiectre.proiectre.entities.Interview;

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

    private List<InterviewDto> copyInterviewsToDto(List<Interview> interviews) {
        List<InterviewDto> dtos = new ArrayList<>();
        for (Interview i : interviews) {
            dtos.add(new InterviewDto(
                    i.getId(), i.getSummary(), i.getResult(),
                    i.getApplication().getId())); // i.getApplication() != null ? i.getApplication().getId() : null));
        }
        return dtos;
    }
}