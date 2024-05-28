package ru.kata.spring.boot_security.demo.repository.imp;

import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import jakarta.persistence.EntityManager;
import java.util.List;


@Repository
public class RoleRepositoryImp implements RoleRepository {
    @PersistenceContext
    private EntityManager eM;

    protected EntityManager getEntityManager() {
        return eM;
    }

    @Override
    public Role getRoleByName(String name) {
        Role role = null;
        try {
            role = getEntityManager()
                    .createQuery("SELECT r FROM Role r WHERE r.name=:name", Role.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NullPointerException e) {
            System.out.println("Role not found");
        }
        return role;
    }

    @Override
    public Role getRoleById(long id) {
        return getEntityManager().find(Role.class, id);
    }

    @Override
    public List<Role> allRoles() {
        return getEntityManager()
                .createQuery("select r from Role r", Role.class)
                .getResultList();
    }

    @Override
    public Role getDefaultRole() {
        return getRoleByName("ROLE_USER");
    }
}

