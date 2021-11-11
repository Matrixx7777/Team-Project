package com.kodilla.ecommercee.mapper;

import org.springframework.stereotype.Service;
import com.kodilla.ecommercee.dto.GroupDto;
import com.kodilla.ecommercee.domain.Group;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupMapper {

    public GroupDto mapToGroupDto(Group group) {
        GroupDto groupDto = new GroupDto();
        groupDto.setName(group.getName());
        return groupDto;
    }

    public Group mapToGroup(GroupDto groupDto) {
        Group group = new Group();
        group.setName(groupDto.getName());
        return group;
    }

    public List<GroupDto> mapToGroupDtoList(List<Group> groupList) {
        return groupList.stream()
                .map(this::mapToGroupDto)
                .collect(Collectors.toList());
    }
}
