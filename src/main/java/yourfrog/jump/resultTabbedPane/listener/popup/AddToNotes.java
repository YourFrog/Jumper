package yourfrog.jump.resultTabbedPane.listener.popup;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTextArea;
import yourfrog.jump.resultTabbedPane.ResultTable;

/**
 * @author YourFrog
 */
public class AddToNotes implements MouseListener
{
    ResultTable table;

    public AddToNotes(ResultTable table) {
        this.table = table;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        HashMap<String, ArrayList<String>> values = table.getSelectionValues();
        int columnIndex = table.getSelectedColumn();
        String header = (String) table.getTableHeader().getColumnModel().getColumn(columnIndex).getHeaderValue();
        
        JTextArea notes = table.getNotes();
        
        if( values.size() == 1 ) {
            values.forEach((k, v) -> {
                notes.append(k + " => " + String.join(", ", v) + "\n");
            });
        } else {
            ArrayList<ArrayList<String>> rowMap = new ArrayList();
            
            for(Map.Entry<String, ArrayList<String>> entry : values.entrySet()) {
                for(int i = 0; i < entry.getValue().size(); i++) {
                    if( rowMap.size() == i ) {
                        rowMap.add(new ArrayList());
                    }
                    
                    rowMap.get(i).add(entry.getKey() + " => " + entry.getValue().get(i));
                }
            }
            
            rowMap.forEach((v) -> {
                notes.append(String.join(", ", v) + "\n");
            });
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }
}
