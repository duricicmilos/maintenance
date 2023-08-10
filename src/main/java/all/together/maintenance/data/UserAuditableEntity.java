package all.together.maintenance.data;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@ToString
public class UserAuditableEntity extends AuditableEntity {

    @JsonIgnore
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "uuid", scope = Tenant.class)
    @JoinColumn(nullable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Tenant tenant;

    @JsonIgnore
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "uuid", scope = User.class)
    @CreatedBy
    @JoinColumn(nullable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @ToString.Exclude
    private User createdBy;

    @JsonIgnore
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "uuid", scope = User.class)
    @LastModifiedBy
    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User updatedBy;

    @JsonIgnore
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "uuid", scope = User.class)
    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User deletedBy;

    public UserAuditableEntity(LocalDateTime createdAt) {
        super(createdAt);
    }
}
