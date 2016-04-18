package yourfrog.jump.operationTree;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import yourfrog.jump.operationTree.listener.AddHostListener;
import yourfrog.jump.operationTree.listener.AddSectionListener;
import yourfrog.jump.operationTree.listener.AddVirtualQueryListener;
import yourfrog.jump.operationTree.listener.ChangeNodeListener;
import yourfrog.jump.operationTree.listener.DeleteNodeListener;
import yourfrog.jump.operationTree.listener.LoadListener;
import yourfrog.jump.operationTree.listener.RunVirtualQueryListener;
import yourfrog.jump.operationTree.listener.SaveListener;

/**
 * @author YourFrog
 */
public class OparationJPopupMenu extends JPopupMenu
{
    private OperationJTree jTree;
    
    public OparationJPopupMenu(OperationJTree jTree) {
        this.jTree = jTree;
        
        JMenuItem menuItem;
        
        javax.swing.JMenu createMenu = new  JMenu("Utwórz..");
        menuItem = prepareAddHostMenuItem();
        createMenu.add(menuItem);
        
        menuItem = prepareAddSectionMenuItem();
        createMenu.add(menuItem);
        
        menuItem = prepareAddVirtualQuery();
        createMenu.add(menuItem);
        
        add(createMenu);
        
        menuItem = prepareChangeNode();
        add(menuItem);
        
        menuItem = prepareDeleteNode();
        add(menuItem);
        
        addSeparator();
        
        menuItem = prepareRunVirtualQuery();
        add(menuItem);
        
        addSeparator();
        
        menuItem = prepareSave();
        add(menuItem);
        
        menuItem = prepareLoad();
        add(menuItem);
    }
    
    private JMenuItem prepareSave() {
        SaveListener mouseListener = new SaveListener();
        mouseListener.setTree(jTree);
        
        JMenuItem menuItem = new JMenuItem("Zapisz");
        menuItem.addMouseListener(mouseListener);
            
        return menuItem;
    }
    
    private JMenuItem prepareLoad() {
        LoadListener mouseListener = new LoadListener();
        mouseListener.setTree(jTree);
        
        JMenuItem menuItem = new JMenuItem("Załaduj");
        menuItem.addMouseListener(mouseListener);
            
        return menuItem;
    }
    
    private JMenuItem prepareAddHostMenuItem()
    {
        AddHostListener mouseListener = new AddHostListener();
        mouseListener.setTree(jTree);
        
        JMenuItem menuItem = new JMenuItem("Host");
        menuItem.addMouseListener(mouseListener);
        
        return menuItem;
    }
    
    private JMenuItem prepareAddSectionMenuItem()
    {
        AddSectionListener mouseListener = new AddSectionListener();
        mouseListener.setTree(jTree);
        
        JMenuItem item = new JMenuItem("Sekcje");
        item.addMouseListener(mouseListener);
        
        return item;
    }
    
    private JMenuItem prepareAddVirtualQuery()
    {
        AddVirtualQueryListener mouseListener = new AddVirtualQueryListener();
        mouseListener.setTree(jTree);
        
        JMenuItem item = new JMenuItem("Zapytanie");
        item.addMouseListener(mouseListener);
        
        return item; 
    }
    
    private JMenuItem prepareChangeNode() {
        ChangeNodeListener mouseListener = new ChangeNodeListener();
        mouseListener.setTree(jTree);
        
        JMenuItem item = new JMenuItem("Edytuj");
        item.addMouseListener(mouseListener);
        
        return item;
    }
    
    private JMenuItem prepareDeleteNode() {
        DeleteNodeListener mouseListener = new DeleteNodeListener();
        mouseListener.setTree(jTree);
        
        JMenuItem item = new JMenuItem("Usuń");
        item.addMouseListener(mouseListener);
        
        return item;
    }
    
    private JMenuItem prepareRunVirtualQuery()       
    {
        RunVirtualQueryListener mouseListener = new RunVirtualQueryListener();
        mouseListener.setTree(jTree);
        
        JMenuItem item = new JMenuItem("Uruchom zapytanie");
        item.addMouseListener(mouseListener);
        
        return item; 
    }
}
