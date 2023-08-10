package all.together.maintenance.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "email")
public class Email extends UserAuditableEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_uuid", nullable = false)
    private User user;

    @jakarta.validation.constraints.Email(message = "Valid email address contains latin characters, digits and dot" +
            ", followed by at(@) and domain part where additional, to previously mentioned, character is hyphen(-)."
            ,regexp = "^[a-zA-Z0-9.]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3}$\n")
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