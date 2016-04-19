package yourfrog.jump.operationTree.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import yourfrog.jump.QueryParametrDialog;
import yourfrog.jump.db.Configuration;
import yourfrog.jump.db.VirtualParametr;
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
            
            VirtualQuery query = runner.getVirtualQuery();
            if( query.getParamCount() == 1 ) {
                String paramName = query.getParamName(0);
                String value = JOptionPane.showInputDialog(null, "Podaj wartość parametru '" + paramName + "'");

                if( value == null ) {
                    JOptionPane.showMessageDialog(null, "Nie podano wartości dla parametru");
                    return;
                }
                
                query.setParamValue(paramName, value);
            }
            
            if( query.getParamCount() >= 2 ) {
                String[] cols = { "Parametr", "Typ", "Wartość" };
                String[][] data = new String[query.getParamCount()][3];
                
                for(int i = 0; i < query.getParamCount(); i++) {
                    VirtualParametr param = query.getParam(i);
                    
                    data[i][0] = param.getKeyName();
                    data[i][1] = param.getType();
                    
                    if( param.defaultIsNull() ) {
                        data[i][1] += " / null";
                    }
                    
                    data[i][2] = param.getValue();
                }
                
                QueryParametrDialog dialog = new QueryParametrDialog(data, cols);
                dialog.setVisible(true);
                
                String[] values = dialog.getParamValues(3);
                for(int i = 0; i < values.length; i++) {
                    query.setParamValue(i, values[i]);
                }
            }
                       
            ResultTabbedPane tabPane = jTree.getTabbedPane();
            tabPane.addTab(runner.getVirtualQuery().getDisplayName(), runner.getResult(tabPane, jTree));
            
            for(MouseListener listener : tabPane.getMouseListeners() ) {
                listener.mouseReleased(me);
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
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
        runner.setVirtualQuery(virtualQuery.clone());
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
