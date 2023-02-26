package by.zbokostya.zerend.controller;

import by.zbokostya.zerend.entity.Ability;
import by.zbokostya.zerend.entity.input.AbilityInput;
import by.zbokostya.zerend.service.AbilityService;
import by.zbokostya.zerend.service.UserService;
import by.zbokostya.zerend.service.impl.ApikeyProjectService;
import by.zbokostya.zerend.service.impl.ProjectService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.UUID;

import static by.zbokostya.zerend.entity.EntityUtils.generate;
import static by.zbokostya.zerend.security.SecurityUtils.getCurrentUserLogin;

@Controller
@RequestMapping("/user/project/{projectId}")
public class AbilityController {

    private final AbilityService abilityService;
    private final UserService userService;
    private final ApikeyProjectService apikeyProjectService;

    @Value("${directory.default}")
    private String defaultDirectory;

    private final ProjectService projectService;

    public AbilityController(AbilityService abilityService, UserService userService, ApikeyProjectService apikeyProjectService, ProjectService projectService) {
        this.abilityService = abilityService;
        this.userService = userService;
        this.apikeyProjectService = apikeyProjectService;
        this.projectService = projectService;
    }

    @GetMapping("/ability/{abilityId}")
    public ResponseEntity<?> getAbilityById(
            @PathVariable UUID abilityId,
            @PathVariable UUID projectId) throws FileNotFoundException {
        UUID userId = findUserByUsername();
        Ability ability = abilityService.getAbilityById(userId, projectId, abilityId);
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

    @PostMapping("/ability/create")
    public ResponseEntity<Ability> createAbility(
            @PathVariable UUID projectId,
            @RequestBody AbilityInput abilityInput) {
        Ability ability = new Ability();
        ability.setId(UUID.randomUUID());
        ability.setName(abilityInput.getName());
        ability.setUrl(abilityInput.getName() + "_" + generate(128));
        ability.setProject(projectId);
        ability.setType("text");


        return ResponseEntity.ok(abilityService.createAbility(ability));
    }

    @PostMapping(value = "/ability/file")
    public ResponseEntity<Ability> createFileAbility(
            @PathVariable UUID projectId,
            @RequestPart("ability") AbilityInput abilityInput,
            @RequestPart("file") MultipartFile multipartFile) {
        Ability ability = new Ability();
        ability.setId(UUID.randomUUID());
        ability.setName(abilityInput.getName());
        ability.setUrl(generate(128) + "_" + multipartFile.getOriginalFilename());
        ability.setProject(projectId);
        ability.setType("file");


        return ResponseEntity.ok(abilityService.createFileAbility(ability, multipartFile));
    }


    private UUID findUserByUsername() {
        String login = getCurrentUserLogin();
        if (login.length() == 64) return apikeyProjectService.findByUsername(login).getId();
        else return userService.findUserByUsername(login).getId();
    }

}
