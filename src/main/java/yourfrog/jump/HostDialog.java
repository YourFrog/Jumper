package yourfrog.jump;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import yourfrog.jump.db.Configuration;

/**
 * @author YourFrog
 */
public class HostDialog extends CentreJDialog
{
    private Configuration configuration;
    
    private JTextArea name;
    
    private JComboBox databaseComboBox;
    private JLabel databaseComboBoxLabel;
    
    private JTextArea dbname;
    
    private JTextArea host;
    private JTextArea port;
   
    private JTextArea username;
    private JTextArea password;
    
    private JLabel nameLabel;
    
    private JLabel dbnameLabel;
    
    private JLabel hostLabel;
    private JLabel portLabel;
    
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    
    private JButton submit;
    
    private JButton cancel;
    
    private boolean isCancel = true;
    
    public HostDialog(Configuration conf) {
        this();
        
        this.configuration = conf;
        setTitle("Edycja hosta " + configuration.getDisplayName());
        
        name.setText(configuration.getDisplayName());
        dbname.setText(configuration.getDbname());
        
        username.setText(configuration.getUsername());
        password.setText(configuration.getPassword());
        
        host.setText(configuration.getHost());
        port.setText("" + configuration.getPort());
        
        switch(configuration.getDatabaseType()) {
            case MySQL:
                    databaseComboBox.setSelectedIndex(0);
                break;
            case POSTGRESQL:
                    databaseComboBox.setSelectedIndex(1);
                break;
        }
    }
    
    public HostDialog() {
        setTitle("Dodanie nowego hosta");
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(245, 280));
                
        name = new JTextArea(1, 16);
        nameLabel = new JLabel("Nazwa: ");
        add(nameLabel);
        add(name);
        
        databaseComboBox = new JComboBox(new String[] {"MySQL", "Postgresql"});
        databaseComboBoxLabel = new JLabel("Baza danych: ");
        add(databaseComboBox);
        add(databaseComboBoxLabel);
        
        host = new JTextArea(1, 16);
        port = new JTextArea(1, 8);
        hostLabel = new JLabel("Host: ");
        portLabel = new JLabel("Port: ");
        add(host);
        add(hostLabel);
        add(port);
        add(portLabel);
        
        dbname = new JTextArea(1, 12);
        dbnameLabel = new JLabel("Baza danych: ");
        add(dbname);
        add(dbnameLabel);
        
        username = new JTextArea(1, 12);
        password = new JTextArea(1, 12);
        usernameLabel = new JLabel("DB Username: ");
        passwordLabel = new JLabel("DB Password: ");
        add(username);
        add(usernameLabel);
        add(password);
        add(passwordLabel);
        
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
        
        setLayout();
        
        pack();
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        centreLocation();
    }
    
    private void setLayout() {
        SpringLayout layout = new SpringLayout();
        layout.putConstraint(SpringLayout.WEST, nameLabel, 5, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 10, SpringLayout.NORTH, getContentPane());
        
        layout.putConstraint(SpringLayout.WEST, name, 50, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, name, 5, SpringLayout.NORTH, getContentPane());
        
        layout.putConstraint(SpringLayout.WEST, databaseComboBoxLabel, 5, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, databaseComboBoxLabel, 40, SpringLayout.NORTH, getContentPane());
        
        layout.putConstraint(SpringLayout.WEST, databaseComboBox, 145, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, databaseComboBox, 35, SpringLayout.NORTH, getContentPane());
                
        layout.putConstraint(SpringLayout.WEST, hostLabel, 5, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, hostLabel, 70, SpringLayout.NORTH, getContentPane());
        
        layout.putConstraint(SpringLayout.WEST, host, 50, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, host, 65, SpringLayout.NORTH, getContentPane());
        
        layout.putConstraint(SpringLayout.WEST, portLabel, 5, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, portLabel, 100, SpringLayout.NORTH, getContentPane());
        
        layout.putConstraint(SpringLayout.WEST, port, 50, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, port, 95, SpringLayout.NORTH, getContentPane());
        
        layout.putConstraint(SpringLayout.WEST, dbnameLabel, 5, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, dbnameLabel, 130, SpringLayout.NORTH, getContentPane());
        
        layout.putConstraint(SpringLayout.WEST, dbname, 93, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, dbname, 125, SpringLayout.NORTH, getContentPane());
                        
        layout.putConstraint(SpringLayout.WEST, usernameLabel, 5, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, usernameLabel, 160, SpringLayout.NORTH, getContentPane());
        
        layout.putConstraint(SpringLayout.WEST, username, 93, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, username, 155, SpringLayout.NORTH, getContentPane());
        
        layout.putConstraint(SpringLayout.WEST, passwordLabel, 5, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, passwordLabel, 190, SpringLayout.NORTH, getContentPane());
        
        layout.putConstraint(SpringLayout.WEST, password, 93, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, password, 185, SpringLayout.NORTH, getContentPane());

        layout.putConstraint(SpringLayout.WEST, submit, 73, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, submit, 220, SpringLayout.NORTH, getContentPane());
        
        layout.putConstraint(SpringLayout.WEST, cancel, 143, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, cancel, 220, SpringLayout.NORTH, getContentPane());
        
        setLayout(layout);
    }
    
    public Configuration getConfiguration() {
        if( configuration == null ) {
            configuration = new Configuration();
        }
        
        configuration.setDisplayName(name.getText());
        
        try {
            int portNumber = Integer.parseInt(port.getText());
            configuration.setPort(portNumber);
        } catch(Exception e) { }
    
        configuration.setHost(host.getText());

        configuration.setDbname(dbname.getText());
        
        configuration.setUsername(username.getText());
        configuration.setPassword(password.getText());
        
        if( databaseComboBox.getSelectedIndex() == 0 ) {
            configuration.setDatabaseType(Configuration.DatabaseType.MySQL);
        }
        
        if( databaseComboBox.getSelectedIndex() == 1 ) {
            configuration.setDatabaseType(Configuration.DatabaseType.POSTGRESQL);
        }
        
        return configuration;
    }
    
    public boolean isCancel() {
        return isCancel;
    }
}
