package yourfrog.jump.json;

import java.io.File;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import yourfrog.jump.db.Configuration;
import yourfrog.jump.db.VirtualParametr;
import yourfrog.jump.db.VirtualQuery;

/**
 * @author YourFrog
 */
public class JsonTree 
{
    public JSONObject toJson(JTree tree) throws Exception {
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();        
        return putNode(root);
    }
    
    private JSONObject putNode(DefaultMutableTreeNode node) throws Exception {
        JSONObject json = new JSONObject();
        
        json.put("__class", getClassName(node));
        json.put("__value", toJsonNode(node));
        json.put("__children", toJsonChildrenNode(node));
        
        return json;
    }
    
    private String getClassName(DefaultMutableTreeNode node) {
        return node.getUserObject().getClass().getName();
    }
    
    private JSONArray toJsonChildrenNode(DefaultMutableTreeNode node) throws Exception {
        JSONArray json = new JSONArray();
        
        for(int i = 0; i < node.getChildCount(); i++) {
            DefaultMutableTreeNode nodeChildren = (DefaultMutableTreeNode) node.getChildAt(i);            
            json.put(putNode(nodeChildren));
        }
        
        return json;
    }
    
    private Object toJsonNode(DefaultMutableTreeNode node) throws Exception {
        Object userObject = node.getUserObject();

        if( userObject instanceof String ) {
            return (String) userObject;
        }

        if( userObject instanceof JsonSerialize ) {
            return ((JsonSerialize) userObject).toJson();
        }

        throw new Exception("Nie można serializować obiektu");
    }
    
    /**
     *  Załadowanie z pliku json'a
     * 
     * @param file
     * @return 
     */
    public DefaultMutableTreeNode loadFromFile(File file) throws Exception {
        String content = FileUtils.readFileToString(file);
        JSONObject json = new JSONObject(content);
        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        
        load(root, json);
        
        return root;
    }
    
    private void load(DefaultMutableTreeNode node, JSONObject json) {
        String jsonClassName = json.getString("__class");
        switch(jsonClassName) {
            case "java.lang.String":
                    String jsonValue = json.getString("__value");
                    node.setUserObject(jsonValue);
                break;
                
            case "yourfrog.jump.db.Configuration":
                    loadConfiguration(node, json.getJSONObject("__value"));
                break;
                
            case "yourfrog.jump.db.VirtualQuery":
                    loadVirtualQuery(node, json.getJSONObject("__value"));
                break;
                
            default:
                node.setUserObject(jsonClassName);
        }
        
        JSONArray jsonArray = json.getJSONArray("__children");
        if( jsonArray.length() > 0 ) {
            loadChildren(node, jsonArray);
        }
    }
    
    private void loadConfiguration(DefaultMutableTreeNode node, JSONObject json) {
        Configuration conf = new Configuration();
        conf.setPassword(json.getString("password"));
        conf.setPort(json.getInt("port"));
        conf.setDisplayName(json.getString("displayName"));
        conf.setHost(json.getString("host"));
        conf.setUsername(json.getString("username"));
        conf.setDbname(json.getString("dbname"));
        
        int dbType = json.getInt("db_type");
        
        if( dbType == Configuration.DatabaseType.MySQL.ordinal() ) {
            conf.setDatabaseType(Configuration.DatabaseType.MySQL);
        }
        
        if( dbType == Configuration.DatabaseType.POSTGRESQL.ordinal() ) {
            conf.setDatabaseType(Configuration.DatabaseType.POSTGRESQL);
        }
        
        node.setUserObject(conf);
    }
    
    private void loadVirtualQuery(DefaultMutableTreeNode node, JSONObject json) {
        VirtualQuery query = new VirtualQuery();

        query.setDisplayName(json.getString("displayName"));
        query.setQuery(json.getString("query"));
        query.setDescription(json.getString("description"));
                        
        loadVirtualQueryParams(query, json.getJSONArray("params"));

        node.setUserObject(query);
    }
    
    private void loadVirtualQueryParams(VirtualQuery query, JSONArray json) {       
        for(int i = 0; i < json.length(); i++) {
            String name = json.getJSONObject(i).getString("name");
            boolean isRequired = json.getJSONObject(i).getBoolean("is_require");
            String defaultValue = json.getJSONObject(i).getString("default_value");
            String type = json.getJSONObject(i).getString("type");

            VirtualParametr param = new VirtualParametr(name, isRequired, type, defaultValue);    
            query.addParam(param);
        }
    }
    
    private void loadChildren(DefaultMutableTreeNode node, JSONArray json) {
        for(int i = 0; i < json.length(); i++) {
            DefaultMutableTreeNode children = new DefaultMutableTreeNode();
            
            load(children, json.getJSONObject(i));            
            node.insert(children, i);
        }
    }
}
