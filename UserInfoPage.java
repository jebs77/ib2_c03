import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInfoPage extends JFrame{

    private JLabel myLabel0;
    private JLabel myLabel1;
    private JLabel myLabel2;
    private JLabel myLabel3;
    private ImageIcon icon;
    private JPanel myPanel;


    public UserInfoPage(String username){
        super("YOUR SMART BIRD FEEDER INTERFACE");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myPanel = new JPanel();
        //myPanel.setBackground(Color.white);

        myLabel0 = new JLabel();
        myLabel0.setText("User Profile");
        myLabel0.setBounds(220,0,600,80);
        myLabel0.setFont(new Font("Gill Sans Ultra Bold Condensed",Font.PLAIN,30));
        myPanel.add(myLabel0);
        myLabel1 = new JLabel();
        myLabel1.setOpaque(true);
        myLabel1.setBackground(Color.black);
        myLabel1.setBounds(0,65,600,2);
        //myLabel1.setFont(new Font("",Font.PLAIN,30));
        myPanel.add(myLabel1);


        // Create name label
        myLabel2 = new JLabel();
        myLabel2.setText("username: "+username);
        myLabel2.setFont(new Font("Ebrima",Font.PLAIN,15));
        myLabel2.setBounds(200,350,340,50);
        myPanel.add(myLabel2);

        //myPanel op JFrame zetten
        this.setContentPane(myPanel);

    }

    public static void main(String user) {


        JFrame test = new UserInfoPage(user);
        //the frame needs to become visible
        test.setLayout(null);
        test.setSize(600,800);
        test.setResizable(false);
        System.out.println("User: "+user);

        if(user.equals("Boma")){
            ImageIcon icon = new ImageIcon("boma.jpg");
            JLabel myLabel3 = new JLabel();
            myLabel3.setIcon(icon);
            myLabel3.setOpaque(true);
            myLabel3.setBounds(200,150,200,200);
            test.add(myLabel3);
        }
        else{
            ImageIcon icon = new ImageIcon("ProfilePic.jpg");
            JLabel myLabel3 = new JLabel();
            myLabel3.setIcon(icon);
            myLabel3.setOpaque(true);
            myLabel3.setBounds(200,150,200,200);
            test.add(myLabel3);
        }


        //Create the back button
        JButton myButton1 = new JButton();
        myButton1.setText("<");
        myButton1.setBounds(10,10,50,50);
        myButton1.setBackground(Color.white);
        myButton1.setFont(new Font("Gill Sans Ultra Bold Condensed",Font.BOLD,15));
        test.add(myButton1);
        myButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                test.dispose();
                //String [] arguments = new String[] {""};
                MainPage.main(user);
            }
        });


        JButton myButton4 = new JButton();
        myButton4.setText("LOG OUT");
        myButton4.setBounds(100,550,400,50);
        myButton4.setBackground(Color.white);
        myButton4.setFont(new Font("Ebrima",Font.PLAIN,15));
        test.add(myButton4);

        myButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame confirmation = new JFrame();
                JOptionPane.showMessageDialog(confirmation, "You logged out successfully.");
                test.dispose();
                String[] arguments = new String[] {""};
                LoginPage.main(arguments);

            }
        });

        //Create Change password button
        JButton myButton3 = new JButton();
        myButton3.setText("CHANGE PASSWORD");
        myButton3.setBounds(200,400,200,30);
        myButton3.setBackground(Color.white);
        myButton3.setFont(new Font("Ebrima",Font.PLAIN,10));
        test.add(myButton3);
        myButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangePassword.main(user);
            }
        });


        //Set visible after al buttons are added
        test.setVisible(true);
    }
}
