package ru.kata.spring.boot_security.demo.repository.imp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import javax.management.relation.RoleInfoNotFoundException;
import javax.management.relation.RoleNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class RoleRepositoryImp implements RoleRepository {
    private static final Logger log = LoggerFactory.getLogger(RoleRepositoryImp.class);
    // @PersistenceContext используется для получения экземпляра
    // EntityManager, который представляет собой интерфейс,
    // предоставляющий доступ к базе данных.
    // Затем этот экземпляр использую для сохранения объекта сущности в базе данных.
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getRole(String name) {
        Role role = null;
        try {
            role = entityManager.find(Role.class, name);
            return role;
        } catch (Exception e) {
            log.error("Role not found");
        }
        return role;
    }

    @Override
    public Role getRoleById(long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public List<Role> allRoles() {
        return entityManager.createQuery("SELECT r FROM Role r", Role.class)
                .getResultList();
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

}

