package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.GroupDto;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/group")
public class GroupController {

    @RequestMapping(method = RequestMethod.GET, value = "getGroups")
    public List<GroupDto> getGroups() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getGroup")
    public GroupDto getGroup (@RequestParam int groupId) {
        return new GroupDto(new BigInteger(String.valueOf(1)),"Fruit");
    }

    @RequestMapping(method = RequestMethod.POST, value = "createGroup")
    public GroupDto createGroup(@RequestBody GroupDto groupDto) {
        return groupDto;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateGroup")
    public GroupDto updateGroup(@RequestParam int groupId) {
        return new GroupDto(new BigInteger(String.valueOf(1)), "Fruit");
    }

    @RequestMapping( method = RequestMethod.DELETE, value = "deleteGroup")
    public void deleteGroup(@RequestParam int groupId) {
    }

}
