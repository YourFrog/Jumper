package yourfrog.jump.resultTabbedPane;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import yourfrog.jump.db.VirtualQueryRunner;
import yourfrog.jump.operationTree.OperationJTree;

/**
 * @author YourFrog
 */
public class ResultTable extends JTable
{
    VirtualQueryRunner runner;

    ResultTabbedPane tabbedPane;
    
    JTextArea notes;
    
    public ResultTable(VirtualQueryRunner runner, DefaultTableModel model, ResultTabbedPane tabbedPane, OperationJTree tree) {
        super(model);
        
        this.runner = runner;
        this.tabbedPane = tabbedPane;
        
        PopupMenu popup = new PopupMenu(this, tabbedPane, tree);
        this.setComponentPopupMenu(popup);
    }
    
    public void refreshQuery() throws Exception {
        DefaultTableModel model = (DefaultTableModel) this.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);
        
        runner.getResult(this);
    }
    
    public String getSelectionValue() {
        int columnIndex = getSelectedColumn();
        int rowIndex = getSelectedRow();
        
        return (String) getValueAt(rowIndex, columnIndex);        
    }
    
    public HashMap<String, ArrayList<String>> getSelectionValues() {
        HashMap<String, ArrayList<String>> map = new HashMap();
        
        for(int columnIndex : getSelectedColumns()) {
            String columnName = getColumnName(columnIndex);
            ArrayList<String> list = new ArrayList();
            
            for(int rowIndex : getSelectedRows()) {
                String value = (String) getValueAt(rowIndex, columnIndex);
                list.add(value);
            }
            
            map.put(columnName, list);
        }
        
        return map;
    }
    
    public void setNotes(JTextArea notes) {
        this.notes = notes;
        
    }
    public JTextArea getNotes() {
        return notes;
    }
}
