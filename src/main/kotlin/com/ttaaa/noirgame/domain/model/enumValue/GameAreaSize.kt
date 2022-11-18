package com.ttaaa.noirgame.domain.model.enumValue

enum class GameAreaSize(val size: Int, val cardGroup: CardGroup) {
    SMALL(5, CardGroup.GAME_5X5),
    MEDIUM(6, CardGroup.GAME_6X6),
    LARGE(7, CardGroup.GAME_7X7),
}