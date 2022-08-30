package by.zbokostya.controller;

import by.zbokostya.entity.Ability;
import by.zbokostya.service.AbilityService;
import by.zbokostya.service.ProjectService;
import by.zbokostya.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import static by.zbokostya.entity.EntityUtils.generate;
import static by.zbokostya.security.SecurityUtils.getCurrentUserLogin;

@Controller
@RequestMapping("/user/project/{projectId}")
public class AbilityController {

    private final AbilityService abilityService;
    private final UserService userService;

    private final ProjectService projectService;

    public AbilityController(AbilityService abilityService, UserService userService, ProjectService projectService) {
        this.abilityService = abilityService;
        this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping("/ability/{abilityId}")
    public ResponseEntity<Ability> getAbilityById(@PathVariable UUID abilityId, @PathVariable UUID projectId) {
        UUID userId = userService.findUserByUsername(getCurrentUserLogin()).getId();
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

}
