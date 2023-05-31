package com.Intuit.services;

import com.Intuit.models.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service Implementation for managing {@link Player}.
 */

@Slf4j
@Service
public class PlayerService {

    private Map<String, Player> players;

    @Autowired
    public PlayerService(Map<String, Player> players) {
        this.players = players;
    }

    /**
     * Get one player by id.
     *
     * @param playerId the id of the entity.
     * @return the entity.
     */

    public Player getPlayerById(String playerId){
        Player player;
        try{
            player = Optional.ofNullable(players.get(playerId)).orElseThrow(()->new NoSuchElementException(String.format("no player with id %s",playerId)));
        }catch (NoSuchElementException e){
            log.info(e.getMessage());
            throw e;
        }
        catch (Exception e){
            log.error("[PlayerService][getPlayerById] Exception accrued during searching for player with id {}",playerId,e);
            throw new RuntimeException();
        }
       return player;
    }

    /**
     * Get all the players.
     *
     * @return the list of entities.
     */
    public List<Player> getAllPlayers(){
        List<Player> allPlayers = new ArrayList<>();
        try {
            for(String playerKey:players.keySet()){
                allPlayers.add(players.get(playerKey));
            }
        }catch (Exception e){
            log.error("[PlayerService][getAllPlayers] Exception accrued during searching for all players",e);
            throw new RuntimeException();
        }
        return allPlayers;
    }

}
