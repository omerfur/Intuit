package com.Intuit.mappers;

import com.Intuit.controllers.dtos.PlayerResponseDto;
import com.Intuit.models.Player;
import com.fasterxml.jackson.databind.util.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerMapper{

    PlayerMapper MAPPER = Mappers.getMapper(PlayerMapper.class);

    PlayerResponseDto playerToPlayerResponseDto(Player player);
}
