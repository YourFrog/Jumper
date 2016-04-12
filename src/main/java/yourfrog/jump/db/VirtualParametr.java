package yourfrog.jump.db;

import org.json.JSONObject;
import yourfrog.jump.json.JsonSerialize;

/**
 * @author YourFrog
 */
public class VirtualParametr implements JsonSerialize
{
    private boolean isRequire;
    
    private String keyName;
    
    private String value;

    public VirtualParametr(String keyName, boolean isRequire) {
        this.isRequire = isRequire;
        this.keyName = keyName;
    }

    public String getKeyName() {
        return keyName;
    }
    
    public boolean isCorrect() {
        if( isRequire == false ) {
            return true;
        }
        
        return (value != null);
    }
    
    public String getValue() {
        if( value == null ) {
            return "NULL";
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
        
        return json;
    }
    
    
}
