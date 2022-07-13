package com.crud.tasks.trello.mapper;

import com.crud.tasks.controller.NullObjectMappedException;
import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    void mapToBoardsTest() throws NullObjectMappedException {
        //Given
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "test", new ArrayList<>());
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto);

        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);

        //Then
        assertEquals(1, trelloBoardList.size());
        assertEquals("1", trelloBoardList.get(0).getId());
        assertEquals("test", trelloBoardList.get(0).getName());
        assertNotNull(trelloBoardList.get(0).getLists());
    }

    @Test
    void mapNullTrelloBoardsDtoToBoardsTest() {
        //Given & When
        List<TrelloBoardDto> trelloBoardDtoList = null;

        //Then
        assertThrows(NullObjectMappedException.class, () -> trelloMapper.mapToBoards(trelloBoardDtoList));
    }

    @Test
    void mapToBoardsDtoTest() throws NullObjectMappedException {
        //Given
        TrelloBoard trelloBoard = new TrelloBoard("1", "test", new ArrayList<>());
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(trelloBoard);

        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);

        //Then
        assertEquals(1, trelloBoardDtoList.size());
        assertEquals("1", trelloBoardDtoList.get(0).getId());
        assertEquals("test", trelloBoardDtoList.get(0).getName());
        assertNotNull(trelloBoardDtoList.get(0).getLists());
    }

    @Test
    void mapNullTrelloBoardsToBoardsDtoTest() {
        //Given & When
        List<TrelloBoard> trelloBoardsList = null;

        //Then
        assertThrows(NullObjectMappedException.class, () -> trelloMapper.mapToBoardsDto(trelloBoardsList));
    }

    @Test
    void mapToListTest() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1", "test", true);
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        trelloListDtoList.add(trelloListDto);

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtoList);

        //Then
        assertEquals(1, trelloLists.size());
        assertEquals("1", trelloLists.get(0).getId());
        assertEquals("test", trelloLists.get(0).getName());
        assertTrue(trelloLists.get(0).isClosed());
    }

    @Test
    void mapToListDtoTest() {
        //Given
        TrelloList trelloList = new TrelloList("1", "test", true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);

        //When
        List<TrelloListDto> trelloListDtoList = trelloMapper.mapToListDto(trelloLists);

        //Then
        assertEquals(1, trelloListDtoList.size());
        assertEquals("1", trelloListDtoList.get(0).getId());
        assertEquals("test", trelloListDtoList.get(0).getName());
        assertTrue(trelloListDtoList.get(0).isClosed());
    }

    @Test
    void mapToCardDtoTest() throws NullObjectMappedException {
        //Given
        TrelloCard trelloCard = new TrelloCard("test", "test description",
                "test pos", "1");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("test", trelloCardDto.getName());
        assertEquals("test description", trelloCardDto.getDescription());
        assertEquals("test pos", trelloCardDto.getPos());
        assertEquals("1", trelloCardDto.getListId());
    }

    @Test
    void mapNullTrelloCardToCardDtoTest() {
        //Given & When
        TrelloCard trelloCard = null;

        //Then
        assertThrows(NullObjectMappedException.class, () -> trelloMapper.mapToCardDto(trelloCard));
    }

    @Test
    void mapTrelloCardContainingNullToCardDtoTest() throws NullObjectMappedException {
        //Given
        TrelloCard trelloCard = new TrelloCard(null, null, null, null);

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertNull(trelloCardDto.getName());
        assertNull(trelloCardDto.getDescription());
        assertNull(trelloCardDto.getPos());
        assertNull(trelloCardDto.getListId());
    }

    @Test
    void mapToCardTest() throws NullObjectMappedException {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test", "test description",
                "test pos", "1");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("test", trelloCard.getName());
        assertEquals("test description", trelloCard.getDescription());
        assertEquals("test pos", trelloCard.getPos());
        assertEquals("1", trelloCard.getListId());
    }

    @Test
    void mapNullTrelloCardDtoToCardTest() {
        //Given & When
        TrelloCardDto trelloCardDto = null;

        //Then
        assertThrows(NullObjectMappedException.class, () -> trelloMapper.mapToCard(trelloCardDto));
    }

    @Test
    void mapTrelloCardDtoContainingNullToCardTest() throws NullObjectMappedException {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(null, null, null, null);

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertNull(trelloCard.getName());
        assertNull(trelloCard.getDescription());
        assertNull(trelloCard.getPos());
        assertNull(trelloCard.getListId());
    }
}