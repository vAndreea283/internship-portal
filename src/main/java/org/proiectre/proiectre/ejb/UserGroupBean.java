package org.proiectre.proiectre.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.proiectre.proiectre.common.UserGroupDto;
import org.proiectre.proiectre.entities.UserGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class UserGroupBean {

    private static final Logger LOG = Logger.getLogger(UserGroupBean.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    public List<UserGroupDto> findAllUserGroups() {
        LOG.info("findAllUserGroups");
        try {
            TypedQuery<UserGroup> typedQuery = entityManager.createQuery("SELECT g FROM UserGroup g", UserGroup.class);
            List<UserGroup> groups = typedQuery.getResultList();
            return copyGroupsToDto(groups);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    private List<UserGroupDto> copyGroupsToDto(List<UserGroup> groups) {
        List<UserGroupDto> dtos = new ArrayList<>();
        for (UserGroup g : groups) {
            dtos.add(new UserGroupDto(g.getId(), g.getUsername(), g.getUserGroup()));
        }
        return dtos;
    }
}