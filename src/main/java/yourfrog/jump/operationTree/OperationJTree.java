package yourfrog.jump.operationTree;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import yourfrog.jump.db.Configuration;
import yourfrog.jump.db.VirtualQuery;
import yourfrog.jump.resultTabbedPane.ResultTabbedPane;

/**
 * @author YourFrog
 */
public class OperationJTree extends JTree
{
    /**
     *  Aktualnie przechowywany model
     */
    private DefaultTreeModel model;
    
    /**
     *  Korzeń drzewa
     */
    private DefaultMutableTreeNode root;
    
    private ResultTabbedPane tabbedPane;
    
    public OperationJTree(ResultTabbedPane tabbedPane)
    {
        super();
        
        this.tabbedPane = tabbedPane;
        
        root = new DefaultMutableTreeNode("Root");
        model = new DefaultTreeModel(root);
        
        OparationJPopupMenu popupMenu = new OparationJPopupMenu(this);
        
        setComponentPopupMenu(popupMenu);
        setModel(model);
    }
    
    public DefaultMutableTreeNode getSeletedNode() {
        Object obj = this.getLastSelectedPathComponent();
        
        if( obj == null ) {
            return null;
        }
        
        return (DefaultMutableTreeNode) obj;
    }
    
    public void addHostNode(Configuration config) {
        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(config);
        
        model.insertNodeInto(newNode, root, root.getChildCount());
    }
    
    public void addSectionNode(String name, DefaultMutableTreeNode parentNode) {
        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(name);
        
        model.insertNodeInto(newNode, parentNode, parentNode.getChildCount());
    }
    
    public void addQueryNode(VirtualQuery virtualQuery, DefaultMutableTreeNode parentNode) {
        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(virtualQuery);
        
        model.insertNodeInto(newNode, parentNode, parentNode.getChildCount());
    }
    
    public ResultTabbedPane getTabbedPane() {
        return tabbedPane;
    }
    
    public void setRoot(DefaultMutableTreeNode root) {
        model = new DefaultTreeModel(root);
        setModel(model);
    }
}