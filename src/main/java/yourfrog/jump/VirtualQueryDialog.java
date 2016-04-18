package yourfrog.jump;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;
import jsyntaxpane.DefaultSyntaxKit;
import yourfrog.jump.db.VirtualParametr;
import yourfrog.jump.db.VirtualQuery;

/**
 * @author YourFrog
 */
public class VirtualQueryDialog extends CentreJDialog
{
    private class JTableComboBoxColumn extends DefaultCellEditor {
        private DefaultComboBoxModel model;

        public JTableComboBoxColumn(String[] values) {
            super(new JComboBox(values));
            this.model = (DefaultComboBoxModel)((JComboBox)getComponent()).getModel();
        }
 
    }
    
    private VirtualQuery virtualQuery;
    
    private JTextField name;
    
    private JEditorPane query;
    
    private JTextArea description;
    
    private JScrollPane scrollParams;
    
    private JTable params;
    
    private JButton paramsAdd;
    
    private JButton paramsDelete;
    
    private JLabel nameLabel;
    
    private JLabel queryLabel;
    
    private JLabel descriptionLabel;
    
    private JLabel paramsLabel;
    
    private JScrollPane scrPane;
    
    private JButton submit;
    
    private JButton cancel;
    
    private boolean isCancel = true;
    
    public VirtualQueryDialog(VirtualQuery virtualQuery) {
        this();
       
        this.virtualQuery = virtualQuery;
       
        name.setText(virtualQuery.getDisplayName());
        description.setText(virtualQuery.getDescription());
        query.setText(virtualQuery.getQuery());
       
        DefaultTableModel model = (DefaultTableModel) params.getModel();
        int paramCount = virtualQuery.getParamCount();
        for(int i = 0; i < paramCount; i++) {
            VirtualParametr p = virtualQuery.getParam(i);
       
            model.addRow(new Object[] {p.getKeyName(), p.getType(), p.isRequired() ? "TAK" : "NIE", p.getValue()});
        }
        model.fireTableDataChanged();
       
        setTitle("Edycja zapytania " + virtualQuery.getDisplayName());
    }
    
    public VirtualQueryDialog() {
        name = new JTextField("", 31);
        add(name);
        
        description = new JTextArea(3, 35);
        add(description);
        
        DefaultSyntaxKit.initKit();
        
        query = new JEditorPane();
        
        scrPane = new JScrollPane(query);
        scrPane.setPreferredSize(new Dimension(398, 410));
        add(scrPane);
                
        query.setFont(description.getFont());
        query.setContentType("text/sql");

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[] {"Nazwa", "Typ", "Wymagalny", "Wartość"});
        
        paramsAdd = new JButton("Dodaj");
        paramsDelete = new JButton("Usuń");
        params = new JTable();
        params.setModel(model);
        params.getColumnModel().getColumn(1).setCellEditor(new JTableComboBoxColumn(new String[] {"Integer", "String"}));
        params.getColumnModel().getColumn(2).setCellEditor(new JTableComboBoxColumn(new String[] {"TAK", "NIE"}));
        
        scrollParams = new JScrollPane(params);
        scrollParams.setPreferredSize(new Dimension(370, 200));
        paramsLabel = new JLabel("Parametry");
        add(scrollParams);
        add(paramsLabel);
        add(paramsAdd);
        add(paramsDelete);
        
        paramsAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                DefaultTableModel model = (DefaultTableModel) params.getModel();
                model.addRow(new Object[] {"", "Integer", "NIE", "NULL"});
                model.fireTableDataChanged();
            }
        });
        
        paramsDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int index = params.getSelectedRow();
                
                if( index == -1 ) {
                    return;
                }
                
                DefaultTableModel model = (DefaultTableModel) params.getModel();
                model.removeRow(index);
                model.fireTableDataChanged();
            }
        });
        
        nameLabel = new JLabel("Nazwa:");
        add(nameLabel);
        
        queryLabel = new JLabel("Zapytanie:");
        add(queryLabel);
        
        descriptionLabel = new JLabel("Opis:");
        add(descriptionLabel);
        
        setTitle("Dodanie nowego zapytania");
        
        submit = new JButton("Zapisz");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                isCancel = false;
                dispose();
            }
        });
        add(submit);
        
        cancel = new JButton("Anuluj");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                isCancel = true;
                dispose();
            }
        });
        add(cancel);
        
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(815, 630));
        setLayout();
        pack();
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        centreLocation();
    }
    
    private void setPositionComponent(SpringLayout layout, Component component, int x, int y) {        
        layout.putConstraint(SpringLayout.WEST, component, x, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, component, y, SpringLayout.NORTH, getContentPane());
    }
    
    private void setLayout() {
        SpringLayout layout = new SpringLayout();
        
        setPositionComponent(layout, nameLabel, 5, 10);
        setPositionComponent(layout, name, 50, 5);
        
        setPositionComponent(layout, paramsLabel, 420, 10);
        setPositionComponent(layout, scrollParams, 420, 30);
        setPositionComponent(layout, paramsAdd, 720, 235);
        setPositionComponent(layout, paramsDelete, 650, 235);
        
        setPositionComponent(layout, description, 5, 65);
        setPositionComponent(layout, descriptionLabel, 5, 40);
        
        setPositionComponent(layout, queryLabel, 5, 130);
        setPositionComponent(layout, scrPane, 5, 155);
        
        setPositionComponent(layout, submit, 273, 570);
        setPositionComponent(layout, cancel, 343, 570);
        
        setLayout(layout);
    }

    public VirtualQuery getVirtualQuery() {
        if( virtualQuery == null ) {
            virtualQuery = new VirtualQuery();
        }
        
        virtualQuery.setDescription(description.getText());
        virtualQuery.setDisplayName(name.getText());
        virtualQuery.setQuery(query.getText());
        virtualQuery.clearParams();
        
        int rowCount = params.getModel().getRowCount();        
        for(int i = 0; i < rowCount; i++) {
            String pName = (String) params.getModel().getValueAt(i, 0);
            String pType = (((String) params.getModel().getValueAt(i, 1)).equals("TAK") ? "Integer" : "String");
            boolean pRequire = (((String) params.getModel().getValueAt(i, 2)).equals("TAK") ? true : false);
            String pValue = (String) params.getModel().getValueAt(i, 3);
            
            VirtualParametr p = new VirtualParametr(pName, pRequire, pType, pValue);
            virtualQuery.addParam(p);
        }
        return virtualQuery;
    }
    
    public boolean isCancel() {
        return isCancel;
    }
}
