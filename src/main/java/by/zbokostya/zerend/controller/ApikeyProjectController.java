package by.zbokostya.zerend.controller;

import by.zbokostya.zerend.entity.Apikey;
import by.zbokostya.zerend.entity.Project;
import by.zbokostya.zerend.service.ApikeyProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import static by.zbokostya.zerend.security.SecurityUtils.getCurrentUserLogin;

@Controller
@RequestMapping("/apikey")
public class ApikeyProjectController {

    private final ApikeyProjectService apikeyProjectService;


    public ApikeyProjectController(ApikeyProjectService apikeyProjectService) {
        this.apikeyProjectService = apikeyProjectService;
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable UUID projectId) {
        Apikey apikey = apikeyProjectService.findByUsername(getCurrentUserLogin());
        if(!apikey.getProject().equals(projectId)) throw new RuntimeException();
        return ResponseEntity.ok(apikeyProjectService.findByApikey(apikey.getId()));
    }


}
