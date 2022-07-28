
import com.sourceforge.snap7.moka7.S7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class About extends JFrame {
    JFrame frame = new JFrame();
    boolean visible=false;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    public About() {

        frame.setBounds(100, 100, 350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("About");
        frame.getContentPane().setLayout(null);


        JLabel Aboutlabel = new JLabel("About program");
        Aboutlabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        Aboutlabel.setBounds(110, 0, 350, 40);
        frame.getContentPane().add(Aboutlabel);

        JLabel textLabel1 = new JLabel("This program allow to connect , write and read data ");
        textLabel1.setFont(new Font("Tahoma", Font.BOLD, 10));
        textLabel1.setBounds(10, 30, 350, 40);
        frame.getContentPane().add(textLabel1);

        JLabel textLabel2 = new JLabel(" from plc siemens s7 1200. By clik on scope button you can");
        textLabel2.setFont(new Font("Tahoma", Font.BOLD, 10));
        textLabel2.setBounds(10, 50, 350, 40);
        frame.getContentPane().add(textLabel2);

        JLabel textLabel3 = new JLabel(" see all data on scope.");
        textLabel3.setFont(new Font("Tahoma", Font.BOLD, 10));
        textLabel3.setBounds(10, 70, 350, 40);
        frame.getContentPane().add(textLabel3);

        JButton okButton = new JButton();
        okButton.setBounds(120, 130,90 ,20);
        okButton.setText("ok");

        frame.getContentPane().add(okButton);


        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


              frame.hide();
            }
        });


        frame.setVisible(visible);
    }
    public void setVISIBLE(){

        frame.setVisible(visible);
    }
    public static void main(String[] args) {
        About ab = new About();
    }
}