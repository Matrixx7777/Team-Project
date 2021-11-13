package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupDbService {

    private final GroupRepository groupRepository;

    public Optional<Group> getGroup(final Long groupId) {
        return groupRepository.findById(groupId);
    }
}
