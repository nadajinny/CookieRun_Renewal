package panels;

import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
import main.Main;
import Client.*;

public class ModeSelectPanel extends JPanel {
    private CardLayout cardLayout;
    private Container parentPanel;
    private Main main;
    private Image backgroundImage;
    private String mode; 
    public ModeSelectPanel(CardLayout cardLayout, Container container, Main main) {
        this.cardLayout = cardLayout;
        this.parentPanel = container;
        this.main = main;

        // 이미지 로드
        ImageIcon icon = new ImageIcon("img/새로 추가된 이미지/background.png");
        backgroundImage = icon.getImage();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH; // 버튼의 공간을 꽉 채우도록 설정
        gbc.weightx = 0; // 버튼의 가로 확장여부 설정
        gbc.weighty = 0; // 버튼의 세로 확장여부 설정

        Dimension buttonSize = new Dimension(250, 80); // 버튼 크기 설정

        // 1인 모드 버튼 추가
        JButton soloModeButton = new JButton(new ImageIcon("img/새로 추가된 이미지/1인_모드.png"));
        soloModeButton.setName("soloModeButton");
        soloModeButton.setPreferredSize(buttonSize);
        soloModeButton.setBorderPainted(false);
        soloModeButton.setContentAreaFilled(false);
        soloModeButton.setFocusPainted(false);
        soloModeButton.setHorizontalAlignment(SwingConstants.CENTER);
        soloModeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(container, "select");
                main.getSelectPanel().requestFocus();
                Main.setMode("solo");
            }
        });
        add(soloModeButton, gbc);

        gbc.gridy++;

        // 2:2 팀매치 모드 버튼 추가
        JButton teamModeButton = new JButton(new ImageIcon("img/새로 추가된 이미지/2대2_이어달리기.png"));
        teamModeButton.setName("teamModeButton");
        teamModeButton.setPreferredSize(buttonSize);
        teamModeButton.setBorderPainted(false);
        teamModeButton.setContentAreaFilled(false);
        teamModeButton.setFocusPainted(false);
        teamModeButton.setHorizontalAlignment(SwingConstants.CENTER);
        teamModeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 클라이언트 실행
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        chatClient client = new chatClient(); // 클라이언트 생성
                        client.setVisible(true); // 클라이언트 창 표시
                        Main.setMode("2:2");
                    }
                });
            }
        });
        add(teamModeButton, gbc);

        gbc.gridy++;

        // 2인 3각 모드 버튼 추가
        JButton triangleModeButton = new JButton(new ImageIcon("img/새로 추가된 이미지/2인_3각_모드.png"));
        triangleModeButton.setName("triangleModeButton");
        triangleModeButton.setPreferredSize(buttonSize);
        triangleModeButton.setBorderPainted(false);
        triangleModeButton.setContentAreaFilled(false);
        triangleModeButton.setFocusPainted(false);
        triangleModeButton.setHorizontalAlignment(SwingConstants.CENTER);
        triangleModeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 클라이언트 실행
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        chatClient client = new chatClient(); // 클라이언트 생성
                        client.setVisible(true); // 클라이언트 창 표시
                        Main.setMode("multi");
                    }
                });
            }
        });
        add(triangleModeButton, gbc);
    }
    public String modegetter() {
    	return mode;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
