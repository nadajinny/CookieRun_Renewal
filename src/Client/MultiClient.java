package Client;

import panels.MultiEndPanel;     
import Server.MultiServer;
import Server.chatServer;
import panels.MultiGamePanel;
import panels.IntroPanel;
import panels.m1SelectPanel;
import panels.MultiSelectPanel;
import ingame.CookieImg;
import main.Main;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MultiClient {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private DataInputStream dis;
    private DataOutputStream dos;
    private Socket socket;
    private IntroPanel introPanel;
    private MultiSelectPanel selectPanel;
    private MultiGamePanel gamePanel;
    private MultiEndPanel endpanel;
    public static JFrame mainFrame;
    private ImageIcon btn = new ImageIcon("img/end/button.png");
    private JButton btnNewButton;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel;
    private int condition=0;//condition ==0->alive condition==-1->die
    private int role;
    private int score;
    public MultiClient() {
        mainFrame = new JFrame();
        mainFrame.setBounds(100, 100, 800, 500);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        System.out.println("in the multiplayer");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        introPanel = new IntroPanel();
        selectPanel = new MultiSelectPanel(this::readycheck);
        
        mainPanel.add(introPanel, "Loading");
        mainPanel.add(selectPanel, "Selection");
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
        cardLayout.show(mainPanel, "Loading");
        try {
        	System.out.println("int the Multi");
            connectingServer();
        } catch (IOException e) {
        	
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainFrame, "Failed to connect to the server.");
        }
    }
    
    
    private void connectingServer() throws IOException {
        socket = new Socket("localhost", 7875);
        dos = new DataOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());

        new Thread(() -> {
            try {
                while (true) {
                	String msg = dis.readUTF();
                    if (msg.equals("gotoSelect")) {
                        SwingUtilities.invokeLater(() -> cardLayout.show(mainPanel, "Selection"));
                    } else if (msg.equals("gameStart")) {
                        SwingUtilities.invokeLater(() -> {
                            CookieImg selectedCookieImg = selectPanel.getCi();
                            try {
                                gamePanel = new MultiGamePanel(mainFrame, cardLayout, dos, selectedCookieImg,this::handleGameMessage,this,role);
                                mainPanel.add(gamePanel, "Game");
                                cardLayout.show(mainPanel, "Game");
                                gamePanel.gameStart(this::handleGameMessage);
                            } catch (Exception e) {
                                e.printStackTrace();
                                JOptionPane.showMessageDialog(mainFrame, "Failed to start the game.");
                            }
                        });
                    }else if(msg.equals("EscOn")) {
                    	ClienttoGameMessage("EscOn");
                    }else if(msg.equals("game over")) {
                    	break;
                    }else if(msg.equals("i'm jump")) {
                    	role = 0;
                    }else if(msg.equals("i'm slide")) {
                    	role = 1;
                    }else if (msg.equals("end")) {
                    	break;
                    }
                    else if(msg.equals("givemescore")) {
                    	score = MultiGamePanel.getresultscore();
                    	dos.writeUTF(Integer.toString(score));
                    }else if(msg.startsWith("Score:")) {
                    	score = Integer.parseInt(msg.split(":")[1]);
                    	endGame();
                    	break;
                    }
                    else //if(msg.equals("jump")||msg.equals("hit")||msg.equals("downKeyOn")||msg.equals("jumpBtn")||msg.equals("Down")){
                    	{ClienttoGameMessage(msg);}

                }
                String msg = dis.readUTF();
                if(msg == "givemescore") {
                	score = MultiGamePanel.getresultscore();
                	dos.writeInt(score);
                }
            } catch (IOException err) {
            	try {
					dos.close();
	                dis.close();
	                socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                err.printStackTrace();
            }
        }).start();
    }
    
    
    private void endGame() {
    	try {
    		endpanel= new MultiEndPanel(null);
    		endpanel.setResultScore(score);
    		endpanel.setLayout(null);
    		mainPanel.add(endpanel,"end");
    		cardLayout.show(mainPanel, "end");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	endpanel.start();
    }

    private void readycheck() {
        try {
            dos.writeUTF("Ready");
            dos.flush();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
    private void ClienttoGameMessage(String msg) {
        SwingUtilities.invokeLater(() -> {
            try {
            	System.out.println(msg);
                gamePanel.handleMessageactive(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    private void handleGameMessage() {
        SwingUtilities.invokeLater(() -> {
            try {
                dos.writeUTF(gamePanel.handleMessage());
                dos.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    private void sendESCsignal() {
    	  try {
              dos.writeUTF("EscOn");
              dos.flush();
          } catch (IOException e) {
              e.printStackTrace();
          }
    }
    public void sendGameMessage(String msg) {
        try {
        	System.out.println(msg);
            dos.writeUTF(msg);
            System.out.println(msg);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public void gameover() {
//    	Main.getEndPanel().setResultScore(score);
//		cardLayout.show(mainFrame.getContentPane(), "end");
//		mainFrame.requestFocus();
//    }
    

}


