package main;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ingame.CookieImg;
import panels.m1EndPanel;
import panels.m1GamePanel;
import panels.IntroPanel;
import panels.ModeSelectPanel;
import panels.m1SelectPanel;
import java.awt.CardLayout;

public class Main extends listenAdapter {
    private JFrame frame;
    private IntroPanel introPanel;
    private ModeSelectPanel modeSelectPanel;
    private m1SelectPanel m1SelectPanel;
    private m1GamePanel m1Gamepanel;
    private m1EndPanel m1Endpanel;
    private CardLayout cl;
    private CookieImg ci;
    private static String mode;

    public static void setMode(String newMode) {
        mode = newMode;
    }

    public m1SelectPanel getSelectPanel() {
        return m1SelectPanel;
    }

    public static String getmode() {
        return mode;
    }

    public m1GamePanel getGamePanel() {
        return m1Gamepanel;
    }

    public void setGamePanel(m1GamePanel m1Gamepanel) {
        this.m1Gamepanel = m1Gamepanel;
    }

    public m1EndPanel getEndPanel() {
        return m1Endpanel;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main window = new Main();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Main() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cl = new CardLayout(0, 0);
        frame.getContentPane().setLayout(cl);

        introPanel = new IntroPanel();
        introPanel.addMouseListener(this);
        frame.getContentPane().add(introPanel, "intro");

        modeSelectPanel = new ModeSelectPanel(cl, frame.getContentPane(), this);
        frame.getContentPane().add(modeSelectPanel, "mode");

        m1SelectPanel = new m1SelectPanel(this);
        frame.getContentPane().add(m1SelectPanel, "select");

        m1Gamepanel = new m1GamePanel(frame, cl, this);
        frame.getContentPane().add(m1Gamepanel, "game");

        m1Endpanel = new m1EndPanel(this);
        frame.getContentPane().add(m1Endpanel, "end");

        introPanel.setLayout(null);
        m1SelectPanel.setLayout(null);
        m1Gamepanel.setLayout(null);
        m1Endpanel.setLayout(null);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getComponent().toString().contains("IntroPanel")) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            cl.show(frame.getContentPane(), "mode");
            modeSelectPanel.requestFocus();
            mode = modeSelectPanel.modegetter();
        } else if (e.getComponent().getName().equals("soloModeButton")) {
            cl.show(frame.getContentPane(), "select");
            m1SelectPanel.requestFocus();
        } else if (e.getComponent().getName().equals("StartBtn")) {
            if (m1SelectPanel.getCi() == null) {
                JOptionPane.showMessageDialog(null, "캐릭터를 선택해주세요");
            } else {
                cl.show(frame.getContentPane(), "game");
                m1Gamepanel.gameSet(m1SelectPanel.getCi());
                m1Gamepanel.gameStart();
                m1Gamepanel.requestFocus();
            }
        } else if (e.getComponent().getName().equals("endAccept")) {
            frame.getContentPane().remove(m1Gamepanel);
            m1Gamepanel = new m1GamePanel(frame, cl, this);
            m1Gamepanel.setLayout(null);
            frame.getContentPane().add(m1Gamepanel, "game");
            frame.getContentPane().remove(m1SelectPanel);
            m1SelectPanel = new m1SelectPanel(this);
            m1SelectPanel.setLayout(null);
            frame.getContentPane().add(m1SelectPanel, "select");
            cl.show(frame.getContentPane(), "select");
            m1SelectPanel.requestFocus();
        }
    }
}
