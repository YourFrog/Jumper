package yourfrog.jump.resultTabbedPane.listener;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import yourfrog.jump.QueryInformationPanel;
import yourfrog.jump.db.VirtualQuery;
import yourfrog.jump.resultTabbedPane.ResultTabbedPane;
import yourfrog.jump.resultTabbedPane.ResultTable;

/**
 * @author YourFrog
 */
public class SelectedTab implements MouseListener
{
    final private String TAB_TITLE = "Informacje o zaznacznych wynikach";
    
    JTabbedPane pane;
    
    ResultTabbedPane resultPane;
    
    public SelectedTab(ResultTabbedPane resultPane, JTabbedPane pane) {
        this.pane = pane;
        this.resultPane = resultPane;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        deleteQueryInformationTab();
        
        Component scrollPane = resultPane.getActiveTab();
        if( (scrollPane instanceof JScrollPane) == false ) {
            return;
        }
        
        Component component = ((JScrollPane) scrollPane).getViewport().getView();
        if( (component instanceof ResultTable) == false ) {
            return;
        }
        
        VirtualQuery query = ((ResultTable) component).getRunner().getVirtualQuery();
        addQueryInformationTab(query);

        int index = pane.indexOfTab(this.TAB_TITLE);
        pane.setSelectedIndex(index);
    }

    private void addQueryInformationTab(VirtualQuery query) {       
        QueryInformationPanel panel = new QueryInformationPanel(query);
        pane.addTab(TAB_TITLE, panel);
    }
    
    private void deleteQueryInformationTab() {
        int index = pane.indexOfTab(this.TAB_TITLE);
        
        if( index == -1 ) {
            return;
        }
        
        pane.remove(index);
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }
}
