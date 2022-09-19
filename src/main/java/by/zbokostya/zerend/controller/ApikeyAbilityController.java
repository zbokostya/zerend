package by.zbokostya.zerend.controller;


import by.zbokostya.zerend.entity.Ability;
import by.zbokostya.zerend.entity.input.AbilityInput;
import by.zbokostya.zerend.service.ApikeyService;
import by.zbokostya.zerend.service.impl.ApikeyAbilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import static by.zbokostya.zerend.security.SecurityUtils.getCurrentUserLogin;

@Controller
@RequestMapping("/apikey/project/{projectId}")
public class ApikeyAbilityController {

    private final ApikeyAbilityService apikeyAbilityService;
    private final ApikeyService apikeyService;

    public ApikeyAbilityController(ApikeyAbilityService apikeyAbilityService, ApikeyService apikeyService) {
        this.apikeyAbilityService = apikeyAbilityService;
        this.apikeyService = apikeyService;
    }


    @GetMapping("/ability/{abilityId}")
    public ResponseEntity<Ability> getById(@PathVariable UUID projectId, @PathVariable UUID abilityId) {
        UUID apikeyId = apikeyService.findByUsername(getCurrentUserLogin()).getId();
        return ResponseEntity.ok(apikeyAbilityService.getById(apikeyId, projectId, abilityId));
    }

    @PostMapping("/ability/create")
    public ResponseEntity<Ability> create(@PathVariable UUID projectId,
                                          @RequestBody AbilityInput abilityInput) {
        UUID apikeyId = apikeyService.findByUsername(getCurrentUserLogin()).getId();
        Ability newAbility = new Ability();

        UUID uuid = UUID.randomUUID();
        newAbility.setId(uuid);
        newAbility.setName(abilityInput.getName());
        newAbility.setType(abilityInput.getType());
        newAbility.setUrl(abilityInput.getUrl());
        newAbility.setProject(projectId);
        return ResponseEntity.ok(
                apikeyAbilityService.insert(apikeyId, projectId, newAbility));
    }
}
