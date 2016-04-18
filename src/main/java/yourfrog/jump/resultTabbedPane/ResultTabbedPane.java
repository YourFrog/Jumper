package yourfrog.jump.resultTabbedPane;

import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import yourfrog.jump.Notes;
import yourfrog.jump.db.VirtualQueryRunner;
import yourfrog.jump.operationTree.OperationJTree;

/**
 * @author YourFrog
 */
public class ResultTabbedPane extends JTabbedPane
{
    private JTextArea notes;
    
    public ResultTabbedPane() {
        super();
        
        addNotepad();
        
        ResultTabbedPanePopupMenu popup = new ResultTabbedPanePopupMenu(this);
        setComponentPopupMenu(popup);
    }
    
    protected void addNotepad() {
        notes = new Notes();
        
        addTab("Notatnik", notes);
    }
    
    public void addTab(VirtualQueryRunner runner, OperationJTree tree) throws Exception {
        ResultTable resultTable = runner.getResult(this, tree);
        String title = runner.getVirtualQuery().getDisplayName();
        
        addTab(title, resultTable);
    }
    
    public void addTab(String title, ResultTable component) {
        component.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        component.setNotes(notes);
        JScrollPane scrollPane = new JScrollPane(component, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        super.addTab(title, (Component) scrollPane);
        setActiveLastTab();
    }
    
    public void setActiveLastTab() {    
        int lastIndex = getTabCount() - 1;
        setSelectedIndex(lastIndex);
    }
    
    public Component getActiveTab() {
        return this.getSelectedComponent();
    }
    
    public JTextArea getNotes() {
        return notes;
    }
}
