import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import org.json.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.*;

public class Plane extends JFrame {
    private ImageIcon icon;
    private JLabel myLabel0;
    private JLabel myLabel1;
    private JLabel myLabel2;
    private JLabel myLabel3;
    private JLabel myLabel4;
    private JPanel myPanel;
    private JButton myButton1;
    private JButton myButton2;
    private JButton myButton3;
    private JButton myButton4;
    private JButton myButton5;

    private JTextField myTextField;

    public Plane(String bird,String birdFile ,int min,int max,int start){
        super("YOUR SMART BIRD FEEDER INTERFACE");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        myPanel = new JPanel();


        //Create the titel
        myLabel0 = new JLabel();
        myLabel0.setText("Bird Info");
        myLabel0.setBounds(240,0,600,80);
        myLabel0.setFont(new Font("Gill Sans Ultra Bold Condensed",Font.PLAIN,30));
        myPanel.add(myLabel0);

        myLabel1 = new JLabel();
        myLabel1.setOpaque(true);
        myLabel1.setBackground(Color.black);
        myLabel1.setBounds(0,65,600,2);
        //myLabel1.setFont(new Font("",Font.PLAIN,30));
        myPanel.add(myLabel1);


        // Create weight Label
        myLabel2 = new JLabel();
        myLabel2.setText("weight range:  "+min+" g  -  "+max+" g");
        myLabel2.setFont(new Font("Ebrima",Font.PLAIN,15));
        myLabel2.setBounds(195,350,300,50);
        myPanel.add(myLabel2);


        // Create Titel Label
        myLabel3 = new JLabel();
        myLabel3.setText(bird);
        myLabel3.setFont(new Font("Ebrima",Font.PLAIN,20));
        myLabel3.setBounds(200,100,300,50);
        myPanel.add(myLabel3);


        icon = new ImageIcon(birdFile);
        myLabel4 = new JLabel();
        myLabel4.setIcon(icon);
        myLabel4.setOpaque(true);
        myLabel4.setBounds(200,150,200,200);
        myPanel.add(myLabel4);


        //myPanel op JFrame zetten
        this.setContentPane(myPanel);

    }


    public static void main(String user, String bird,String birdfile,int min,int max, int start) {
        //generate my UI
        JFrame test= new Plane(bird ,birdfile,min,max,start);
        //the frame needs to become visible
        test.setLayout(null);
        test.setSize(600,800);
        test.setResizable(false);
        test.setVisible(true);
        //ui.pack();

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

        //Create Remove button
        JButton myButton4 = new JButton();
        myButton4.setText("REMOVE");
        myButton4.setBounds(160,540,120,80);
        myButton4.setBackground(Color.white);
        myButton4.setFont(new Font("Ebrima",Font.PLAIN,15));
        test.add(myButton4);

        myButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JFrame confirmation = new JFrame();
                JOptionPane.showMessageDialog(confirmation, bird +" successfully removed from birds fed.");

                //Remove bird
                DBMethods db = new DBMethods();
                String response = db.makeGETRequest("https://studev.groept.be/api/a21ib2c03/RemoveBird/"+bird);


                //Open the bird menu
                String[] arguments = new String[] {""};
                BirdMenu.main(user);
                test.dispose();


            }
        });

        JButton myButton5 = new JButton();
        myButton5.setText("ADD");
        myButton5.setBounds(320,540,120,80);
        myButton5.setBackground(Color.white);
        myButton5.setFont(new Font("Ebrima",Font.PLAIN,15));
        test.add(myButton5);
        myButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Find out if bird is already in db or not
                boolean alreadyAdded = false;
                DBMethods db = new DBMethods();
                String names = db.makeGETRequest("https://studev.groept.be/api/a21ib2c03/GetBirdNames");
                ArrayList<String> lijst = db.returnJSONNames(names);
                for(String name: lijst){
                    if(name.equals(bird)==true){
                        alreadyAdded = true;
                    }
                }

                //Add if the bird isn't in the db yet
                if(alreadyAdded == false){
                    int index = lijst.size() + 1 ;
                    db.makeGETRequest("https://studev.groept.be/api/a21ib2c03/AddBird/"+index+"/"+bird+"/"+min+"/"+max);
                    JFrame confirmation = new JFrame();
                    JOptionPane.showMessageDialog(confirmation, bird +" successfully added to birds fed.");
                }
                //Show frame if bird is already added
                else{
                    JFrame already = new JFrame();
                    JOptionPane.showMessageDialog( already,bird +" was already added");
                }


                //Back to bird menu
                String[] arguments = new String[] {""};
                BirdMenu.main(user);
                test.dispose();

            }
        });




    }



}
