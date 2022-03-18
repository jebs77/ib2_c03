import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainPage extends JFrame
{
    private JPanel panel;
    private ImageIcon settings;
    private ImageIcon user;
    private JLabel title;
    private JButton button1;
    private JLabel label;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private Image image1;
    private JButton button2;
    private JLabel food;
    private JLabel total;
    private JLabel info;
    private JButton birdMenu;
    private JButton hatch;
    private JButton buzzer;
    private JLabel stats;
    private JLabel birds;
    private JButton furtherStats;
    private JLabel myLabel;

    public MainPage()
    {
        super("YOUR SMART BIRD FEEDER INTERFACE");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        //panel.setBackground(Color.white);


        title = new JLabel();
        title.setText("Main Menu");
        title.setBounds(220,10,600,80);
        title.setFont(new Font("Gill Sans Ultra Bold Condensed",Font.PLAIN,30));
        panel.add(title);

        myLabel = new JLabel();
        myLabel.setOpaque(true);
        myLabel.setBackground(Color.black);
        myLabel.setBounds(0,80,600,2);
        panel.add(myLabel);


        label1 = new JLabel();
        label1.setOpaque(true);
        label1.setBackground(Color.black);
        label1.setBounds(10,100,560,2);
        panel.add(label1);

        label2 = new JLabel();
        label2.setOpaque(true);
        label2.setBackground(Color.black);
        label2.setBounds(10,100,2,270);
        panel.add(label2);

        label3 = new JLabel();
        label3.setOpaque(true);
        label3.setBackground(Color.black);
        label3.setBounds(10,370,560,2);
        panel.add(label3);

        label4 = new JLabel();
        label4.setOpaque(true);
        label4.setBackground(Color.black);
        label4.setBounds(570,100,2,272);
        panel.add(label4);


        label5 = new JLabel();
        label5.setOpaque(true);
        label5.setBackground(Color.black);
        label5.setBounds(10,375,560,2);
        panel.add(label5);

        label6 = new JLabel();
        label6.setOpaque(true);
        label6.setBackground(Color.black);
        label6.setBounds(10,375,2,160);
        panel.add(label6);

        label7 = new JLabel();
        label7.setOpaque(true);
        label7.setBackground(Color.black);
        label7.setBounds(10,535,560,2);
        panel.add(label7);

        label8 = new JLabel();
        label8.setOpaque(true);
        label8.setBackground(Color.black);
        label8.setBounds(570,375,2,162);
        panel.add(label8);

        stats = new JLabel();
        stats.setText("BIRD FEEDER STATUS OVERVIEW");
        stats.setBounds(20,80,600,80);
        stats.setFont(new Font("Ebrima",Font.BOLD,15));
        panel.add(stats);


        food = new JLabel();
        food.setText("food container status :...% filled");
        food.setBounds(20,200,300,80);
        food.setFont(new Font("Ebrima",Font.PLAIN,15));
        panel.add(food);

       /* total = new JLabel();
        total.setText("number of birds fed today:");
        total.setBounds(20,220,300,80);
        total.setFont(new Font("Ebrima",Font.PLAIN,15));
        panel.add(total);

        */



        birds = new JLabel();
        birds.setText("BIRD CONFIGURATION OVERVIEW");
        birds.setBounds(20,350,600,80);
        birds.setFont(new Font("Ebrima",Font.BOLD,15));
        panel.add(birds);


        info = new JLabel();
        info.setText("bird types that can be fed:");
        info.setBounds(20,400,600,80);
        info.setFont(new Font("Ebrima",Font.PLAIN,15));
        panel.add(info);


        this.setContentPane(panel);
    }


    public static void main(String user)
    {
        JFrame ui = new MainPage();
        ui.setLayout(null);
        ui.setSize(600, 800);
        ui.setResizable(false);
        ui.setVisible(true);

        JLabel hatchStatus = new JLabel();
        hatchStatus.setBounds(20,150,100,80);
        hatchStatus.setFont(new Font("Ebrima",Font.BOLD,15));
        hatchStatus.setText("hatch status:");
        ui.add(hatchStatus);

        JLabel total = new JLabel();
        total.setBounds(115,150,400,80);
        total.setFont(new Font("Ebrima",Font.PLAIN,15));
        ui.add(total);

        DBMethods db3 = new DBMethods();
        String respons = db3.makeGETRequest("https://studev.groept.be/api/a21ib2c03/getHatchInfo");
        ArrayList<String> info = db3.returnJSONDeviceStatus(respons);
        int manual = Integer.parseInt(info.get(4));
        int on = Integer.parseInt(info.get(3));

        if(manual ==0)
        {

            if (on==1)
            {
                total.setText("OPEN");
                total.setForeground(Color.GREEN);
            }
            else
            {
                total.setText("CLOSED");
                total.setForeground(Color.black);
            }
        }
        else
        {
            total.setText("manually CLOSED until further notice");
            total.setForeground(Color.black);
        }

        JButton birdMenu = new JButton();
        birdMenu.setText("Manage birds fed");
        birdMenu.setBounds(30,480,530,40);
        birdMenu.setFont(new Font("Ebrima", Font.PLAIN, 15));
        birdMenu.setBackground(Color.white);
        ui.add(birdMenu);

        birdMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ui.dispose();
                String [] arguments = new String[] {""};
                BirdMenu.main(user);
                ui.dispose();
            }
        });

        JButton hatch = new JButton();
        hatch.setText("HATCH");
        hatch.setBounds(150,550,100,60);
        hatch.setFont(new Font("Ebrima",Font.PLAIN,15));
        hatch.setOpaque(true);
        ui.add(hatch);

        DBMethods db = new DBMethods();
        String answer = db.makeGETRequest("https://studev.groept.be/api/a21ib2c03/getHatchInfo");
        ArrayList<String> hatchInfo = db.returnJSONDeviceStatus(answer);
        int last = Integer.parseInt(info.get(0));
        int curr = Integer.parseInt(info.get(1));
        String InOut = info.get(2);
        int hatchOn = Integer.parseInt(hatchInfo.get(3));
        int hatchManual = Integer.parseInt(info.get(4));
        if(hatchManual == 0){
            hatch.setBackground(Color.LIGHT_GRAY);
        }
        else{
            hatch.setBackground(Color.RED);
        }



        hatch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String respons = db.makeGETRequest("https://studev.groept.be/api/a21ib2c03/getHatchInfo");
                ArrayList<String> info = db.returnJSONDeviceStatus(respons);
                int manual = Integer.parseInt(info.get(4));
                if(manual ==0)
                {
                    hatch.setBackground(Color.RED);
                    String set = db.makeGETRequest("https://studev.groept.be/api/a21ib2c03/SetHatch/"+1);
                    JFrame confirmation = new JFrame();
                    JOptionPane.showMessageDialog(confirmation, "Hatch is now closed");
                }
                else
                {
                    hatch.setBackground(Color.LIGHT_GRAY);
                    String set = db.makeGETRequest("https://studev.groept.be/api/a21ib2c03/SetHatch/"+0);
                    JFrame confirmation = new JFrame();
                    JOptionPane.showMessageDialog(confirmation, "Hatch is now working  automatically.");
                }
            }
        });

        JButton buzzer = new JButton();
        buzzer.setText("BUZZER");
        buzzer.setBounds(310,550,100,60);
        buzzer.setBackground(Color.white);
        buzzer.setFont(new Font("Ebrima",Font.PLAIN,15));
        buzzer.setOpaque(true);

        String buzzerRespons = db.makeGETRequest("https://studev.groept.be/api/a21ib2c03/GetBuzzerInfo");
        ArrayList<String> buzzerInfo = db.returnJSONDeviceStatus(buzzerRespons);
        int manualBuzzer = Integer.parseInt(buzzerInfo.get(4));
        if(manualBuzzer == 1){
            buzzer.setBackground(Color.GREEN);
        }
        else{
            buzzer.setBackground(Color.RED);
        }

        ui.add(buzzer);

        buzzer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buzzerRespons = db.makeGETRequest("https://studev.groept.be/api/a21ib2c03/GetBuzzerInfo");
                ArrayList<String> buzzerInfo = db.returnJSONDeviceStatus(buzzerRespons);
                int manualBuzzer = Integer.parseInt(buzzerInfo.get(4));
                if (manualBuzzer == 1)
                {
                    buzzer.setBackground(Color.RED);
                    String set = db.makeGETRequest("https://studev.groept.be/api/a21ib2c03/SetBuzzer/"+0);
                    JFrame confirmation = new JFrame();
                    JOptionPane.showMessageDialog(confirmation, "Buzzer is OFF until further notice.");
                }
                else
                {
                    buzzer.setBackground(Color.GREEN);
                    String set = db.makeGETRequest("https://studev.groept.be/api/a21ib2c03/SetBuzzer/"+1);
                    JFrame confirmation = new JFrame();
                    JOptionPane.showMessageDialog(confirmation, "Buzzer is ON until further notice.");
                }

            }
        });

        JButton furtherStats = new JButton();
        furtherStats.setText("See statistics");
        furtherStats.setBounds(30,315,530,40);
        furtherStats.setFont(new Font("Ebrima", Font.PLAIN, 15));
        furtherStats.setBackground(Color.white);
        ui.add(furtherStats);

        furtherStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame stats = new JFrame();
                JLabel myLabel1 = new JLabel();
                myLabel1.setText("STATISTICS");
                myLabel1.setBounds(10,10,250,50);
                stats.add(myLabel1);

                stats.setLayout(null);
                stats.setSize(300,100);
                stats.setResizable(false);
                stats.setVisible(true);

            }
        });

        ImageIcon settings = new ImageIcon("settings.jpg");
        JButton button1 = new JButton();
        button1.setIcon(settings);
        button1.setOpaque(true);
        button1.setBackground(Color.white);
        button1.setBounds(10,10,60,60);
        ui.add(button1);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ui.dispose();
                //String [] arguments = new String[] {};
                SettingsPage.main(user);
                ui.dispose();
            }
        });



        ImageIcon UIUser = new ImageIcon("icon.jpg");
        JButton button2 = new JButton();
        button2.setIcon(UIUser);
        button1.setOpaque(true);
        button2.setBounds(520,10,60,60);
        button2.setBackground(Color.white);
        ui.add(button2);

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ui.dispose();
                //String [] arguments = new String[] {""};
                UserInfoPage.main(user);
                ui.dispose();
            }
        });

        String birdsFed = "";

        DBMethods db2 = new DBMethods();
        String names = db2.makeGETRequest("https://studev.groept.be/api/a21ib2c03/GetBirdNames");
        ArrayList<String> list = db2.returnJSONNames(names);

        for(int i = 0; i < list.size(); i++)
        {
            if (i != list.size()-1)
            {
                birdsFed = birdsFed + list.get(i) + ", ";
            }
            else
            {
                birdsFed = birdsFed + list.get(i) + ".";
            }
        }

        JLabel birdTypes = new JLabel();
        birdTypes.setText("bird types that can be fed: "+birdsFed);
        birdTypes.setBounds(20,400,600,80);
        birdTypes.setFont(new Font("Ebrima",Font.PLAIN,15));
        ui.add(birdTypes);



        ui.setVisible(true);

    }




}