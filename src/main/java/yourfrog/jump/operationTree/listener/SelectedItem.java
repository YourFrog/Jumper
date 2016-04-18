package yourfrog.jump.operationTree.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;
import yourfrog.jump.db.Configuration;
import yourfrog.jump.db.VirtualQuery;
import yourfrog.jump.operationTree.OperationJTree;

/**
 * @author YourFrog
 */
public class SelectedItem implements MouseListener
{
    final private String TAB_TITLE = "Informacje o zaznaczonym elemencie";
    
    OperationJTree tree;
    
    JTabbedPane pane;
    
    public SelectedItem(OperationJTree tree, JTabbedPane pane) {
        this.tree = tree;
        this.pane = pane;
    }
    
    
    @Override
    public void mouseClicked(MouseEvent me) {
        
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        deleteQueryInformationTab();

        DefaultMutableTreeNode node = tree.getSeletedNode();
        
        if( node == null ) {
            return;
        }
        
        Object userObject = node.getUserObject();
        
        if( userObject instanceof VirtualQuery ) {
            addQueryInformationTab((VirtualQuery) userObject);
            setActiveTab();
        }
        
        if( userObject instanceof Configuration ) {
            addConfigurationInformationTab((Configuration) userObject);
            setActiveTab();
        }
    }

    private void setActiveTab() {
        int index = pane.indexOfTab(this.TAB_TITLE);
        pane.setSelectedIndex(index);        
    }
    
    private void addQueryInformationTab(VirtualQuery query) {
        
        String text = "";
        text += "Nazwa: \n" + query.getDisplayName() + "\n\n";
        text += "Opis: \n" + query.getDescription() + "\n\n";
        text += "Query: \n" + query.getQuery();
        
        JTextArea textarea = new JTextArea();
        textarea.setText(text);
        
        JScrollPane scrollPane = new JScrollPane(textarea);
        pane.addTab(this.TAB_TITLE, scrollPane);
    }
    
    private void addConfigurationInformationTab(Configuration conf) {
        
        String text = "";
        
        text += "Nazwa: " + conf.getDisplayName() + "\n";
        text += "Host: " + conf.getHost() + ":" + conf.getPort() + "\n";
        text += "Username: " + conf.getUsername();
        
        JTextArea textarea = new JTextArea();
        textarea.setText(text);
        
        JScrollPane scrollPane = new JScrollPane(textarea);
        pane.addTab(this.TAB_TITLE, scrollPane);
    }
    
    private void deleteQueryInformationTab() {
        int index = pane.indexOfTab(this.TAB_TITLE);
        
        if( index == -1 ) {
            return;
        }
        
        pane.remove(index);
    }
    
    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }
}
