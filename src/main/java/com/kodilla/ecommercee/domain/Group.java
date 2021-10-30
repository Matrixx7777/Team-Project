package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "GROUPS")
public class Group {

    private Long id;

    public Group() {

    }

    public Group(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "GROUP_ID", unique = true)
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }
}
