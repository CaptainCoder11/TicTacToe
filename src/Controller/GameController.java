package Controller;

import AI.ArtificalIntellienge;
import Model.Location;
import Model.Status;
import Model.User;
import Network.SocketClient;
import Network.SocketServer;
import Utils.Extensions;
import View.TicTacGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameController {
    int xscore = 0;
    int tiescore = 0;
    int oscore = 0;
    TicTacGui gui;
    private int tscores[][] = new int[3][3];
    private int fscores[][] = new int[5][5];
    boolean tie = false;

    boolean won = false;

    public GameController(TicTacGui gui)
    {
        this.gui = gui;
    }
    public int[][] get3x3scores() {
        return tscores;
    }

    public int[][] get5x5scores() {
        return fscores;
    }

    public void reset(JButton[][] button) {
        won = false;
        tie = false;
        tscores = new int[3][3];
        fscores = new int[5][5];
        for (int row = 0; row < button.length; row++) {
            for (int col = 0; col < button[row].length; col++) {
                button[row][col].setText("");
            }
        }

    }

    public int get5x5result() {
        int winner = 0;
        System.out.println("5x5 called");
        if (!won) {
            if (fscores[0][0] == 1 && fscores[1][1] == 1 && fscores[2][2] == 1 && fscores[3][3] == 1 && fscores[4][4] == 1) {
                won = true;
                System.out.println("PLayer 1 Won! 1");
                winner = 1;
            } else if (fscores[0][0] == 2 && fscores[1][1] == 2 && fscores[2][2] == 2 && fscores[3][3] == 2 && fscores[4][4] == 2) {
                won = true;
                System.out.println("PLayer 2 Won! :1");
                winner = 2;
            }

            if (fscores[0][0] == 1 && fscores[1][0] == 1 && fscores[2][0] == 1 && fscores[3][0] == 1 && fscores[4][0] == 1) {
                won = true;
                System.out.println("PLayer 1 Won! 2");
                winner = 1;
            } else if (fscores[0][0] == 2 && fscores[1][0] == 2 && fscores[2][0] == 2 && fscores[3][0] == 2 && fscores[4][0] == 2) {
                won = true;
                System.out.println("PLayer 2 Won! 2");
                winner = 2;
            }

            if (fscores[0][1] == 1 && fscores[1][1] == 1 && fscores[2][1] == 1 && fscores[3][1] == 1 && fscores[4][1] == 1) {
                won = true;
                System.out.println("PLayer 1 Won! 3");
                winner = 1;
            } else if (fscores[0][1] == 2 && fscores[1][1] == 2 && fscores[2][1] == 2 && fscores[3][1] == 2 && fscores[4][1] == 2) {
                won = true;
                System.out.println("PLayer 2 Won! 3");
                winner = 2;
            }


            if (fscores[0][2] == 1 && fscores[1][2] == 1 && fscores[2][2] == 1 && fscores[3][2] == 1 && fscores[4][2] == 1) {
                won = true;
                System.out.println("PLayer 1 Won! 4");
                winner = 1;
            } else if (fscores[0][2] == 2 && fscores[1][2] == 2 && fscores[2][2] == 2 && fscores[3][2] == 2 && fscores[4][2] == 2) {
                won = true;
                System.out.println("PLayer 2 Won! 4");
                winner = 2;
            }


            if (fscores[0][3] == 1 && fscores[1][3] == 1 && fscores[2][3] == 1 && fscores[3][3] == 1 && fscores[4][3] == 1) {
                won = true;
                System.out.println("PLayer 1 Won! 5");
                winner = 1;
            } else if (fscores[0][3] == 2 && fscores[1][3] == 2 && fscores[2][3] == 2 && fscores[3][3] == 2 && fscores[4][3] == 2) {
                won = true;
                winner = 2;
                System.out.println("PLayer 2 Won! 5");
            }

            if (fscores[0][4] == 1 && fscores[1][4] == 1 && fscores[2][4] == 1 && fscores[3][4] == 1 && fscores[4][4] == 1) {
                won = true;
                System.out.println("PLayer 1 Won! 6");
                winner = 1;
            } else if (fscores[0][4] == 2 && fscores[1][4] == 2 && fscores[2][4] == 2 && fscores[3][4] == 2 && fscores[4][4] == 2) {
                won = true;
                System.out.println("PLayer 2 Won! 6");
                winner = 2;
            }


            if (fscores[0][4] == 1 && fscores[1][3] == 1 && fscores[2][2] == 1 && fscores[3][1] == 1 && fscores[4][0] == 1) {
                won = true;
                System.out.println("PLayer 1 Won! 7");
                winner = 1;
            } else if (fscores[0][4] == 2 && fscores[1][3] == 2 && fscores[2][2] == 2 && fscores[3][1] == 2 && fscores[4][0] == 2) {
                won = true;
                System.out.println("PLayer 2 Won! 7");
                winner = 2;
            }

            if (fscores[0][0] == 1 && fscores[0][1] == 1 && fscores[0][2] == 1 && fscores[0][3] == 1 && fscores[0][4] == 1) {
                won = true;
                System.out.println("PLayer 1 Won! 8");
                winner = 1;
            } else if (fscores[0][0] == 2 && fscores[0][1] == 2 && fscores[0][2] == 2 && fscores[0][3] == 2 && fscores[0][4] == 2) {
                won = true;
                System.out.println("PLayer 2 Won! 8");
                winner = 2;
            }

            if (fscores[1][0] == 1 && fscores[1][1] == 1 && fscores[1][2] == 1 && fscores[1][3] == 1 && fscores[1][4] == 1) {
                won = true;
                System.out.println("PLayer 1 Won! 9");
                winner = 1;
            } else if (fscores[1][0] == 2 && fscores[1][1] == 2 && fscores[1][2] == 2 && fscores[1][3] == 2 && fscores[1][4] == 2) {
                won = true;
                System.out.println("PLayer 2 Won! 9");
                winner = 2;
            }
////////
            if (fscores[2][0] == 1 && fscores[2][1] == 1 && fscores[2][2] == 1 && fscores[2][3] == 1 && fscores[2][4] == 1) {
                won = true;
                System.out.println("PLayer 1 Won! 10");
                winner = 1;
            } else if (fscores[2][0] == 2 && fscores[2][1] == 2 && fscores[2][2] == 2 && fscores[2][3] == 2 && fscores[2][4] == 2) {
                won = true;
                System.out.println("PLayer 2 Won! 10");
                winner = 2;
            }

            if (fscores[3][0] == 1 && fscores[3][1] == 1 && fscores[3][2] == 1 && fscores[3][3] == 1 && fscores[3][4] == 1) {
                won = true;
                System.out.println("PLayer 1 Won! 11");
                winner = 1;
            } else if (fscores[3][0] == 2 && fscores[3][1] == 2 && fscores[3][2] == 2 && fscores[3][3] == 2 && fscores[3][4] == 2) {
                won = true;
                System.out.println("PLayer 2 Won! 11");
                winner = 2;
            }

            if (fscores[4][0] == 1 && fscores[4][1] == 1 && fscores[4][2] == 1 && fscores[4][3] == 1 && fscores[4][4] == 1) {
                won = true;
                System.out.println("PLayer 1 Won! 12");
                winner = 1;
            } else if (fscores[4][0] == 2 && fscores[4][1] == 2 && fscores[4][2] == 2 && fscores[4][3] == 2 && fscores[4][4] == 2) {
                won = true;
                System.out.println("PLayer 2 Won! 12");
                winner = 2;
            }
        }

        return winner;

    }


    public int get3x3result() {
        boolean tie = false;
        int winner = 0;
        if (!won) {
            if (tscores[0][0] == 1 && tscores[1][1] == 1 && tscores[2][2] == 1) {
                System.out.println("PLayer 1 Won!");
                won = true;
                winner = 1;
            } else if (tscores[0][0] == 2 && tscores[1][1] == 2 && tscores[2][2] == 2) {
                System.out.println("PLayer 2 Won! :1");
                won = true;
                winner = 2;
            }

            if (tscores[0][0] == 1 && tscores[1][0] == 1 && tscores[2][0] == 1) {
                System.out.println("PLayer 1 Won!");
                won = true;
                winner = 1;
            } else if (tscores[0][0] == 2 && tscores[1][0] == 2 && tscores[2][0] == 2) {
                System.out.println("" + tscores[0][0]);
                System.out.println("" + tscores[1][0]);
                System.out.println("" + tscores[2][0]);
                System.out.println("PLayer 2 Won! 2");
                won = true;
                winner = 2;
            }

            if (tscores[0][1] == 1 && tscores[1][1] == 1 && tscores[2][1] == 1) {
                System.out.println("PLayer 1 Won! 3");
                won = true;
                winner = 1;
            } else if (tscores[0][1] == 2 && tscores[1][1] == 2 && tscores[2][1] == 2) {
                won = true;
                System.out.println("PLayer 2 Won! 4");
                winner = 2;
            }


            if (tscores[0][2] == 1 && tscores[1][2] == 1 && tscores[2][2] == 1) {
                System.out.println("PLayer 1 Won!");
                won = true;
                winner = 1;
            } else if (tscores[0][2] == 2 && tscores[1][2] == 2 && tscores[2][2] == 2) {
                won = true;
                System.out.println("PLayer 2 Won! 5");
                winner = 2;
            }

            if (tscores[0][2] == 1 && tscores[1][1] == 1 && tscores[2][0] == 1) {
                won = true;
                System.out.println("PLayer 1 Won! 6");
                winner = 1;
            } else if (tscores[0][2] == 2 && tscores[1][1] == 2 && tscores[2][0] == 2) {
                won = true;
                System.out.println("PLayer 2 Won! 7");
                winner = 2;
            }

            if (tscores[2][0] == 1 && tscores[2][1] == 1 && tscores[2][2] == 1) {
                won = true;
                System.out.println("PLayer 1 Won!");
                winner = 1;
            } else if (tscores[2][0] == 2 && tscores[2][1] == 2 && tscores[2][2] == 2) {
                won = true;
                System.out.println("PLayer 2 Won! 8");
                winner = 2;
            }


            if (tscores[1][0] == 1 && tscores[1][1] == 1 && tscores[1][2] == 1) {
                won = true;
                System.out.println("PLayer 1 Won! ");
                winner = 1;
            } else if (tscores[1][0] == 2 && tscores[1][1] == 2 && tscores[1][2] == 2) {
                won = true;
                System.out.println("PLayer 2 Won! 9");
                winner = 2;
            }

////////
            if (tscores[0][0] == 1 && tscores[0][1] == 1 && tscores[0][2] == 1) {
                won = true;
                System.out.println("PLayer 1 Won!");
                winner = 1;
            } else if (tscores[0][0] == 2 && tscores[0][1] == 2 && tscores[0][2] == 2) {
                won = true;
                System.out.println("PLayer 2 Won! 10");
                winner = 2;
            }
        }
        return winner;
    }

    public String[][] get3x3board(JButton[][] button) {
        String s[][] = new String[3][3];
        for (int row = 0; row < button.length; row++) {
            for (int col = 0; col < button[row].length; col++) {
                s[row][col] = button[row][col].getText();
            }
        }
        return s;
    }

    public String[][] get5x5board(JButton[][] button) {
        String s[][] = new String[5][5];
        for (int row = 0; row < button.length; row++) {
            for (int col = 0; col < button[row].length; col++) {
                s[row][col] = button[row][col].getText();
            }
        }
        return s;
    }

    //adapter for going offline online logic
    public MouseAdapter networkadapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(gui.getStatus()==Status.isoffline)
            {
                gui.setStatus(Status.isonline);
                gui.getLbnetwork().setText("Online!");
                try {
                    SocketServer.receive();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            else{
                gui.setStatus(Status.isoffline);
                gui.getLbnetwork().setText("Offline!");
            }
        }
    };

    //action listener for switching number of players
   public MouseAdapter playeradaper = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Clicked");
            if (gui.is2player()) {
                ImageIcon onep = null;
                try {
                    onep = new ImageIcon(new File("/Users/ruchit/IdeaProjects/TicTacToe/assets/onep.png").toURI().toURL());
                } catch (MalformedURLException malformedURLException) {
                    malformedURLException.printStackTrace();
                }
                gui.getPlayerimg().setIcon(onep);
                gui.setStatus(Status.isoffline);
                gui.getPo().setText("Computer(O)");
                gui.getLbnetwork().setText("Go Online!");
                gui.setIs2player(false);
            } else {
                ImageIcon twop = null;
                try {
                    twop = new ImageIcon(new File("/Users/ruchit/IdeaProjects/TicTacToe/assets/twop.png").toURI().toURL());
                } catch (MalformedURLException malformedURLException) {
                    malformedURLException.printStackTrace();
                }
                gui.getPo().setText("Player(O)");
                gui.getPlayerimg().setIcon(twop);
                System.out.println("Set Icon Called");
                gui.setIs2player(true);
            }
        }
    };


        //add action list and logic for game result
        public final ActionListener listener3x3 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton selectedBtn = (JButton) e.getSource();
                for (int row = 0; row < gui.getbtgrid().length; row++) {
                    for (int col = 0; col < gui.getbtgrid()[row].length; col++) {
                        if (gui.getbtgrid()[row][col] == selectedBtn) {
                            int finalRow = row;
                            int finalCol = col;
                            System.out.printf("Selected row and column: %d %d%n", row, col);
                            if (!gui.getgameended()&&!tie) {
                                if (gui.getUser() == User.Computer && selectedBtn.getText() == "") {
                                    Thread t = new Thread(() -> {
                                        //X=1
                                        //O=2
                                        if (gui.getStatus() == Status.isonline) {
                                            Location location = new Location();
                                            location.setcol(finalCol);
                                            location.setrow(finalRow);
                                            try {
                                                SocketClient.send(location);
                                            } catch (Exception exception) {
                                                exception.printStackTrace();
                                            }
                                        }
                                        get3x3scores()[finalRow][finalCol] = 2;
                                        selectedBtn.setText("O");
                                        gui.setUser(User.PLayer);
                                        Extensions.playSound("/Users/ruchit/IdeaProjects/TicTacToe/assets/o.wav");

                                        int result = get3x3result();
                                        if (result == 1) {
                                            xscore += 1;
                                            gui.getx().setText("" + xscore);
                                            gui.setgameended(true);
                                        } else if (result == 2) {
                                            oscore += 1;
                                            gui.geto().setText("" + oscore);
                                            gui.setgameended(true);
                                        } else {
                                            if (checktie(gui.getbtgrid())&&!gui.getgameended()){
                                                tiescore += 1;
                                                gui.gettie().setText("" + tiescore);
                                                gui.setgameended(true);
                                            }
                                        }
                                    });
                                    t.start();
                                } else if (gui.getUser() == User.PLayer && selectedBtn.getText() == "") {
                                    Thread t = new Thread(() -> {
                                        //X=1
                                        //O=0
                                        if (gui.getStatus() == Status.isonline) {
                                            Location location = new Location();
                                            location.setcol(finalCol);
                                            location.setrow(finalRow);
                                            try {
                                                SocketClient.send(location);
                                            } catch (Exception exception) {
                                                exception.printStackTrace();
                                            }
                                        }
                                        get3x3scores()[finalRow][finalCol] = 1;
                                        selectedBtn.setText("X");
                                        gui.setUser(User.Computer);
                                        Extensions.playSound("/Users/ruchit/IdeaProjects/TicTacToe/assets/x.wav");
                                        int result = get3x3result();
                                        if (result == 1) {
                                            xscore += 1;
                                            gui.getx().setText("" + xscore);
                                            gui.setgameended(true);
                                        } else if (result == 2) {
                                            oscore += 1;
                                            gui.geto().setText("" + oscore);
                                            gui.setgameended(true);
                                        } else {
                                            if (checktie(gui.getbtgrid())&&!gui.getgameended()) {
                                                tiescore += 1;
                                                gui.gettie().setText("" + tiescore);
                                                gui.setgameended(true);
                                            }
                                        }
                                        if (!gui.getgameended() && !gui.is2player() && !tie) {
                                            ArtificalIntellienge.Move move = null;
                                            move = new ArtificalIntellienge().findBestMove(get5x5board(gui.getbtgrid()));
                                            gui.getbtgrid()[move.row][move.col].doClick();
                                        }

                                    });
                                    t.start();
                                }
                            } else {
                                gui.setgameended(false);
                                gui.setUser(User.PLayer);
                                reset(gui.getbtgrid());
                            }
                        }
                    }
                }
            }
        };
    public final ActionListener listener5x5 = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton selectedBtn = (JButton) e.getSource();
            for (int row = 0; row < gui.getbtgrid().length; row++) {
                for (int col = 0; col < gui.getbtgrid()[row].length; col++) {
                    if (gui.getbtgrid()[row][col] == selectedBtn) {
                        int finalRow = row;
                        int finalCol = col;
                        System.out.printf("Selected row and column: %d %d%n", row, col);
                        if (!gui.getgameended()&&!tie) {
                            if (gui.getUser() == User.Computer && selectedBtn.getText() == "") {
                                Thread t = new Thread(() -> {
                                    //X=1
                                    //O=2
                                    if (gui.getStatus() == Status.isonline) {
                                        Location location = new Location();
                                        location.setcol(finalCol);
                                        location.setrow(finalRow);
                                        try {
                                            SocketClient.send(location);
                                        } catch (Exception exception) {
                                            exception.printStackTrace();
                                        }
                                    }
                                    get5x5scores()[finalRow][finalCol] = 2;
                                    selectedBtn.setText("O");
                                    gui.setUser(User.PLayer);
                                    Extensions.playSound("/Users/ruchit/IdeaProjects/TicTacToe/assets/o.wav");

                                    int result = get5x5result();
                                    if (result == 1) {
                                        xscore += 1;
                                        gui.getx().setText("" + xscore);
                                        gui.setgameended(true);
                                    } else if (result == 2) {
                                        oscore += 1;
                                        gui.geto().setText("" + oscore);
                                        gui.setgameended(true);
                                    } else {
                                        if (checktie(gui.getbtgrid())&&!gui.getgameended()){
                                            tiescore += 1;
                                            gui.gettie().setText("" + tiescore);
                                            gui.setgameended(true);
                                        }
                                    }
                                });
                                t.start();
                            } else if (gui.getUser() == User.PLayer && selectedBtn.getText() == "") {
                                Thread t = new Thread(() -> {
                                    //X=1
                                    //O=0
                                    if (gui.getStatus() == Status.isonline) {
                                        Location location = new Location();
                                        location.setcol(finalCol);
                                        location.setrow(finalRow);
                                        try {
                                            SocketClient.send(location);
                                        } catch (Exception exception) {
                                            exception.printStackTrace();
                                        }
                                    }
                                    get5x5scores()[finalRow][finalCol] = 1;
                                    selectedBtn.setText("X");
                                    gui.setUser(User.Computer);
                                    Extensions.playSound("/Users/ruchit/IdeaProjects/TicTacToe/assets/x.wav");
                                    int result = get5x5result();
                                    if (result == 1) {
                                        xscore += 1;
                                        gui.getx().setText("" + xscore);
                                        gui.setgameended(true);
                                    } else if (result == 2) {
                                        oscore += 1;
                                        gui.geto().setText("" + oscore);
                                        gui.setgameended(true);
                                    } else {
                                        if (checktie(gui.getbtgrid())&&!gui.getgameended()) {
                                            tiescore += 1;
                                            gui.gettie().setText("" + tiescore);
                                            gui.setgameended(true);
                                        }
                                    }
                                    if (!gui.getgameended() && !gui.is2player() && !tie) {
                                        ArtificalIntellienge.Move move = null;
                                        move = new ArtificalIntellienge().findBestMove(get5x5board(gui.getbtgrid()));
                                        gui.getbtgrid()[move.row][move.col].doClick();
                                    }

                                });
                                t.start();
                            }
                        } else {
                            gui.setgameended(false);
                            gui.setUser(User.PLayer);
                            reset(gui.getbtgrid());
                        }
                    }
                }
            }
        }
    };

        public boolean checktie(JButton[][] button) {
           if(!tie) {
               for (int row = 0; row < button.length; row++) {
                   for (int col = 0; col < button[row].length; col++) {
                       if (button[row][col].getText() == "") {
                           tie = false;
                           break;
                       } else if (button[row][col].getText() == "X" || button[row][col].getText() == "O") {
                           tie = true;
                       }
                   }
               }
           }
            return tie;
        }

    }
