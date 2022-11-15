package com.ttaaa.noirgame.domain.model

import com.ttaaa.noirgame.domain.model.enumValue.Card
import com.ttaaa.noirgame.domain.model.enumValue.HorizontalDirection
import com.ttaaa.noirgame.domain.model.enumValue.HorizontalDirection.LEFT
import com.ttaaa.noirgame.domain.model.enumValue.HorizontalDirection.RIGHT
import com.ttaaa.noirgame.domain.model.enumValue.VerticalDirection
import com.ttaaa.noirgame.domain.model.enumValue.VerticalDirection.DOWN
import com.ttaaa.noirgame.domain.model.enumValue.VerticalDirection.UP

class Area(
    var cntRows: Int,
    var cntColumns: Int,
    var matrix: List<List<AreaCard>>
) {

    fun moveRow(rowNumber: Int, direction: HorizontalDirection) {
        if (cntRows < rowNumber || rowNumber < 0) {
            throw IllegalArgumentException("RowNumber must be in this interval [0; $cntRows]")
        }

        when (direction) {
            LEFT -> {}
            RIGHT -> {}
        }
    }

    fun moveColumn(columnNumber: Int, direction: VerticalDirection) {
        if (cntColumns < columnNumber || columnNumber < 0) {
            throw IllegalArgumentException("RowNumber must be in this interval [0; $cntColumns]")
        }

        when (direction) {
            UP -> {}
            DOWN -> {}
        }
    }

    fun killPerson(victim: Card, killer: Card) {
        
    }
    
}