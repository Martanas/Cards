package com.martanhub.game.highlow.engine

class CircularQueue<T>(private val capacity: Int) {
    private val elements = mutableListOf<T>()

    init {
        require(capacity > 0)
    }

    fun peek(): T? = elements.lastOrNull()

    fun element(): T = elements.last()

    fun remove(): T = elements.removeLast()

    fun add(element: T) {
        if (elements.size == capacity) {
            elements.removeFirst()
        }
        elements.add(element)
    }

    fun getAll(): List<T> = elements
}