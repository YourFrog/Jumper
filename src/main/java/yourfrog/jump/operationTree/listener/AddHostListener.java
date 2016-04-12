package yourfrog.jump.operationTree.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
        Configuration config = new Configuration();
        config.setDisplayName("Dev - Senuto");
        config.setHost("mysql.senuto.com");
        config.setPort(3306);
        
        jTree.addHostNode(config);
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
