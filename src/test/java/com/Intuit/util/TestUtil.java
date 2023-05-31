package com.Intuit.util;

import com.Intuit.models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestUtil {

    public static Player createMockPlayer(){
        Player player = Player.builder()
                .playerId(String.valueOf(Math.random()*10))
                .firstName("first_name_"+String.valueOf(Math.random()*10))
                .lastName("last_name_"+String.valueOf(Math.random()*10))
                .build();
        return player;
    }

    public static List<Player> createMockPlayerList(){
        List<Player> mockPlayerList = new ArrayList<>();
        for(int i=0;i<10;i++){
            mockPlayerList.add(createMockPlayer());
        }
        return mockPlayerList;
    }

    public static Map<String,Player> createMockPlayerMap(){
        List<Player> mockPlayerList = createMockPlayerList();
        Map<String,Player> mockPlayerMap = new HashMap<>();
        mockPlayerList.forEach(player -> mockPlayerMap.put(player.getPlayerId(), player));
        return mockPlayerMap;
    }
}
