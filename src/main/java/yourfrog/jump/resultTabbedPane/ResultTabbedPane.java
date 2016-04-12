package yourfrog.jump.resultTabbedPane;

import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import yourfrog.jump.Notes;

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
    
    public void addTab(String title, ResultTable component) {
        component.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        component.setNotes(notes);
        JScrollPane scrollPane = new JScrollPane(component, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        super.addTab(title, (Component) scrollPane);
    }
    
    public JTextArea getNotes() {
        return notes;
    }
}
