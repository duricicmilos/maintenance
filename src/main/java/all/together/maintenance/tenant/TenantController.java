package all.together.maintenance.tenant;

import all.together.maintenance.data.Tenant;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/api/tenant")
@RequiredArgsConstructor
public class TenantController {

    private final TenantService tenantService;

    @PostMapping
    public ResponseEntity<Tenant> create(@Valid @RequestBody Tenant tenant) {
        return ResponseEntity.ok(tenantService.create(tenant));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Tenant> read(@Valid @PathVariable("id") UUID uuid) {
        Tenant byId = tenantService.findByUuid(uuid);
        return ResponseEntity.ok(byId);
    }

    @GetMapping(value = "/root")
    public ResponseEntity<Tenant> readRoot() {
        Tenant byId = tenantService.findRoot();
        return ResponseEntity.ok(byId);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Tenant> update(@PathVariable("id") UUID uuid, @Valid @RequestBody Tenant tenant) {
        return ResponseEntity.ok(tenantService.update(uuid, tenant));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID uuid) {
        tenantService.delete(uuid);
        return ResponseEntity.ok().build();
    }
}
