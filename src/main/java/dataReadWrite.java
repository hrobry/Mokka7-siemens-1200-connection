

import com.sourceforge.snap7.moka7.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;









public class dataReadWrite implements Runnable {
    public static byte[] BitBuffer = new byte[65536];
    public static byte[] BitSendBuffer = new byte[65536];
    public static byte[] WordBuffer = new byte[65536];
    public S7Client Client;
    JFrame frame = new JFrame();
    private boolean ReadBit=false;
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    boolean visible=true;


    public dataReadWrite(S7Client Client,boolean visible) {
        this.Client=Client;
        this.visible = visible;
        initialize();

    }

    // runingData runing = new runingData(  Client );
    // Thread t2 =new Thread(runing);




    JButton ReadButton = new JButton();
    JButton WriteButton = new JButton();
    JLabel DBNummerWriteLabel = new JLabel("Write DB:");
    JLabel DBWordWriteLabel= new JLabel("Write Word:");
    JLabel DBNummerReadLabel = new JLabel(" Read DB:");
    JLabel DBWordReadLabel = new JLabel("Read Word :");
    JButton readOnce = new JButton();
    JButton WRITE = new JButton();


    JTextField DBNumerWrite = new JTextField();
    JTextField DBWordWrite = new JTextField();
    JTextField DBNumerRead = new JTextField();
    JTextField DBWordRead = new JTextField();
    JTextField DataToWrite= new JTextField();
    JTextField DataReaded = new JTextField();





    @Override
    public void run() {
        while (true) {
            if (Client.Connected) {
                // System.out.println("READING...");

                Client.ReadArea(S7.S7AreaPA, 3, 0, 1, BitBuffer);


                // System.out.println(S7.GetBitAt(GUI.Buffer,0,0   ));
                //System.out.println(S7.GetBitAt( BitBuffer,0,1  ));
                // System.out.println(S7.GetBitAt( BitBuffer,0,2   ));
                //System.out.println(S7.GetBitAt( BitBuffer,0,3   ));


                //  System.out.print(BitBuffer);
                //  System.out.print(BitBuffer);
                // System.out.print(BitBuffer);
                // System.out.println(BitBuffer);

                if (ReadBit == true) {

                    //Client.Connect();
                    Client.ReadArea(S7.S7AreaDB, Integer.parseInt(DBNumerRead.getText()), 0, 2, WordBuffer);
                    //  System.out.println(WordBuffer[0]);
                    //  System.out.println(ReadBit);
                    int readData = S7.GetWordAt(WordBuffer, Integer.parseInt(DBWordRead.getText()));
                    // System.out.println(readData);
                   DataReaded.setText(Integer.toString(readData));
                    // System.out.println( "READ BIT");
                   // printWord(WordBuffer);

                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException c) {
                }

            }
        }
    }
    private void initialize() {

            //    Client.ReadArea(S7.S7AreaPA, 0, 0, 4, BitBuffer);
            int firstCol = 30, secondCol = 120, thirdCol = 210, fourthCol =300, fifthCol = 150, sixthCol = 180;
            int firstColB = 30, secondColB = 120, thirdColB = 180, fourthColB = 120, fifthColB = 150, sixthColB = 180;
            int fieldSizeH = 25, fieldSizeL = 90;
            int controlH = 15, controlL = 15;
            int firstRow = 40, secondRow = 90, thirdRow =140,fourthRow = 210;

            int firstRowLabel = 20, secondRowLabel = 60, thirdRowLabel =140,fourthRowLabel = 210;



            frame.setBounds(500, 100, 500, 300);
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.setTitle("PLC SIEMENS S7");
            frame.getContentPane().setLayout(null);


            WriteButton.setBounds(thirdCol, firstRow, fieldSizeL,fieldSizeH);
            WriteButton.setText("WRITE");
            frame.getContentPane().add(WriteButton);

            DBNumerWrite.setBounds(firstCol,  firstRow, fieldSizeL, fieldSizeH);
            DBNumerWrite.setText("3");
            frame.getContentPane().add(DBNumerWrite);
            DBNumerWrite.setColumns(10);

            DBWordWrite.setBounds(secondCol,  firstRow, fieldSizeL, fieldSizeH);
            DBWordWrite.setText("3");
            frame.getContentPane().add(DBWordWrite);
            DBWordWrite.setColumns(10);

            DataToWrite.setBounds(fourthCol,  firstRow, fieldSizeL, fieldSizeH);
            DataToWrite.setText("3");
            frame.getContentPane().add(DataToWrite);
            DataToWrite.setColumns(10);


            ReadButton.setBounds(thirdCol,secondRow, fieldSizeL, fieldSizeH);
            ReadButton.setText("READ");
            frame.getContentPane().add(ReadButton);

        readOnce.setBounds(thirdCol,thirdRow, fieldSizeL, fieldSizeH);
        readOnce.setText("READ ONCE");
        frame.getContentPane().add(readOnce);

        DBNumerRead.setBounds(firstCol,  secondRow, fieldSizeL, fieldSizeH);
        DBNumerRead.setText("3");
        frame.getContentPane().add(DBNumerRead);
        DBNumerRead.setColumns(10);

        DBWordRead.setBounds(secondCol, secondRow, fieldSizeL, fieldSizeH);
        DBWordRead.setText("3");
        frame.getContentPane().add(DBWordRead);
        DBWordRead.setColumns(10);

        DataReaded.setBounds(fourthCol,  secondRow, fieldSizeL, fieldSizeH);
        DataReaded.setText("3");
        frame.getContentPane().add(DataReaded);
        DataReaded.setColumns(10);


        DBNummerWriteLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        DBNummerWriteLabel.setBounds(firstCol, firstRowLabel, fieldSizeL ,fieldSizeH);
        frame.getContentPane().add(DBNummerWriteLabel );

        DBWordWriteLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        DBWordWriteLabel.setBounds(secondCol, firstRowLabel, fieldSizeL ,fieldSizeH);
        frame.getContentPane().add(DBWordWriteLabel );

        DBNummerReadLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        DBNummerReadLabel.setBounds(firstCol, secondRowLabel, fieldSizeL ,fieldSizeH);
        frame.getContentPane().add(DBNummerReadLabel );

        DBWordReadLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        DBWordReadLabel.setBounds(secondCol, secondRowLabel, fieldSizeL ,fieldSizeH);
        frame.getContentPane().add(DBWordReadLabel );


        ReadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                {
                    // System.out.println("Button2");
                    ReadBit=true;



                }
            }
        });

        WriteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                {
                    // System.out.println("Button2");
                    ReadBit=true;
                    S7.SetWordAt(WordBuffer, Integer.parseInt(DBWordWrite.getText()), Integer.parseInt(DataToWrite.getText()));
                    Client.WriteArea(S7.S7AreaDB, Integer.parseInt(DBNumerWrite.getText()), 0, 2, WordBuffer);


                }
            }
        });
        frame.setVisible(visible);


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
        dataReadWrite plc1200 = new dataReadWrite(Client,true);
        System.out.println("kjsahkjadshjkdsfhjkadsfh");

    }


}