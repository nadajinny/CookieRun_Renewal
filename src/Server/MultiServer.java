package Server;
 
import java.io.*;  
import java.net.*;
import java.util.ArrayList;
import java.util.List;
 
public class MultiServer {
    private static final int PORT = 7875; // 서버 연결할 포트
    private List<ClientManager> clients = new ArrayList<>(); // 접속된 클라이언트 관리를 위한 리스트
    private ServerSocket serverSocket; // 서버
    private int role;
    private int n=0;
    private int score;
    private int condition=0;
    public MultiServer() throws IOException {
        serverSocket = new ServerSocket(PORT); // 서버 만들기
        System.out.println("Server is opened");
        System.out.println("socket is opend");
        while (clients.size() < 2) {  // 2개의 클라이언트가 접속될 때까지 대기
            Socket client = serverSocket.accept(); // 클라이언트 접속 시도시 연결
            if(clients.size()==0) {role= 0;}
            else {role =1;}
            ClientManager clientManager = new ClientManager(client,role);
            clients.add(clientManager);
            new Thread(clientManager).start();
            System.out.println(clients.size() + " client connected");
        }
        actingbroadcast();
        broadcast("gotoSelect"); // 2명의 플레이어가 들어왔을 때 클라이언트로 메시지 발송

        // 서버가 계속 실행되도록 유지
        while (true) {
            // 필요시 추가 로직 작성
        }
    }
    public void actingbroadcast() throws IOException{
    	for(ClientManager client : clients) {
    		if(n == 0 ) client.sendMessage("i'm jump");
    		else client.sendMessage("i'm slide");
    		n++;
    	}
    }

    public void broadcast(String msg) throws IOException {
        for (ClientManager client : clients) {
            client.sendMessage(msg);
        }
    }
    
    private class ClientManager implements Runnable {
        private Socket client;
        private DataInputStream dis;
        private DataOutputStream dos;
        private boolean ready;
        private int role; //role == 0이면 점프만 가능 , role == 1이면 슬라이드만 가능
        public ClientManager(Socket client,int role) throws IOException {
            this.client = client;
            this.dis = new DataInputStream(client.getInputStream());
            this.dos = new DataOutputStream(client.getOutputStream());
            this.ready = false;
            this.role = role; 
        }

        public void run() {
            try {
                while (true) {
                    String msg = dis.readUTF();
                    System.out.println(msg);
                    if (msg.equals("Ready")) {
                        ready = true;
                        gameReady();
                    }else if(msg.equals("jump")) {
                    	if(role == 0 ) broadcast("jump");
                    }
                    else if(msg.equals("EscOn")) {broadcast("EscOn");}
                    else if(msg.equals("Down")) {if (role ==1 ) broadcast("Down");}
                    else if(msg.equals("downKeyOn")) {if(role ==1)broadcast("downKeyOn");}
                    else if(msg.equals("jumpBtn")) {if(role ==0) broadcast("jumpBtn");}
                    else if (msg.equals("hit")) {broadcast("hit");}
                    else if(msg.equals("fall")) {broadcast("fall");}
                    else if(msg.equals("end")) {
                    	condition=-1;
                    	broadcast("end");
                    	for (ClientManager client: clients) {
                    		if(role ==0 ) {
                    			client.sendMessage("givemescore");
                    			score = dis.readInt();
                    			break;
                    		}
                    		else {
                    			client.sendMessage("givemescore");
                    			score = dis.readInt();
                    		}
                    	}
                    	break;
                    }
                    else {
                    	System.out.println(msg);
                    	broadcast(msg);}
                }
                broadcast(Integer.toString(score));
                
                
            } catch (IOException err) {
				try {
					dos.close();
					dis.close();
					serverSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				clients.remove(client);
                err.printStackTrace();
            }
            try {
				dos.close();
				dis.close();
				serverSocket.close();
				clients.remove(client);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
        }

        public void sendMessage(String msg) throws IOException {
            dos.writeUTF(msg);
            dos.flush();
        }

        private void gameReady() throws IOException {
            boolean gameStart = clients.stream().allMatch(client -> client.ready);
            if (gameStart) {
                broadcast("gameStart");
            }
        }

        private void handleGameMessage(String msg) throws IOException {
            // 클라이언트에서 받은 게임 메시지를 다른 클라이언트들에게 브로드캐스트
            broadcast(msg);
        }
    }
}
