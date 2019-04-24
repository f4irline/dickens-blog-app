package com.github.dickens.blogapp.user.role;

/**
 * Enum that's used for roles.
 *
 * <p>
 * New roles can easily be added by extending this Enum implementation. After the role's have been defined
 * here, the role's can be used using the @PreAuthorize annotation in controllers for example.
 * </p>
 *
 * @author Tommi Lepola
 * @version 1.0
 * @since 2019.0330
 */
public enum RoleDefinition {
    ROLE_USER,
    ROLE_ADMIN
}
