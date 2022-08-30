package by.zbokostya.entity;

import java.util.UUID;

public class Ability {

    private UUID id;
    private String name;
    private String url;
    private String type;
    private UUID project;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UUID getProject() {
        return project;
    }

    public void setProject(UUID project) {
        this.project = project;
    }
}
