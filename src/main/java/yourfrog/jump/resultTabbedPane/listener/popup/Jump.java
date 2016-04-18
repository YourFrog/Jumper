package yourfrog.jump.resultTabbedPane.listener.popup;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import yourfrog.jump.db.Configuration;
import yourfrog.jump.db.VirtualQuery;
import yourfrog.jump.db.VirtualQueryRunner;
import yourfrog.jump.operationTree.OperationJTree;
import yourfrog.jump.resultTabbedPane.ResultTabbedPane;
import yourfrog.jump.resultTabbedPane.ResultTable;
import yourfrog.jump.resultTabbedPane.listener.popup.jump.NodeNotFoundException;

/**
 * @author YourFrog
 */
public class Jump implements MouseListener
{
    final int BUTTON_NEXT = 0;
    final int BUTTON_BACK = 1;
    final int BUTTON_CANCEL = 2;
    
    /**
     *  Tabela do której zapisujemy dane z wyniku zapytania
     */
    private ResultTable table;

    private ResultTabbedPane tabbedPane;
    
    private OperationJTree tree;
        
    private VirtualQueryRunner runner;
    
    /**
     *  Aktualnie zaznaczony node
     */
    private DefaultMutableTreeNode actualNode;
    
    /**
     *  Guziki wyświetlane przy wyborze przez klienta
     */
    private String[] buttons = new String[] {
        "Dalej", "Wstecz", "Anuluj"
    };
    
    public Jump(ResultTable table, ResultTabbedPane tabbedPane, OperationJTree tree) {
        this.table = table;
        this.tabbedPane = tabbedPane;
        this.tree = tree;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }
    
    @Override
    public void mouseReleased(MouseEvent me) { 
        try {
            loadQueryNode();
            loadRunner();
            setQueryParams();
            
            tabbedPane.addTab(runner, tree);
        } catch(NodeNotFoundException e) {
            // Nic nie robimy nie wybrano node'a z zapytaniem
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;            
        }
    }
    
    private void loadQueryNode() throws NodeNotFoundException {
        actualNode = tree.getRoot();
        
        do {
            ArrayList<DefaultMutableTreeNode> options = getOptions();
            
            JComboBox comboBox = null;
            int returnVal;
            
            if( options.isEmpty() ) {
                JOptionPane.showMessageDialog(null, "Nie odnaleziono zdefiniowanych potomków");
                returnVal = 1;
            } else {
                comboBox = new JComboBox(options.toArray());
                returnVal = JOptionPane.showOptionDialog(null, comboBox, "Wybierz zapytanie", 0, JOptionPane.INFORMATION_MESSAGE, null, buttons, null);
            }
            
            actualNode = getSelectedNode(returnVal, comboBox, actualNode);            
            if( actualNode.getUserObject() instanceof VirtualQuery ) {
                break;
            }
        } while( true );
    }
    
    private DefaultMutableTreeNode getSelectedNode(int value, JComboBox comboBox, DefaultMutableTreeNode node) throws NodeNotFoundException {
        if( value == BUTTON_NEXT ) {
            DefaultMutableTreeNode selectedValue = (DefaultMutableTreeNode) comboBox.getSelectedItem();
            return selectedValue;
        }
        
        if( value == BUTTON_BACK ) {
            TreeNode parent = node.getParent();
            if( parent != null ) {
                return(DefaultMutableTreeNode) parent; 
            }
        }
        
        throw new NodeNotFoundException();
    }

    private void setQueryParams() {
        String value = table.getSelectionValue();
        String[] names = runner.getVirtualQuery().getParamsName();

        if( names.length == 1 ) {
            runner.getVirtualQuery().setParamValue(names[0], value);            
        }
        
        if( names.length > 1 ) {
            buttons = new String[] {
                "Zatwierdź", "Anuluj"
            };

            JComboBox comboBox = new JComboBox(names);
            int returnVal = JOptionPane.showOptionDialog(null, comboBox, "Wybierz parametr", 0, JOptionPane.INFORMATION_MESSAGE, null, buttons, null);

            if( returnVal == 0 ) {
                int index = comboBox.getSelectedIndex();
                runner.getVirtualQuery().setParamValue(names[index], value);
            }   
        }
    }

    private ArrayList<DefaultMutableTreeNode> getOptions() {
        ArrayList<DefaultMutableTreeNode> options = new ArrayList();

        int cc = actualNode.getChildCount();
        for(int i = 0; i < cc; i++) {
            DefaultMutableTreeNode childrenNode = (DefaultMutableTreeNode) actualNode.getChildAt(i);
            options.add(childrenNode);
        }

        Collections.sort(options, new Comparator<DefaultMutableTreeNode>() {
            @Override
            public int compare(DefaultMutableTreeNode t, DefaultMutableTreeNode t1) {
                return t.getUserObject().toString().compareTo(t1.getUserObject().toString());
            }
        });

        return options;
    }
    
    /**
     *  Zwrócenie wpełni skonfigurowanego runnera zapytan
     * 
     * @return
     * @throws Exception 
     */
    private void loadRunner() throws Exception {
        VirtualQuery virtualQuery = (VirtualQuery) actualNode.getUserObject();
        Configuration configuration = tree.getParentDatabaseConfiguration(actualNode);
        
         
        runner = new VirtualQueryRunner();
        runner.setVirtualQuery(virtualQuery.clone());
        runner.setDatabaseConfiguration(configuration);
    }
    
    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }
}