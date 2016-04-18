package yourfrog.jump;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDialog;

/**
 * @author YourFrog
 */
public class CentreJDialog extends JDialog
{
    public void centreLocation() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        
        setLocation(x, y);
    }
}
