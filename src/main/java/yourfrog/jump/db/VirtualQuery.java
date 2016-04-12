package yourfrog.jump.db;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import yourfrog.jump.json.JsonSerialize;

/**
 *  Reprezentacja zapytania
 * 
 * @author YourFrog
 */
public class VirtualQuery implements JsonSerialize
{
    /**
     *  Wyświetlana nazwa zapytania
     */
    private String displayName = "Unknown";
    
    /**
     *  Opis zapytania
     */
    private String description;
    
    /**
     *  Zapytanie
     */
    private String query;

    /**
     *  Podpinane parametry do zapytania
     */
    private ArrayList<VirtualParametr> params;

    public VirtualQuery() {
        params = new ArrayList();
    }
    
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getQuery() {
        return query;
    }

    public void setParamValue(String key, String value) {
        params.forEach(v -> {
            if( v.getKeyName().equals(key) ) {
                v.setValue(value);
            }
        });
    }
    
    public void addParam(VirtualParametr param) {
        params.add(param);
    }
    
    public String getParseQuery() throws Exception {
        if( params.size() == 0 ) {
            return getQuery();
        }
        
        String query = getQuery();
        for(VirtualParametr param : params) {
            if( param.isCorrect() == false ) {
                throw new Exception("Problem z parametrem: " + param.getKeyName());
            }
            
            query = query.replace(":" + param.getKeyName(), param.getValue());
        }
        
        return query;
    }
    
    @Override
    public String toString() {
        return displayName;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        
        json.put("displayName", displayName);
        json.put("description", description);
        json.put("query", query);
        
        json.put("params", toJsonParams());
        
        return json;
    }
    
    private JSONArray toJsonParams() {
        JSONArray json = new JSONArray();
        
        for(VirtualParametr param : params) {
            json.put(param.toJson());
        }
        
        return json;
    }
}
