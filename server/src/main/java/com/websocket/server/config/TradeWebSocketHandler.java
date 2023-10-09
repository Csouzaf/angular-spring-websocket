package com.websocket.server.config;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.websocket.server.model.Stock;

public class TradeWebSocketHandler extends TextWebSocketHandler{
    private final ObjectMapper objectMapper = new ObjectMapper(); //Verify it
    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>(); //Verify copyOnWrite

    Random random = new Random();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception{
        float oldPrice = 0.0f;

        //publicando um novo valor a cada 1 segundo por 100 vezes
        for (int i =0; i< 100; i++){
            //calculando uma variação de preços aleatórios entre 12 e 13$
            float stockPrice = 12 + random.nextFloat() * (13-12);
            float roundedPrice = (float) (Math.round(stockPrice * 100.0) / 100.0);
            
            Stock stock = new Stock("Leilão", "Leilão Icon", roundedPrice, false);

            //verificando se o preço do stock aumentou ou diminuiu
            if (roundedPrice > oldPrice){
                stock.setIncreased(true);
            }
            oldPrice = roundedPrice;

            //enviando preco do stoque
            TextMessage message = new TextMessage(objectMapper.writeValueAsString(stock));
            session.sendMessage(message);
            Thread.sleep(1000);

        }
        sessions.add(session); //Verify it

    }
}
