import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginPage extends JFrame
{
    private JPanel panel;
    private JLabel welcome;
    private JLabel title;
    private ImageIcon logo;
    private JLabel label;
    private JLabel username;
    private JTextField usernameInput;
    private JLabel password;
    private JTextField passwordInput;
    private JButton createAccount;
    private JButton logIn;
    private JLabel myLabel;

    public LoginPage()
    {
        super("YOUR SMART BIRD FEEDER INTERFACE");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        //panel.setBackground(Color.white);

        title = new JLabel();
        title.setText("Log In");
        title.setBounds(240,0,600,80);
        title.setFont(new Font("Gill Sans Ultra Bold Condensed",Font.PLAIN,30)); //OCR A Extended
        panel.add(title);

        myLabel = new JLabel();
        myLabel.setOpaque(true);
        myLabel.setBackground(Color.black);
        myLabel.setBounds(0,65,600,2);
        //myLabel1.setFont(new Font("",Font.PLAIN,30));
        panel.add(myLabel);


        logo = new ImageIcon("logo1.png");
        label = new JLabel();
        label.setIcon(logo);
        label.setOpaque(true);
        label.setBounds(-10,90,900,200);
        panel.add(label);

        username = new JLabel();
        username.setText("username");
        username.setBounds(10,300,600,80);
        username.setFont(new Font("Ebrima",Font.PLAIN,15));
        panel.add(username);


        password = new JLabel();
        password.setText("password ");
        password.setBounds(10,370,600,80);
        password.setFont(new Font("Ebrima",Font.PLAIN,15));
        panel.add(password);



        this.setContentPane(panel);


    }

    public static void main(String[] args) {
        //generate my UI
        JFrame ui = new LoginPage();
        ui.setLayout(null);
        ui.setSize(600, 800);
        ui.setResizable(false);
        ui.setVisible(true);

        JTextField usernameInput = new JTextField();
        usernameInput.setBounds(10,350,570,40);
        ui.add(usernameInput);

        JTextField passwordInput = new JTextField();
        passwordInput.setBounds(10,420,570,40);
        ui.add(passwordInput);

        JButton createAccount = new JButton();
        createAccount.setText("CREATE ACCOUNT");
        createAccount.setBounds(180,480,200,25);
        createAccount.setFont(new Font("Ebrima", Font.PLAIN, 10));
        createAccount.setBackground(Color.white);
        ui.add(createAccount);

        createAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ui.dispose();
                String [] arguments = new String[] {""};
                NewAccountPage.main(usernameInput.getText());
                ui.dispose();
            }
        });

        JButton change = new JButton();
        change.setText("CHANGE PASSWORD ");
        change.setBounds(30,550,200,40);
        change.setFont(new Font("Ebrima", Font.PLAIN, 15));
        change.setBackground(Color.white);

        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(usernameInput.getText().isEmpty()){
                    JFrame error = new JFrame();
                    JOptionPane.showMessageDialog(error, "Enter a username first!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    DBMethods db = new DBMethods();
                    String info = db.makeGETRequest("https://studev.groept.be/api/a21ib2c03/GetUserInfo");
                    ArrayList<String> names = db.returnJSONUserInfo(info,"User");
                    boolean definedUser = false;
                    for(String n: names){
                        if(n.equals(usernameInput.getText())){
                            definedUser = true;
                        }
                    }
                    if(!definedUser){
                        JFrame error = new JFrame();
                        JOptionPane.showMessageDialog(error, "Not a valid username!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        //String[] arguments = new String[] {""};
                        ChangePassword.main(usernameInput.getText());
                    }
                }
            }
        });
        ui.add(change);



        JButton logIn = new JButton();
        logIn.setText("LOG IN ");
        logIn.setBounds(370,550,200,40);
        logIn.setFont(new Font("Ebrima", Font.PLAIN, 15));
        logIn.setBackground(Color.white);
        ui.add(logIn);
        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ui.dispose();
                if (passwordInput.getText().isEmpty() || usernameInput.getText().isEmpty())
                {
                    JFrame error = new JFrame();
                    JOptionPane.showMessageDialog(error, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    DBMethods db = new DBMethods();
                    String info = db.makeGETRequest("https://studev.groept.be/api/a21ib2c03/GetUserInfo");
                    ArrayList<String> userNames = db.returnJSONUserInfo(info,"User");
                    ArrayList<String> userPasswords = db.returnJSONUserInfo(info,"Password");
                    boolean definedUser = false;
                    String name = "";
                    for(String n: userNames){
                        if(n.equals(usernameInput.getText())){
                            for(String p: userPasswords){
                                System.out.println(p);
                                if(p.equals(passwordInput.getText())){
                                    name = n;
                                    definedUser = true;
                                }
                            }
                        }

                    }
                    if(definedUser){
                        String [] arguments = new String[] {""};
                        MainPage.main(name);
                        ui.dispose();
                    }
                    else{
                        JFrame error = new JFrame();
                        JOptionPane.showMessageDialog(error, "This isn't a valid combination!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

}


