import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.sourceforge.snap7.moka7.*;


public class GUI  {
public static final S7Client Client = new S7Client();
public static byte[] Buffer = new byte[65536];
    public static boolean[] boolBuffer= new boolean[65536];
    public GUI() {
        initialize();
    }

    private void initialize() {

        plcS71200 plc1200 = new plcS71200(Client,false);
        dataReadWrite readWrite = new dataReadWrite(Client,false);
        About about = new About();
        Thread t1 =new Thread(plc1200);   // Using the constructor Thread(Runnable r)
        Thread t2 =new Thread(readWrite );

        int firstCol=30,secondCol = 120 , thirdCol = 250,fourthCol=380;
        int fieldSizeH = 25,fieldSizeL = 120;

        JFrame frame = new JFrame();
        frame.setBounds(100, 100, 450, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("PLC SIEMENS S7 1200");
        frame.getContentPane().setLayout(null);

try {
    BufferedImage myPicture = ImageIO.read(new File("1211c.jpg"));

    JLabel picLabel = new JLabel(new ImageIcon(myPicture));
    picLabel.setBounds(20, 160, 230, 230);
    frame.getContentPane().add(picLabel);
}catch(IOException e){}



        JLabel label = new JLabel("PLC ");
        label.setFont(new Font("Tahoma", Font.BOLD, 15));
        label.setBounds(30, 0, 150, 40);
        frame.getContentPane().add(label);


        JLabel labelIPAddress = new JLabel("Ip Address:");
        labelIPAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
        labelIPAddress.setBounds(firstCol, 30, fieldSizeL ,fieldSizeH);
        frame.getContentPane().add(labelIPAddress);

        JTextField IPAddressText = new JTextField();
        IPAddressText.setBounds(secondCol, 30, fieldSizeL ,fieldSizeH);
        IPAddressText.setText("192.168.0.1");
        frame.getContentPane().add(IPAddressText);
        IPAddressText.setColumns(10);


        JLabel labelRack = new JLabel("Rack:");
        labelRack.setFont(new Font("Tahoma", Font.PLAIN, 12));
        labelRack.setBounds(firstCol, 60, fieldSizeL ,fieldSizeH);
        frame.getContentPane().add(labelRack);

        JTextField textRack = new JTextField();
        textRack.setBounds(secondCol, 60, fieldSizeL ,fieldSizeH);
        textRack.setText("0");
        frame.getContentPane().add(textRack);
        textRack.setColumns(10);


        JLabel labelSlot = new JLabel("Slot:");
        labelSlot.setFont(new Font("Tahoma", Font.PLAIN, 12));
        labelSlot.setBounds(firstCol, 90, fieldSizeL ,fieldSizeH);
        frame.getContentPane().add(labelSlot);

        JTextField textSlot = new JTextField();
        textSlot.setBounds(secondCol, 90, fieldSizeL ,fieldSizeH);
        textSlot.setText("1");
        frame.getContentPane().add(textSlot);
        textSlot.setColumns(10);


        JLabel labelStatus = new JLabel("labelStatus :");
        labelStatus .setFont(new Font("Tahoma", Font.PLAIN, 12));
        labelStatus .setBounds(firstCol, 120, fieldSizeL ,fieldSizeH);
        frame.getContentPane().add(labelStatus );

        JTextField textStatus = new JTextField();
        textStatus.setBounds(secondCol, 120, fieldSizeL ,fieldSizeH);
        frame.getContentPane().add(textStatus);
        textStatus.setColumns(10);



        JButton connectButton = new JButton();
        connectButton.setBounds(thirdCol, 30,fieldSizeL ,fieldSizeH);
        connectButton.setText("connect");
        frame.getContentPane().add(connectButton);

        JButton disconnectButton = new JButton();
        disconnectButton.setBounds(thirdCol, 60,fieldSizeL ,fieldSizeH);
        disconnectButton.setText("disconnect");
        frame.getContentPane().add(disconnectButton);


        JButton plcStopButton = new JButton();
        plcStopButton .setBounds(thirdCol, 90,fieldSizeL ,fieldSizeH);
        plcStopButton.setText("PLC stop");
        frame.getContentPane().add(plcStopButton );

        JButton plcStartButton  = new JButton();
        plcStartButton.setBounds(thirdCol, 120,fieldSizeL ,fieldSizeH);
        plcStartButton.setText("plc Start");
        frame.getContentPane().add(plcStartButton);

        JButton plcDBINFOButton  = new JButton();
        plcDBINFOButton .setBounds(thirdCol, 150,fieldSizeL ,fieldSizeH);
        plcDBINFOButton .setText("DB INFO");
        frame.getContentPane().add(plcDBINFOButton );



        JButton ReadWriteButton  = new JButton();
        ReadWriteButton.setBounds(thirdCol, 210,fieldSizeL ,fieldSizeH);
        ReadWriteButton.setText("READ/WRITE");
        frame.getContentPane().add(ReadWriteButton);

        JButton GetPlcStatus  = new JButton();
        GetPlcStatus .setBounds(thirdCol, 180,fieldSizeL ,fieldSizeH);
        GetPlcStatus .setText("PLC STATUS ");
        frame.getContentPane().add( GetPlcStatus );

        JButton S7200Button = new JButton();
        S7200Button.setBounds(thirdCol, 240,fieldSizeL ,fieldSizeH);
        S7200Button .setText(" S7 1200 ");
        frame.getContentPane().add( S7200Button );

        JButton About = new JButton();
        About.setBounds(thirdCol, 270,fieldSizeL ,fieldSizeH);
        About .setText(" About ");
        frame.getContentPane().add(About );

        About.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {



                boolean ab =about.isVisible();
                if(ab)
                {System.out.println("ab = true");
                    about.setVisible(false);
                    about.setVISIBLE();

                }else{
                    System.out.println("ab = false");
                    about.setVisible(true);
                    about.setVISIBLE();

                }

            }
        });

