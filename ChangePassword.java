import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePassword extends JFrame {
    private JLabel myLabel1;
    private JLabel myLabel2;
    private JLabel myLabel3;
    private JPanel myPanel;

    public ChangePassword(){
        super("new password");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        myPanel = new JPanel();
/*
        myLabel1 = new JLabel();
        myLabel1.setText("CHANGE OF PASSWORD");
        myLabel1.setFont(new Font("passwordTitel",Font.PLAIN,20));
        myLabel1.setBounds(10,10,250,30);
        myPanel.add(myLabel1);*/

        myLabel2 = new JLabel();
        myLabel2.setText("new password");
        myLabel2.setBounds(10,10,200,30);
        myLabel2.setFont(new Font("Ebrima",Font.PLAIN,15));
        myPanel.add(myLabel2);
/*
        myLabel3 = new JLabel();
        myLabel3.setOpaque(true);
        myLabel3.setBackground(Color.BLACK);
        myLabel3.setBounds(0,50,300,2);
        myPanel.add(myLabel3);*/


        super.setContentPane(myPanel);
    }

    public static void main(String user) {
        JFrame password = new ChangePassword();
        password.setLayout(null);
        password.setSize(300,150);
        password.setResizable(false);

        JTextField myText = new JTextField();
        myText.setBounds(10,40,150,30);
        password.add(myText);

        JButton mybutton = new JButton();
        mybutton.setText("SAVE");
        mybutton.setFont(new Font("Ebrima",Font.PLAIN,15));
        mybutton.setBackground(Color.white);
        mybutton.setBounds(175,40,100,30);

        password.add(mybutton);

        mybutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (myText.getText().isEmpty())
                {
                    Frame error = new JFrame();
                    JOptionPane.showMessageDialog(error, "Please fill in your new password before saving.", "Error", JOptionPane.ERROR_MESSAGE);

                }
                else
                {
                    DBMethods db = new DBMethods();
                    String set = db.makeGETRequest("https://studev.groept.be/api/a21ib2c03/SetPassword/"+myText.getText()+"/"+user);
                    JFrame confirmation = new JFrame();
                    JOptionPane.showMessageDialog(confirmation, "Password was successfully updated.");
                    String[] arguments = new String[] {""};
                    password.dispose();
                    }


                }

        });

        password.setVisible(true);
    }

}
