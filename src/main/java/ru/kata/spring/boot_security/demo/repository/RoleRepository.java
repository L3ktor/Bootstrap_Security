package ru.kata.spring.boot_security.demo.repository;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleRepository {
    Role getRoleByName(String name);

    Role getRoleById(long id);

    List<Role> allRoles();

    Role getDefaultRole();
}
