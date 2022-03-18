import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BirdMenu extends JFrame {


    private JLabel myLabel0;
    private JLabel myLabel1;
    private JLabel myLabel2;
    private JPanel myPanel;


    public BirdMenu(){
        super("YOUR SMART BIRD FEEDER INTERFACE");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myPanel = new JPanel();
        //myPanel.setBackground(Color.white);

        myLabel0 = new JLabel();
        myLabel0.setText("Bird Menu");
        myLabel0.setBounds(230,0,600,80);
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

    public static void main(String user) {

        JFrame test = new BirdMenu();
        //the frame needs to become visible
        test.setLayout(null);
        test.setSize(600,800);
        test.setResizable(false);
        //ui.pack();

        //Create the back button
        JButton myButton1 = new JButton();
        myButton1.setText("<");
        myButton1.setBounds(10,10,50,50);
        myButton1.setFont(new Font("Gill Sans Ultra Bold Condensed",Font.BOLD,15));
        myButton1.setBackground(Color.white);

        test.add(myButton1);
        myButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                test.dispose();
                //String [] arguments = new String[] {""};
                MainPage.main(user);
            }
        });




        //Create the bird buttons
        JButton myButton4 = new JButton();
        myButton4.setText("BLACKBIRD");
        myButton4.setBounds(150,80,300,25);
        myButton4.setBackground(Color.white);
        myButton4.setFont(new Font("Ebrima", Font.PLAIN, 15));
        test.add(myButton4);

        myButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Plane.main(user,"BLACKBIRD","blackbird.jpg",90,110,250);
                test.dispose();
            }
        });



        JButton myButton5 = new JButton();
        myButton5.setText("CHAFFINCH");
        myButton5.setBounds(150,110,300,25);
        myButton5.setBackground(Color.white);
        myButton5.setFont(new Font("Ebrima", Font.PLAIN, 15));
        test.add(myButton5);

        myButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Plane.main(user,"CHAFFINCH","Chaffinch.jpg",19,23,250);
                test.dispose();
            }
        });


        JButton myButton6 = new JButton();
        myButton6.setText("EUROPEAN ROBIN");
        myButton6.setBounds(150,140,300,25);
        myButton6.setBackground(Color.white);
        myButton6.setFont(new Font("Ebrima", Font.PLAIN, 15));
        test.add(myButton6);

        myButton6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Plane.main(user,"EUROPEAN_ROBIN","Robin.jpg",16,22,180);
                test.dispose();
            }
        });


        JButton myButton7 = new JButton();
        myButton7.setText("GREAT TIT");
        myButton7.setBounds(150,170,300,25);
        myButton7.setBackground(Color.white);
        myButton7.setFont(new Font("Ebrima", Font.PLAIN, 15));
        test.add(myButton7);

        myButton7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Plane.main(user,"GREAT TIT","Tit.jpg",14,22,250);
                test.dispose();
            }
        });


        JButton myButton8 = new JButton();
        myButton8.setText("EUROPEAN GOLDFINCH");
        myButton8.setBounds(150,200,300,25);
        myButton8.setBackground(Color.white);
        myButton8.setFont(new Font("Ebrima", Font.PLAIN, 15));
        test.add(myButton8);

        myButton8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Plane.main(user,"EUROPEAN_GOLDFINCH","EuropeanGoldfinch.jpg",14,19,250);
                test.dispose();

            }
        });

        JButton myButton9 = new JButton();
        myButton9.setText("HOUSE SPARROW");
        myButton9.setBounds(150,230,300,25);
        myButton9.setBackground(Color.white);
        myButton9.setFont(new Font("Ebrima", Font.PLAIN, 15));
        test.add(myButton9);

        myButton9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Plane.main(user,"HOUSE_SPARROW","Sparrow.png",24,40,280);
                test.dispose();
            }
        });

        JButton myButton10 = new JButton();
        myButton10.setText("EURASIAN BLUE TIT");
        myButton10.setBounds(150,260,300,25);
        myButton10.setBackground(Color.white);
        myButton10.setFont(new Font("Ebrima", Font.PLAIN, 15));
        test.add(myButton10);

        myButton10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Plane.main(user,"EURASIAN_BLUE_TIT","EurasianBlueTit.jpg",8,12,280);
                test.dispose();
            }
        });

        JButton myButton11 = new JButton();
        myButton11.setText("EURASIAN JAY");
        myButton11.setBounds(150,290,300,25);
        myButton11.setBackground(Color.white);
        myButton11.setFont(new Font("Ebrima", Font.PLAIN, 15));
        test.add(myButton11);

        myButton11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Plane.main(user,"EURASIAN_JAY","EurasianJay.jpg",150,170,280);
                test.dispose();
            }
        });

        JButton myButton12 = new JButton();
        myButton12.setText("EURASIAN NUTHATCH");
        myButton12.setBounds(150,320,300,25);
        myButton12.setBackground(Color.white);
        myButton12.setFont(new Font("Ebrima", Font.PLAIN, 15));
        test.add(myButton12);

        myButton12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Plane.main(user,"EURASIAN_NUTHATCH","EurasianNuthatch.jpg",18,22,280);
                test.dispose();
            }
        });

        JButton myButton13 = new JButton();
        myButton13.setText("COAL TIT");
        myButton13.setBounds(150,350,300,25);
        myButton13.setBackground(Color.white);
        myButton13.setFont(new Font("Ebrima", Font.PLAIN, 15));
        test.add(myButton13);

        myButton13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Plane.main(user,"COAL_TIT","CoalTit.jpg",8,11,280);
                test.dispose();
            }
        });

        JButton myButton14 = new JButton();
        myButton14.setText("GREAT SPOTTED WOODPECKER");
        myButton14.setBounds(150,380,300,25);
        myButton14.setBackground(Color.white);
        myButton14.setFont(new Font("Ebrima", Font.PLAIN, 15));
        test.add(myButton14);

        myButton14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Plane.main(user,"GREAT_SPOTTED_WOODPECKER","GreatSpottedWoodpecker.jpg",80,90,280);
                test.dispose();
            }
        });

        JButton myButton15 = new JButton();
        myButton15.setText("EURASIAN MAGPIE");
        myButton15.setBounds(150,410,300,25);
        myButton15.setBackground(Color.white);
        myButton15.setFont(new Font("Ebrima", Font.PLAIN, 15));
        test.add(myButton15);

        myButton15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Plane.main(user,"EURASIAN_MAGPIE","EurasianMagpie.jpg",210,270,280);
                test.dispose();
            }
        });

        JButton myButton16 = new JButton();
        myButton16.setText("WOOD DOVE");
        myButton16.setBounds(150,440,300,25);
        myButton16.setBackground(Color.white);
        myButton16.setFont(new Font("Ebrima", Font.PLAIN, 15));
        test.add(myButton16);

        myButton16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Plane.main(user,"WOOD_DOVE","WoodDove.jpg",300,620,280);
                test.dispose();
            }
        });

        JButton myButton17 = new JButton();
        myButton17.setText("EURASIAN COLLARED DOVE");
        myButton17.setBounds(150,470,300,25);
        myButton17.setBackground(Color.white);
        myButton17.setFont(new Font("Ebrima", Font.PLAIN, 15));
        test.add(myButton17);

        myButton17.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Plane.main(user,"EURASIAN_COLLARED_DOVE","EurasianCollaredDove.jpg",140,160,280);
                test.dispose();
            }
        });

        JButton myButton18 = new JButton();
        myButton18.setText("EUROPEAN GREEN WOODPECKER");
        myButton18.setBounds(150,500,300,25);
        myButton18.setBackground(Color.white);
        myButton18.setFont(new Font("Ebrima", Font.PLAIN, 15));
        test.add(myButton18);

        myButton18.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Plane.main(user,"EUROPEAN_GREEN_WOODPECKER","EuropeanGreenWoodpecker.jpg",170,190,280);
                test.dispose();
            }
        });

        JButton myButton19 = new JButton();
        myButton19.setText("EURASIAN JACKDAW");
        myButton19.setBounds(150,530,300,25);
        myButton19.setBackground(Color.white);
        myButton19.setFont(new Font("Ebrima", Font.PLAIN, 15));
        test.add(myButton19);

        myButton19.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Plane.main(user,"EURASIAN_JACKDAW","EurasianJackdaw.jpg",240,260,280);
                test.dispose();
            }
        });

        JButton myButton20 = new JButton();
        myButton20.setText("CROW");
        myButton20.setBounds(150,560,300,25);
        myButton20.setBackground(Color.white);
        myButton20.setFont(new Font("Ebrima", Font.PLAIN, 15));
        test.add(myButton20);

        myButton20.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Plane.main(user,"CROW","Crow.jpg",500,540,280);
                test.dispose();
            }
        });


        //Create the custom bird menu button
        JButton myButton0 = new JButton();
        myButton0.setText("CUSTOM BIRDS");
        myButton0.setBounds(150,590,300,40);
        myButton0.setBackground(Color.white);
        myButton0.setFont(new Font("Ebrima", Font.PLAIN, 15));
        test.add(myButton0);
        myButton0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] arguments = new String[] {""};
                CustomBirds.main(user);
                test.dispose();

            }
        });

        //Set visible after al buttons are added
        test.setVisible(true);

    }



        //Set visible after al buttons are added
        //test.setVisible(true);





}