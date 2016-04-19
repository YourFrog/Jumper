package yourfrog.jump;

import java.awt.Font;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import yourfrog.jump.db.VirtualQuery;

/**
 *
 * @author p.stelmasiak
 */
public class QueryInformationPanel extends JSplitPane {
    private VirtualQuery query;
    
    public QueryInformationPanel(VirtualQuery query) {
        this.query = query;
        
        prepareLeftComponent();
        prepareRightComponent();
        
        setOrientation(HORIZONTAL_SPLIT);
        setDividerLocation(350);
    }
    
    private void prepareLeftComponent() {
        String text = "";
        text += "<b>Nazwa:</b> <br>" + query.getDisplayName() + "<br><br>";
        text += "<b>Opis:</b> <br>" + query.getDescription() + "<br><br>";
        
        JEditorPane basicInformation = new JEditorPane();
        basicInformation.setFont(new Font("Courier New", Font.PLAIN, 11));
        basicInformation.setContentType("text/html");
        basicInformation.setText(text);
        
        JScrollPane scrollBasicInformation = new JScrollPane(basicInformation);
        setLeftComponent(scrollBasicInformation);
    }
    
    private void prepareRightComponent() {
        JEditorPane editorPane = new JEditorPane();
        JScrollPane scrollEditorPane = new JScrollPane(editorPane);
        
        editorPane.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        editorPane.setContentType("text/sql");
        editorPane.setText(query.getQuery());
        
        setRightComponent(scrollEditorPane);
    }
}
