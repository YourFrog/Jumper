package yourfrog.jump.db;

import org.json.JSONObject;
import yourfrog.jump.json.JsonSerialize;

/**
 * @author YourFrog
 */
public class VirtualParametr implements JsonSerialize, Cloneable
{
    private boolean isRequire;
    
    private String keyName;
    
    private String value;
    
    private String defaultValue;

    private String type;
    
    public VirtualParametr(String keyName, boolean isRequire, String type, String defaultValue) {
        this.isRequire = isRequire;
        this.keyName = keyName;
        this.defaultValue = defaultValue;
        this.type = type;
    }

    public String getKeyName() {
        return keyName;
    }
    
    public boolean isCorrect() {
        if( isRequire == false || defaultValue != null ) {
            return true;
        }
        
        return (value != null);
    }

    public boolean defaultIsNull() {
        return (defaultValue == null) || (defaultValue.equals("NULL"));
    }
    
    public boolean isRequired() {
        return isRequire;
    }
    
    public String getType() {
        return type;
    }
    
    public String getValue() {
        if( value == null ) {
            return defaultValue;
        }
        
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        
        json.put("is_require", isRequire);
        json.put("name", keyName);
        json.put("default_value", defaultValue);
        json.put("type", type);
        
        return json;
    }
    
    
    public VirtualParametr clone() {
        VirtualParametr obj = new VirtualParametr(keyName, isRequire, type, defaultValue);
        
        return obj;
    }
    
}
