package yourfrog.jump;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *  Okienko dialogowe obsługujące parametry zapytania
 */
public class QueryParametrDialog extends CentreJDialog
{
    private static final long serialVersionUID = 1L;
    
    private JTable table;
    
    public QueryParametrDialog(String[][] data, String[] columnNames) {
        setTitle("Podaj wartości parametrów");
        
        table = new JTable(data, columnNames);
        
        setLocationRelativeTo(null);
       
        preparePanel();

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setPreferredSize(new Dimension(450, 200));
        pack();
        
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        centreLocation();
    }
    
    private void preparePanel() {
        GridLayout l = new GridLayout(2, 1);

        JPanel panel = new JPanel();
        
        JButton button = new JButton("Zamknij");
        button.setPreferredSize(new Dimension(75, 25));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });

        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);     
        scrollPane.setPreferredSize(new Dimension(420, 130));
        
        panel.add(scrollPane);
        panel.add(button);
        
        add(panel);        
    }
    
    public String[] getParamValues(int columnIndex) {
        int rowCount = table.getRowCount();
        
        String[] result = new String[rowCount];
        
        for(int i = 0; i < rowCount; i++) {
            result[i] = (String) table.getValueAt(i, columnIndex - 1);
        }
        
        return result;
    }
}
