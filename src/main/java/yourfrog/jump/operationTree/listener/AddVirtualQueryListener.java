package yourfrog.jump.operationTree.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import yourfrog.jump.VirtualQueryDialog;
import yourfrog.jump.operationTree.OperationJTree;

/**
 * @author YourFrog
 */
public class AddVirtualQueryListener implements MouseListener
{
    /**
     *  Drzewko do którego dodajemy sekcje
     */
    private OperationJTree jTree;

    public void setTree(OperationJTree jTree) {
        this.jTree = jTree;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        DefaultMutableTreeNode node = jTree.getSeletedNode();
        
        if( node == null ) {
            JOptionPane.showMessageDialog(null, "Brak zaznaczonego elementu nadrzędnego");
            return;
        }
        
        Object obj = node.getUserObject();
        
        if( (obj instanceof String) == false ) {
            JOptionPane.showMessageDialog(null, "Zaznacz sekcje do której przypisać tworzone zapytanie");
            return;
        }
        
        VirtualQueryDialog queryDialog = new VirtualQueryDialog();
        queryDialog.setVisible(true);
        
        if( queryDialog.isCancel() ) {
            return;
        }
        
        jTree.addQueryNode(queryDialog.getVirtualQuery(), node);
        jTree.sortNode();
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}