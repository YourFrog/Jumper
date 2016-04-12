package yourfrog.jump.operationTree.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import yourfrog.jump.operationTree.OperationJTree;

/**
 * @author YourFrog
 */
public class AddSectionListener implements MouseListener
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
        
        String sectionName = JOptionPane.showInputDialog(null, "Podaj nazwę sekcji");
        if( sectionName == null || sectionName.length() == 0 ) {
            JOptionPane.showMessageDialog(null, "Nie podano nazwy sekcji");
            return;
        }
        
        jTree.addSectionNode(sectionName, node);
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
