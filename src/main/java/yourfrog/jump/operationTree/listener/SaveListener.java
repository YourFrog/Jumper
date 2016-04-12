package yourfrog.jump.operationTree.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import yourfrog.jump.json.JsonTree;
import yourfrog.jump.operationTree.OperationJTree;

/**
 * @author YourFrog
 */
public class SaveListener implements MouseListener
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
            JSONObject json = jsonTree.toJson(jTree);
            
            final JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(null);

            if( returnVal != JFileChooser.APPROVE_OPTION ) {
                JOptionPane.showMessageDialog(null, "Nie zatwierdzono opcji");
                return;
            }
            
            File file = fc.getSelectedFile();
            if( file.exists() ) {
                int returnConfirm = JOptionPane.showConfirmDialog(null, "Czy chcesz nadpisać plik \"" + file.getAbsolutePath() + "\" ?");
                
                if( returnConfirm != JOptionPane.OK_OPTION ) {
                    return;
                }
            }
            
            FileUtils.writeStringToFile(file, json.toString(4));
            JOptionPane.showMessageDialog(null, "Poprawnie zapisano");
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Zapis nie powiódł się: " + e.getMessage());
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
