package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.domain.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupDbService {

    private final GroupRepository groupRepository;

    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    public Optional<Group> getGroup(final Long groupId) {
        return groupRepository.findById(groupId);
    }

    public Group saveGroup(final Group group) { return groupRepository.save(group); }

    public void deleteById(Long groupId) throws GroupNotFoundException {
        if(groupRepository.existsById(groupId)) {
            groupRepository.deleteById(groupId);
        }
        else throw new GroupNotFoundException();
    }
}