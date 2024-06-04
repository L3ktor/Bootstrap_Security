package ru.kata.spring.boot_security.demo.repository;

import ru.kata.spring.boot_security.demo.model.Role;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

public interface RoleRepository {
    Role getRole(String name);

    Role getRoleById(long id);

    List<Role> allRoles();

    void addRole(Role role);
}
