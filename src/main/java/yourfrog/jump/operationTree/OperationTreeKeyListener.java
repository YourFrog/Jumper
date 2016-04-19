package yourfrog.jump.operationTree;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

/**
 *
 * @author p.stelmasiak
 */
public class OperationTreeKeyListener implements KeyListener
{
    private OperationJTree tree;

    public OperationTreeKeyListener(OperationJTree tree) {
        this.tree = tree;
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        OparationJPopupMenu popup;
        
        switch(ke.getKeyCode()) {
            case KeyEvent.VK_J: 
                    popup = (OparationJPopupMenu) tree.getComponentPopupMenu();
                    popup.executeRunQuery(tree);
                break;
                
            case KeyEvent.VK_L:
                    if( ke.isControlDown() ) {                        
                        popup = (OparationJPopupMenu) tree.getComponentPopupMenu();
                        popup.executeLoadTree(tree);
                    }
                break;
        } 
    }    
}
