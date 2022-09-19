package by.zbokostya.zerend.controller;

import by.zbokostya.zerend.controller.error.BadRequestAlertException;
import by.zbokostya.zerend.entity.Project;
import by.zbokostya.zerend.entity.response.ProjectResponse;
import by.zbokostya.zerend.service.ProjectService;
import by.zbokostya.zerend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import static by.zbokostya.zerend.entity.EntityUtils.generate;
import static by.zbokostya.zerend.security.SecurityUtils.getCurrentUserLogin;


@Controller
@RequestMapping("/user")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }


    @GetMapping("/projects")
    public ResponseEntity<List<Project>> getAllProjects() {
        UUID userId = userService.findUserByUsername(getCurrentUserLogin()).getId();
        return ResponseEntity.ok(projectService.getAllProjects(userId));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable UUID projectId) {
        UUID userId = userService.findUserByUsername(getCurrentUserLogin()).getId();
        return ResponseEntity.ok(projectService.getProjectById(userId, projectId));
    }

    @PostMapping("/project/create")
    public ResponseEntity<ProjectResponse> createProject(@RequestBody Project project) throws URISyntaxException {
        if (project.getId() != null)
            //todo
            throw new BadRequestAlertException("error");

        UUID userId = userService.findUserByUsername(getCurrentUserLogin()).getId();

        project.setId(UUID.randomUUID());
        project.setUrl(project.getName() + "_" + generate(128));
//        project.setApiKey(generate(256));
        project.setOwner(userId);

        projectService.createProject(project);

        ProjectResponse response = new ProjectResponse();
        response.setId(project.getId());
        response.setName(project.getName());
        response.setUrl(project.getUrl());

        return ResponseEntity.ok(response);
    }

    // todo
    @PostMapping("/project/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable(required = true) UUID projectId, @RequestBody Project project) {
        if (project.getId() == null)
            // todo
            throw new BadRequestAlertException("error");
        if (!project.getId().equals(projectId))
            // todo
            throw new BadRequestAlertException("error");

        UUID userId = userService.findUserByUsername(getCurrentUserLogin()).getId();
        if (!projectService.existsProjectById(userId, projectId))
            // todo
            throw new BadRequestAlertException("error");

        Project existingProject = projectService.getProjectById(userId, project.getId());
        if (project.getName() != null) existingProject.setName(project.getName());
        if (project.getUrl() != null) existingProject.setUrl(project.getUrl());

        projectService.updateProject(existingProject);
        return ResponseEntity.ok(existingProject);
    }


}
