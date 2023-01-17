package com.ttaaa.noirgame.domain.model

import com.ttaaa.noirgame.domain.model.enumValue.Card
import com.ttaaa.noirgame.domain.model.enumValue.CardType
import com.ttaaa.noirgame.domain.model.enumValue.CardType.*

class AreaCard(
    val card: Card,
    var type: CardType,
) {
    fun killPerson() {
        when (type) {
            DIED_SUSPECT, FACE_DOWN_CARD -> throw IllegalArgumentException("Died suspect can't be killed!")
            SUSPECT, EVIDENCE -> {
                type = DIED_SUSPECT
            }
        }
    }

    fun isAlive(): Boolean {
        return when (type) {
            DIED_SUSPECT, FACE_DOWN_CARD -> false
            SUSPECT, EVIDENCE -> true
        }
    }
}