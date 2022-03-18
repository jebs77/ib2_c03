import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPage extends JFrame
{
    private JLabel title;
    private JLabel LEDs;
    private JLabel buzzerVolume;
    private JLabel notifications;
    private JLabel hatch;
    private JLabel foodLevel;
    private JTextField volumeInput;
    private JLabel percentage;
    private JLabel myLabel;




    public SettingsPage()
    {
        super("YOUR SMART BIRD FEEDER INTERFACE");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        //panel.setBackground(Color.white);

        title = new JLabel();
        title.setText("Settings");
        title.setBounds(225,0,600,80);
        title.setFont(new Font("Gill Sans Ultra Bold Condensed",Font.PLAIN,30));
        panel.add(title);

        myLabel = new JLabel();
        myLabel.setOpaque(true);
        myLabel.setBackground(Color.black);
        myLabel.setBounds(0,65,600,2);
        //myLabel1.setFont(new Font("",Font.PLAIN,30));
        panel.add(myLabel);

/*
        LEDs = new JLabel();
        LEDs.setText("LEDs");
        LEDs.setBounds(10,60,600,80);
        LEDs.setFont(new Font("Ebrima",Font.BOLD,15));
        panel.add(LEDs);


        buzzerVolume = new JLabel();
        buzzerVolume.setText("Buzzer volume");
        buzzerVolume.setBounds(10,150,600,80);
        buzzerVolume.setFont(new Font("Ebrima",Font.BOLD,15));
        panel.add(buzzerVolume);

        percentage = new JLabel();
        percentage.setText("%");
        percentage.setBounds(100,220,30,30);
        percentage.setFont(new Font("Ebrima",Font.PLAIN,15));
        panel.add(percentage);

 */


        notifications = new JLabel();
        notifications.setText("Notifications");
        notifications.setBounds(10,250,600,80);
        notifications.setFont(new Font("Ebrima",Font.BOLD,15));
        panel.add(notifications);

        hatch = new JLabel();
        hatch.setText("bird detection / open hatch");
        hatch.setBounds(50,300,600,80);
        hatch.setFont(new Font("Ebrima",Font.PLAIN,10));
        panel.add(hatch);


        foodLevel = new JLabel();
        foodLevel.setText("low food level");
        foodLevel.setBounds(50,360,600,80);
        foodLevel.setFont(new Font("Ebrima",Font.PLAIN,10));
        panel.add(foodLevel);

        this.setContentPane(panel);

    }

    public static void main(String user)
    {

        JFrame ui = new SettingsPage();
        ui.setLayout(null);
        ui.setSize(600, 800);
        ui.setResizable(false);
        ui.setVisible(true);

        JButton back = new JButton();
        back.setText("<");
        back.setBounds(10,10,50,40);
        back.setFont(new Font("Ebrima", Font.BOLD, 15));
        back.setBackground(Color.white);
        ui.add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ui.dispose();
                //String [] arguments = new String[] {""};
                MainPage.main(user);
                ui.dispose();
            }
        });

        JButton LEDsON = new JButton();
        LEDsON.setText("ON");
        LEDsON.setBounds(50,120,70,40);
        LEDsON.setFont(new Font("Ebrima", Font.PLAIN, 10));
        LEDsON.setBackground(Color.white);
        ui.add(LEDsON);

        JButton LEDsOFF = new JButton();
        LEDsOFF.setText("OFF");
        LEDsOFF.setBounds(150,120,70,40);
        LEDsOFF.setFont(new Font("Ebrima", Font.PLAIN, 10));
        LEDsOFF.setBackground(Color.lightGray);
        ui.add(LEDsOFF);

        LEDsON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (LEDsON.getBackground()==Color.white)
                {
                    LEDsON.setBackground(Color.lightGray);
                    LEDsOFF.setBackground(Color.white);

                }
                else
                {
                    LEDsON.setBackground(Color.white);
                    LEDsOFF.setBackground(Color.lightGray);
                }
            }
        });

        LEDsOFF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (LEDsOFF.getBackground()==Color.white)
                {
                    LEDsOFF.setBackground(Color.lightGray);
                    LEDsON.setBackground(Color.white);

                }
                else
                {
                    LEDsOFF.setBackground(Color.white);
                    LEDsON.setBackground(Color.lightGray);
                }
            }
        });
