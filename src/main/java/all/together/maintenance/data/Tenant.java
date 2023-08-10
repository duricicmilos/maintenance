package all.together.maintenance.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "tenant")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Tenant extends AuditableEntity {
    @Column(nullable = false, updatable = false, unique = true)
    private String name;
    public Tenant(LocalDateTime createdAt, String name) {
        super(createdAt);
        this.name = name;
    }
}
