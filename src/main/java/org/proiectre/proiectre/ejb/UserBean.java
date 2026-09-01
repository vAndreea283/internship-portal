/* UserBean este un EJB stateless care foloseste EntityManager pentru a lua toti utilizatorii din baza de date,
ii transforma din entitati User in obiecte UserDto si returneaza lista rezultata */

package org.proiectre.proiectre.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.proiectre.proiectre.common.UserDto;
import org.proiectre.proiectre.entities.User;
import org.proiectre.proiectre.entities.UserGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class UserBean {

    private static final Logger LOG = Logger.getLogger(UserBean.class.getName()); // scrie mesaje in logurile aplicatiei, util pentru debugging

    @PersistenceContext
    private EntityManager entityManager; // „intermediar” între Java si baza de date

    @Inject
    private PasswordBean passwordBean;

    /*DTO (Data Transfer Object) = obiect folosit pentru a transfera date intre diferite componente ale aplicatiei*/
    public List<UserDto> findAllUsers() {
        LOG.info("findAllUsers"); // scrie in log ca metoda findAllUsers() a fost apelata
        try {
            TypedQuery<User> typedQuery = entityManager.createQuery("SELECT u FROM User u", User.class); // User.class = rezultatul acestei interogari este format din obiecte de tip User
            List<User> users = typedQuery.getResultList();
            return copyUsersToDto(users);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public void createUser(String username, String email, String password, Collection<String> groups) {
        LOG.info("createUser");
        try {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setPassword(passwordBean.convertToSha256(password));
            entityManager.persist(newUser); /* creeaza un rand in tabela users */

            assignGroupsToUser(username, groups);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    private void assignGroupsToUser(String username, Collection<String> groups) {
        LOG.info("assignGroupsToUser");
        for (String group : groups) {
            UserGroup userGroup = new UserGroup();
            userGroup.setUsername(username);
            userGroup.setUserGroup(group);
            entityManager.persist(userGroup);
        }
    }

    private List<UserDto> copyUsersToDto(List<User> users) {
        List<UserDto> dtos = new ArrayList<>();
        for (User u : users) {
            dtos.add(new UserDto(u.getId(), u.getUsername(), u.getEmail()));
        }
        return dtos;
    }

    public List<UserDto> findUsersWithoutCompany() {
        LOG.info("findUsersWithoutCompany");
        try {
            TypedQuery<User> typedQuery = entityManager.createQuery(
                    "SELECT u FROM User u WHERE u.id NOT IN (SELECT c.user.id FROM Company c)", User.class);
            return copyUsersToDto(typedQuery.getResultList());
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public List<UserDto> findUsersWithoutStudent() {
        LOG.info("findUsersWithoutStudent");
        try {
            TypedQuery<User> typedQuery = entityManager.createQuery(
                    "SELECT u FROM User u WHERE u.id NOT IN (SELECT s.user.id FROM Student s)", User.class);
            return copyUsersToDto(typedQuery.getResultList());
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
}