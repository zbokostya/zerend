package by.zbokostya.controller;

import by.zbokostya.controller.error.BadRequestAlertException;
import by.zbokostya.entity.Project;
import by.zbokostya.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import static by.zbokostya.entity.EntityUtils.generate;

@Controller
@RequestMapping("/user")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    @GetMapping("/projects")
    public ResponseEntity<List<Project>> getAllProjects() {
        UUID userId = projectService.getCurrentUserUUID();
        return ResponseEntity.ok(projectService.getAllProjects(userId));
    }

    @GetMapping("/project/{project_id}")
    public ResponseEntity<Project> getProjectById(@PathVariable UUID project_id) {
        UUID userId = projectService.getCurrentUserUUID();
        return ResponseEntity.ok(projectService.getProjectById(userId, project_id));
    }

    @PostMapping("/project/create")
    public ResponseEntity<Project> createProject(@RequestBody Project project) throws URISyntaxException {
        if (project.getId() != null)
            //todo
            throw new BadRequestAlertException("error");

        UUID userId = projectService.getCurrentUserUUID();

        project.setId(UUID.randomUUID());
        project.setUrl(project.getName() + "_" + generate(128));
        project.setApikey(generate(256));
        project.setOwnerId(userId);

        projectService.createProject(project);
        return ResponseEntity.ok(project);
    }

    // todo
    @PostMapping("/project/{project_id}")
    public ResponseEntity<Project> updateProject(
            @PathVariable(required = false) UUID project_id,
            @RequestBody Project project) {
        if (project.getId() == null)
            // todo
            throw new BadRequestAlertException("error");
        if (!project.getId().equals(project_id))
            // todo
            throw new BadRequestAlertException("error");

        UUID userId = projectService.getCurrentUserUUID();
        if (!projectService.existsProjectById(userId, project_id))
            // todo
            throw new BadRequestAlertException("error");

        Project existingProject = projectService.getProjectById(userId, project.getId());
        if (project.getName() != null) existingProject.setName(project.getName());
        if (project.getApikey() != null) existingProject.setApikey(project.getApikey());
        if (project.getUrl() != null) existingProject.setUrl(project.getUrl());

        projectService.updateProject(existingProject);
        return ResponseEntity.ok(existingProject);
    }


}
