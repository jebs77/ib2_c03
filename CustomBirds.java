import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class CustomBirds extends JFrame {
    private JLabel myLabel0;
    private JLabel myLabel1;
    private JLabel myLabel2;
    private JLabel myLabel3;
    private JLabel myLabel4;
    private JLabel myLabel5;
    private JLabel myLabel6;
    private JLabel myLabel7;
    private JPanel myPanel;

    public CustomBirds(){
        super("CustomBirds");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        myPanel = new JPanel();

        myLabel0 = new JLabel();
        myLabel0.setText("Custom Birds");
        myLabel0.setBounds(190,0,180,60);
        myLabel0.setFont(new Font("Gill Sans Ultra Bold Condensed",Font.PLAIN,30));
        myPanel.add(myLabel0);

        myLabel1 = new JLabel();
        myLabel1.setOpaque(true);
        myLabel1.setBackground(Color.black);
        myLabel1.setBounds(0,65,600,2);
        //myLabel1.setFont(new Font("",Font.PLAIN,30));
        myPanel.add(myLabel1);

        this.setContentPane(myPanel);
    }

    public static void main(String user){
        JFrame test = new CustomBirds();
        //the frame needs to become visible
        test.setLayout(null);
        test.setSize(600,800);
        test.setResizable(false);

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


        DBMethods db = new DBMethods();
        String names = db.makeGETRequest("https://studev.groept.be/api/a21ib2c03/GetBirdNames");
        ArrayList<String> lijst = db.returnJSONNames(names);
        ArrayList<String> custom = new ArrayList<>();
        ArrayList<String> namesList = new ArrayList<>(Arrays.asList("BLACKBIRD", "CHAFFINCH", "EUROPEAN_ROBIN", "GREAT_TIT", "EUROPEAN_GOLDFINCH",
                "HOUSE SPARROW", "EURASIAN BLUE TIT", "EURASIAN JAY", "EURASIAN NUTHATCH", "COAL TIT"
                , "GREAT_SPOTTED_WOODPECKER", "EURASIAN_MAGPIE", "WOOD_DOVE", "EURASIAN_COLLARED_DOVE",
                "EUROPEAN_GREEN_WOODPECKER", "EURASIAN_JACKDAW", "CROW"));
        

        for (String bird : lijst)
        {
            int counter = 0;
            for (String name: namesList) {
                if (bird.equals(name)) {
                    counter++;
                }
            }
            if (counter==0)
            {
                custom.add(bird);
            }
            else
            {
                counter=0;
            }
        }

        for(int i = 0; i < custom.size() ; i++){
            String name = custom.get(i);
            int yPosition = 150 + (40 * i);

            JLabel myLabel = new JLabel();
            myLabel.setBounds(20,yPosition,100,20);
            myLabel.setText(name);
            myLabel.setFont(new Font("Ebrima",Font.PLAIN,15));
            test.add(myLabel);

            JButton remove = new JButton();
            remove.setBounds(130,yPosition,160,20);
            remove.setText("REMOVE");
            remove.setFont(new Font("Ebrima",Font.PLAIN,15));
            test.add(remove);
            remove.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame confirmation = new JFrame();
                    JOptionPane.showMessageDialog(confirmation, name +" successfully removed from birds fed.");

                    //Remove bird
                    DBMethods db = new DBMethods();
                    String response = db.makeGETRequest("https://studev.groept.be/api/a21ib2c03/RemoveBird/"+name);


                    //Open the bird menu
                    String[] arguments = new String[] {""};
                    BirdMenu.main(user);
                    test.dispose();
                }
            });

            JButton add = new JButton();
            add.setBounds(310,yPosition,160,20);
            add.setText("CHANGE WEIGHT");
            add.setFont(new Font("Ebrima",Font.PLAIN,15));
            test.add(add);
            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //
                    ChangeWeight.main(name,user);
                    test.dispose();

                }
            });



        }

        JButton myButton10 = new JButton();
        myButton10.setText("ADD A CUSTOM BIRD");
        myButton10.setBounds(130,80,340,50);
        myButton10.setBackground(Color.white);
        myButton10.setFont(new Font("Ebrima", Font.PLAIN, 15));
        test.add(myButton10);

        myButton10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] arguments = new String[] {""};
                CustomBirdPage.main(user);
                test.dispose();
            }
        });



        test.setVisible(true);
    }
}
