package com.Intuit.configuration;

import com.Intuit.models.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class DataConfiguration {


    @Value("${file.path}")
    private String playersFilePath;

    /**
     * Load data from Player.csv file and build a bean Map<String,Player> players.
     */

    @Bean
    public Map<String, Player>players(){

        Map<String,Player> players = new HashMap<>();
        Path csvPath = Paths.get(Path.of(playersFilePath).toUri());

        int index=0;
        int lineNumber = 2;
        try {
            BufferedReader br = Files.newBufferedReader(csvPath, StandardCharsets.US_ASCII);
            br.readLine(); //skip the first line (titles line)
            String line = br.readLine();
            while (line != null) {
                try {
                    String[] playerAttributes = line.split(",");
                    Player player = Player.builder()
                            .playerId((String) buildPlayerAttribute(playerAttributes, index, String.class))
                            .birthYear((Long) buildPlayerAttribute(playerAttributes, ++index, Long.class))
                            .birthMonth((Integer) buildPlayerAttribute(playerAttributes, ++index, Integer.class))
                            .birthDay((Integer) buildPlayerAttribute(playerAttributes, ++index, Integer.class))
                            .birthCountry((String) buildPlayerAttribute(playerAttributes, ++index, String.class))
                            .birthState((String) buildPlayerAttribute(playerAttributes, ++index, String.class))
                            .birthCity((String) buildPlayerAttribute(playerAttributes, ++index, String.class))
                            .deathYear((Long) buildPlayerAttribute(playerAttributes, ++index, Long.class))
                            .deathMonth((Integer) buildPlayerAttribute(playerAttributes, ++index, Integer.class))
                            .deathDay((Integer) buildPlayerAttribute(playerAttributes, ++index, Integer.class))
                            .deathCountry((String) buildPlayerAttribute(playerAttributes, ++index, String.class))
                            .deathCountry((String) buildPlayerAttribute(playerAttributes, ++index, String.class))
                            .deathCity((String) buildPlayerAttribute(playerAttributes, ++index, String.class))
                            .firstName((String) buildPlayerAttribute(playerAttributes, ++index, String.class))
                            .lastName((String) buildPlayerAttribute(playerAttributes, ++index, String.class))
                            .nameGiven((String) buildPlayerAttribute(playerAttributes, ++index, String.class))
                            .weight((Integer) buildPlayerAttribute(playerAttributes, ++index, Integer.class))
                            .height((Integer) buildPlayerAttribute(playerAttributes, ++index, Integer.class))
                            .bats((Character) buildPlayerAttribute(playerAttributes, ++index, Character.class))
                            .throwsLetter((Character) buildPlayerAttribute(playerAttributes, ++index, Character.class))
                            .debut((Date) buildPlayerAttribute(playerAttributes, ++index, Date.class))
                            .finalGame((Date) buildPlayerAttribute(playerAttributes, ++index, Date.class))
                            .retroID((String) buildPlayerAttribute(playerAttributes, ++index, String.class))
                            .bbrefID((String) buildPlayerAttribute(playerAttributes, ++index, String.class))
                            .build();
                    players.put(player.getPlayerId(), player);
                } catch (Exception e) {
                    log.error("[DataConfiguration][players] Error accrued during data loading in attribute {}, line number {}",index,lineNumber,e);
                    //increase matrix
                }
                line = br.readLine();
                index = 0;
                lineNumber++;
            }
        } catch (IOException e) {
            log.error("[DataConfiguration][players] Error accrued during data loading from file in path {}",csvPath,e);
            //increase matrix
            throw new RuntimeException(e);

        }
        return players;

    }
    private Object buildPlayerAttribute(String [] attributes, int index, Class returnObjectType){
        if((index>=attributes.length)||(!StringUtils.hasText(attributes[index]))){
            return null;
        }
        if(returnObjectType.isAssignableFrom(Date.class)){
            return Date.valueOf(attributes[index]);
        }
        if(returnObjectType.isAssignableFrom(Integer.class)){
            return Integer.valueOf(attributes[index]);
        }
        if(returnObjectType.isAssignableFrom(Long.class)){
            return Long.valueOf(attributes[index]);
        }
        if(returnObjectType.isAssignableFrom(Character.class)){
            return attributes[index].charAt(0);
        }
        return attributes[index];
    }

}
