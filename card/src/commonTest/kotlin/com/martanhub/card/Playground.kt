package com.martanhub.card

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class Playground {
    // POC for how Game could wait for player's action
    @Test
    fun test() = runTest {
        val actionFlow = MutableSharedFlow<Boolean>()

        launch(Dispatchers.Unconfined) {
            println("Await")
            val value = actionFlow.first()
            println("Action collected $value")
        }
        println("Emit")
        actionFlow.emit(true)

        launch(Dispatchers.Unconfined) {
            println("Await")
            val value = actionFlow.first()
            println("Action collected $value")
        }

        println("Emit")
        actionFlow.emit(false)
    }
}