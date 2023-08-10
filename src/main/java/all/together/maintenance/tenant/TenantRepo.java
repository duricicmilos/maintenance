package all.together.maintenance.tenant;

import all.together.maintenance.data.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TenantRepo extends JpaRepository<Tenant, UUID> {

    @Query(nativeQuery = true, value = "select * from tenant where name like 'root'")
    Optional<Tenant> findRoot();
}