        S7200Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (Client.Connected)
                {
                System.out.println("connected");
                boolean got = plc1200.isVisible();
                    if(got)
                    {System.out.println("got = true");
                        plc1200.setVisible(false);
                        plc1200.setVISIBLE();
                        if(t1.isAlive()) {
                            try {
                                t1.wait();
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }else{
                        System.out.println("got = false");
                        plc1200.setVisible(true);
                        plc1200.setVISIBLE();
                        t1.start();
                    }

                   // plcS71200 plc1200 = new plcS71200(GUI.Client);




                }

            }
        });

        GetPlcStatus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (Client.Connected)
                {IntByRef Status = new IntByRef();
                    Client.GetPlcStatus(Status);
                   System.out.println(Status);
                }

            }
        });

        plcDBINFOButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (Client.Connected)
                {
                    S7BlockInfo sie=new S7BlockInfo();


                    Client.GetAgBlockInfo(S7.Block_DB,3,sie);

                    System.out.println(sie.CodeDate());
                    System.out.println(sie.Author());
                    System.out.println(sie.Header());
                    System.out.println(sie.Version());
                    System.out.println(sie.LocalData());
                    System.out.println(sie.SBBLength());
                    System.out.println(sie.MC7Size());
                    System.out.println(sie.BlkLang());

                    System.out.println("sie");
                    textStatus.setText(" PLC stoped");
                }

            }
        });

        plcStopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (Client.Connected)
                {
                    S7BlockInfo sie=new S7BlockInfo();
                  Client.PlcStop();

                    textStatus.setText(" PLC stoped");
                }

            }
        });

        plcStartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (Client.Connected)
                {
                    Client.PlcColdStart();
                    textStatus.setText(" PLC started");
                }

            }
        });


        disconnectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Client.Disconnect();
                if (!Client.Connected)
                {

                    textStatus.setText(" PLC DISCONNECTED");
                }

            }
        });




        ReadWriteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                {
                    // S7.SetBitAt(Buffer, 0, 0,false);
                    // S7.SetBitAt(Buffer,0, 1,false);
                    // S7.SetBitAt(Buffer, 0, 2,false);
                    // S7.SetBitAt(Buffer,0, 3,false);

                    // Client.WriteArea(S7.S7AreaDB, Integer.parseInt(textDBNumer.getText()), 0, 4, Buffer);
                    if (Client.Connected) {
                        System.out.println("connected");
                        boolean rw = readWrite.isVisible();
                        if (rw) {
                            System.out.println("rw = true");
                            readWrite.setVisible(false);
                            readWrite.setVISIBLE();
                            if (t2.isAlive()) {
                                try {
                                    t2.wait();
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            System.out.println("got = false");
                            readWrite.setVisible(true);
                            readWrite.setVISIBLE();
                            t2.start();
                        }
                    }
                }
            }
        });


        connectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                Client.SetConnectionType(S7.OP);
                Client.ConnectTo(IPAddressText.getText(),Integer.parseInt(textRack.getText()),Integer.parseInt(textSlot.getText()));
                Client.Connect();
                if (Client.Connected)
                {

                    textStatus.setText(" PLC CONNECTED");
                }
            }
        });

        frame.setVisible(true);
    }


    public static void main(String[] args) {

        GUI gui = new GUI();

        System.out.println("kjsahkjadshjkdsfhjkadsfh");

    }
}