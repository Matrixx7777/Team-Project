package com.kodilla.ecommercee.repository;

import org.springframework.data.repository.CrudRepository;
import com.kodilla.ecommercee.domain.Group;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
@Transactional
public interface GroupRepository extends CrudRepository<Group,Long> {

    @Override
    List<Group> findAll();

    @Override
    Optional<Group> findById(Long groupId);

    @Override
    Group save(Group group);
}