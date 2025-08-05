package panels;

import Client.MultiClient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class RelayEndPanel extends JPanel {
    ImageIcon btn = new ImageIcon("img/end/button.png");
    JButton btnNewButton;
    JLabel lblTeamScore;
    JLabel lblEnemyScore;
    JLabel lblNewLabel;
    JPanel backgroundPanel;

    public void setTotalScore(int totalTeamscore, int totalEnemyscore) {
        this.lblTeamScore.setText("Our Team: " + totalTeamscore);
        this.lblEnemyScore.setText("Enemy: " + totalEnemyscore);
    }

    public RelayEndPanel(MouseListener listener) {
        setLayout(new BorderLayout());

        // 배경 이미지 패널
        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon("img/end/cookierunbg.jpg");
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);
        add(backgroundPanel, BorderLayout.CENTER);

        this.btnNewButton = new JButton(this.btn);
        this.btnNewButton.setName("endAccept");
        this.btnNewButton.addMouseListener(listener);
        this.btnNewButton.setBounds(550, 370, 199, 81);
        this.btnNewButton.setBorderPainted(false);
        this.btnNewButton.setFocusPainted(false);
        this.btnNewButton.setContentAreaFilled(false);
        backgroundPanel.add(this.btnNewButton);

        this.lblTeamScore = new JLabel("Our Team: 0");
        this.lblTeamScore.setHorizontalAlignment(SwingConstants.CENTER);
        this.lblTeamScore.setFont(new Font("Gill Sans Ultra Bold", 0, 37));
        this.lblTeamScore.setBounds(251, 52, 459, 87);
        backgroundPanel.add(this.lblTeamScore);

        this.lblEnemyScore = new JLabel("Enemy: 0");
        this.lblEnemyScore.setHorizontalAlignment(SwingConstants.CENTER);
        this.lblEnemyScore.setFont(new Font("Harlow Solid Italic", 0, 49));
        this.lblEnemyScore.setBounds(251, 152, 459, 87);
        backgroundPanel.add(this.lblEnemyScore);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 500); // 패널의 기본 크기 설정
    }

}
