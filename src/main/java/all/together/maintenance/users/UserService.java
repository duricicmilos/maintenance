package all.together.maintenance.users;

import all.together.maintenance.data.Tenant;
import all.together.maintenance.data.User;
import all.together.maintenance.tenant.TenantRepo;
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
public class UserService {
    private final UserRepo userRepo;
    private final TenantRepo tenantRepo;
    public User create(UUID tenantUuid, User user) {
        final Tenant tenant = tenantRepo.findById(tenantUuid)
                .orElseThrow(() -> new RuntimeException("no tenant"));
        user.setTenant(tenant);
        user.setEnabled(true);
        user.setTokenExpired(false);
        User createdBy = userRepo.findFirstUser()
                .orElseThrow(() -> new RuntimeException("no first user"));
        user.setCreatedBy(createdBy);
        user.setCreatedAt(LocalDateTime.now());
        return userRepo.save(user);

    }

    public User findByUuid(UUID uuid) {
        return userRepo.findById(uuid)
                .orElseThrow(() -> new RuntimeException("User with id " + uuid + " not found!"));
    }

    public User update(UUID uuid, User updates) {
        User user = userRepo.findById(uuid)
                .orElseThrow(() -> new RuntimeException(String.format("Item with uuid [%s] not found", uuid)));
        user.setFirstName(updates.getFirstName());
        user.setLastName(updates.getLastName());
        user.setUpdatedBy(user);
        user.setUpdatedAt(LocalDateTime.now());
        return userRepo.save(user);
    }

    public void delete(UUID uuid) {
        final User deletedBy = userRepo.findFirstUser()
                .orElseThrow(() -> new RuntimeException("no first user"));
        User user = userRepo.findById(uuid)
                .orElseThrow(() -> new RuntimeException(String.format("Item with uuid [%s] not found", uuid)));
        user.setDeletedAt(LocalDateTime.now());
        user.setDeletedBy(deletedBy);
        userRepo.save(user);
    }

}
