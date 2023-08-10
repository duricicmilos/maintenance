package all.together.maintenance.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User extends UserAuditableEntity {
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;

    //    @Pattern(message = "Valid username contains only latin alphabet characters and digits"
//            , regexp = "^[a-zA-Z\\d]+$"
//    )
    @Column(unique = true
            , updatable = false
            , nullable = false)
    @Length(min = 4, max = 16)
    private String username;
    @Column(nullable = false)
    private String password;
    private boolean enabled;
    private boolean tokenExpired;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Email> emails;

    @ManyToMany
    @JoinTable(
            name = "users_roles"
            , uniqueConstraints = @UniqueConstraint(columnNames = {"user_uuid", "role_uuid"})
            , joinColumns = @JoinColumn(name = "user_uuid", referencedColumnName = "uuid")
            , inverseJoinColumns = @JoinColumn(name = "role_uuid", referencedColumnName = "uuid")
    )
    @ToString.Exclude
    private Collection<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return getUuid() != null && Objects.equals(getUuid(), user.getUuid());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
