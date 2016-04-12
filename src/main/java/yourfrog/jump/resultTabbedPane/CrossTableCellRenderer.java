package yourfrog.jump.resultTabbedPane;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * @author YourFrog
 */
public class CrossTableCellRenderer extends DefaultTableCellRenderer {
    
    JTable table;
    
    int column;
    
    int row;
    
    boolean isSelected;
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        this.table = table;
        this.row = row;
        this.column = column;
        this.isSelected = isSelected;
        
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        repaintColumns();
        cellComponent.setBackground(getColor());
                
        return cellComponent;
    }
    
    private Color getColor() {
        if( isSelected ) {
            return new Color(59, 115, 232);
        }
        
        if( row == table.getSelectedRow() || column == table.getSelectedColumn() ) {
            return new Color(224, 241, 255);
        }
        
        return null;
    }
    
    private void repaintColumns() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for(int i = 0; i < model.getRowCount(); i++) {
            model.fireTableCellUpdated(i, table.getSelectedColumn());
        }
    }
}