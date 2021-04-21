package View;

import AI.ArtificalIntellienge;
import Controller.GameController;
import Model.Location;
import Model.Status;
import Model.User;
import Network.NetworkInterface;
import Network.SocketClient;
import Network.SocketServer;
import Utils.Extensions;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.MalformedURLException;

public class TicTacGui extends JFrame implements NetworkInterface {
    private User user = User.PLayer;
    private Status status = Status.isoffline;
    private boolean is2player = false;
   boolean gameended = false;
   private JLabel x;
    private JLabel playerimg;
   private JLabel tie;
   private JLabel o;
   private SocketServer server = new SocketServer();
   private GameController controller;
    private JLabel po;
    private JButton[][] buttonGrid;
    private JLabel lbnetwork = new JLabel("Go Online!");


    public JLabel getPo(){
        return po;
    }

    public JLabel getx(){
        return x;
    }
    public JLabel getPlayerimg(){
        return playerimg;
    }
    public JLabel getLbnetwork(){
        return lbnetwork;
    }
    public JLabel geto(){
        return o;
    }
    public JLabel gettie(){
        return tie;
    }

    public boolean is2player(){
        return is2player;
    }
    public void setgameended(boolean game){
        gameended = game;
    }
    public void setIs2player(boolean player){
        this.is2player = player;
    }
    public void setStatus(Status status){
        this.status = status;
    }

    public Status getStatus(){
        return status;
    }
    public void setUser(User u){
        this.user = u;
    }
    public User getUser() {
        return user;
    }

    public boolean getgameended(){
        return gameended;
    }
    public JButton[][] getbtgrid(){
        return buttonGrid;
    }

