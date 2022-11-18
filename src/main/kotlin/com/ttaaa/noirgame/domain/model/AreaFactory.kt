package com.ttaaa.noirgame.domain.model

import com.ttaaa.noirgame.domain.model.enumValue.Card
import com.ttaaa.noirgame.domain.model.enumValue.CardGroup.*
import com.ttaaa.noirgame.domain.model.enumValue.GameAreaSize
import com.ttaaa.noirgame.domain.model.enumValue.GameType
import org.springframework.stereotype.Component

@Component
class AreaFactory {
    fun getArea(gameType: GameType, gameAreaSize: GameAreaSize): Area {
        if (gameAreaSize !in gameType.possibleGameAreaSize) {
            throw IllegalArgumentException("AreaFactory can't create Area for type of game: $gameType, with size: $gameAreaSize")
        }

        val cards: List<Card> = when (gameAreaSize.cardGroup) {
            GAME_5X5 -> {
                Card.values().toList().filter { it.group == GAME_5X5 }
            }

            GAME_6X6 -> {
                Card.values().toList().filter { it.group == GAME_5X5 || it.group == GAME_6X6 }
            }

            GAME_7X7 -> {
                Card.values().toList()
            }
        }

        val matrix = cards.shuffled().map { card ->
            AreaCard(card = card, type = gameType.defaultCardType)
        }.chunked(gameAreaSize.size).map { list -> list.toMutableList() }.toMutableList()

        return Area(
            cntRows = gameAreaSize.size,
            cntColumns = gameAreaSize.size,
            matrix = matrix
        )
    }
}