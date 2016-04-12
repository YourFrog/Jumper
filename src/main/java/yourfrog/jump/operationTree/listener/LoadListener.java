package yourfrog.jump.operationTree.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import yourfrog.jump.json.JsonTree;
import yourfrog.jump.operationTree.OperationJTree;

/**
 * @author YourFrog
 */
public class LoadListener implements MouseListener
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
        JsonTree jsonTree = new JsonTree();
        
        try {
            final JFileChooser fc = new JFileChooser();
            
            int returnVal = fc.showOpenDialog(null);
            if( returnVal != JFileChooser.APPROVE_OPTION ) {
                JOptionPane.showMessageDialog(null, "Nie wybrano pliku");
                return;
            }
            
            File file = fc.getSelectedFile();
            if( file.exists() == false ) {
                JOptionPane.showMessageDialog(null, "Podany plik nie istnieje");
                return;
            }
            
            DefaultMutableTreeNode root = jsonTree.loadFromFile(file);
            jTree.setRoot(root);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Załadowanie nie powiódło się: " + e.getMessage());
            return;
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
