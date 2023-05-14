package org.jirsak.kviz;

import io.micronaut.websocket.WebSocketBroadcaster;
import io.micronaut.websocket.WebSocketSession;
import io.micronaut.websocket.annotation.OnClose;
import io.micronaut.websocket.annotation.OnMessage;
import io.micronaut.websocket.annotation.OnOpen;
import io.micronaut.websocket.annotation.ServerWebSocket;
import org.reactivestreams.Publisher;

@ServerWebSocket("/kviz")
public class KvizWebSocketServer {
    private final WebSocketBroadcaster broadcaster;

    public KvizWebSocketServer(WebSocketBroadcaster broadcaster) {
        this.broadcaster = broadcaster;
    }

    @OnOpen
    public void onOpen(WebSocketSession session) {
        System.out.println("open");
    }

    @OnMessage
    public Publisher<String> onMessage(String message, WebSocketSession session) {
        System.out.printf("message: %s", message).println();
        return broadcaster.broadcast(message, WebSocketSession::isOpen);
    }

    @OnClose
    public void onClose(WebSocketSession session) {
        System.out.println("close");
    }
}
