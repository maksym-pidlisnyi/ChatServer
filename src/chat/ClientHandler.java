package chat;

import packet.OPacket;
import packet.PacketManager;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {

    private final Socket client;
    private String nickname = "Unidentified";

    public ClientHandler(Socket client) {
        this.client = client;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public void run() {
        while (true) {
            if (!readDate())
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
        }
    }

    private boolean readDate() {
        try {
            DataInputStream dis = new DataInputStream(client.getInputStream());
            if (dis.available() <= 0)
                return false;
            short id = dis.readShort();
            // read packet
            OPacket packet = PacketManager.getPacket(id);
            packet.setSocket(client);
            packet.read(dis);
            packet.handle();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void invalidate() {
        ServerLoader.invalidate(client);
    }

}
