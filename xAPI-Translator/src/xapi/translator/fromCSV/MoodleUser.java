package xapi.translator.fromCSV;

/**
 * POJO class for reading user id file
 * @author Chloe Lao <chloe@jia-online.de>
 */
public class MoodleUser {
    
    private String id;
    private String user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
