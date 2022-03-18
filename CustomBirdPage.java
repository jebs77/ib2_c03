import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CustomBirdPage extends JFrame {

    private JLabel myLabel0;
    private JLabel myLabel1;
    private JLabel myLabel2;
    private JLabel myLabel3;
    private JLabel myLabel4;
    private JLabel myLabel5;
    private JLabel myLabel6;
    private JLabel myLabel7;
    private JPanel myPanel;
    private JButton myButton4;
    private JTextField myText1;
    private JTextField myText2;
    private JTextField myText3;

    public CustomBirdPage(){
        super("SMART BIRD FEEDER INTERFACE");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myPanel = new JPanel();
        //myPanel.setBackground(Color.white);


        //Create the titel
        myLabel0 = new JLabel();
        myLabel0.setText("Custom Bird Configuration");
        myLabel0.setBounds(130,0,600,60);
        myLabel0.setFont(new Font("Gill Sans Ultra Bold Condensed",Font.PLAIN,30));
        myPanel.add(myLabel0);

        myLabel1 = new JLabel();
        myLabel1.setOpaque(true);
        myLabel1.setBackground(Color.black);
        myLabel1.setBounds(0,65,600,2);
        //myLabel1.setFont(new Font("",Font.PLAIN,30));
        myPanel.add(myLabel1);

        // Create Titel Label
        /*
        myLabel2 = new JLabel();
        myLabel2.setText("CUSTOM BIRD CONFIGURATION");
        myLabel2.setFont(new Font("bird",Font.PLAIN,30));
        myLabel2.setBounds(50,80,500,50);
        myPanel.add(myLabel2);*/

        // Create Name Label
        myLabel3 = new JLabel();
        myLabel3.setText("bird name");
        myLabel3.setFont(new Font("Ebrima",Font.PLAIN,15));
        myLabel3.setBounds(260,150,500,50);
        myPanel.add(myLabel3);



        // Create Range Label
        myLabel4 = new JLabel();
        myLabel4.setText("weight ranges");
        myLabel4.setFont(new Font("Ebrima",Font.PLAIN,15));
        myLabel4.setBounds(250,250,500,50);
        myPanel.add(myLabel4);

        //Create labels for textfields

        myLabel5 = new JLabel();
        myLabel5.setText("from");
        myLabel5.setFont(new Font("Ebrima",Font.PLAIN,15));
        myLabel5.setBounds(50,300,90,50);
        myPanel.add(myLabel5);

        myLabel6 = new JLabel();
        myLabel6.setText("g                  to ");
        myLabel6.setFont(new Font("Ebrima",Font.PLAIN,15));
        myLabel6.setBounds(210,300,200,50);
        myPanel.add(myLabel6);

        myLabel7 = new JLabel();
        myLabel7.setText("g");
        myLabel7.setFont(new Font("Ebrima",Font.PLAIN,15));
        myLabel7.setBounds(510,300,90,50);
        myPanel.add(myLabel7);


        //myPanel op JFrame zetten
        this.setContentPane(myPanel);
    }
    public static void main(String user) {

        JFrame test = new CustomBirdPage();
        //the frame needs to become visible
        test.setLayout(null);
        test.setSize(600,800);
        test.setResizable(false);

        //Create Name TextField
        JTextField name = new JTextField();
        name.setBounds(100,200,400,50);
        test.add(name);

        //Create minimum weight field
        JTextField minWeight = new JTextField();
        minWeight.setBounds(100,300,100,50);
        test.add(minWeight);

        //Create minimum weight field
        JTextField maxWeight = new JTextField();
        maxWeight.setBounds(400,300,100,50);
        test.add(maxWeight);


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
                String[] arguments = new String[] {""};
                BirdMenu.main(user);
                test.dispose();
            }
        });

        //Create the configure custom bird button



        JButton myButton4 = new JButton();
        myButton4.setText("CONFIRM");
        myButton4.setBounds(200,400,200,80);
        myButton4.setBackground(Color.white);
        myButton4.setFont(new Font("Ebrima",Font.PLAIN,15));
        test.add(myButton4);

        myButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {




                //test.dispose();
                String nameBird = name.getText();
                int min = Integer.parseInt(minWeight.getText());
                int max = Integer.parseInt(maxWeight.getText());

                //Find out if bird is already in db or not
                boolean alreadyAdded = false;
                DBMethods db = new DBMethods();
                String names = db.makeGETRequest("https://studev.groept.be/api/a21ib2c03/GetBirdNames");
                ArrayList<String> lijst = db.returnJSONNames(names);
                for(String name: lijst){
                    if(name.equals(nameBird)==true){
                        alreadyAdded = true;
                    }
                }

                if (name.getText().isEmpty() || minWeight.getText().isEmpty()|| maxWeight.getText().isEmpty())
                {
                    JFrame error = new JFrame();
                    JOptionPane.showMessageDialog(error, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    if (min > max || min < 1)
                    {
                        JFrame error = new JFrame();
                        JOptionPane.showMessageDialog(error, "Invalid weight maxima. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        if(alreadyAdded == true){
                            JFrame error = new JFrame();
                            JOptionPane.showMessageDialog(error, "Bird was already added.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else {
                            int index = lijst.size() + 1 ;
                            db.makeGETRequest("https://studev.groept.be/api/a21ib2c03/AddBird/"+index+"/"+nameBird+"/"+min+"/"+max);
                            JFrame confirmation = new JFrame();
                            JOptionPane.showMessageDialog(confirmation, "Custom bird was successfully configured.");
                            //String[] arguments = new String[]{""};
                            MainPage.main(user);
                            test.dispose();
                        }
                    }
                }

            }
        });

        //Set visible after al buttons are added
        test.setVisible(true);

    }


}