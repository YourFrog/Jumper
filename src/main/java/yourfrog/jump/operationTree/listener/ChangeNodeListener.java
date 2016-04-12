package yourfrog.jump.operationTree.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import yourfrog.jump.db.Configuration;
import yourfrog.jump.operationTree.OperationJTree;

/**
 * @author YourFrog
 */
public class ChangeNodeListener implements MouseListener
{   
    /**
     *  Drzewko do którego dodajemy hosty
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
            JOptionPane.showMessageDialog(null, "Nie zaznaczono elementu");
            return;
        }
        
        Object obj = node.getUserObject();
        
        if( obj instanceof String ) {
            String answer = JOptionPane.showInputDialog(null, "Podaj nową nazwę", (String) obj);
            if( answer == null || answer.length() == 0 ) {
                JOptionPane.showMessageDialog(null, "Nie podano nazwy");
                return;
            }
            
            node.setUserObject(answer);
            ((DefaultTreeModel) jTree.getModel()).nodeChanged(node);
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
