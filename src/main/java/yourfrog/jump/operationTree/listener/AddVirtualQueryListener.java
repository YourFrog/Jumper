package yourfrog.jump.operationTree.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import yourfrog.jump.db.VirtualParametr;
import yourfrog.jump.db.VirtualQuery;
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
        
        VirtualQuery virtualQuery = new VirtualQuery();
        virtualQuery.setDisplayName("Wszyscy użytkownicy");
        virtualQuery.setDescription("Pobranie wszystkich użytkowników znajdujących się w bazie");
        virtualQuery.setQuery("SELECT * FROM users");
        
        jTree.addQueryNode(virtualQuery, node);
        
        virtualQuery = new VirtualQuery();
        virtualQuery.setDisplayName("Konkretny użytkownik");
        virtualQuery.setDescription("Pobranie konkretnego użytkownika z senuto");
        virtualQuery.setQuery("SELECT * FROM users WHERE user_id = :user_id");
        virtualQuery.addParam(new VirtualParametr("user_id", true));
        
        jTree.addQueryNode(virtualQuery, node);
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}