package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.exception.GroupAlreadyExistsException;
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

    private GroupRepository groupRepository;

    public List<Group> getGroups() { return groupRepository.findAll(); }

    public Optional<Group> getGroup(final Long groupId) { return groupRepository.findById(groupId); }

    public void saveGroup(final Group group) throws GroupAlreadyExistsException {
        if (!groupRepository.findByName(group.getName()).isPresent()) {
            groupRepository.save(group);
        } else throw new GroupAlreadyExistsException();
    }

    public void update(final Long groupId, Group group) throws GroupNotFoundException {
        if(groupRepository.existsById(groupId)){
            group.setId(groupId);
        } else throw new GroupNotFoundException();
    }

    public void deleteGroup ( final Long groupId){
        groupRepository.deleteById(groupId);
    }
}
