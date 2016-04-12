package yourfrog.jump.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import yourfrog.jump.resultTabbedPane.CrossTableCellRenderer;
import yourfrog.jump.resultTabbedPane.ResultTabbedPane;
import yourfrog.jump.resultTabbedPane.ResultTable;

/**
 * @author YourFrog
 */
public class VirtualQueryRunner 
{
    /**
     *  Zapytanie ktore zostanie wykonane
     */
    VirtualQuery virtualQuery;
    
    /**
     *  Konfiguracja bazy danych na ktorej zostanie wykonane zapytanie
     */
    Configuration configuration;

    /**
     *  Polaczenie z baza danych
     */
    Connection connection;
    
    public void setVirtualQuery(VirtualQuery virtualQuery) {
        this.virtualQuery = virtualQuery;
    }

    public void setDatabaseConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public VirtualQuery getVirtualQuery() {
        return virtualQuery;
    }
    
    public ResultTable getResult(ResultTabbedPane tabbedPane) throws Exception {        
        DefaultTableModel model = new DefaultTableModel();
        ResultTable table = new ResultTable(this, model, tabbedPane, null);
        table.setShowGrid(true);
        table.setCellSelectionEnabled(true);

        getResult(table);
        
        return table;
    }
    
    public void getResult(ResultTable table) throws Exception {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
               
        initialize();

        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(virtualQuery.getParseQuery());

        ResultSetMetaData metaData = rs.getMetaData();

        prepareColumn(metaData, table);
        while( rs.next() ) {
            addRow(rs, model);
        }
        connection.close();
        connection = null;
    }
    
    protected void prepareColumn(ResultSetMetaData metaData, JTable table) throws SQLException {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableColumnModel columnModel = table.getColumnModel();
        
        int cc = metaData.getColumnCount();
        for (int i = 1; i <= cc; i++ ) {
            String columnName = metaData.getColumnName(i);
            model.addColumn(columnName);
        }
        
        for(int i = 0; i < cc; i++) {
            TableColumn column = columnModel.getColumn(i);
            
            column.setCellRenderer(new CrossTableCellRenderer());
            column.setMinWidth(175);
        }
    }
    
    protected void addRow(ResultSet resultSet, DefaultTableModel model) throws SQLException {
        int cc = resultSet.getMetaData().getColumnCount();

        Object[] row = new Object[cc];
        for(int i = 1; i < cc; i++) {
            row[i - 1] = resultSet.getString(i);
        }

        model.addRow(row);
    }
    
    protected void initialize()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch(Exception e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }
        
        connection = null;
	try {
            connection = DriverManager.getConnection("jdbc:mysql://mysql.senuto.com:3306/audits","audyt", "tAXEJIzE8O");
	} catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
	}

	if( connection == null ) {
            System.out.println("Failed to make connection!");
	}
    }
}