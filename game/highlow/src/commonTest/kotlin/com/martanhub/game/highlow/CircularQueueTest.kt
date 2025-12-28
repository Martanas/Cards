package com.martanhub.game.highlow

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

class CircularQueueTest {
    @Test
    fun `zero capacity queue throws an exception`() {
        assertFailsWith(IllegalArgumentException::class) { CircularQueue(capacity = 0) }
    }

    //region peek
    @Test
    fun `peek on an empty queue returns null`() {
        val queue = CircularQueue(capacity = 1)

        assertNull(queue.peek())
    }

    @Test
    fun `peek on a non-empty queue returns last added element`() {
        val queue = CircularQueue(capacity = 1)
        queue.add(69)

        assertEquals(69, queue.peek())
    }
    //endregion

    //region element
    @Test
    fun `element on an empty queue throws an exception`() {
        val queue = CircularQueue(capacity = 1)

        assertFailsWith(NoSuchElementException::class) { queue.element() }
    }

    @Test
    fun `element on a non-empty queue returns last added element`() {
        val queue = CircularQueue(capacity = 1)
        queue.add(69)

        assertEquals(69, queue.element())
    }
    //endregion

    //region remove
    @Test
    fun `remove on an empty queue throws an exception`() {
        val queue = CircularQueue(capacity = 1)

        assertFailsWith(NoSuchElementException::class) { queue.remove() }
    }

    @Test
    fun `remove on a non-empty queue removes last added element and returns it`() {
        val queue = CircularQueue(capacity = 1)
        queue.add(69)

        assertEquals(69, queue.remove())
        assertFailsWith(NoSuchElementException::class) { queue.remove() }
    }
    //endregion

    //region add
    @Test
    fun `adding to already filled queue overwrites first value`() {
        val queue = CircularQueue(capacity = 3)
        queue.add(69)
        queue.add(112)
        queue.add(420)
        queue.add(69)

        assertEquals(listOf(112, 420, 69), queue.getAll())
    }
}