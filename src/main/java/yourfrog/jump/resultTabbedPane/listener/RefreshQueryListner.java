package yourfrog.jump.resultTabbedPane.listener;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import yourfrog.jump.resultTabbedPane.ResultTabbedPane;
import yourfrog.jump.resultTabbedPane.ResultTable;

/**
 * @author YourFrog
 */
public class RefreshQueryListner implements ActionListener
{
    private ResultTabbedPane tabbedPane;

    public RefreshQueryListner(ResultTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        Component component = tabbedPane.getSelectedComponent();
        if( component == null ) {
            JOptionPane.showMessageDialog(null, "Nie odnaleziono zaznaczonej zakładki");
            return;
        }
        
        if( (component instanceof JScrollPane) == false ) {
            JOptionPane.showMessageDialog(null, "Zaznacz zakładkę z wynikami");
            return;
        }
        
        JViewport viewport = ((JScrollPane) component).getViewport();
        if( (viewport instanceof JViewport) == false ) {
            JOptionPane.showMessageDialog(null, "Zaznacz zakładkę z wynikami");
            return;
        }
        
        component = viewport.getView();
        if( component == null ) {
            JOptionPane.showMessageDialog(null, "Zaznacz zakładkę z wynikami");
            return;
        }
        
        if( (component instanceof ResultTable) == false ) {
            JOptionPane.showMessageDialog(null, "Zaznacz zakładkę z wynikami");
            return;
        }
        
        try {
            ((ResultTable) component).refreshQuery();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        }
    }
}
