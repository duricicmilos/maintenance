package all.together.maintenance.users;

import all.together.maintenance.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {
    @Query(nativeQuery = true
            , value =
            """
                    select *
                    from users
                    order by created_at desc
                    limit 1""")
    Optional<User> findFirstUser();
}
