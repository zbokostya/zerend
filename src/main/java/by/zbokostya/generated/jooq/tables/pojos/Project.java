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
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private String name;
    private String url;
    private UUID owner;

    public Project() {}

    public Project(Project value) {
        this.id = value.id;
        this.name = value.name;
        this.url = value.url;
        this.owner = value.owner;
    }

    public Project(
        UUID id,
        String name,
        String url,
        UUID owner
    ) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.owner = owner;
    }

    /**
     * Getter for <code>public.project.id</code>.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.project.id</code>.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Getter for <code>public.project.name</code>.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for <code>public.project.name</code>.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for <code>public.project.url</code>.
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Setter for <code>public.project.url</code>.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Getter for <code>public.project.owner</code>.
     */
    public UUID getOwner() {
        return this.owner;
    }

    /**
     * Setter for <code>public.project.owner</code>.
     */
    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Project other = (Project) obj;
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
        if (this.owner == null) {
            if (other.owner != null)
                return false;
        }
        else if (!this.owner.equals(other.owner))
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
        result = prime * result + ((this.owner == null) ? 0 : this.owner.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Project (");

        sb.append(id);
        sb.append(", ").append(name);
        sb.append(", ").append(url);
        sb.append(", ").append(owner);

        sb.append(")");
        return sb.toString();
    }
}
