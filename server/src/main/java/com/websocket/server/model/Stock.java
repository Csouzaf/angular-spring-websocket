package com.websocket.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Stock {
    
    String name;
    String icon;
    float price;
    boolean increased;

   
}
