package yourfrog.jump.db;

import org.json.JSONObject;
import yourfrog.jump.json.JsonSerialize;

/**
 * @author YourFrog
 */
public class Configuration implements JsonSerialize
{
    public enum DatabaseType {
        MySQL,
        POSTGRESQL
    }
    
    /**
     *  Nazwa serwera (widoczna dla użytkownika)
     */
    private String displayName;
    
    private DatabaseType databaseType = DatabaseType.MySQL;
    
    private String dbname;
    
    private String username;
    
    private String password;
    
    private String host;
    
    private int port;

    public String getDisplayName() {
        return displayName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public void setDatabaseType(DatabaseType databaseType) {
        this.databaseType = databaseType;
    }

    public DatabaseType getDatabaseType() {
        return databaseType;
    }
    
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDbname() {
        return dbname;
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

    public void setDbname(String dbname) {
        this.dbname = dbname;
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
        json.put("dbname", dbname);
        json.put("port", port);
        json.put("displayName", displayName);
        json.put("db_type", databaseType.ordinal());
        
        return json;
    }
}