    public TicTacGui(String title, int width, int height) throws MalformedURLException {
        super(title);
        server.add(this);
        controller = new GameController(this);
        setSize(width, height);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);    //Null means use Absolute coordinates.
        showwelcomescreen();
        setVisible(true);   //After setting up all components
    }

    private void create3x3grid(String player) throws MalformedURLException {
        ImageIcon onep = new ImageIcon(
                new File("/Users/ruchit/IdeaProjects/TicTacToe/assets/onep.png").toURI().toURL());

           playerimg = new JLabel(onep);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        //create grid layout
        GridLayout experimentLayout = new GridLayout(0, 3, 2, 2);
        JPanel grid = new JPanel();
        grid.setLayout(experimentLayout);
        getContentPane().setBackground(Color.WHITE);
        add(grid);

        //add heading labels
        JPanel hp = new JPanel();
        hp.setBackground(Color.BLACK);
        po = new JLabel(player + "(O)", SwingConstants.CENTER);
        lbnetwork.setForeground(Color.GREEN);
        lbnetwork.setBackground(Color.RED);
        po.setForeground(Color.white);
        po.setBorder(new EmptyBorder(0, 0, 0, 10));
        hp.setLayout(new BoxLayout(hp, BoxLayout.X_AXIS));
        hp.add(new JLabel("   PLAYER (X)", SwingConstants.CENTER)).setForeground(Color.white);
        lbnetwork.setBorder(new EmptyBorder(0, 25, 0, 0));
        hp.add(lbnetwork);
        JLabel lbtie = new JLabel("   TIE", SwingConstants.CENTER);
        lbtie.setForeground(Color.white);
        lbtie.setBorder(new EmptyBorder(0, 125, 0, 0));
        hp.add(lbtie);

        hp.add(Box.createHorizontalGlue());
        hp.add(po);


        //add score labels
        JPanel vp = new JPanel();
        vp.setLayout(new BoxLayout(vp, BoxLayout.X_AXIS));
        vp.setBackground(Color.BLACK);
        Border xmargin = new EmptyBorder(0, 20, 0, 0);
        Border omargin = new EmptyBorder(0, 0, 0, 20);
        x = new JLabel("0");
        x.setBorder(xmargin);
        x.setSize(new Dimension(300, 60));
        x.setForeground(Color.WHITE);
        tie = new JLabel("0", SwingConstants.CENTER);
        tie.setSize(new Dimension(100, 60));
        tie.setForeground(Color.WHITE);
        o = new JLabel("0", SwingConstants.CENTER);
        o.setSize(new Dimension(100, 60));
        o.setMaximumSize(new Dimension(100, 60));
        o.setForeground(Color.WHITE);
        o.setBorder(omargin);
        vp.add(x);
        vp.add(Box.createHorizontalGlue());
        vp.add(tie);
        vp.add(Box.createHorizontalGlue());
        vp.add(o);

        JPanel p = new JPanel();
        p.setBackground(Color.BLACK);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(hp);
        p.add(vp);

        JPanel mp = new JPanel();
        mp.setBackground(Color.BLACK);
        mp.setLayout(new BoxLayout(mp, BoxLayout.X_AXIS));
        mp.add(p);
        mp.add(playerimg);

        add(mp);

        //add click listener for network label
        lbnetwork.addMouseListener(controller.networkadapter);

        //add click listner for player images switching
        playerimg.addMouseListener(controller.playeradaper);

        //Create array of grid buttons
        buttonGrid = new JButton[3][3];


                //run loop for adding grid buttons
                for (int row = 0; row < buttonGrid.length; row++) {
                    for (int col = 0; col < buttonGrid[row].length; col++) {
                        buttonGrid[row][col] = new JButton();
                        buttonGrid[row][col].addActionListener(controller.listener3x3);
                        buttonGrid[row][col].setFont(Extensions.getfont("/Users/ruchit/IdeaProjects/TicTacToe/assets/Hanalei-Regular.ttf"));
                        buttonGrid[row][col].setBorder(BorderFactory.createLineBorder(Color.WHITE));
                        buttonGrid[row][col].setOpaque(true);
                        buttonGrid[row][col].setBackground(Color.BLACK);
                        buttonGrid[row][col].setForeground(Color.WHITE);

                        grid.add(buttonGrid[row][col]);
                        add(grid);

                    }
                }
            }

  private void showwelcomescreen() throws MalformedURLException {
        //<editor-fold desc="Background">
        //show background
      JPanel mp = new JPanel(null);
        ImageIcon backgroundIcon = new ImageIcon(
                new File("/Users/ruchit/IdeaProjects/TicTacToe/assets/background.gif").toURI().toURL());


        Image backimage = backgroundIcon.getImage().getScaledInstance(650,650,Image.SCALE_DEFAULT);
        backgroundIcon = new ImageIcon(backimage);

       JLabel background = new JLabel(backgroundIcon);
      background.setBounds(0,0,650,650);

      //</editor-fold>
        //<editor-fold desc="Heading Labels">
      JPanel ly = new JPanel(new BorderLayout());
      JPanel greet = new JPanel();
      greet.setLayout(new BoxLayout(greet,BoxLayout.Y_AXIS));

      greet.setOpaque(false);
       JLabel welcom = new JLabel("Tic Tac Toe");
      JLabel intro = new JLabel("Choose Your Mode");
      welcom.setAlignmentX(Component.CENTER_ALIGNMENT);
      intro.setAlignmentX(Component.CENTER_ALIGNMENT);
      welcom.setFont(Extensions.getfont("/Users/ruchit/IdeaProjects/TicTacToe/assets/Hanalei-Regular.ttf"));
      intro.setForeground(Color.white);
      welcom.setForeground(Color.WHITE);
      //</editor-fold>
        //<editor-fold desc="Mode Buttons">
      //online button
      JPanel p = new JPanel();
      p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
      JButton online = new JButton("3X3");
      online.setFont(Extensions.getfont("/Users/ruchit/IdeaProjects/TicTacToe/assets/Hanalei-Regular.ttf"));
      online.setFocusPainted(false);
      online.setOpaque(false);
      online.setContentAreaFilled(false);
      online.setBorderPainted(false);
      online.setForeground(Color.WHITE);

      online.setAlignmentX(Component.CENTER_ALIGNMENT);


      //offline button
      JButton offline = new JButton("5X5");
      offline.setFocusPainted(false);
      offline.setOpaque(false);
      offline.setContentAreaFilled(false);
      offline.setBorderPainted(false);
      offline.setForeground(Color.WHITE);
      offline.setFont(Extensions.getfont("/Users/ruchit/IdeaProjects/TicTacToe/assets/Hanalei-Regular.ttf"));

      offline.setAlignmentX(Component.CENTER_ALIGNMENT);

      p.setOpaque(false);
      p.add(online);
      p.add(offline);
      //</editor-fold>
        //<editor-fold desc="Add Elements to layout">
      greet.add(welcom);
      greet.add(intro);
      p.setBorder(new EmptyBorder(0, 150, 0, 0));
      ly.setOpaque(false);
      ly.add(greet,BorderLayout.NORTH);
      ly.add(p,BorderLayout.CENTER);


      ly.setBounds(0,0,650,650);
      mp.setBounds(0,0,650,650);
       mp.add(ly);
       mp.add(background);
       add(mp);
    //</editor-fold>
        //<editor-fold desc="Button Listeners">
       online.addActionListener(e -> {
           mp.setVisible(false);
           try {
               create3x3grid("Computer");
           } catch (MalformedURLException malformedURLException) {
               malformedURLException.printStackTrace();
           }

       });

       offline.addActionListener(e->{
           status = Status.isoffline;
           mp.setVisible(false);
           try {
               create5x5grid("Computer");
           } catch (MalformedURLException malformedURLException) {
               malformedURLException.printStackTrace();
           }
       });
       //</editor-fold>
    }

    private void create5x5grid(String player) throws MalformedURLException {
        //<editor-fold desc="Create Layout">

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(new Dimension(650,750));
        //create grid layout
        GridLayout experimentLayout = new GridLayout(0,5,2,2);
        JPanel grid = new JPanel();
        grid.setLayout(experimentLayout);
        getContentPane().setBackground(Color.WHITE);
        grid.setMaximumSize(new Dimension(650,650));
        add(grid);

        ImageIcon onep = new ImageIcon(
                new File("/Users/ruchit/IdeaProjects/TicTacToe/assets/onep.png").toURI().toURL());

        playerimg = new JLabel(onep);

        //</editor-fold>
        //<editor-fold desc="Heading Labels">
        //add heading labels
        JPanel hp = new JPanel();
        hp.setBackground(Color.BLACK);
        po = new JLabel(player +"(O)",SwingConstants.CENTER);
        po.setForeground(Color.white);
        po.setBorder(new EmptyBorder(0,0,0,10));
        hp.setLayout(new BoxLayout(hp,BoxLayout.X_AXIS));
        hp.add(new JLabel("   PLAYER (X)",SwingConstants.CENTER)).setForeground(Color.white);
        lbnetwork.setForeground(Color.GREEN);
        lbnetwork.setBackground(Color.RED);
        lbnetwork.setBorder(new EmptyBorder(0, 25, 0, 0));
        hp.add(lbnetwork);
        JLabel lbtie = new JLabel("TIE", SwingConstants.CENTER);
        lbtie.setForeground(Color.white);
        lbtie.setBorder(new EmptyBorder(0, 125, 0, 0));
        hp.add(lbtie);
        hp.add(Box.createHorizontalGlue());
        hp.add(po);
        //</editor-fold>
        //<editor-fold desc="Score Labels and add to layout">
        //add score labels
        JPanel vp = new JPanel();
        vp.setLayout(new BoxLayout(vp,BoxLayout.X_AXIS));
        vp.setBackground(Color.BLACK);
        Border xmargin = new EmptyBorder(0,20,0,0);
        Border omargin = new EmptyBorder(0,0,0,20);
        x = new JLabel("0");
        x.setBorder(xmargin);
        x.setSize(new Dimension(300,60));
        x.setForeground(Color.WHITE);
        tie = new JLabel("0",SwingConstants.CENTER);
        tie.setSize(new Dimension(100,60));
        tie.setForeground(Color.WHITE);
        o = new JLabel("0",SwingConstants.CENTER);
        o.setSize(new Dimension(100,60));
        o.setMaximumSize(new Dimension(100,60));
        o.setForeground(Color.WHITE);
        o.setBorder(omargin);
        vp.add(x);
        vp.add(Box.createHorizontalGlue());
        vp.add(tie);
        vp.add(Box.createHorizontalGlue());
        vp.add(o);

        JPanel p = new JPanel();
        p.setBackground(Color.BLACK);
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        p.add(hp);
        p.add(vp);

        JPanel mp = new JPanel();
        mp.setBackground(Color.BLACK);
        mp.setLayout(new BoxLayout(mp, BoxLayout.X_AXIS));
        mp.add(p);
        mp.add(playerimg);

        add(mp);
        //</editor-fold>
        //<editor-fold desc="Add Listeners">
        lbnetwork.addMouseListener(controller.networkadapter);

        playerimg.addMouseListener(controller.playeradaper);
        //</editor-fold>
        //<editor-fold desc="Create Array grid of buttons">
        //Create array of grid buttons
       buttonGrid  = new JButton[5][5];

        //add action list and logic for game result



        //run loop for adding grid buttons
        for (int row = 0; row < buttonGrid.length; row++) {
            for (int col = 0; col < buttonGrid[row].length; col++) {
                buttonGrid[row][col] = new JButton();
                buttonGrid[row][col].addActionListener(controller.listener5x5);
                buttonGrid[row][col].setFont(Extensions.getfont("/Users/ruchit/IdeaProjects/TicTacToe/assets/Hanalei-Regular.ttf"));
                buttonGrid[row][col].setBorder(BorderFactory.createLineBorder(Color.WHITE));
                buttonGrid[row][col].setOpaque(true);
                buttonGrid[row][col].setBackground(Color.BLACK);
                buttonGrid[row][col].setForeground(Color.WHITE);

                grid.add(buttonGrid[row][col]);
                add(grid);

            }
        }
        //</editor-fold>

    }

    @Override
    public void received(Location s) {
        buttonGrid[s.gettrow()][s.gettcol()].doClick();
    }

}
