package yourfrog.jump.resultTabbedPane.listener.popup;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import yourfrog.jump.Notes;
import yourfrog.jump.resultTabbedPane.ResultTabbedPane;

/**
 * @author YourFrog
 */
public class CloseTab implements ActionListener
{
    private ResultTabbedPane tabbedPane;

    public CloseTab(ResultTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        int tabIndex = tabbedPane.getSelectedIndex();
        Component component = tabbedPane.getSelectedComponent();
        
        if( component instanceof Notes ) {
            JOptionPane.showMessageDialog(null, "Nie można usunąć zakładki z notatkami");
            return;
        }
        
        int answer = JOptionPane.showConfirmDialog(null, "Czy jesteś pewien że chcesz usunąć zakładkę \"" + tabbedPane.getTitleAt(tabIndex) + "\"");
        
        if( answer == JOptionPane.OK_OPTION ) {
            tabbedPane.remove(tabIndex);
        }
    }
}
