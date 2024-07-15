import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class GlobalWarming extends JFrame {
    public GlobalWarming() {
        super("Global Warming");

        setBounds(350,300, 696,400);

        setLocationRelativeTo(null);

        //set layout manager null to manually position our gui
        setLayout(null);

        //prevent any resize of gui
        setResizable(false);

        addGuiComponents();
    }

    public void addGuiComponents() {

        JLabel label = new JLabel("GLOBAL WARMING");
        label.setFont(new Font("Stencil", Font.BOLD, 28));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(225, 11, 250, 24);
        add(label);

        JLabel srno = new JLabel("SrNo");
        srno.setHorizontalAlignment(SwingConstants.CENTER);
        srno.setBounds(70, 96, 89, 14);
        getContentPane().add(srno);

        JLabel year = new JLabel("Year");
        year.setHorizontalAlignment(SwingConstants.CENTER);
        year.setBounds(235, 96, 66, 14);
        getContentPane().add(year);

        JLabel gwpercentage = new JLabel("GWPercentage");
        gwpercentage.setHorizontalAlignment(SwingConstants.CENTER);
        gwpercentage.setBounds(365, 96, 99, 14);
        getContentPane().add(gwpercentage);

        JLabel heatwave = new JLabel("HeatWave");
        heatwave.setHorizontalAlignment(SwingConstants.CENTER);
        heatwave.setBounds(530, 96, 66, 14);
        getContentPane().add(heatwave);


        JTable table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "SrNo", "Year", "GWPercentage", "HeatWave"
                }
        ));
        table.setBounds(48, 121, 590, 225);
        add(table);


        JButton display = new JButton("Display Data");
        display.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/globalwarming","root","shubham2003");
                    Statement st = connection.createStatement();
                    String query = "SELECT * FROM gwdata";
                    ResultSet rs = st.executeQuery(query);
                    ResultSetMetaData rsmd = rs.getMetaData();
                    DefaultTableModel model = (DefaultTableModel) table.getModel();

                    int cols = rsmd.getColumnCount();
                    String[] colName = new String[cols];
                    for (int i = 0; i < cols; i++)
                        colName[i] = rsmd.getColumnName(i + 1);
                    model.setColumnIdentifiers(colName);


                    while (rs.next()) {
                        String no, year, gwp, hw;
                        no = rs.getString(1);
                        year = rs.getString(2);
                        gwp = rs.getString(3);
                        hw = rs.getString(4);

                        String[] row = {no, year, gwp, hw};
                        model.addRow(row);
                    }

                    st.close();
                    connection.close();

                } catch (ClassNotFoundException |SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        display.setFont(new Font("Dialog", Font.BOLD, 20));
        display.setBounds(259, 46, 154, 37);
        display.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(display);

    }
}
