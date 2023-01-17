package com.ttaaa.noirgame

import com.ttaaa.noirgame.domain.model.AreaFactory
import com.ttaaa.noirgame.domain.model.enumValue.GameAreaSize
import com.ttaaa.noirgame.domain.model.enumValue.GameType
import com.ttaaa.noirgame.domain.model.enumValue.HorizontalDirection
import com.ttaaa.noirgame.domain.model.enumValue.VerticalDirection
import kotlin.random.Random

fun main(args: Array<String>) {
    val areaFactory = AreaFactory()

    val area1 = areaFactory.getArea(GameType.GAME_1, GameAreaSize.SMALL)

    println(area1)

    repeat(10) {
        val killer = area1.getCards { areaCard -> areaCard.isAlive() }.random()
        val victim = area1.getKingMoveCards(killer).filterNot { card -> card == killer }.random()
        println("Killer: $killer\nVictim: $victim")
        area1.killPerson(victim, killer)
        println(area1)
    }

    repeat(10) {
        val rowNumber = Random.nextInt(area1.getRowsCount())
        val hDirection = HorizontalDirection.values().random()
        println("Row: ${rowNumber + 1}, direction: $hDirection")
        area1.moveRow(rowNumber, hDirection)

        println(area1)

        val columnNumber = Random.nextInt(area1.getColumnsCount())
        val vDirection = VerticalDirection.values().random()
        println("Column: ${columnNumber + 1}, direction: $vDirection")
        area1.moveColumn(columnNumber, vDirection)

        println(area1)
    }

    // val area2 = areaFactory.getArea(GameType.GAME_2, GameAreaSize.LARGE)
    // println(area2)
    //
    // val area3 = areaFactory.getArea(GameType.GAME_1, GameAreaSize.MEDIUM)
    // println(area3)
}