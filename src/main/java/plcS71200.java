
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.sourceforge.snap7.moka7.*;


public class plcS71200 implements Runnable {
    public static byte[] BitBuffer = new byte[65536];
    public static byte[] BitSendBuffer = new byte[65536];
    public static byte[] WordBuffer = new byte[65536];
    public  S7Client Client;
    JFrame frame = new JFrame();
    private boolean ReadBit=false;
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    boolean visible=true;


    public plcS71200(S7Client Client,boolean visible) {
       this.Client=Client;
       this.visible = visible;
        initialize();

    }

   // runingData runing = new runingData(  Client );
   // Thread t2 =new Thread(runing);


    JCheckBox I00 = new JCheckBox();
    JCheckBox I01 = new JCheckBox();
    JCheckBox I02 = new JCheckBox();
    JCheckBox I03 = new JCheckBox();
    JCheckBox I04 = new JCheckBox();
    JCheckBox I05 = new JCheckBox();


    JCheckBox O00 = new JCheckBox();
    JCheckBox O01 = new JCheckBox();
    JCheckBox O02 = new JCheckBox();
    JCheckBox O03 = new JCheckBox();


    JTextField DBNumer = new JTextField();
    JTextField DBWord = new JTextField();
    JTextField statusWord = new JTextField();
    @Override
    public void run() {
        while (true) {
            if (Client.Connected) {
               // System.out.println("READING...");


                Client.ReadArea(S7.S7AreaPE, 3, 0, 1, BitBuffer);

                if (S7.GetBitAt(BitBuffer,0,0  )) {

                    I00.setBackground(Color.YELLOW);

                } else {

                    I00.setBackground(Color.blue);

                }
                if (S7.GetBitAt( BitBuffer,0,1   )) {

                    I01.setBackground(Color.YELLOW);
                    //  System.out.println( "001 Yellow");
                } else {

                    I01.setBackground(Color.blue);

                }

                if (S7.GetBitAt( BitBuffer,0,2   )) {

                   I02.setBackground(Color.YELLOW);
                    // System.out.println( "002 Yellow");
                } else {

                    I02.setBackground(Color.blue);

                }
                if (S7.GetBitAt( BitBuffer,0,3   )) {

                    I03.setBackground(Color.YELLOW);
                    //System.out.println( "003 Yellow");
                } else {

                    I03.setBackground(Color.blue);

                }

                if (S7.GetBitAt( BitBuffer,0,4   )) {

                    I04.setBackground(Color.YELLOW);
                    // System.out.println( "002 Yellow");
                } else {

                    I04.setBackground(Color.blue);

                }
                if (S7.GetBitAt( BitBuffer,0,5   )) {

                    I05.setBackground(Color.YELLOW);
                    //System.out.println( "003 Yellow");
                } else {

                    I05.setBackground(Color.blue);

                }
                Client.ReadArea(S7.S7AreaPA, 3, 0, 1, BitBuffer);



              // System.out.println(S7.GetBitAt(GUI.Buffer,0,0   ));
               //System.out.println(S7.GetBitAt( BitBuffer,0,1  ));
               // System.out.println(S7.GetBitAt( BitBuffer,0,2   ));
                //System.out.println(S7.GetBitAt( BitBuffer,0,3   ));



                if (S7.GetBitAt(BitBuffer,0,0  )) {

                    O00.setBackground(Color.YELLOW);

                } else {

                    O00.setBackground(Color.blue);

                }
                if (S7.GetBitAt( BitBuffer,0,1   )) {

                    O01.setBackground(Color.YELLOW);
                  //  System.out.println( "001 Yellow");
                } else {

                   O01.setBackground(Color.blue);

                }

                if (S7.GetBitAt( BitBuffer,0,2   )) {

                    O02.setBackground(Color.YELLOW);
                   // System.out.println( "002 Yellow");
                } else {

                    O02.setBackground(Color.blue);

                }
                if (S7.GetBitAt( BitBuffer,0,3   )) {

                    O03.setBackground(Color.YELLOW);
                   //System.out.println( "003 Yellow");
                } else {

                    O03.setBackground(Color.blue);

                }


            }

          //  System.out.print(BitBuffer);
          //  System.out.print(BitBuffer);
           // System.out.print(BitBuffer);
           // System.out.println(BitBuffer);

            if (ReadBit == true) {

                //Client.Connect();
                Client.ReadArea(S7.S7AreaDB,Integer.parseInt(DBNumer.getText()),0,2,WordBuffer);
               //  System.out.println(WordBuffer[0]);
              //  System.out.println(ReadBit);
                int readData = S7.GetWordAt(WordBuffer,Integer.parseInt(DBWord.getText()));
               // System.out.println(readData);
                statusWord.setText(Integer.toString(readData));
               // System.out.println( "READ BIT");
                printWord(WordBuffer);

            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException c) {
            }

        }
    }

