import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewAccountPage extends JFrame
{
    private JLabel title;
    private ImageIcon photo;
    private JLabel label;
    private JLabel username;
    private JTextField usernameInput;
    private JLabel password1;
    private JTextField passwordInput1;
    private JLabel password2;
    private JTextField passwordInput2;
    private JButton confirm;

    public NewAccountPage()
    {
        super ("YOUR SMART BIRD FEEDER INTERFACE");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        //panel.setBackground(Color.white);

        title = new JLabel();
        title.setText("New Account ");
        title.setBounds(210,0,600,80);
        title.setFont(new Font("Gill Sans Ultra Bold Condensed",Font.PLAIN,30));
        panel.add(title);

        photo = new ImageIcon("user.jpg");
        label = new JLabel();
        label.setIcon(photo);
        label.setOpaque(true);
        label.setBounds(205,80,200,200);
        panel.add(label);

        username = new JLabel();
        username.setText("username");
        username.setBounds(10,300,600,80);
        username.setFont(new Font("Ebrima",Font.PLAIN,15));
        panel.add(username);


        password1 = new JLabel();
        password1.setText("password");
        password1.setBounds(10,370,600,80);
        password1.setFont(new Font("Ebrima",Font.PLAIN,15));
        panel.add(password1);


        password2 = new JLabel();
        password2.setText("confirm password ");
        password2.setBounds(10,440,600,80);
        password2.setFont(new Font("Ebrima",Font.PLAIN,15));
        panel.add(password2);


        this.setContentPane(panel);

    }


    public static void main(String user) {
        JFrame ui = new NewAccountPage();
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
                String [] arguments = new String[] {""};
                LoginPage.main(arguments);
                ui.dispose();
            }
        });

        JTextField usernameInput = new JTextField();
        usernameInput.setBounds(10,350,570,40);
        ui.add(usernameInput);

        JButton confirm = new JButton();
        confirm.setText("CONFIRM");
        confirm.setBounds(180,550,200,40);
        confirm.setFont(new Font("Ebrima", Font.PLAIN, 10));
        confirm.setBackground(Color.white);
        ui.add(confirm);

        JTextField passwordInput1 = new JTextField();
        passwordInput1.setBounds(10,420,570,40);
        ui.add(passwordInput1);

        JTextField passwordInput2 = new JTextField();
        passwordInput2.setBounds(10,490,570,40);
        ui.add(passwordInput2);



        confirm.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (passwordInput1.getText().isEmpty() || passwordInput2.getText().isEmpty()|| usernameInput.getText().isEmpty())
                {
                    JFrame error = new JFrame();
                    JOptionPane.showMessageDialog(error, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    if (passwordInput1.getText().equals(passwordInput2.getText()))
                    {
                        DBMethods db = new DBMethods();
                        String setup = db.makeGETRequest("//https://studev.groept.be/api/a21ib2c03/AddUser/"+usernameInput.getText()+"/"+passwordInput1.getText());
                        ui.dispose();
                        JFrame confirmation = new JFrame();
                        JOptionPane.showMessageDialog(confirmation, "Your account was successfully created. You can log in using your new username and password from now on.");
                        String [] arguments = new String[] {""};
                        MainPage.main(usernameInput.getText());

                    }
                    else
                    {
                        JFrame error = new JFrame();
                        JOptionPane.showMessageDialog(error, "Password and confirmation password are different.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }

            }


        });
        ui.setVisible(true);
    }


}

