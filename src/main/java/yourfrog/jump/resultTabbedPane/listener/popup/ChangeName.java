package yourfrog.jump.resultTabbedPane.listener.popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import yourfrog.jump.resultTabbedPane.ResultTabbedPane;

/**
 * @author YourFrog
 */
public class ChangeName implements ActionListener
{
    private ResultTabbedPane tabbedPane;

    public ChangeName(ResultTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        int selectedIndex = tabbedPane.getSelectedIndex();
        String answer = JOptionPane.showInputDialog(null, "Podaj nowa nazwe zakładki", tabbedPane.getTitleAt(selectedIndex));
        
        if( answer == null || answer.length() == 0 ) {
            return;
        }
        
        tabbedPane.setTitleAt(selectedIndex, answer);
    }
}
