package com.ttaaa.noirgame

import com.ttaaa.noirgame.domain.model.AreaFactory
import com.ttaaa.noirgame.domain.model.enumValue.GameAreaSize
import com.ttaaa.noirgame.domain.model.enumValue.GameType

fun main(args: Array<String>) {
    val areaFactory = AreaFactory()

    val area1 = areaFactory.getArea(GameType.GAME_1, GameAreaSize.SMALL)
    println(area1)

    val area2 = areaFactory.getArea(GameType.GAME_2, GameAreaSize.LARGE)
    println(area2)

    val area3 = areaFactory.getArea(GameType.GAME_1, GameAreaSize.MEDIUM)
    println(area3)
}