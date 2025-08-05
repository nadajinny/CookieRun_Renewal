package Client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.Main;
import Server.*;

public class chatClient extends JFrame implements ActionListener, KeyListener{

    // 로그인 GUI 변수
    private JFrame Login_GUI = new JFrame();
    private JPanel loginPane;
    private JTextField ip_textField; // id 받는 텍스트필드
    private JTextField port_textField; // port 받는 텍스트필드
    private JTextField id_textField; // id 받는 텍스트필드
    private JButton login_button = new JButton("접속"); // 접속 버튼 리스너
    private JLabel createLabel;
    
    // 대기 + 채팅창 GUI 변수
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField message_textfield = new JTextField();

    private JButton notesend_btn = new JButton(""); 
    private JButton joinroom_btn = new JButton("");
    private JButton send_btn = new JButton(""); 
    private  JButton createroom_btn =  new JButton(""); 
    private  JButton ready_btn =  new JButton("");
    private JList User_list = new JList(); // 전체 접속자 list
    private JList Room_list = new JList(); // 전체 방목록 list
    private JTextArea Chat_area = new JTextArea(); // 채팅창 변수

    // 네트워크를 위한 변수
    private Socket socket;
    private String ip = "";
    private int port; // 서버 소스코드 내의 포트 번호
    private String id;
    private InputStream is;
    private OutputStream os;
    private DataInputStream dis;
    private DataOutputStream dos;

    // 그외 변수들
    Vector user_list = new Vector();
    Vector room_list = new Vector();
    StringTokenizer st;
    
    private String My_Room;    //내가 있는 방 이름 

    public chatClient() { // 생성자 메소드
        Login_init(); // 로그인창 화면 구성 메소드
        start();
    }