    private void initialize() {

    //    Client.ReadArea(S7.S7AreaPA, 0, 0, 4, BitBuffer);
        int firstCol=30,secondCol = 60 , thirdCol = 90,fourthCol=120,fifthCol = 150, sixthCol = 180;
        int firstColB=30,secondColB = 120 , thirdColB = 180,fourthColB=120,fifthColB = 150, sixthColB = 180;
        int fieldSizeH = 25,fieldSizeL = 90;
        int controlH =15, controlL =15;
        int firstRow = 20,secondRow=50,fourthRow = 100;


        frame.setBounds(500, 100, 300, 300);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setTitle("PLC SIEMENS S7");
        frame.getContentPane().setLayout(null);


        I00.setBounds(firstCol,firstRow,controlH,controlL);
        I00.setBackground(Color.blue);
        I00.setOpaque(true);
        frame.getContentPane().add(I00);

        JButton I00B = new JButton();
        I00B.setBounds(firstCol, secondRow,controlH,controlL);
        frame.getContentPane().add(I00B );


        I01.setBounds(secondCol,firstRow,controlH,controlL);
        I01.setBackground(Color.blue);
        I01.setOpaque(true);
        frame.getContentPane().add(I01);

        JButton I01B = new JButton();
        I01B.setBounds(secondCol, secondRow,controlH,controlL);
        frame.getContentPane().add(I01B );


        I02.setBounds(thirdCol,firstRow,controlH,controlL);
        I02.setBackground(Color.blue);
        I02.setOpaque(true);
        frame.getContentPane().add(I02);

        JButton I02B = new JButton();
        I02B.setBounds(thirdCol, secondRow,controlH,controlL);
        frame.getContentPane().add(I02B );


        I03.setBounds(fourthCol,firstRow,controlH,controlL);
        I03.setBackground(Color.blue);
        I03.setOpaque(true);
        frame.getContentPane().add(I03);

        JButton I03B = new JButton();
        I03B.setBounds(fourthCol, secondRow,controlH,controlL);
        frame.getContentPane().add(I03B );


        I04.setBounds(fifthCol,firstRow,controlH,controlL);
        I04.setBackground(Color.blue);
        I04.setOpaque(true);
        frame.getContentPane().add(I04);

        JButton I04B = new JButton();
        I04B.setBounds(fifthCol, secondRow,controlH,controlL);
        frame.getContentPane().add(I04B );


        I05.setBounds(sixthCol,firstRow,controlH,controlL);
        I05.setBackground(Color.blue);
        I05.setOpaque(true);
        frame.getContentPane().add(I05);

        JButton I05B = new JButton();
        I05B.setBounds(sixthCol, secondRow,controlH,controlL);
        frame.getContentPane().add(I05B );


        O00.setBounds(firstCol,fourthRow,controlH,controlL);
        O00.setBackground(Color.blue);
        O00.setOpaque(true);
        frame.getContentPane().add(O00);



        O01.setBounds(secondCol,fourthRow,controlH,controlL);
        O01.setBackground(Color.blue);
        O01.setOpaque(true);
        frame.getContentPane().add(O01);


        O02.setBounds(thirdCol,fourthRow,controlH,controlL);
        O02.setBackground(Color.blue);
        O02.setOpaque(true);
        frame.getContentPane().add(O02);


        O03.setBounds(fourthCol,fourthRow,controlH,controlL);
        O03.setBackground(Color.blue);
        O03.setOpaque(true);
        frame.getContentPane().add(O03);

        JLabel labelWord = new JLabel("Word:");
        labelWord.setFont(new Font("Tahoma", Font.PLAIN, 12));
        labelWord.setBounds(secondColB, 140, fieldSizeL ,fieldSizeH);
        frame.getContentPane().add(labelWord );

        DBWord.setBounds(secondColB, 170, fieldSizeL ,fieldSizeH);
        DBWord.setText("1");
        frame.getContentPane().add(DBWord);
        DBWord.setColumns(10);

        JLabel labelNumer = new JLabel("Numer:");
        labelNumer.setFont(new Font("Tahoma", Font.PLAIN, 12));
        labelNumer.setBounds(firstCol, 140, fieldSizeL ,fieldSizeH);
        frame.getContentPane().add(labelNumer );


        DBNumer.setBounds(firstColB, 170, fieldSizeL ,fieldSizeH);
        DBNumer.setText("3");
        frame.getContentPane().add(DBNumer);
        DBNumer.setColumns(10);



        statusWord.setBounds(secondColB, 200, fieldSizeL ,fieldSizeH);
        statusWord.setText("0");
        frame.getContentPane().add(statusWord);
        statusWord.setColumns(10);

        JButton ReadButton = new JButton();
        ReadButton.setBounds(firstColB, 200,fieldSizeL ,fieldSizeH);
        ReadButton.setText("READ");
        frame.getContentPane().add(ReadButton);

        I00B.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                {
                    Client.ReadArea(S7.S7AreaDB, 3, 2, 1, BitBuffer);
                  //  System.out.println("Button1");
                   // System.out.println((S7.GetBitAt(BitBuffer,2,0)));
                    if(S7.GetBitAt(BitBuffer,0,0))
                    {
                        S7.SetBitAt(BitBuffer, 0, 0,false);
                    }else{

                        S7.SetBitAt(BitBuffer, 0, 0,true);
                    }

                    Client.WriteArea(S7.S7AreaDB, 3, 2, 1,BitBuffer);
                   printBYTE(BitBuffer);
                }
            }
        });

        I01B.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                {
                   // System.out.println("Button2");
                    Client.ReadArea(S7.S7AreaDB, 3, 2, 1,BitBuffer);
                    if(S7.GetBitAt(BitBuffer,0,1))
                    {
                        S7.SetBitAt(BitBuffer, 0, 1,false);
                    }else{

                        S7.SetBitAt(BitBuffer, 0, 1,true);
                    }

                    Client.WriteArea(S7.S7AreaDB,3, 2, 1,BitBuffer);

                }
            }
        });

        I02B.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                {
                   // System.out.println("Button3");
                    Client.ReadArea(S7.S7AreaDB, 3, 2, 1, BitBuffer);

                    if(S7.GetBitAt(BitBuffer,0,2))
                    {
                        S7.SetBitAt(BitBuffer, 0, 2,false);
                    }else{

                        S7.SetBitAt(BitBuffer, 0, 2,true);
                    }



                    Client.WriteArea(S7.S7AreaDB, 3, 2, 1,BitBuffer);

                }
            }
        });

        I03B.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                {

                  //  System.out.println("Button4");
                    Client.ReadArea(S7.S7AreaDB, 3, 2, 1,BitBuffer);
                    if(S7.GetBitAt(BitBuffer,0,3))
                    {
                        S7.SetBitAt(BitBuffer, 0, 3,false);
                    }else{

                        S7.SetBitAt(BitBuffer, 0, 3,true);
                    }



                    Client.WriteArea(S7.S7AreaDB, 3, 2, 1,BitBuffer);

                }
            }
        });

        I04B.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                {
                    S7.SetBitAt(GUI.Buffer, 0, 4,true);



                    Client.WriteArea(S7.S7AreaDB, 5, 0, 4,GUI.Buffer);

                }
            }
        });

        I05B.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                {
                    S7.SetBitAt(GUI.Buffer, 0, 5,true);



                    Client.WriteArea(S7.S7AreaDB, 5, 0, 4, GUI.Buffer);

                }
            }
        });







        ReadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                if (!ReadBit){

                    ReadBit=true;

                }else{

                    ReadBit=false;
                }

                   // runing.setData(Integer.parseInt(DBNumer.getText()),Integer.parseInt(DBWord.getText()));
                  //  t2.start();
                   // Client.ReadArea(S7.S7AreaDB,Integer.parseInt(DBNumer.getText()),0,10,Buffer);

                    // System.out.println(Buffer);
                   // int readData = S7.GetWordAt(Buffer,Integer.parseInt(DBWord.getText()));
                   // statusWord.setText(Integer.toString(readData));
                    //   System.out.println(readData );
                    // System.out.println(Buffer[0] );
                    // System.out.println(Buffer[1] );
                    // System.out.println(Buffer[2] );
                    //System.out.println(Buffer[3] );



            }



        });



        frame.setVisible(visible);
    }

    public void printWord(byte[] bytest)
    {
        for (int i=0 ;i<20;i++)
        {

            System.out.print(bytest[i]);
            System.out.print(" ");
        }

        System.out.println(" ");
    }
    public void printBYTE(byte[] bytest)
    {
        for (int i=0 ;i<40;i++)
        {

            System.out.print(S7.GetBitAt(GUI.Buffer,0,i  ));

        }

        System.out.println(" ");
    }
    public void setVISIBLE(){

        frame.setVisible(visible);
    }

    public static void main(String[] args) {
        S7Client Client = new S7Client();
        Client.SetConnectionType(S7.OP);
        Client.ConnectTo("192.168.0.1",0,1);
        Client.Connect();
        if (Client.Connected)
        {

           System.out.println("1200 connected");
        }
        plcS71200 plc1200 = new plcS71200(Client,true);
        System.out.println("kjsahkjadshjkdsfhjkadsfh");

    }


}
