package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.dto.GroupDto;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupMapper groupMapper;
    private final GroupDbService groupDbService;

    @RequestMapping(method = RequestMethod.GET, value = "getGroups")
    public List<GroupDto> getGroups() {
        List<Group> groups = groupDbService.getGroups();
        return groupMapper.mapToGroupDtoList(groups);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getGroup")
        public GroupDto getGroup (@RequestParam Long groupId) throws GroupNotFoundException {
            return groupMapper.mapToGroupDto(
                    groupDbService.getGroup(groupId).orElseThrow(GroupNotFoundException::new)
            );
        }

    @RequestMapping(method = RequestMethod.POST, value = "createGroup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createGroup (@RequestBody GroupDto groupDto){
        Group group = groupMapper.mapToGroup(groupDto);
        groupDbService.saveGroup(group);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateGroup",consumes = MediaType.APPLICATION_JSON_VALUE)
    public GroupDto updateGroup(@RequestParam Long groupId, @RequestBody GroupDto groupDto) throws GroupNotFoundException {
        Group updatedGroup = groupMapper.mapToGroup(groupDto);
        updatedGroup.setId(groupId);
        groupDbService.update(groupId, updatedGroup);
        return groupMapper.mapToGroupDto(updatedGroup);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteGroup")
    public void deleteGroup (@RequestParam Long groupId) throws GroupNotFoundException {
        if (groupDbService.getGroup(groupId).isPresent()) {
            groupDbService.deleteGroup(groupId);
        } else throw new GroupNotFoundException();
    }
}

