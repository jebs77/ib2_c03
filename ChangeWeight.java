import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChangeWeight extends JFrame {
    private JLabel myLabel1;
    private JLabel myLabel2;
    private JLabel myLabel3;
    private JLabel myLabel4;
    private JPanel myPanel;
public ChangeWeight(){
    super("Change weight");
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    myPanel = new JPanel();

    myLabel1 = new JLabel();
    myLabel1.setText("CURRENT MIN:");
    myLabel1.setBounds(10,10,120,30);
    myLabel1.setFont(new Font("Ebrima",Font.PLAIN,15));
    myPanel.add(myLabel1);

    myLabel2 = new JLabel();
    myLabel2.setText("CURRENT MAX:");
    myLabel2.setBounds(10,60,120,30);
    myLabel2.setFont(new Font("Ebrima",Font.PLAIN,15));
    myPanel.add(myLabel2);

    myLabel3 = new JLabel();
    myLabel3.setText("NEW MIN:");
    myLabel3.setBounds(10,110,100,30);
    myLabel3.setFont(new Font("Ebrima",Font.PLAIN,15));
    myPanel.add(myLabel3);

    myLabel4 = new JLabel();
    myLabel4.setText("NEW MAX:");
    myLabel4.setBounds(10,160,100,30);
    myLabel4.setFont(new Font("Ebrima",Font.PLAIN,15));
    myPanel.add(myLabel4);

    super.setContentPane(myPanel);
}
    //String name, int min, int max
public static void main(String name,String user){


    JFrame weightChange = new ChangeWeight();
    weightChange.setLayout(null);
    weightChange.setSize(300,300);
    weightChange.setResizable(false);

    DBMethods db = new DBMethods();
    String weights = db.makeGETRequest("https://studev.groept.be/api/a21ib2c03/GetWeight/"+name);
    ArrayList<String> lijst = db.returnJSONWeight(weights);
    String min = lijst.get(1);
    String max = lijst.get(0);

    JTextField myText1 = new JTextField();
    myText1.setBounds(120,110,100,30);
    weightChange.add(myText1);

    JTextField myText2 = new JTextField();
    myText2.setBounds(120,160,100,30);
    weightChange.add(myText2);

    JLabel myLabel1 = new JLabel();
    myLabel1.setText(String.valueOf(min));
    myLabel1.setFont(new Font("Ebrima",Font.PLAIN,15));
    myLabel1.setBounds(140,10,150,30);
    weightChange.add(myLabel1);

    JLabel myLabel2 = new JLabel();
    myLabel2.setText(String.valueOf(max));
    myLabel2.setFont(new Font("Ebrima",Font.PLAIN,15));
    myLabel2.setBounds(140,60,150,30);
    weightChange.add(myLabel2);

    JButton mybutton = new JButton();
    mybutton.setText("SAVE");
    mybutton.setFont(new Font("Ebrima",Font.PLAIN,15));
    mybutton.setBackground(Color.white);
    mybutton.setBounds(10,210,100,30);
    weightChange.add(mybutton);

    mybutton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            DBMethods db = new DBMethods();
            String delete = db.makeGETRequest("https://studev.groept.be/api/a21ib2c03/RemoveBird/"+name);
            String names = db.makeGETRequest("https://studev.groept.be/api/a21ib2c03/GetBirdNames");
            ArrayList<String> lijst = db.returnJSONNames(names);
            int iD = lijst.size() + 1;
            if(myText1.getText().isEmpty() && myText2.getText().isEmpty()){
                JFrame error = new JFrame();
                JOptionPane.showMessageDialog(error, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                if (Integer.parseInt(myText1.getText()) > Integer.parseInt(myText2.getText()) || Integer.parseInt(myText2.getText()) < 1)
                {
                    JFrame error = new JFrame();
                    JOptionPane.showMessageDialog(error, "Invalid weight maxima. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    String add = db.makeGETRequest("https://studev.groept.be/api/a21ib2c03/AddBird/"+iD+"/"+name+"/"+myText1.getText()+"/"+myText2.getText());
                    weightChange.dispose();
                    JFrame confirmation = new JFrame();
                    JOptionPane.showMessageDialog(confirmation, "Weightrange succesfully changed.");
                    String[] arguments = new String[] {""};
                    BirdMenu.main(user);
                }
            }
        }
    });




    weightChange.setVisible(true);


}
}
