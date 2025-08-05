package Server;

import lombok.Getter;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class RelayServer {
    private static final int PORT = 6666;
    private List<ClientManager> team1 = new ArrayList<>();
    private List<ClientManager> team2 = new ArrayList<>();
    private ServerSocket serverSocket;
    private boolean[] playersReady = new boolean[4];
    private boolean team1Player2Finished = false;
    private boolean team2Player2Finished = false;
    private int team1Score = 0;
    private int team2Score = 0;
    public RelayServer() throws IOException {
        serverSocket = new ServerSocket(PORT);
        System.out.println("Server is opened");

        while (team1.size() + team2.size() < 4) {
            Socket client = serverSocket.accept();
            int teamId = (team1.size() <= team2.size()) ? 1 : 2;
            ClientManager clientManager = new ClientManager(client, (teamId == 1 ? team1.size() : team2.size()), teamId);
            if (teamId == 1) {
                team1.add(clientManager);
            } else {
                team2.add(clientManager);
            }
            new Thread(clientManager).start();
            System.out.println((teamId == 1 ? team1.size() : team2.size()) + " client connected to team " + teamId);
        }

        // 모든 플레이어가 접속하면 게임 시작 메시지 전송
        broadcast("gameStart", team1);
        broadcast("gameStart", team2);

        while (true) {
            // 서버가 계속 실행되도록 유지
        }
    }

    public void broadcast(String msg, List<ClientManager> team) throws IOException {
        for (ClientManager client : team) {
            client.sendMessage(msg);
        }
    }

    private synchronized void checkEndGame(){
        if(team1Player2Finished && team2Player2Finished){
            endGame();
        }
    }

    private void endGame() {
        try {
            int team1Score = team1.stream().mapToInt(ClientManager::getScore).sum();
            int team2Score = team2.stream().mapToInt(ClientManager::getScore).sum();
            String team1EndMessage = "endGame:" + team1Score + ":" + team2Score;
            String team2EndMessage = "endGame:" + team2Score + ":" + team1Score;

            for (ClientManager client : team1) {
                client.sendMessage(team1EndMessage);
            }
            for (ClientManager client : team2) {
                client.sendMessage(team2EndMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ClientManager implements Runnable {
        private Socket client;
        private DataInputStream dis;
        private DataOutputStream dos;
        private boolean ready;
        private int clientId;
        private int teamId;
        @Getter
        private int score;

        public ClientManager(Socket client, int clientId, int teamId) throws IOException {
            this.client = client;
            this.dis = new DataInputStream(client.getInputStream());
            this.dos = new DataOutputStream(client.getOutputStream());
            this.ready = false;
            this.clientId = clientId;
            this.teamId = teamId;
            this.dos.writeBoolean(clientId == 0);
            this.dos.writeBoolean(teamId == 1);
            this.dos.flush();
        }

        public void run() {
            try {
                while (true) {
                    String msg = dis.readUTF();
                    System.out.println("Received from client: " + msg); // Debugging message
                    handleGameMessage(msg);
                }
            } catch (IOException err) {
                err.printStackTrace();
            }
        }

        public void sendMessage(String msg) throws IOException {
            System.out.println("Sending to client: " + msg); // Debugging message
            dos.writeUTF(msg);
            dos.flush();
        }

        public void sendEndGameMessage(int teamScore, int enemyScore) throws IOException {
            dos.writeUTF("endGame");
            dos.writeInt(teamScore);
            dos.writeInt(enemyScore);
            dos.flush();
        }

        private void handleGameMessage(String msg) throws IOException {
            System.out.println("Handling game message: " + msg); // Debugging message
            if (msg.startsWith("score:")) {
                this.score = Integer.parseInt(msg.split(":")[1]);
            } else if (msg.startsWith("endGame:")) {
                this.score = Integer.parseInt(msg.split(":")[1]);
                if (teamId == 1) {
                    team1Score += this.score;
                } else {
                    team2Score += this.score;
                }

                if (clientId == 1 && teamId == 1) {
                    team1Player2Finished = true;
                } else if (clientId == 1 && teamId == 2) {
                    team2Player2Finished = true;
                }
                checkEndGame();
            }
            List<ClientManager> team = (teamId == 1) ? team1 : team2;
            for (ClientManager client : team) {
                if (client != this) {
                    client.sendMessage(msg);
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            new RelayServer();
        } catch (IOException err) {
            System.out.println(err);
            err.printStackTrace();
        }
    }
}
