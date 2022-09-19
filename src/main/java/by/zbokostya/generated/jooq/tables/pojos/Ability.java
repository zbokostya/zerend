/*
 * This file is generated by jOOQ.
 */
package by.zbokostya.generated.jooq.tables.pojos;


import java.io.Serializable;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Ability implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private String name;
    private String url;
    private String type;
    private UUID project;

    public Ability() {}

    public Ability(Ability value) {
        this.id = value.id;
        this.name = value.name;
        this.url = value.url;
        this.type = value.type;
        this.project = value.project;
    }

    public Ability(
        UUID id,
        String name,
        String url,
        String type,
        UUID project
    ) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.type = type;
        this.project = project;
    }

    /**
     * Getter for <code>public.ability.id</code>.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.ability.id</code>.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Getter for <code>public.ability.name</code>.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for <code>public.ability.name</code>.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for <code>public.ability.url</code>.
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Setter for <code>public.ability.url</code>.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Getter for <code>public.ability.type</code>.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Setter for <code>public.ability.type</code>.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for <code>public.ability.project</code>.
     */
    public UUID getProject() {
        return this.project;
    }

    /**
     * Setter for <code>public.ability.project</code>.
     */
    public void setProject(UUID project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Ability other = (Ability) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.name == null) {
            if (other.name != null)
                return false;
        }
        else if (!this.name.equals(other.name))
            return false;
        if (this.url == null) {
            if (other.url != null)
                return false;
        }
        else if (!this.url.equals(other.url))
            return false;
        if (this.type == null) {
            if (other.type != null)
                return false;
        }
        else if (!this.type.equals(other.type))
            return false;
        if (this.project == null) {
            if (other.project != null)
                return false;
        }
        else if (!this.project.equals(other.project))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.url == null) ? 0 : this.url.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.project == null) ? 0 : this.project.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Ability (");

        sb.append(id);
        sb.append(", ").append(name);
        sb.append(", ").append(url);
        sb.append(", ").append(type);
        sb.append(", ").append(project);

        sb.append(")");
        return sb.toString();
    }
}