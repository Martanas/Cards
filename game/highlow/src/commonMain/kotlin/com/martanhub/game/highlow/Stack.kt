package com.martanhub.game.highlow

class Stack<T>(private val capacity: Int = 0) {
    private var size = 0
    private val elements = ArrayList<T>(capacity)

    fun push(item: T) {
        if (size == capacity) {
            throw StackOverflowException()
        }
        elements.add(size++, item)
    }

    fun peek(): T {
        if (size == 0) {
            throw StackUnderflowException()
        }
        return elements[this.size - 1]
    }

    fun pop(): T {
        if (size == 0) {
            throw StackUnderflowException()
        }
        return elements[--this.size]
    }

    class StackOverflowException : RuntimeException()

    class StackUnderflowException : RuntimeException()
}