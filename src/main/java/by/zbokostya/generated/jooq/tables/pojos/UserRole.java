/*
 * This file is generated by jOOQ.
 */
package by.zbokostya.generated.jooq.tables.pojos;


import java.io.Serializable;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID userId;
    private String userRole;

    public UserRole() {}

    public UserRole(UserRole value) {
        this.userId = value.userId;
        this.userRole = value.userRole;
    }

    public UserRole(
        UUID userId,
        String userRole
    ) {
        this.userId = userId;
        this.userRole = userRole;
    }

    /**
     * Getter for <code>public.user_role.user_id</code>.
     */
    public UUID getUserId() {
        return this.userId;
    }

    /**
     * Setter for <code>public.user_role.user_id</code>.
     */
    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    /**
     * Getter for <code>public.user_role.user_role</code>.
     */
    public String getUserRole() {
        return this.userRole;
    }

    /**
     * Setter for <code>public.user_role.user_role</code>.
     */
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final UserRole other = (UserRole) obj;
        if (this.userId == null) {
            if (other.userId != null)
                return false;
        }
        else if (!this.userId.equals(other.userId))
            return false;
        if (this.userRole == null) {
            if (other.userRole != null)
                return false;
        }
        else if (!this.userRole.equals(other.userRole))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.userRole == null) ? 0 : this.userRole.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UserRole (");

        sb.append(userId);
        sb.append(", ").append(userRole);

        sb.append(")");
        return sb.toString();
    }
}
