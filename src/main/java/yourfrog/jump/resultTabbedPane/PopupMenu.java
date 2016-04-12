/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package yourfrog.jump.resultTabbedPane;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import yourfrog.jump.operationTree.OperationJTree;
import yourfrog.jump.resultTabbedPane.listener.popup.AddToNotes;
import yourfrog.jump.resultTabbedPane.listener.popup.Jump;

/**
 * @author YourFrog
 */
public class PopupMenu extends JPopupMenu
{
    protected ResultTable table;
    
    private ResultTabbedPane tabbedPane;
    
    private OperationJTree tree;
    
    public PopupMenu(ResultTable table, ResultTabbedPane tabbedPane, OperationJTree tree) {
        this.table = table;
        this.tabbedPane = tabbedPane;
        this.tree = tree;
        
        prepareAddToNotes();
        prepareJump();
    }
    
    private void prepareAddToNotes() {
        JMenuItem item = new JMenuItem("Dodaj do notatek");
        item.addMouseListener(new AddToNotes(table));
        
        add(item);
    }
    
    private void prepareJump() {
        JMenuItem item = new JMenuItem("Jump ...");
        item.addMouseListener(new Jump(table, tabbedPane, tree));
        
        add(item);
    }
}
