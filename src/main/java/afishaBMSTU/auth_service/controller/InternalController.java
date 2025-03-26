package afishaBMSTU.auth_service.controller;

import afishaBMSTU.auth_service.service.user.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/internal")
@RequiredArgsConstructor
@Hidden
public class InternalController {

    private final UserService userService;

    @DeleteMapping
    void deleteUser(@RequestBody UUID externalID) {
        userService.deleteUser(externalID);
    }
}
