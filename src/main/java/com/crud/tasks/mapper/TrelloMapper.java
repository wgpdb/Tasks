package com.crud.tasks.mapper;

import com.crud.tasks.controller.NullObjectMappedException;
import com.crud.tasks.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Component
public class TrelloMapper {

    private Object nullValidator(Object validationObject) throws NullObjectMappedException {
        return Optional.ofNullable(validationObject).orElseThrow(NullObjectMappedException::new);
    }

    private List<?> nullListValidator(List<?> validationList) throws NullObjectMappedException {
        return Optional.ofNullable(validationList).orElseThrow(NullObjectMappedException::new);
    }

    public List<TrelloBoard> mapToBoards(final List<TrelloBoardDto> trelloBoardDto) throws NullObjectMappedException {
        List<TrelloBoardDto> validatedList = (List<TrelloBoardDto>) nullListValidator(trelloBoardDto);

        return validatedList.stream()
                    .map(trelloBoard -> new TrelloBoard(trelloBoard.getId(),
                            trelloBoard.getName(),
                            mapToList(trelloBoard.getLists())))
                    .collect(toList());
    }

    public List<TrelloBoardDto> mapToBoardsDto(final List<TrelloBoard> trelloBoards) throws NullObjectMappedException {
        List<TrelloBoard> validatedList = (List<TrelloBoard>) nullListValidator(trelloBoards);

        return validatedList.stream()
                .map(trelloBoard -> new TrelloBoardDto(trelloBoard.getId(),
                        trelloBoard.getName(),
                        mapToListDto(trelloBoard.getLists())))
                .collect(toList());
    }

    public List<TrelloList> mapToList(final List<TrelloListDto> trelloListDto) {
        return trelloListDto.stream()
                .map(trelloList -> new TrelloList(trelloList.getId(),
                        trelloList.getName(),
                        trelloList.isClosed()))
                .collect(toList());
    }

    public List<TrelloListDto> mapToListDto(final List<TrelloList> trelloLists) {
        return trelloLists.stream()
                .map(trelloList -> new TrelloListDto(trelloList.getId(),
                        trelloList.getName(),
                        trelloList.isClosed()))
                .collect(toList());
    }

    public TrelloCard mapToCard(final TrelloCardDto trelloCardDto) throws NullObjectMappedException {
        TrelloCardDto validatedTrelloCard = (TrelloCardDto) nullValidator(trelloCardDto);

        return new TrelloCard(trelloCardDto.getName(),
                validatedTrelloCard.getDescription(),
                validatedTrelloCard.getPos(),
                validatedTrelloCard.getListId());
    }

    public TrelloCardDto mapToCardDto(final TrelloCard trelloCard) throws NullObjectMappedException {
        TrelloCard validatedTrelloCard = (TrelloCard) nullValidator(trelloCard);

        return new TrelloCardDto(trelloCard.getName(),
                validatedTrelloCard.getDescription(),
                validatedTrelloCard.getPos(),
                validatedTrelloCard.getListId());
    }
}