/*
        JTextField volumeInput = new JTextField();
        volumeInput.setBounds(50,220,40,30);
        ui.add(volumeInput);

        JButton save = new JButton();
        save.setText("SAVE");
        save.setBounds(150,215,70,40);
        save.setFont(new Font("Ebrima", Font.PLAIN, 10));
        save.setBackground(Color.white);
        ui.add(save);



        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (volumeInput.getText().isEmpty())
                {
                    JFrame error = new JFrame();
                    JOptionPane.showMessageDialog(error, "Field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    if (Integer.parseInt(volumeInput.getText())>100 || Integer.parseInt(volumeInput.getText())<0)
                    {
                        JFrame error = new JFrame();
                        JOptionPane.showMessageDialog(error, "Volume typed exceeds percentage range.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        JFrame confirmation = new JFrame();
                        JOptionPane.showMessageDialog(confirmation, "Buzzer volume was successfully updated.");
                    }

                }
            }
        });

 */


        JButton hatchON = new JButton();
        hatchON.setText("ON");
        hatchON.setBounds(190,320,70,40);
        hatchON.setFont(new Font("Ebrima", Font.PLAIN, 10));
        hatchON.setBackground(Color.lightGray);
        ui.add(hatchON);

        JButton hatchOFF = new JButton();
        hatchOFF.setText("OFF");
        hatchOFF.setBounds(290,320,70,40);
        hatchOFF.setFont(new Font("Ebrima", Font.PLAIN, 10));
        hatchOFF.setBackground(Color.white);
        ui.add(hatchOFF);

        hatchON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hatchON.getBackground()==Color.white)
                {
                    hatchON.setBackground(Color.lightGray);
                    hatchOFF.setBackground(Color.white);

                }
                else
                {
                    hatchON.setBackground(Color.white);
                    hatchOFF.setBackground(Color.lightGray);
                }
            }
        });

        hatchOFF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hatchOFF.getBackground()==Color.white)
                {
                    hatchOFF.setBackground(Color.lightGray);
                    hatchON.setBackground(Color.white);

                }
                else
                {
                    hatchOFF.setBackground(Color.white);
                    hatchON.setBackground(Color.lightGray);
                }
            }
        });


        JButton foodLevelON = new JButton();
        foodLevelON.setText("ON");
        foodLevelON.setBounds(190,380,70,40);
        foodLevelON.setFont(new Font("Ebrima", Font.PLAIN, 10));
        foodLevelON.setBackground(Color.lightGray);
        ui.add(foodLevelON);

        JButton foodLevelOFF = new JButton();
        foodLevelOFF.setText("OFF");
        foodLevelOFF.setBounds(290,380,70,40);
        foodLevelOFF.setFont(new Font("Ebrima", Font.PLAIN, 10));
        foodLevelOFF.setBackground(Color.white);
        ui.add(foodLevelOFF);

        foodLevelON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (foodLevelON.getBackground()==Color.white)
                {
                    foodLevelON.setBackground(Color.lightGray);
                    foodLevelOFF.setBackground(Color.white);

                }
                else
                {
                    foodLevelON.setBackground(Color.white);
                    foodLevelOFF.setBackground(Color.lightGray);
                }
            }
        });

        foodLevelOFF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (foodLevelOFF.getBackground()==Color.white)
                {
                    foodLevelOFF.setBackground(Color.lightGray);
                    foodLevelON.setBackground(Color.white);

                }
                else
                {
                    foodLevelOFF.setBackground(Color.white);
                    foodLevelON.setBackground(Color.lightGray);
                }
            }
        });



        ui.setVisible(true);




    }

}
