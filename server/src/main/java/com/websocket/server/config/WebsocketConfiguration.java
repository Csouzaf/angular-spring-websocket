package com.websocket.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebsocketConfiguration implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry){
        registry.addHandler(tradeWebSocketHandler(), "/stocks") //add all class TradeWebSocketHandler in router stocks
            .setAllowedOrigins("http://localhost:4200");
    }

    @Bean
    public WebSocketHandler tradeWebSocketHandler(){
        return new TradeWebSocketHandler();
    }






    // @Override
    // public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'registerWebSocketHandlers'");
    // }
    
}
