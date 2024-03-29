package packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public abstract class OPacket {

    public abstract short getId();

    private Socket socket;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public abstract void write(DataOutputStream dos) throws IOException;

    public abstract void read(DataInputStream dis) throws IOException;

    public abstract void handle();

}
