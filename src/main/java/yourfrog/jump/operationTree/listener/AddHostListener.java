package yourfrog.jump.operationTree.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import yourfrog.jump.HostDialog;
import yourfrog.jump.db.Configuration;
import yourfrog.jump.operationTree.OperationJTree;

/**
 * @author YourFrog
 */
public class AddHostListener implements MouseListener
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
        
        HostDialog dialog = new HostDialog();
        dialog.setVisible(true);
                
        if( dialog.isCancel() ) {
            return;
        }
        
        jTree.addHostNode(dialog.getConfiguration());
        jTree.sortNode();
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
