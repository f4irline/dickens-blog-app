package com.github.dickens.blogapp.user.role;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * Role entity object for database implementation.
 *
 * @author Tommi Lepola
 * @version 1.0
 * @since 2019.0330
 */
@Entity
@Table(name = "roles")
public class Role {

    /**
     * Role's ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Definition of the role as an Enum.
     */
    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleDefinition definition;

    /**
     * Empty constructor, not used.
     */
    public Role() {

    }

    /**
     * Constructor.
     *
     * @param definition role definition as Enum.
     */
    public Role(RoleDefinition definition) {
        this.definition = definition;
    }

    /**
     * Returns role's ID.
     *
     * @return role's ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets role's ID.
     *
     * @param id role's ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns role's definition.
     *
     * @return role's definition.
     */
    public RoleDefinition getDefinition() {
        return definition;
    }

    /**
     * Sets role's definition.
     *
     * @param definition role's definition.
     */
    public void setDefinition(RoleDefinition definition) {
        this.definition = definition;
    }
}
