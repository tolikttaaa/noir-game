package com.ttaaa.noirgame.domain.model

import com.ttaaa.noirgame.domain.model.enumValue.Card
import com.ttaaa.noirgame.domain.model.enumValue.HorizontalDirection
import com.ttaaa.noirgame.domain.model.enumValue.HorizontalDirection.LEFT
import com.ttaaa.noirgame.domain.model.enumValue.HorizontalDirection.RIGHT
import com.ttaaa.noirgame.domain.model.enumValue.VerticalDirection
import com.ttaaa.noirgame.domain.model.enumValue.VerticalDirection.DOWN
import com.ttaaa.noirgame.domain.model.enumValue.VerticalDirection.UP
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Area(
    private var cntRows: Int,
    private var cntColumns: Int,
    private val matrix: MutableList<MutableList<AreaCard>>
) {

    fun moveRow(rowNumber: Int, direction: HorizontalDirection) {
        if (cntRows < rowNumber || rowNumber < 0) {
            throw IllegalArgumentException("RowNumber must be in this interval [0; $cntRows]")
        }

        when (direction) {
            LEFT -> {
                val tmp: AreaCard = matrix[rowNumber][0]

                for (i in 1..cntColumns) {
                    matrix[rowNumber][i - 1] = matrix[rowNumber][i]
                }

                matrix[rowNumber][cntColumns - 1] = tmp
            }

            RIGHT -> {
                val tmp: AreaCard = matrix[rowNumber][cntColumns - 1]

                for (i in cntColumns - 1..1) {
                    matrix[rowNumber][i] = matrix[rowNumber][i - 1]
                }

                matrix[rowNumber][0] = tmp
            }
        }
    }

    fun moveColumn(columnNumber: Int, direction: VerticalDirection) {
        if (cntColumns < columnNumber || columnNumber < 0) {
            throw IllegalArgumentException("RowNumber must be in this interval [0; $cntColumns]")
        }

        when (direction) {
            UP -> {
                val tmp: AreaCard = matrix[0][columnNumber]

                for (i in 1..cntRows) {
                    matrix[i - 1][columnNumber] = matrix[i][columnNumber]
                }

                matrix[cntRows - 1][columnNumber] = tmp
            }

            DOWN -> {
                val tmp: AreaCard = matrix[cntRows - 1][columnNumber]

                for (i in cntRows - 1..1) {
                    matrix[i][columnNumber] = matrix[i - 1][columnNumber]
                }

                matrix[0][columnNumber] = tmp
            }
        }
    }

    fun getKingMoveCards(card: Card): List<Card> {
        val cardCoordinates =
            findCardOnAreaOrNull(card) ?: throw IllegalArgumentException("Card should be on the area!")

        val res: MutableList<AreaCard> = mutableListOf()

        for (i in max(0, cardCoordinates.first - 1)..min(cntRows - 1, cardCoordinates.first + 1)) {
            for (j in max(0, cardCoordinates.second - 1)..min(cntColumns - 1, cardCoordinates.second + 1)) {
                res.add(matrix[i][j])
            }
        }

        return res.filter { areaCard -> areaCard.isAlive() }.map { areaCard -> areaCard.card }
    }

    fun killPerson(victim: Card, killer: Card) {
        val victimCoordinates =
            findCardOnAreaOrNull(victim) ?: throw IllegalArgumentException("Victim's card should be on the area!")

        val killerCoordinates =
            findCardOnAreaOrNull(killer) ?: throw IllegalArgumentException("Killer's card should be on the area!")

        if (!isKingMove(victimCoordinates, killerCoordinates)) {
            throw IllegalArgumentException("Killer's card should be by the king-move cell from victim's card!")
        }

        matrix[victimCoordinates.first][victimCoordinates.second].killPerson()
    }

    private fun findCardOnAreaOrNull(cardToSearch: Card): Pair<Int, Int>? {
        for (i in 0 until cntRows) {
            for (j in 0 until cntColumns) {
                if (matrix[i][j].card == cardToSearch) {
                    return Pair(i, j)
                }
            }
        }

        return null
    }

    private fun isKingMove(coordinates1: Pair<Int, Int>, coordinates2: Pair<Int, Int>): Boolean {
        if (abs(coordinates1.first - coordinates2.first) > 1) {
            return false
        }

        if (abs(coordinates1.second - coordinates2.second) > 1) {
            return false
        }

        return true
    }

    override fun toString(): String {
        val mapString = StringBuilder()

        mapString.addHorizontalLine()

        for (row in matrix) {
            mapString.append("|")
            for (element in row) {
                mapString.append(" ")
                mapString.append(element.card.person.padEnd(STRING_CARD_WIDTH - 1))
                mapString.append("|")
            }
            mapString.append("\n")

            mapString.append("|")
            for (element in row) {
                mapString.append(" ")
                mapString.append(element.type.name.padEnd(STRING_CARD_WIDTH - 1))
                mapString.append("|")
            }
            mapString.append("\n")

            mapString.addHorizontalLine()
        }

        return """
            #GameArea: 
            #   countOfRows     = $cntRows
            #   countOfColumns  = $cntColumns
            #Map: 
            #$mapString       
        """.trimMargin("#")
    }

    private fun StringBuilder.addHorizontalLine() {
        this.append("+")
        this.append(("-".repeat(STRING_CARD_WIDTH) + "+").repeat( cntColumns))
        this.append("\n")
    }

    companion object {
        private const val STRING_CARD_WIDTH = 16
    }
}