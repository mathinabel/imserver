package bean;

public class User {
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public User(String uuid) {
        this.uuid = uuid;
    }
}
