package org.proiectre.proiectre.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.proiectre.proiectre.common.CompanyDto;
import org.proiectre.proiectre.entities.Company;
import org.proiectre.proiectre.entities.CompanyStatus;
import org.proiectre.proiectre.entities.User;
import org.proiectre.proiectre.entities.UserGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CompanyBean {

    private static final Logger LOG = Logger.getLogger(CompanyBean.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private PasswordBean passwordBean;

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

    public CompanyDto findByUsername(String username) {
        LOG.info("findByUsername " + username);
        try {
            TypedQuery<Company> typedQuery = entityManager.createQuery(
                    "SELECT c FROM Company c WHERE c.user.username = :username", Company.class);
            typedQuery.setParameter("username", username);
            List<Company> results = typedQuery.getResultList();
            return results.isEmpty() ? null : toDto(results.get(0));
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    // inregistrare self-service: creeaza User + UserGroup + Company (status PENDING), toate intr-o singura tranzactie EJB
    public String registerCompany(String username, String email, String password, String companyName, String description) {
        LOG.info("registerCompany " + username);
        try {
            TypedQuery<Long> countQuery = entityManager.createQuery(
                    "SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class);
            countQuery.setParameter("username", username);
            if (countQuery.getSingleResult() > 0) {
                return "Acest username este deja folosit.";
            }

            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(passwordBean.convertToSha256(password));
            entityManager.persist(user);

            UserGroup group1 = new UserGroup();
            group1.setUsername(username);
            group1.setUserGroup("MANAGE_OWN_COMPANY");
            entityManager.persist(group1);

            UserGroup group2 = new UserGroup();
            group2.setUsername(username);
            group2.setUserGroup("MANAGE_OWN_POSITIONS");
            entityManager.persist(group2);

            UserGroup group3 = new UserGroup();
            group3.setUsername(username);
            group3.setUserGroup("MANAGE_OWN_APPLICATIONS");
            entityManager.persist(group3);

            Company company = new Company();
            company.setName(companyName);
            company.setDescription(description);
            company.setStatus(CompanyStatus.PENDING);
            company.setUser(user);
            entityManager.persist(company);

            return null;
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
}