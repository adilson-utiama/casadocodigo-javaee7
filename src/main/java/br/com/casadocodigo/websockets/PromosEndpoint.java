package br.com.casadocodigo.websockets;

import java.io.IOException;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/canal/promos")
public class PromosEndpoint {

    @OnOpen
    public void onMessage(Session session) {
        if(session.isOpen()) {
            try {
                session.getBasicRemote().sendText("{\"titulo\": \"Livro de Angular 4 com 40% de desconto\", \"livroId\": 10}");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}