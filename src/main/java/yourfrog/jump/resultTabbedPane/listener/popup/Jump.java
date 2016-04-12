package yourfrog.jump.resultTabbedPane.listener.popup;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
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
        
        String[] options = new String[] {
            "Dalej", "Wstecz", "Anuluj"
        };
        
        JComboBox comboBox = new JComboBox(new String[] { "1", "2" });
        
        do {
            JOptionPane.showOptionDialog(null, comboBox, "Wykonanie zapytania", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
        } while( false );
        
        try {
            VirtualQueryRunner runner = getVirtualQueryRunner();
            runner.getVirtualQuery().setParamValue(columnName, value);
            
            tabbedPane.addTab(runner.getVirtualQuery().getDisplayName(), runner.getResult(tabbedPane));
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