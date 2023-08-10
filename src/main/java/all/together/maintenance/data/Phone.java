package all.together.maintenance.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Entity
@Table(name = "phone_number")
public class Phone extends UserAuditableEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_uuid", nullable = false)
    private User user;

    @Pattern(message = "Valid phone number starts with + and is followed by up to 15 digits",
            regexp = "^\\+\\d{1,15}$\n")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "value", nullable = false, unique = true)
    private String value;

    @Column(name = "confirmed", nullable = false)
    @JdbcTypeCode(SqlTypes.BOOLEAN)
    private boolean confirmed;

    @Column(name = "selected", nullable = false)
    @JdbcTypeCode(SqlTypes.BOOLEAN)
    private boolean selected;

}