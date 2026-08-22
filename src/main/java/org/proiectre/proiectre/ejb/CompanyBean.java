package org.proiectre.proiectre.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.proiectre.proiectre.common.CompanyDto;
import org.proiectre.proiectre.entities.Company;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CompanyBean {

    private static final Logger LOG = Logger.getLogger(CompanyBean.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    public List<CompanyDto> findAllCompanies() {
        LOG.info("findAllCompanies");
        try {
            TypedQuery<Company> typedQuery = entityManager.createQuery("SELECT c FROM Company c", Company.class);
            List<Company> companies = typedQuery.getResultList();
            return copyCompaniesToDto(companies);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    private List<CompanyDto> copyCompaniesToDto(List<Company> companies) {
        List<CompanyDto> dtos = new ArrayList<>();
        for (Company c : companies) {
            dtos.add(new CompanyDto(
                    c.getId(), c.getName(), c.getDescription(), c.getStatus(),
                    c.getUser().getUsername())); // c.getUser() != null ? c.getUser().getUsername() : null));
        }
        return dtos;
    }
}