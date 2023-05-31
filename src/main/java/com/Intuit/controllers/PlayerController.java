package com.Intuit.controllers;

import com.Intuit.models.Player;
import com.Intuit.services.PlayerService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing {@link com.Intuit.models.Player}.
 */

@RestController()
@RequestMapping("/api")
@Slf4j
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    /**
     * {@code GET  /players} : get all the players.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of players in body.
     */

    @GetMapping("/players")
    public ResponseEntity<List<Player>> getAllPlayers(){
        log.debug("REST request to get all players");
        List<Player> allPlayers = playerService.getAllPlayers();
        return ResponseEntity.ok().body(allPlayers);
    }

    /**
     * {@code GET  /player/id} : get the "id" author.
     *
     * @param playerId the id of the player to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the player, or with status {@code 404 (Not Found)}.
     */

    @GetMapping("/player/{playerId}")
    public ResponseEntity<Player> getPlayerById(@PathVariable @NonNull String playerId){
        log.debug("REST request to get player with id: {}",playerId);
        Player player = playerService.getPlayerById(playerId);
        return ResponseEntity.ok().body(player);
    }
}
