package panels;

import ingame.CookieImg; 
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.JLabel;

public class MultiSelectPanel extends JPanel{
    private ImageIcon ch01 = new ImageIcon("img/select/selectCh1.png");
    private ImageIcon ch02 = new ImageIcon("img/select/selectCh2.png");
    private ImageIcon ch03 = new ImageIcon("img/select/selectCh3.png");
    private ImageIcon ch04 = new ImageIcon("img/select/selectCh4.png");
    private ImageIcon ch011 = new ImageIcon("img/select/selectedCh1.png");
    private ImageIcon ch022 = new ImageIcon("img/select/selectedCh2.png");
    private ImageIcon ch033 = new ImageIcon("img/select/selectedCh3.png");
    private ImageIcon ch044 = new ImageIcon("img/select/selectedCh4.png");
    private ImageIcon ready = new ImageIcon("img/select/GameStartBtn.png");
    private JButton ch1;
    private JButton ch2;
    private JButton ch3;
    private JButton ch4;
    private JButton ReadyBtn;
    private JLabel readyLabel;
    private CookieImg ci;
    private CookieImg selectCookieImg;

    public CookieImg getCi() {return this.ci;}

    public MultiSelectPanel(Runnable ready) {
        setLayout(null);

        this.readyLabel = new JLabel("waiting other player");
        this.readyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.readyLabel.setBounds(254,300,290,30);
        this.readyLabel.setVisible(false);
        this.add(readyLabel);

        this.ReadyBtn = new JButton(this.ready);
        this.ReadyBtn.setName("ReadyBtn");
        this.ReadyBtn.addActionListener(e-> {
            setReadyState();
            System.out.println("game start");
            ready.run();
        });
        this.ReadyBtn.setBounds(254,334,291,81);
        this.add(this.ReadyBtn);
        this.ReadyBtn.setBorderPainted(false);
        this.ReadyBtn.setContentAreaFilled(false);
        this.ReadyBtn.setFocusPainted(false);

        this.ReadyBtn.setFocusPainted(false);
        this.ch1 = new JButton(this.ch01);
        this.ch1.setName("ch1");
        this.ch1.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                MultiSelectPanel.this.ch1.setIcon(MultiSelectPanel.this.ch011);
                MultiSelectPanel.this.ch2.setIcon(MultiSelectPanel.this.ch02);
                MultiSelectPanel.this.ch3.setIcon(MultiSelectPanel.this.ch03);
                MultiSelectPanel.this.ch4.setIcon(MultiSelectPanel.this.ch04);
                MultiSelectPanel.this.ci = new CookieImg(new ImageIcon("img/cookieimg/cookie1/player_origin.gif"), new ImageIcon("img/cookieimg/cookie1/player_up.gif"), new ImageIcon("img/cookieimg/cookie1/player_doubleup.gif"), new ImageIcon("img/cookieimg/cookie1/player_jumpend.png"), new ImageIcon("img/cookieimg/cookie1/player_down.gif"), new ImageIcon("img/cookieimg/cookie1/player_attack.png"));
            }
        });
        this.ch1.setBounds(90, 102, 150, 200);
        this.add(this.ch1);
        this.ch1.setBorderPainted(false);
        this.ch1.setContentAreaFilled(false);
        this.ch1.setFocusPainted(false);
        this.ch2 = new JButton(this.ch02);
        this.ch2.setName("ch2");
        this.ch2.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            	MultiSelectPanel.this.ch1.setIcon(MultiSelectPanel.this.ch01);
            	MultiSelectPanel.this.ch2.setIcon(MultiSelectPanel.this.ch022);
                MultiSelectPanel.this.ch3.setIcon(MultiSelectPanel.this.ch03);
                MultiSelectPanel.this.ch4.setIcon(MultiSelectPanel.this.ch04);
                MultiSelectPanel.this.ci = new CookieImg(new ImageIcon("img/cookieimg/cookie2/normal.gif"), new ImageIcon("img/cookieimg/cookie2/jump.gif"), new ImageIcon("img/cookieimg/cookie2/doublejump.gif"), new ImageIcon("img/cookieimg/cookie2/fall.png"), new ImageIcon("img/cookieimg/cookie2/slide.gif"), new ImageIcon("img/cookieimg/cookie2/hit.gif"));
            }
        });
        this.ch2.setBounds(238, 102, 150, 200);
        this.add(this.ch2);
        this.ch2.setBorderPainted(false);
        this.ch2.setContentAreaFilled(false);
        this.ch2.setFocusPainted(false);
        this.ch3 = new JButton(this.ch03);
        this.ch3.setName("ch3");
        this.ch3.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            	MultiSelectPanel.this.ch1.setIcon(MultiSelectPanel.this.ch01);
            	MultiSelectPanel.this.ch2.setIcon(MultiSelectPanel.this.ch02);
            	MultiSelectPanel.this.ch3.setIcon(MultiSelectPanel.this.ch033);
            	MultiSelectPanel.this.ch4.setIcon(MultiSelectPanel.this.ch04);
            	MultiSelectPanel.this.ci = new CookieImg(new ImageIcon("img/cookieimg/cookie3/cookie.gif"), new ImageIcon("img/cookieimg/cookie3/jump.png"), new ImageIcon("img/cookieimg/cookie3/doublejump.gif"), new ImageIcon("img/cookieimg/cookie3/fall.png"), new ImageIcon("img/cookieimg/cookie3/slide.gif"), new ImageIcon("img/cookieimg/cookie3/hit.png"));
            }
        });
        this.ch3.setBounds(386, 102, 150, 200);
        this.add(this.ch3);
        this.ch3.setBorderPainted(false);
        this.ch3.setContentAreaFilled(false);
        this.ch3.setFocusPainted(false);
        this.ch4 = new JButton(this.ch04);
        this.ch4.setName("ch4");
        this.ch4.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            	MultiSelectPanel.this.ch1.setIcon(MultiSelectPanel.this.ch01);
            	MultiSelectPanel.this.ch2.setIcon(MultiSelectPanel.this.ch02);
            	MultiSelectPanel.this.ch3.setIcon(MultiSelectPanel.this.ch03);
            	MultiSelectPanel.this.ch4.setIcon(MultiSelectPanel.this.ch044);
            	MultiSelectPanel.this.ci = new CookieImg(new ImageIcon("img/cookieimg/cookie4/kch.gif"), new ImageIcon("img/cookieimg/cookie4/kjump.gif"), new ImageIcon("img/cookieimg/cookie4/kjump.gif"), new ImageIcon("img/cookieimg/cookie4/kjump.gif"), new ImageIcon("img/cookieimg/cookie4/kslide.gif"), new ImageIcon("img/cookieimg/cookie4/kch.gif"));
            }
        });
        this.ch4.setBounds(534, 102, 150, 200);
        this.add(this.ch4);
        this.ch4.setBorderPainted(false);
        this.ch4.setContentAreaFilled(false);
        this.ch4.setFocusPainted(false);

        JLabel selectTxt = new JLabel("");
        selectTxt.setHorizontalAlignment(0);
        selectTxt.setIcon(new ImageIcon("img/select/selectTxt.png"));
        selectTxt.setBounds(174, 20, 397, 112);
        this.add(selectTxt);
        JLabel selectBg = new JLabel("");
        selectBg.setForeground(Color.ORANGE);
        selectBg.setHorizontalAlignment(0);
        selectBg.setIcon(new ImageIcon("img/select/selectBg.png"));
        selectBg.setBounds(0, 0, 784, 461);
        this.add(selectBg);
    }
    private JButton createCharacterButton(ImageIcon normalIcon, ImageIcon selectedIcon, Runnable onSelect) {
        JButton button = new JButton(normalIcon);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                resetCharacterIcons();
                button.setIcon(selectedIcon);
                onSelect.run();
            }
        });
        return button;
    }

    private void resetCharacterIcons() {
        ch1.setIcon(ch01);
        ch2.setIcon(ch02);
        ch3.setIcon(ch03);
        ch4.setIcon(ch04);
    }

    private void setReadyState() {
        // Ready 상태일 때 다른 버튼 클릭 비활성화
        ch1.setEnabled(false);
        ch2.setEnabled(false);
        ch3.setEnabled(false);
        ch4.setEnabled(false);
        ReadyBtn.setEnabled(false);
        readyLabel.setVisible(true);
    }

}
