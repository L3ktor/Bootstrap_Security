package ru.kata.spring.boot_security.demo.service.imp;

import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {
    @PersistenceContext
    private  PasswordEncoder pE;
    private  UserRepository uR;

    public UserServiceImp(PasswordEncoder pE, UserRepository uR) {
        this.pE = pE;
        this.uR = uR;
    }
    @Bean
    public static PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return uR.getAllUsers();
    }


    @Override
    @Transactional
    public void addUser(User user) {
        user.setPassword(pE.encode(user.getPassword()));
        uR.addUser(user);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
            uR.deleteUserById(id);
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        return uR.getUserById(id);
    }


    @Override
    @Transactional
    public void editUser(User user) {
        uR.addUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return uR.getUserByUsername(username);
    }
}
