package Client;

import ingame.CookieImg;
import panels.RelayEndPanel;
import panels.RelayIntroPanel;
import panels.RelayGamePanel;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class RelayClient {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private DataInputStream dis;
    private DataOutputStream dos;
    private Socket socket;
    private RelayIntroPanel relayIntroPanel;
    private RelayGamePanel relayGamePanel;
    private RelayEndPanel relayEndpanel;
    private JFrame mainFrame;
    private CookieImg player1CookieImg;
    private CookieImg player2CookieImg;
    private boolean isLocalPlayer1;
    private boolean isTeam1;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RelayClient::new);
    }

    public RelayClient() {
        mainFrame = new JFrame();
        mainFrame.setBounds(100, 100, 800, 500);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        relayIntroPanel = new RelayIntroPanel();
        mainPanel.add(relayIntroPanel, "Loading");

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);

        cardLayout.show(mainPanel, "Loading");
        try {
            connectingServer();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainFrame, "Failed to connect to the server.");
        }
    }

    private void connectingServer() throws IOException {
        socket = new Socket("localhost", 6666);
        dos = new DataOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());

        new Thread(() -> {
            try {
                isLocalPlayer1 = dis.readBoolean();
                isTeam1 = dis.readBoolean();

                while (true) {
                    String msg = dis.readUTF();
                    System.out.println("Received from server: " + msg); // Debugging message
                    if (msg.startsWith("gameStart")) {
                        SwingUtilities.invokeLater(this::startGame);
                    } else if (msg.startsWith("endGame")) {
                        SwingUtilities.invokeLater(() -> endGame(msg));
                    } else {
                        handleGameMessage(msg);
                    }
                }
            } catch (IOException err) {
                err.printStackTrace();
            }
        }).start();
    }

    private void startGame() {
        try {
            player1CookieImg = new CookieImg(
                    new ImageIcon("img/cookieimg/cookie1/player_origin.gif"),
                    new ImageIcon("img/cookieimg/cookie1/player_up.gif"),
                    new ImageIcon("img/cookieimg/cookie1/player_doubleup.gif"),
                    new ImageIcon("img/cookieimg/cookie1/player_jumpend.png"),
                    new ImageIcon("img/cookieimg/cookie1/player_down.gif"),
                    new ImageIcon("img/cookieimg/cookie1/player_attack.png")
            );

            player2CookieImg = new CookieImg(
                    new ImageIcon("img/cookieimg/cookie2/normal.gif"),
                    new ImageIcon("img/cookieimg/cookie2/jump.gif"),
                    new ImageIcon("img/cookieimg/cookie2/doublejump.gif"),
                    new ImageIcon("img/cookieimg/cookie2/fall.png"),
                    new ImageIcon("img/cookieimg/cookie2/slide.gif"),
                    new ImageIcon("img/cookieimg/cookie2/hit.gif")
            );

            relayGamePanel = new RelayGamePanel(mainFrame, cardLayout, dos, player1CookieImg, player2CookieImg, isLocalPlayer1, isTeam1);
            mainPanel.add(relayGamePanel, "Game");
            cardLayout.show(mainPanel, "Game");
            relayGamePanel.gameStart();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainFrame, "Failed to start the game.");
        }
    }

    private void endGame(String msg) {
        try {
            String[] parts = msg.split(":");
            int teamScore = Integer.parseInt(parts[1]);
            int enemyScore = Integer.parseInt(parts[2]);

            relayEndpanel = new RelayEndPanel(null);
            relayEndpanel.setTotalScore(teamScore, enemyScore);
            mainPanel.add(relayEndpanel, "End");
            cardLayout.show(mainPanel, "End");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleGameMessage(String msg) {
        if (relayGamePanel != null) {
            SwingUtilities.invokeLater(() -> {
                try {
                    relayGamePanel.handleMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