    private void Login_init() {
        Login_GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("img/새로 추가된 이미지/login_background.png");
        Image backgroundImage = icon.getImage();
        Login_GUI.setBounds(100, 100, 800, 500); // 프레임 크기 설정
        loginPane = new JPanel();
        loginPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        Login_GUI.setContentPane(loginPane);
        loginPane.setLayout(null);

        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage.getScaledInstance(800, 500, Image.SCALE_SMOOTH))); // 이미지 크기 조정하여 설정
        backgroundLabel.setBounds(0, 0, 800, 500);
        loginPane.add(backgroundLabel);

        Font labelFont = new Font("Arial", Font.BOLD, 22); // 라벨 글씨 크기 설정

        // 서버 IP 입력 레이블
        JLabel lblNewLabel = createLabel("Server IP", 130, 150, labelFont);
        backgroundLabel.add(lblNewLabel);

        // 서버 Port 입력 레이블
        JLabel lblNewLabel_1 = createLabel("Server Port", 130, 200, labelFont);
        backgroundLabel.add(lblNewLabel_1);

        // ID 입력 레이블
        JLabel lblNewLabel_2 = createLabel("ID", 130, 250, labelFont);
        backgroundLabel.add(lblNewLabel_2);

        // 서버 IP 입력 텍스트 필드
        ip_textField = new JTextField();
        ip_textField.setHorizontalAlignment(JTextField.CENTER); // 가운데 정렬
        ip_textField.setBounds(300, 150, 200, 30); // 가로 중앙 정렬
        backgroundLabel.add(ip_textField);
        ip_textField.setColumns(10);

        // 서버 Port 입력 텍스트 필드
        port_textField = new JTextField();
        port_textField.setHorizontalAlignment(JTextField.CENTER); // 가운데 정렬
        port_textField.setBounds(300, 200, 200, 30); // 가로 중앙 정렬
        backgroundLabel.add(port_textField);
        port_textField.setColumns(10);

        // ID 입력 텍스트 필드
        id_textField = new JTextField();
        id_textField.setHorizontalAlignment(JTextField.CENTER); // 가운데 정렬
        id_textField.setBounds(300, 250, 200, 30); // 가로 중앙 정렬
        backgroundLabel.add(id_textField);
        id_textField.setColumns(10);

        // 접속 버튼에 이미지 설정
        ImageIcon loginIcon = new ImageIcon("img/새로 추가된 이미지/loginbutton.png");
        login_button = new JButton(loginIcon);
        login_button.setBounds(300, 300, loginIcon.getIconWidth(), loginIcon.getIconHeight()); // 버튼 크기와 위치 설정
        login_button.setContentAreaFilled(false); // 버튼의 배경 투명하게
        login_button.setBorderPainted(false); // 버튼의 테두리 투명하게
        backgroundLabel.add(login_button);

        
        Login_GUI.setVisible(true);
    }
    
    private JLabel createLabel(String text, int x, int y, Font font) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 150, 30); // 크기 조정
        label.setFont(font);
        label.setForeground(Color.WHITE); // 글자색을 흰색으로 설정
        label.setOpaque(true); // 배경을 표시하도록 설정
        label.setBackground(new Color(0, 0, 0, 150)); // 반투명한 배경색 설정
        label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // 여백 추가

        return label;
    }




    private void Main_init() {
         Toolkit kit = Toolkit.getDefaultToolkit();
          Image img = kit.getImage("img/새로 추가된 이미지/cookie.png");
          setIconImage(img); // 아이콘 설정

          setTitle("대기 + 채팅방");
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          setBounds(100, 100, 650, 500);

          contentPane = new JPanel();
          contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
          contentPane.setBackground(new Color(0xF3E9DF)); // 배경색 설정

          setContentPane(contentPane);
          contentPane.setLayout(null);

          JLabel lblNewLabel = new JLabel("전 체 접 속 자");
          lblNewLabel.setBounds(37, 32, 88, 15);
          Font font = new Font("배달의민족 주아", Font.PLAIN, 15);
          lblNewLabel.setFont(font);
          contentPane.add(lblNewLabel);

         
          User_list.setBounds(28, 57, 97, 113);
          contentPane.add(User_list);

          notesend_btn.setIcon(new ImageIcon("img/새로 추가된 이미지/쪽지_보내기.png"));
          notesend_btn.setBounds(28, 180, 97, 40); 
          notesend_btn.setBorderPainted(false);
          notesend_btn.setContentAreaFilled(false);
          notesend_btn.setFocusPainted(false);
          notesend_btn.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
             }
          });
      
          contentPane.add(notesend_btn);

          JLabel lblNewLabel_1 = new JLabel("채 팅 방 목 록");
          lblNewLabel_1.setBounds(37, 235, 88, 15);
          lblNewLabel_1.setFont(font);
          contentPane.add(lblNewLabel_1);

         
          Room_list.setBounds(28, 260, 97, 100);
          contentPane.add(Room_list);
          
          joinroom_btn.setIcon(new ImageIcon("img/새로 추가된 이미지/채팅방_참여.png"));
          joinroom_btn.setBounds(20, 370, 110, 40);
          joinroom_btn.setBorderPainted(false);
          joinroom_btn.setContentAreaFilled(false);
          joinroom_btn.setFocusPainted(false);
          joinroom_btn.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
             }
          });
          contentPane.add(joinroom_btn);

         
          createroom_btn.setIcon(new ImageIcon("img/새로 추가된 이미지/채팅방_만들기.png"));
          createroom_btn.setBounds(20, 411, 110, 40);
          createroom_btn.setBorderPainted(false);
          createroom_btn.setContentAreaFilled(false);
          createroom_btn.setFocusPainted(false);
          createroom_btn.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
             }
          });
          contentPane.add(createroom_btn);
  
 
        
          Chat_area.setBounds(153, 59, 454, 350);
          contentPane.add(Chat_area);
          //6.13추가된부분
          Chat_area.setEditable(false);
          //여기까지


        
          message_textfield.setBounds(153, 425, 371, 21);
          contentPane.add(message_textfield);
          message_textfield.setColumns(10);
          //**
          message_textfield.setEnabled(false);

          //**

          
          send_btn.setIcon(new ImageIcon("img/새로 추가된 이미지/전송.png"));
          send_btn.setBounds(536, 415, 70, 42);
          send_btn.setBorderPainted(false);
          send_btn.setContentAreaFilled(false);
          send_btn.setFocusPainted(false);
          send_btn.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
             }
          });
          contentPane.add(send_btn);
          send_btn.setEnabled(false);
          
          ready_btn.setIcon(new ImageIcon("img/새로 추가된 이미지/ready.png"));
          ready_btn.setBounds(490, 5, 100, 50);
          ready_btn.setBorderPainted(false);
          ready_btn.setContentAreaFilled(false);
          ready_btn.setFocusPainted(false);
          ready_btn.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
             }
          });
          contentPane.add(ready_btn);

          JLabel lblNewLabel_2 = new JLabel("방이름");
          lblNewLabel_2.setBounds(153, 32, 88, 15);
          lblNewLabel_2.setFont(font);
          contentPane.add(lblNewLabel_2);

        
          this.setVisible(true);
    }
    
   private void start() {
        login_button.addActionListener(this);   // 로그인 버튼 리스너
        notesend_btn.addActionListener(this);   // 쪽지보내기 버튼 리스터
        joinroom_btn.addActionListener(this);   // 채팅방 참여 버튼 리스너
        createroom_btn.addActionListener(this);      // 전송 버튼 리스터
        send_btn.addActionListener(this);   // 방만들기 버튼 리스너
        ready_btn.addActionListener(this);       // 준비 버튼 리스너
        message_textfield.addKeyListener(this);
    }

    private void Network() {
        try {
            socket = new Socket(ip, port);
            if (socket != null) { // 정상적으로 소켓이 연결되었을 경우
                Connection();
                // 로그인 창 닫고 메인 창 열기
                Login_GUI.dispose();
                Main_init();
            }

        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, "연결 실패!!", "알림", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "연결 실패!!", "알림", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void Connection() { // 실질적인 메소드 연결 부분
        try {
            is = socket.getInputStream();
            dis = new DataInputStream(is);

            os = socket.getOutputStream();
            dos = new DataOutputStream(os);
        } catch (IOException e) { // 에러처리부분
            JOptionPane.showMessageDialog(null, "연결 실패!!", "알림", JOptionPane.ERROR_MESSAGE);
        } // Stream 설정 끝
       
       
        send_message(id);

        // User_list에 사용자 추가
        user_list.add(id);

        // 스레드 설정 안 하면 GUI가 죽어버림
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String msg = dis.readUTF(); // 메세지 수신
                        System.out.println("서버로부터 수신된 메시지: " + msg);
                        if(msg.equals("ready")) { //inmessage에서 msg 해석 시 오류, 미리 확인 후 모드로 이동
                			changemode();
                        }
                        inmessage(msg);

                    } catch (IOException e) {
                        try {
                            os.close();
                            is.close();
                            dis.close();
                            socket.close();
                            JOptionPane.showMessageDialog(null, "서버와 접속이 끊어짐", "알림", JOptionPane.ERROR_MESSAGE);
                        } catch (IOException e1) {}
                        break;
                    }
                }
            }
        });

        th.start();
    }

    private void inmessage(String str) { // 서버로부터 들어오는 모든 메세지
        st = new StringTokenizer(str, "/");

        String protocol = st.nextToken();
        String Message = st.nextToken();

        System.out.println("프로토콜: " + protocol);
        System.out.println("내용: " + Message);

        if (protocol.equals("NewUser")) {
            user_list.add(Message);
        } 
        
        else if (protocol.equals("OldUser")) {
            user_list.add(Message);
        } 
        
        else if (protocol.equals("Note")) {
            String note = st.nextToken();
            System.out.println(Message + "사용자로부터 온 쪽지" + note);
            JOptionPane.showMessageDialog(null, note, Message + "님으로 부터 쪽지", JOptionPane.CLOSED_OPTION);
        } 
        
        else if (protocol.equals("user_list_update")) {
            User_list.setListData(user_list);
        } 
        
        else if (protocol.equals("CreateRoom")) {
            My_Room = Message;
            
            //**
            message_textfield.setEnabled(true);
            send_btn.setEnabled(true);  
            
            joinroom_btn.setEnabled(false);
            createroom_btn.setEnabled(false);
            //**
        } 
        
        else if (protocol.equals("CreateRoomFail")) {
            JOptionPane.showMessageDialog(null, "방 만들기 실패!!", "알림", JOptionPane.ERROR_MESSAGE);
        } 
        
        else if (protocol.equals("New_Room")) {
            room_list.add(Message);
            Room_list.setListData(room_list);
        } 
        
        else if (protocol.equals("Chatting")) {
            String msg = st.nextToken();
            Chat_area.append(Message + " : " + msg + "\n");
            if (protocol.equals("ready")) {
                Chat_area.append(Message + ": 님이 준비 버튼을 눌렀습니다\n");
            }
        } 
        
        else if (protocol.equals("OldRoom")) {
            room_list.add(Message);
            Room_list.setListData(room_list);
        } 
        
        else if (protocol.equals("room_list_update")) {
            Room_list.setListData(room_list);
        } 
        
        else if (protocol.equals("JoinRoom")) {
            My_Room = Message;

            //**
            message_textfield.setEnabled(true);
            send_btn.setEnabled(true);
            
            joinroom_btn.setEnabled(false);
            createroom_btn.setEnabled(false);
            //**
            
            JOptionPane.showMessageDialog(null, "채팅방에 입장했습니다", "알림", JOptionPane.INFORMATION_MESSAGE);
        }else if (protocol.equals("ready")) {
            Chat_area.append(Message + "\n");
        }
        
        
    }
    private void changemode() { /////////////////////// 여기서 기존 서버와의 연결 해제, 및 새로운 클라이언트 파일 호출
    	System.out.println("changemode is wrong");//추가한 파일은 select, gamepanel, multiserver, multiplayer 
        try {
			os.close();
			 is.close();
		        dis.close();
		        socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(Main.getmode().equals("multi")) {
    		new MultiClient(); //선택한 모드의 클라이언트 생성
    		this.dispose();
    	}
    	if(Main.getmode().equals("2:2")) {//---------여기에 모드 클라이언트 파일 생성자 삽입 위에 모드처럼!
    		new RelayClient();
    		System.out.println("2:2 is start");
    	}
    }

    // """"서버""""에 메세지 보내는 부분
    private void send_message(String str) { 
        try {
            dos.writeUTF(str);
        } catch (IOException e) { // 에러처리부분
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new chatClient();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login_button) {
            System.out.println("로그인 버튼 클릭");
            if(ip_textField.getText().length() == 0) {
                ip_textField.setText("IP를 입력해주세요");
                ip_textField.requestFocus();
            }
            else if(port_textField.getText().length() == 0) {
                port_textField.setText("Port번호를 입력해주세요");
                port_textField.requestFocus();
            }
            else if (id_textField.getText().length() == 0) {
                id_textField.setText("ID를 입력해주세요");
                id_textField.requestFocus();
            }
            else {
                ip = ip_textField.getText().trim(); // ip 받는 부분
                port = Integer.parseInt(port_textField.getText().trim());
                id = id_textField.getText().trim(); // id 받는 부분
                Network();
            }
        } else if (e.getSource() == notesend_btn) {
            System.out.println("쪽지 보내기 버튼 클릭");
            String user = (String) User_list.getSelectedValue();
            String note = JOptionPane.showInputDialog("보낼 메시지");
            if (note != null) {
                send_message("Note/" + user + "/" + note);
            }
            System.out.println("받는 사람: " + user + "| 보낼 내용" + note);
        } else if (e.getSource() == joinroom_btn) {
            String JoinRoom = (String) Room_list.getSelectedValue();
            send_message("JoinRoom/" + JoinRoom);
            System.out.println("채팅방 참여 버튼 클릭");
        } else if (e.getSource() == createroom_btn) {
            String roomname = JOptionPane.showInputDialog("방이름");
            if (roomname != null) {
                send_message("CreateRoom/" + roomname);
            }
            System.out.println("방 만들기 버튼 클릭");
        } else if (e.getSource() == send_btn) {
            send_message("Chatting/" + My_Room + "/" + message_textfield.getText().trim()); // "Chatting + 채팅 내용
            message_textfield.setText(" ");
            message_textfield.requestFocus();
            System.out.println("전송 버튼 클릭");
        } else if (e.getSource() == ready_btn) {
            send_message("ready");
            System.out.println(id + ": 준비 버튼 클릭");
            Chat_area.append(id+"님이 준비 버튼을 눌렀습니다.\n");
            ready_btn.setEnabled(false);
        }
    }

   @Override
   public void keyTyped(KeyEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void keyPressed(KeyEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void keyReleased(KeyEvent e) {
      if (e.getKeyCode() == 10) {
         send_message("Chatting/" + My_Room + "/" + message_textfield.getText().trim()); // "Chatting + 채팅 내용
         message_textfield.setText(" ");
           message_textfield.requestFocus();
      }
      // TODO Auto-generated method stub
      
   }
}