package all.together.maintenance.users;

import all.together.maintenance.data.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> create(
            @RequestParam(name = "tenant_uuid") @NotNull UUID tenantUuid,
            @Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.create(tenantUuid, user));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> read(@Valid @PathVariable("id") UUID uuid) {
        User byId = userService.findByUuid(uuid);
        return ResponseEntity.ok(byId);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable("id") UUID uuid, @Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.update(uuid, user));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID uuid) {
        userService.delete(uuid);
        return ResponseEntity.ok().build();
    }
}
