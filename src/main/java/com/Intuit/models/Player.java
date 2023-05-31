package com.Intuit.models;

import lombok.*;

import java.sql.Date;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Player {

    @NonNull
    private String playerId;
    private Long birthYear;
    private Integer birthMonth;
    private Integer birthDay;
    private String birthCountry;
    private String birthState;
    private String birthCity;
    private Long deathYear;
    private Integer deathMonth;
    private Integer deathDay;
    private String deathCountry;
    private String deathState;
    private String deathCity;
    private String firstName;
    private String lastName;
    private String nameGiven;
    private Integer weight;
    private Integer height;
    private Character bats;
    private Character throwsLetter;
    private Date debut;
    private Date finalGame;
    private String retroID;
    private String bbrefID;

    
}
