package all.together.maintenance.data;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
@Table(name = "privilege")
@ToString
@RequiredArgsConstructor
public class Privilege extends UserAuditableEntity {
    private String name;
    @ManyToMany(mappedBy = "privileges")
    @ToString.Exclude
    private Collection<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Privilege privilege = (Privilege) o;
        return getUuid() != null && Objects.equals(getUuid(), privilege.getUuid());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}