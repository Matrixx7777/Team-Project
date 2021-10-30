package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public Optional<User> getUser(final Long id){
        return repository.findById(id);
    }

    public User saveUser(final User user) {
        return repository.save(user);
    }

    public void deleteById(Long id) {

        if(repository.existsById(id)) {
            repository.deleteById(id);
        }else{
            System.out.println("Nie znaleziono użytkownika");
        }
    }

}