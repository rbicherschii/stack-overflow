/* Copyright (C) PublicRelay, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * The work belongs to the author's employer under work made for hire principles.
 */

package com.roman.example.parentChildTest.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ZZ_PARENT")
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PARENT_ID")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private List<Child> children;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    public void addChild(final Child child) {
        child.setParent(this);
        children.add(child);
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return the children
     */
    public List<Child> getChildren() {
        return children;
    }

    /**
     * @param children the children to set
     */
    public void setChildren(final List<Child> children) {
        this.children = children;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

}
