package yourfrog.jump.resultTabbedPane.listener.popup;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import yourfrog.jump.db.Configuration;
import yourfrog.jump.db.VirtualParametr;
import yourfrog.jump.db.VirtualQuery;
import yourfrog.jump.db.VirtualQueryRunner;
import yourfrog.jump.operationTree.OperationJTree;
import yourfrog.jump.resultTabbedPane.ResultTabbedPane;
import yourfrog.jump.resultTabbedPane.ResultTable;

/**
 * @author YourFrog
 */
public class Jump implements MouseListener
{
    private ResultTable table;

    private ResultTabbedPane tabbedPane;
    
    private OperationJTree tree;
    
    public Jump(ResultTable table, ResultTabbedPane tabbedPane, OperationJTree tree) {
        this.table = table;
        this.tabbedPane = tabbedPane;
        this.tree = tree;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        String value = table.getSelectionValue();
        
        int columnIndex = table.getSelectedColumn();
        String columnName = (String) table.getColumnModel().getColumn(columnIndex).getHeaderValue();
        
        String[] buttons = new String[] {
            "Dalej", "Wstecz", "Anuluj"
        };
        
        
        ArrayList<DefaultMutableTreeNode> options = new ArrayList();
        
        DefaultMutableTreeNode node = tree.getRoot();
        
        do {
            for(int i = 0; i < node.getChildCount(); i++) {
                DefaultMutableTreeNode childrenNode = (DefaultMutableTreeNode) node.getChildAt(i);
                options.add(childrenNode);
            }
            
            JComboBox comboBox = new JComboBox(options.toArray());
            JOptionPane.showOptionDialog(null, comboBox, "Wykonanie zapytania", 0, JOptionPane.INFORMATION_MESSAGE, null, buttons, null);
        } while( false );
        
        try {
            VirtualQueryRunner runner = getVirtualQueryRunner();
            runner.getVirtualQuery().setParamValue(columnName, value);
            
            tabbedPane.addTab(runner.getVirtualQuery().getDisplayName(), runner.getResult(tabbedPane, tree));
            int i = 0;
        } catch( Exception e) {
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
        Configuration config = new Configuration();
        config.setDisplayName("Dev - Senuto");
        config.setHost("mysql.senuto.com");
        config.setPort(3306);
        
        return config;
    }
    
    private VirtualQuery getVirtualQuery() throws Exception {
        VirtualQuery virtualQuery = new VirtualQuery();
        virtualQuery.setDisplayName("Konkretny użytkownik");
        virtualQuery.setDescription("Pobranie konkretnego użytkownika z senuto");
        virtualQuery.setQuery("SELECT * FROM users WHERE user_id = :user_id");
        virtualQuery.addParam(new VirtualParametr("user_id", true));
        
        return virtualQuery;
    }
}