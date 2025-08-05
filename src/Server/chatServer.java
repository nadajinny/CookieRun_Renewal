package Server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ProtocolException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class chatServer extends JFrame implements ActionListener {
   private JPanel contentPane;
   private JTextField port_tf;
   private JTextArea textArea = new JTextArea();
   private JButton start_button = new JButton("서버 실행");
   private JButton stop_button = new JButton("서버 중지");

   private ServerSocket server_socket;
   private Socket socket;
   private int port;
   private Vector uer_vc = new Vector();
   private Vector room_vc = new Vector();

   private Vector user_vc = new Vector();
   StringTokenizer st;

   chatServer() {
      init();
      start();
   }

   private void start() {
      start_button.addActionListener(this);
      stop_button.addActionListener(this);
   }

   private void init() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 464, 511);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

      setContentPane(contentPane);
      contentPane.setLayout(null);

      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(12, 10, 398, 374);
      contentPane.add(scrollPane);
      
      scrollPane.setViewportView(textArea);
      textArea.setEditable(false);

      JLabel lblNewLabel = new JLabel("포트 번호");
      lblNewLabel.setBounds(25, 403, 50, 15);
      contentPane.add(lblNewLabel);

      port_tf = new JTextField();
      port_tf.setBounds(90, 400, 123, 21);
      contentPane.add(port_tf);
      port_tf.setColumns(10);

      start_button.setBounds(12, 428, 151, 23);
      contentPane.add(start_button);

      stop_button.setBounds(166, 428, 123, 23);
      contentPane.add(stop_button);

      //**
      stop_button.setEnabled(false);
      //**

      this.setVisible(true);
   }

   private void Server_start() {
      try {
         server_socket = new ServerSocket(port);
      } catch (IOException e) {
         JOptionPane.showMessageDialog(null, "이미 사용중인 포트", "알림", JOptionPane.ERROR_MESSAGE);
      }

      if (server_socket != null) {
         Connection();
      }
   }

   private void Connection() {
      Thread th = new Thread(new Runnable() {

         @Override
         public void run() {
            while (true) {
               try {
                  textArea.append("사용자 접속 대기중\n");
                  socket = server_socket.accept();
                  textArea.append("사용자 접속\n");

                  UserInfo user = new UserInfo(socket);

                  user.start();

               } catch (IOException e) {
                  //6월 13일 수정 : 114번줄 코드 지움
                  break;
               }
            }
         }
      });

      th.start();
   }

   public static void main(String[] args) {
      new chatServer();
      try {
		new MultiServer();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == start_button) {
         System.out.println("서버 실행 버튼 클릭");
         port = Integer.parseInt(port_tf.getText().trim());
         Server_start();      //소켓 생성 및 사용자 접속 대기
         
         //*
         start_button.setEnabled(false);
         port_tf.setEditable(false);
         stop_button.setEnabled(true);
         //*
         
      } else if (e.getSource() == stop_button) {
         //*
         stop_button.setEnabled(false);
         start_button.setEnabled(true);
         port_tf.setEditable(true);
         //*
         try {
            server_socket.close();
            user_vc.removeAllElements();
            room_vc.removeAllElements();
         } catch (IOException e1) {
            e1.printStackTrace();
         }
         System.out.println("서버 중지 버튼 클릭");
      }
   }

   class UserInfo extends Thread {
      private OutputStream os;
      private InputStream is;
      private DataOutputStream dos;
      private DataInputStream dis;

      private Socket user_socket;
      private String Nickname = "";

      private boolean RoomCh = true;
      private boolean ReadyCh = false;  // 1. readych bool 자료형 만들어서 false로 초기화
      
      UserInfo(Socket soc) {
         this.user_socket = soc;
         UserNetwork();
      }

      private void UserNetwork() {
         try {
            is = user_socket.getInputStream();
            dis = new DataInputStream(is);
            os = user_socket.getOutputStream();
            dos = new DataOutputStream(os);
            Nickname = dis.readUTF();
            textArea.append(Nickname + "사용자 접속");

            System.out.println("현재 접속된 사용자 수: " + (user_vc.size() + 1));

            BroadCast("NewUser/" + Nickname);

            for (int i = 0; i < user_vc.size(); i++) {
               UserInfo u = (UserInfo) user_vc.elementAt(i);
               send_Message("OldUser/" + u.Nickname);
            }

            for(int i = 0; i < room_vc.size(); i++) {
               RoomInfo r = (RoomInfo) room_vc.elementAt(i);
               send_Message("OldRoom/" + r.Room_name);
            }

            BroadCast("room_list_update/ ");
            
            user_vc.add(this);

            BroadCast("user_list_update/ ");

         } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "stream 설정 에러", "알림", JOptionPane.ERROR_MESSAGE);
         }
      }

      public void run() {
         while (true) {
            try {
            	String msg = dis.readUTF();
				System.out.println(msg);
				textArea.append(Nickname + "사용자로부터 들어온 메세지: " + msg + "\n");
				//-----------수정
				if(msg.equals("ready") ){
//					System.out.println("i'm in");
//					dos.writeUTF("mode check");
//					openserver = dis.readUTF();
					ReadyCh = true;  // 개별 사용자 Ready 상태 설정
					checkAllReady();  // 모든 사용자 Ready 상태 확인
					}
				else InMessage(msg);
				//-----------------
            } catch (IOException e) {
               textArea.append(Nickname + ": 사용자 접속 끊어짐\n");
               try {
                  dos.close();
                  dis.close();
                  user_socket.close();
                  user_vc.remove(this);
                  BroadCast("User_out/" + Nickname);
                  BroadCast("user_list_update/ ");
               } catch (IOException e1) {}
               break;
            }
         }
      }

      private void InMessage(String str) {
         st = new StringTokenizer(str, "/");

         String protocol = st.nextToken();
         String message = st.nextToken();

         System.out.println("프로토콜: " + protocol);
         System.out.println("메세지: " + message);

         if (protocol.equals("Note")) {   
            String note = st.nextToken();
            System.out.println("받는 사람: " + message);
            System.out.println("보낼 내용: " + note);

            for(int i = 0; i < user_vc.size(); i++) {
               UserInfo u = (UserInfo)user_vc.elementAt(i);
               
               if(u.Nickname.equals(message)) {
                  u.send_Message("Note/" + Nickname + "/" + note);
               }
            }
         } 
         
         else if (protocol.equals("CreateRoom")) {
            for (int i = 0; i < room_vc.size(); i++) {
               RoomInfo r = (RoomInfo)room_vc.elementAt(i);

               if (r.Room_name.equals(message)) {
                  send_Message("CreateRoomFail/ok");
                  RoomCh = false;
                  break;
               }
            }

            if (RoomCh) {
               RoomInfo new_room = new RoomInfo(message, this);
               room_vc.add(new_room);
               send_Message("CreateRoom/" + message);

               BroadCast("New_Room/" + message);
            }
            RoomCh = true;
         }
         
         else if (protocol.equals("Chatting")) {
            String msg = st.nextToken();
            for(int i = 0; i < room_vc.size(); i++) {
               RoomInfo r = (RoomInfo)room_vc.elementAt(i);
               
               if(r.Room_name.equals(message)) {
                  r.BroadCast_Room("Chatting/" + Nickname + "/" + msg);
               }
            }
         } 
         
         else if (protocol.equals("JoinRoom")) {
            for(int i = 0; i < room_vc.size(); i++) {
               RoomInfo r = (RoomInfo)room_vc.elementAt(i);
               if(r.Room_name.equals(message)) {
                  r.BroadCast_Room("Chatting/알림/********" + Nickname + "님이 입장**********");
                  r.Add_User(this);
                  send_Message("JoinRoom/" + message);
               }
            }
         }
         
         else if (protocol.equals("ready")) {
              for (int i = 0; i < room_vc.size(); i++) {
                  RoomInfo r = (RoomInfo) room_vc.elementAt(i);
                  if (r.Room_name.equals(message)) {
                      r.BroadCast_Room("ready/" + Nickname + "님이 준비버튼을 눌렀습니다.");
                  }
              }
          }

      }

      //***************ready버튼 코드 추가 부분**********************
      private void checkAllReady() {
         boolean allReady = true;
         for (int i = 0; i < user_vc.size(); i++) {
            UserInfo u = (UserInfo) user_vc.elementAt(i);
            if (!u.ReadyCh) {
               allReady = false;
               break;
            }
         }
         if (allReady) {
            for (int i = 0; i < user_vc.size(); i++) {
    			System.out.println("mode is connect");
				UserInfo u = (UserInfo) user_vc.elementAt(i);
				u.send_Message("ready");  // 모든 사용자가 준비되었음을 알림
            }
         }
         
      }
      //***************ready버튼 코드 추가 부분**********************
      
      
      private void BroadCast(String str) {
         for (int i = 0; i < user_vc.size(); i++) {
            UserInfo u = (UserInfo) user_vc.elementAt(i);
            u.send_Message(str);
         }
      }

      private void send_Message(String str) {
         try {
            dos.writeUTF(str);
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }

   class RoomInfo {
      private String Room_name;
      private Vector Room_user_vc = new Vector();

      RoomInfo(String str, UserInfo u) {
         this.Room_name = str;
         this.Room_user_vc.add(u);
      }
      
      public void BroadCast_Room(String str) {
         for(int i = 0; i < Room_user_vc.size(); i++) {
            UserInfo u = (UserInfo)Room_user_vc.elementAt(i);
            u.send_Message(str);
         }
      }
      
      public void Add_User(UserInfo u) {
         this.Room_user_vc.add(u);
      }
   }
}