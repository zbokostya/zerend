package by.zbokostya.zerend.controller;

import by.zbokostya.zerend.entity.Ability;
import by.zbokostya.zerend.service.AbilityService;
import by.zbokostya.zerend.service.ApikeyProjectService;
import by.zbokostya.zerend.service.ProjectService;
import by.zbokostya.zerend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import static by.zbokostya.zerend.entity.EntityUtils.generate;
import static by.zbokostya.zerend.security.SecurityUtils.getCurrentUserLogin;

@Controller
@RequestMapping("/user/project/{projectId}")
public class AbilityController {

    private final AbilityService abilityService;
    private final UserService userService;
    private final ApikeyProjectService apikeyProjectService;

    private final ProjectService projectService;

    public AbilityController(AbilityService abilityService, UserService userService, ApikeyProjectService apikeyProjectService, ProjectService projectService) {
        this.abilityService = abilityService;
        this.userService = userService;
        this.apikeyProjectService = apikeyProjectService;
        this.projectService = projectService;
    }

    @GetMapping("/ability/{abilityId}")
    public ResponseEntity<Ability> getAbilityById(@PathVariable UUID abilityId, @PathVariable UUID projectId) {
        UUID userId = findUserByUsername();
        Ability ability = abilityService.getAbilityById(userId, projectId, abilityId);
        return ResponseEntity.ok(ability);
    }

    @PostMapping("/ability/create")
    public ResponseEntity<Ability> createAbility(@PathVariable UUID projectId, @RequestBody Ability ability) {
        ability.setId(UUID.randomUUID());
        ability.setUrl(ability.getName() + "_" + generate(128));
        ability.setProject(projectId);


        return ResponseEntity.ok(abilityService.createAbility(ability));
    }

    private UUID findUserByUsername() {
        String login = getCurrentUserLogin();
        if (login.length() == 64) return apikeyProjectService.findByUsername(login).getId();
        else return userService.findUserByUsername(login).getId();
    }

}
