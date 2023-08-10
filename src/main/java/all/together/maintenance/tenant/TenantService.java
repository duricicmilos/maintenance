package all.together.maintenance.tenant;

import all.together.maintenance.data.Tenant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class TenantService {

    private final TenantRepo tenantRepo;

    public Tenant create(Tenant tenant) {
        tenant.setCreatedAt(LocalDateTime.now());
        return tenantRepo.save(tenant);

    }

    public Tenant findByUuid(UUID uuid) {
        return tenantRepo.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Tenant with id " + uuid + " not found!"));
    }

    public Tenant findRoot() {
        return tenantRepo.findRoot()
                .orElseThrow(() -> new RuntimeException("Root Tenant not found!"));
    }

    public Tenant update(UUID uuid, Tenant updates) {
        Tenant tenant = tenantRepo.findById(uuid)
                .orElseThrow(() -> new RuntimeException(String.format("Tenant with uuid [%s] not found", uuid)));
        tenant.setName(updates.getName());
        tenant.setUpdatedAt(LocalDateTime.now());
        return tenantRepo.save(tenant);
    }

    public void delete(UUID uuid) {
        Tenant tenant = tenantRepo.findById(uuid)
                .orElseThrow(() -> new RuntimeException(String.format("Item with uuid [%s] not found", uuid)));
        tenant.setDeletedAt(LocalDateTime.now());
        tenantRepo.save(tenant);
    }

}
