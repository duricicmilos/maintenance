package all.together.maintenance.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Collection;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "role")
@ToString
@RequiredArgsConstructor
public class Role extends UserAuditableEntity {
    private String name;
    @ManyToMany(mappedBy = "roles")
    @ToString.Exclude
    private Collection<User> users;

    @ManyToMany
    @JoinTable(
            name = "roles_privileges"
            , uniqueConstraints = @UniqueConstraint(columnNames = {"role_uuid", "privilege_uuid"})
            , joinColumns = @JoinColumn(name = "role_uuid", referencedColumnName = "uuid")
            , inverseJoinColumns = @JoinColumn(name = "privilege_uuid", referencedColumnName = "uuid")
    )
    @ToString.Exclude
    private Collection<Privilege> privileges;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Role role = (Role) o;
        return getUuid() != null && Objects.equals(getUuid(), role.getUuid());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}