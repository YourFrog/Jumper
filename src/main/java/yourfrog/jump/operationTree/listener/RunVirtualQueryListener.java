package yourfrog.jump.operationTree.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import yourfrog.jump.db.Configuration;
import yourfrog.jump.db.VirtualQuery;
import yourfrog.jump.db.VirtualQueryRunner;
import yourfrog.jump.operationTree.OperationJTree;
import yourfrog.jump.resultTabbedPane.ResultTabbedPane;

/**
 * @author YourFrog
 */
public class RunVirtualQueryListener implements MouseListener
{
    /**
     *  Drzewko do którego dodajemy sekcje
     */
    private OperationJTree jTree;

    public void setTree(OperationJTree jTree) {
        this.jTree = jTree;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }
    
    @Override
    public void mouseReleased(MouseEvent me) {
        VirtualQueryRunner runner;
        
        try {
            runner = getVirtualQueryRunner();    
                       
            ResultTabbedPane tabPane = jTree.getTabbedPane();
            tabPane.addTab(runner.getVirtualQuery().getDisplayName(), runner.getResult(tabPane, jTree));
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }

    /**
     *  Zwrócenie wpełni skonfigurowanego runnera zapytan
     * 
     * @return
     * @throws Exception 
     */
    private VirtualQueryRunner getVirtualQueryRunner() throws Exception {
        Configuration configuration = getDatabaseConfiguration();
        VirtualQuery virtualQuery = getVirtualQuery();
        
        VirtualQueryRunner runner = new VirtualQueryRunner();
        runner.setVirtualQuery(virtualQuery);
        runner.setDatabaseConfiguration(configuration);
        
        return runner;
    }
    
    private Configuration getDatabaseConfiguration() throws Exception {
        DefaultMutableTreeNode node = getNode();
        Configuration configuration = getParentDatabaseConfiguration(node);
        if( configuration == null ) {
            throw new Exception("Nie odnaleziono nadrzędnej konfiguracji bazy danych");
        }
        
        return configuration;
    }
    
    private DefaultMutableTreeNode getNode() throws Exception {
        DefaultMutableTreeNode node = jTree.getSeletedNode();
        
        if( node == null ) {
            throw new Exception("Zaznacz jakieś zapytanie");
        }
        
        return node;
    }
    
    private VirtualQuery getVirtualQuery() throws Exception {
        DefaultMutableTreeNode node = getNode();
        
        Object obj = node.getUserObject();
        if( obj instanceof VirtualQuery ) {
            return (VirtualQuery) obj;
        }
        
        throw new Exception("Musisz zaznaczyć zapytanie");
    }
    
    private Configuration getParentDatabaseConfiguration(TreeNode node) {
        do {
            node = node.getParent();
            
            if( node == null || (node instanceof DefaultMutableTreeNode) == false ) {
                break;
            }
            
            DefaultMutableTreeNode mutableNode = (DefaultMutableTreeNode) node;
            Object obj = mutableNode.getUserObject();
            
            if( obj instanceof Configuration ) {
                return (Configuration) obj;
            }
        } while( true );
        
        return null;
    }
}
