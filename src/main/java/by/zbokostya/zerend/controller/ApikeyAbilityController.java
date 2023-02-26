package by.zbokostya.zerend.controller;


import by.zbokostya.zerend.entity.Ability;
import by.zbokostya.zerend.service.ApikeyService;
import by.zbokostya.zerend.service.impl.ApikeyAbilityService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.UUID;

import static by.zbokostya.zerend.security.SecurityUtils.getCurrentUserLogin;

@Controller
@RequestMapping("/apikey/project/{projectId}")
public class ApikeyAbilityController {

    @Value("${directory.default}")
    private String defaultDirectory;

    private final ApikeyAbilityService apikeyAbilityService;
    private final ApikeyService apikeyService;

    public ApikeyAbilityController(ApikeyAbilityService apikeyAbilityService, ApikeyService apikeyService) {
        this.apikeyAbilityService = apikeyAbilityService;
        this.apikeyService = apikeyService;
    }


    @GetMapping("/ability/{abilityId}")
    public ResponseEntity<?> getById(@PathVariable UUID projectId, @PathVariable UUID abilityId) throws FileNotFoundException {
        UUID apikeyId = apikeyService.findByUsername(getCurrentUserLogin()).getId();
        Ability ability = apikeyAbilityService.getById(apikeyId, projectId, abilityId);
        if (ability.getType().equals("file")) {
            File file = new File(defaultDirectory + projectId + "/" + abilityId + "/" + ability.getUrl());
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        }
        return ResponseEntity.ok(ability);
    }

//    @PostMapping("/ability/create")
//    public ResponseEntity<Ability> create(@PathVariable UUID projectId,
//                                          @RequestBody AbilityInput abilityInput) {
//        UUID apikeyId = apikeyService.findByUsername(getCurrentUserLogin()).getId();
//        Ability newAbility = new Ability();
//
//        UUID uuid = UUID.randomUUID();
//        newAbility.setId(uuid);
//        newAbility.setName(abilityInput.getName());
//        newAbility.setType(abilityInput.getType());
//        newAbility.setUrl(abilityInput.getUrl());
//        newAbility.setProject(projectId);
//        return ResponseEntity.ok(
//                apikeyAbilityService.insert(apikeyId, projectId, newAbility));
//    }
}
