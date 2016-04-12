package yourfrog.jump.resultTabbedPane;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import yourfrog.jump.resultTabbedPane.listener.RefreshQueryListner;
import yourfrog.jump.resultTabbedPane.listener.popup.ChangeName;
import yourfrog.jump.resultTabbedPane.listener.popup.CloseTab;

/**
 * @author YourFrog
 */
public class ResultTabbedPanePopupMenu extends JPopupMenu
{
    private ResultTabbedPane tabbedPane;

    public ResultTabbedPanePopupMenu(ResultTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
        
        prepareRefreshMenu();
        prepareChangeName();
        prepareCloseTab();
    }
    
    private void prepareRefreshMenu() {
        JMenuItem item = new JMenuItem("Wykonaj ponownie");
        item.addActionListener(new RefreshQueryListner(tabbedPane));
        
        add(item);
    }
    
    private void prepareCloseTab() {
        JMenuItem item = new JMenuItem("Zamknij");
        item.addActionListener(new CloseTab(tabbedPane));
        
        add(item);
    }
    
    private void prepareChangeName() {
        JMenuItem item = new JMenuItem("Zmień nazwe");
        item.addActionListener(new ChangeName(tabbedPane));
        
        add(item);
    }
}
