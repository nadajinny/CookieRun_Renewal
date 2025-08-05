package panels;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ingame.CookieImg;

public class m1SelectPanel extends JPanel {


    // ������ ĳ���� �̹��� ������
    private ImageIcon ch01 = new ImageIcon("img/select/selectCh1.png");
    private ImageIcon ch02 = new ImageIcon("img/select/selectCh2.png");
    private ImageIcon ch03 = new ImageIcon("img/select/selectCh3.png");
    private ImageIcon ch04 = new ImageIcon("img/select/selectCh4.png");

    // ���õ� ĳ���� �̹��� ������
    private ImageIcon ch011 = new ImageIcon("img/select/selectedCh1.png");
    private ImageIcon ch022 = new ImageIcon("img/select/selectedCh2.png");
    private ImageIcon ch033 = new ImageIcon("img/select/selectedCh3.png");
    private ImageIcon ch044 = new ImageIcon("img/select/selectedCh4.png");

    // ���� ��ư �̹���������
    private ImageIcon start = new ImageIcon("img/select/GameStartBtn.png");

    // �̹����� ������ ��ư
    private JButton ch1;
    private JButton ch2;
    private JButton ch3;
    private JButton ch4;

    // ���� ��ư
    private JButton StartBtn;

    // ���ӿ��� ����� ��Ű �̹������� ���� ������Ʈ
    private CookieImg ci;

    // ��Ű �̹����� ���ο��� gamePanel�� ������ ���� ����
    public CookieImg getCi() {
        return ci;
    }

    public m1SelectPanel(Object o) {

        // ���� ��ư
        StartBtn = new JButton(start);
        StartBtn.setName("StartBtn");
        StartBtn.addMouseListener((MouseListener) o);
        StartBtn.setBounds(254, 334, 291, 81);
        add(StartBtn);
        StartBtn.setBorderPainted(false);
        StartBtn.setContentAreaFilled(false);
        StartBtn.setFocusPainted(false);

        // ĳ���� ch1
        ch1 = new JButton(ch01);
        ch1.setName("ch1");
        ch1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ch1.setIcon(ch011);
                ch2.setIcon(ch02);
                ch3.setIcon(ch03);
                ch4.setIcon(ch04);
                ci = new CookieImg(new ImageIcon("img/cookieimg/cookie1/player_origin.gif"),
                        new ImageIcon("img/cookieimg/cookie1/player_up.gif"),
                        new ImageIcon("img/cookieimg/cookie1/player_doubleup.gif"),
                        new ImageIcon("img/cookieimg/cookie1/player_jumpend.png"),
                        new ImageIcon("img/cookieimg/cookie1/player_down.gif"),
                        new ImageIcon("img/cookieimg/cookie1/player_attack.png"));
            }
        });
        ch1.setBounds(90, 102, 150, 200);
        add(ch1);
        ch1.setBorderPainted(false);
        ch1.setContentAreaFilled(false);
        ch1.setFocusPainted(false);

        // ĳ���� ch2
        ch2 = new JButton(ch02);
        ch2.setName("ch2");
        ch2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ch1.setIcon(ch01);
                ch2.setIcon(ch022);
                ch3.setIcon(ch03);
                ch4.setIcon(ch04);
                ci = new CookieImg(new ImageIcon("img/cookieimg/cookie2/normal.gif"),
                        new ImageIcon("img/cookieimg/cookie2/jump.gif"),
                        new ImageIcon("img/cookieimg/cookie2/doublejump.gif"),
                        new ImageIcon("img/cookieimg/cookie2/fall.png"),
                        new ImageIcon("img/cookieimg/cookie2/slide.gif"),
                        new ImageIcon("img/cookieimg/cookie2/hit.gif"));
            }
        });
        ch2.setBounds(238, 102, 150, 200);
        add(ch2);
        ch2.setBorderPainted(false);
        ch2.setContentAreaFilled(false);
        ch2.setFocusPainted(false);

        // ĳ���� ch3
        ch3 = new JButton(ch03);
        ch3.setName("ch3");
        ch3.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ch1.setIcon(ch01);
                ch2.setIcon(ch02);
                ch3.setIcon(ch033);
                ch4.setIcon(ch04);
                ci = new CookieImg(new ImageIcon("img/cookieimg/cookie3/cookie.gif"),
                        new ImageIcon("img/cookieimg/cookie3/jump.png"),
                        new ImageIcon("img/cookieimg/cookie3/doublejump.gif"),
                        new ImageIcon("img/cookieimg/cookie3/fall.png"),
                        new ImageIcon("img/cookieimg/cookie3/slide.gif"),
                        new ImageIcon("img/cookieimg/cookie3/hit.png"));
            }
        });
        ch3.setBounds(386, 102, 150, 200);
        add(ch3);
        ch3.setBorderPainted(false);
        ch3.setContentAreaFilled(false);
        ch3.setFocusPainted(false);

        // ĳ���� ch4
        ch4 = new JButton(ch04);
        ch4.setName("ch4");
        ch4.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ch1.setIcon(ch01);
                ch2.setIcon(ch02);
                ch3.setIcon(ch03);
                ch4.setIcon(ch044);
                ci = new CookieImg(new ImageIcon("img/cookieimg/cookie4/kch.gif"),
                        new ImageIcon("img/cookieimg/cookie4/kjump.gif"),
                        new ImageIcon("img/cookieimg/cookie4/kjump.gif"),
                        new ImageIcon("img/cookieimg/cookie4/kjump.gif"),
                        new ImageIcon("img/cookieimg/cookie4/kslide.gif"),
                        new ImageIcon("img/cookieimg/cookie4/kch.gif"));
            }
        });
        ch4.setBounds(534, 102, 150, 200);
        add(ch4);
        ch4.setBorderPainted(false);
        ch4.setContentAreaFilled(false);
        ch4.setFocusPainted(false);

        // ���
        JLabel selectBg = new JLabel("");
        selectBg.setForeground(Color.ORANGE);
        selectBg.setHorizontalAlignment(SwingConstants.CENTER);
        selectBg.setIcon(new ImageIcon("img/select/selectBg.png"));
        selectBg.setBounds(0, 0, 784, 461);
        add(selectBg);

        // ĳ���� ���� Ÿ��Ʋ
        JLabel selectTxt = new JLabel("");
        selectTxt.setHorizontalAlignment(SwingConstants.CENTER);
        selectTxt.setIcon(new ImageIcon("img/select/selectTxt.png"));
        selectTxt.setBounds(174, 20, 397, 112);
        add(selectTxt);

    }
}
