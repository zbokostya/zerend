package by.zbokostya.zerend.entity;

public class Authority {

    private String name;

    public Authority(String name) {
        this.name = name;
    }

    public Authority() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "name='" + name + '\'' +
                '}';
    }
}
