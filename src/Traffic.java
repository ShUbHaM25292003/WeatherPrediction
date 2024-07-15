import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Traffic extends JFrame {
    public Traffic() {
        //set gui title
        super("Traffic Info");


        //set size of gui
        setSize(350,300);

        setLocationRelativeTo(null);

        //set layout manager null to manually position our gui
        setLayout(null);

        //prevent any resize of gui
        setResizable(false);

        addGuiComponents();
    }

    private void addGuiComponents() {

        JTextField searchField = new JTextField();

        searchField.setBounds(15,15,250,40);

        searchField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(searchField);


        JButton search = new JButton(loadImage("src/assets/search.png"));
        search.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        search.setBounds(275,15,47,45);
        add(search);

        JTextArea result = new JTextArea();
        result.setBounds(15, 60, 309, 190);
        add(result);

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String city = searchField.getText();
                if ("Mumbai".equalsIgnoreCase(city)) {
                    result.setText("Moderate");
                } else if ("Sion".equalsIgnoreCase(city)) {
                    result.setText("Moderate");
                } else if ("Delhi".equalsIgnoreCase(city)) {
                    result.setText("Heavy");
                }else {
                    result.setText("NO INFO FOUND");
                }
            }

        });

    }

    private ImageIcon loadImage(String resourcePath){
        try{
            // read the image file from the path given
            BufferedImage image = ImageIO.read(new File(resourcePath));

            // returns an image icon so that our component can render it
            return new ImageIcon(image);
        }catch(IOException e){
            e.printStackTrace();
        }

        System.out.println("Could not find resource");
        return null;
    }
}
