package org.proiectre.proiectre.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.proiectre.proiectre.common.CompanyDto;
import org.proiectre.proiectre.entities.Company;
import org.proiectre.proiectre.entities.CompanyStatus;
import org.proiectre.proiectre.entities.User;

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

    /* 1. parcurge lista de Company; 2. transforma fiecare Company in CompanyDto */
    /*private List<CompanyDto> copyCompaniesToDto(List<Company> companies) {
        List<CompanyDto> dtos = new ArrayList<>();
        for (Company c : companies) {
            dtos.add(new CompanyDto(
                    c.getId(),
                    c.getName(),
                    c.getDescription(),
                    c.getStatus(),
                    c.getUser().getUsername())); // c.getUser() != null ? c.getUser().getUsername() : null));
        }
        return dtos;
    }*/

    public CompanyDto findById(Long id) {
        LOG.info("findById " + id);
        try {
            Company company = entityManager.find(Company.class, id);
            if (company == null) {
                return null;
            }
            return toDto(company);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public void createCompany(String name, String description, CompanyStatus status, Long userId) {
        LOG.info("createCompany");
        try {
            User user = entityManager.find(User.class, userId);

            Company company = new Company();
            company.setName(name);
            company.setDescription(description);
            company.setStatus(status);
            company.setUser(user);

            entityManager.persist(company);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public void updateCompany(Long id, String name, String description, CompanyStatus status, Long userId) {
        LOG.info("updateCompany " + id);
        try {
            Company company = entityManager.find(Company.class, id);
            User user = entityManager.find(User.class, userId);

            company.setName(name);
            company.setDescription(description);
            company.setStatus(status);
            company.setUser(user);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public void deleteCompaniesByIds(List<Long> ids) {
        LOG.info("deleteCompaniesByIds " + ids);
        try {
            for (Long id : ids) {
                Company company = entityManager.find(Company.class, id);
                if (company != null) {
                    entityManager.remove(company);
                }
            }
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    private CompanyDto toDto(Company c) {
        return new CompanyDto(
                c.getId(),
                c.getName(),
                c.getDescription(),
                c.getStatus(),
                c.getUser().getUsername(),
                c.getUser().getId());
    }

    private List<CompanyDto> copyCompaniesToDto(List<Company> companies) {
        List<CompanyDto> dtos = new ArrayList<>();
        for (Company c : companies) {
            dtos.add(toDto(c));
        }
        return dtos;
    }
}