package com.ttaaa.noirgame.domain.model.enumValue

enum class GameType(
    val defaultCardType: CardType,
    val possibleGameAreaSize: List<GameAreaSize>
) {
    //FIXME: naming

    GAME_1(CardType.SUSPECT, listOf(GameAreaSize.SMALL, GameAreaSize.MEDIUM, GameAreaSize.LARGE)),
    GAME_2(CardType.FACE_DOWN_CARD, listOf(GameAreaSize.MEDIUM, GameAreaSize.LARGE)),
}