package by.zbokostya.zerend.entity.input;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AbilityInput {
    private String name;
    @JsonIgnore
    private String url;
    @JsonIgnore
    private String type;

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

}
