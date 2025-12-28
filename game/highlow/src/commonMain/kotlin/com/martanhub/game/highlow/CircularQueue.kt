package com.martanhub.game.highlow

class CircularQueue(private val capacity: Int) {
    private val elements = mutableListOf<Int>()

    init {
        require(capacity > 0)
    }

    fun peek(): Int? = elements.lastOrNull()

    fun element(): Int = elements.last()

    fun remove(): Int = elements.removeLast()

    fun add(element: Int) {
        if (elements.size == capacity) {
            elements.removeFirst()
        }
        elements.add(element)
    }

    fun getAll(): List<Int> = elements
}