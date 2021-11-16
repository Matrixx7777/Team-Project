package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDbService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        User userDb = userRepository.findById(user.getId()).orElseThrow(UserNotFoundException::new);
        userDb.setFirstName(user.getFirstName());
        userDb.setLastName(user.getLastName());
        return userRepository.save(userDb);
    }

    public void deleteUser(long userId) {
        if (!userRepository.existsById(userId))
            throw new UserNotFoundException();
        userRepository.deleteById(userId);
    }

    public List<Long> retrieveCartIdListForUser(long userId) {
        return cartRepository.findAllByUser_Id(userId)
                .stream().map(Cart::getId).collect(Collectors.toList());
    }
}
