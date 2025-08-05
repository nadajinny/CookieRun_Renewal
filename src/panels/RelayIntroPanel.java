package panels;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.*;

public class RelayIntroPanel extends JPanel {
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;

    ImageIcon introIc = new ImageIcon("img/intro/intro.png");

    public RelayIntroPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(introIc.getIconWidth(), introIc.getIconHeight()));

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.introIc.getImage(), -60, 0, (ImageObserver)null);
    }
}
