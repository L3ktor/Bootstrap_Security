package ru.kata.spring.boot_security.demo.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {
    private UserRepository uR;

    @Autowired
    public void setUserDao(UserRepository uR) {
        this.uR = uR;
    }

    @Override
    public void addUser(User user) {
        uR.addUser(user);
    }

    @Override
    public void deleteUserById(Long id) {
        uR.deleteUserById(id);
    }

    @Override
    public void editUser(User user) {
        uR.editUser(user);
    }

    @Override
    public User getUserById(Long id) {
        return uR.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return uR.getAllUsers();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return uR.getUserByUsername(username);
    }
}
