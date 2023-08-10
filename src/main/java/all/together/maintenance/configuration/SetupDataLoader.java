package all.together.maintenance.configuration;

import all.together.maintenance.data.Tenant;
import all.together.maintenance.data.User;
import all.together.maintenance.tenant.TenantRepo;
import all.together.maintenance.users.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static all.together.maintenance.configuration.Profile.development;

@Component
@RequiredArgsConstructor
@Profile(value = {development})
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    private final UserRepo userRepo;
    private final TenantRepo tenantRepo;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

        final Tenant tenant = new Tenant(LocalDateTime.now(), "root");
        tenantRepo.save(tenant);

        User user = new User();
        user.setTenant(tenant);
        user.setFirstName("first");
        user.setLastName("user");
        user.setUsername("first");
        user.setPassword("first");
        user.setEnabled(true);
        user.setTokenExpired(false);
        user.setCreatedAt(LocalDateTime.of(0, 1, 1, 0, 0, 0));
        user.setCreatedBy(user);

        userRepo.save(user);

        alreadySetup = true;
    }

}