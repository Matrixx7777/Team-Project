package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final UserDbService userDbService;

    public User mapToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName()
        );
    }

    public UserDto mapToUserDto(User user) {
        List<Long> cartList = userDbService.retrieveCartIdListForUser(user.getId());
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                cartList
        );
    }


    public List<UserDto> mapToUserDtoList(List<User> users) {
        return users.stream().map(this::mapToUserDto).collect(Collectors.toList());
    }

}
