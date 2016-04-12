package yourfrog.jump.db;

import org.json.JSONObject;
import yourfrog.jump.json.JsonSerialize;

/**
 * @author YourFrog
 */
public class Configuration implements JsonSerialize
{
    /**
     *  Nazwa serwera (widoczna dla użytkownika)
     */
    private String displayName;
    
    private String username;
    
    private String password;
    
    private String host;
    
    private int port;

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return displayName + " (" + host + ":" + port + ")";
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        
        json.put("username", username == null ? "" : username);
        json.put("password", password == null ? "" : password);
        json.put("host", host);
        json.put("port", port);
        json.put("displayName", displayName);
        
        return json;
    }
}
