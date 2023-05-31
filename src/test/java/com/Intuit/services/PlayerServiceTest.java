package com.Intuit.services;

import com.Intuit.models.Player;
import com.Intuit.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@SpringBootTest
public class PlayerServiceTest {

    @MockBean
    private PlayerService playerService;


    Map<String, Player> mockPlayerMap;


    @BeforeEach
    public void init(){
        mockPlayerMap = TestUtil.createMockPlayerMap();
        playerService = new PlayerService(mockPlayerMap);
    }

    @Test
    public void getPlayerByIdTest(){
        String mockPlayerId = mockPlayerMap.keySet().stream().findFirst().get();
        Player mockPlayerExpected = mockPlayerMap.get(mockPlayerId);

        Player actualPlayer = playerService.getPlayerById(mockPlayerId);

        Assertions.assertEquals(mockPlayerExpected,actualPlayer);
    }

    @Test
    public void getPlayerByIdTest_ThrowsNoSuchElementException(){
        String mockPlayerId = String.valueOf(Math.random()*10);
        Assertions.assertThrows(NoSuchElementException.class,()->playerService.getPlayerById(mockPlayerId));
    }

    @Test
    public void findAllPlayers(){
        List<Player> mockPlayerList = new ArrayList<>();
        for(String mockPlayerKey: mockPlayerMap.keySet()){
            mockPlayerList.add(mockPlayerMap.get(mockPlayerKey));
        }
        List<Player> actualResults = playerService.getAllPlayers();

        Assertions.assertEquals(mockPlayerList,actualResults);
    }

}
