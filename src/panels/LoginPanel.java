package panels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginPanel extends JPanel {
    private JTextField ipTextField;
    private JTextField portTextField;
    private JTextField idTextField;
    private JButton loginButton;

    public LoginPanel() {
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        JLabel ipLabel = new JLabel("Server IP");
        ipLabel.setBounds(34, 67, 70, 15);
        add(ipLabel);

        JLabel portLabel = new JLabel("Server Port");
        portLabel.setBounds(34, 104, 88, 15);
        add(portLabel);

        JLabel idLabel = new JLabel("ID");
        idLabel.setBounds(34, 158, 50, 15);
        add(idLabel);

        ipTextField = new JTextField();
        ipTextField.setBounds(135, 64, 150, 21);
        add(ipTextField);
        ipTextField.setColumns(10);

        portTextField = new JTextField();
        portTextField.setBounds(135, 101, 150, 21);
        add(portTextField);
        portTextField.setColumns(10);

        idTextField = new JTextField();
        idTextField.setBounds(135, 155, 150, 21);
        add(idTextField);
        idTextField.setColumns(10);

        loginButton = new JButton("로그인");
        loginButton.setBounds(34, 211, 251, 23);
        add(loginButton);
    }

    public String getIp() {
        return ipTextField.getText().trim();
    }

    public String getPort() {
        return portTextField.getText().trim();
    }

    public String getId() {
        return idTextField.getText().trim();
    }

    public JButton getLoginButton() {
        return loginButton;
    }
}
