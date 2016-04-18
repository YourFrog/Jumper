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
public class VirtualQuery implements JsonSerialize, Cloneable
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

    public void clearParams() {
        this.params = new ArrayList();
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
    
    public void setParamValue(int index, String value) {
        params.get(index).setValue(value);
    }
    
    public int getParamCount() {
        return this.params.size();
    }
    
    public VirtualParametr getParam(int index) {
        return this.params.get(index);
    }
    
    public String getParamName(int index) {
        return this.params.get(index).getKeyName();
    }
    public String[] getParamsName() {
        String[] names = new String[params.size()];
        
        for(int i = 0; i < params.size(); i++) {
            names[i] = params.get(i).getKeyName();
        }
        
        return names;
    }

    public String getDescription() {
        return description;
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
            
            switch(param.getType().toLowerCase()) {
                case "string":
                        query = query.replace(":" + param.getKeyName(), '"' + param.getValue() + '"');
                    break;
                    
                case "integer":
                        query = query.replace(":" + param.getKeyName(), param.getValue());
                    break;
                    
                default:
                    throw new Exception("Nie obslugiwany typ danych '" + param.getType() + '"');
            }
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
    
    public VirtualQuery clone() {
        VirtualQuery obj = new VirtualQuery();
        
        obj.setDisplayName(displayName);
        obj.setDescription(description);
        obj.setQuery(query);
        
        for(int i = 0; i < params.size(); i++) {
            obj.addParam(params.get(i).clone());
        }
        
        return obj;
    }
}
