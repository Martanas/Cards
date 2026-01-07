package com.martanhub.game.highlow

import com.martanhub.game.highlow.Stack.StackOverflowException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class StackTest {
    @Test
    fun `push to zero capacity stack throws overflow exception`() {
        assertFailsWith(StackOverflowException::class) { Stack<Unit>().push(Unit) }
    }

    @Test
    fun `pushing over capacity throws overflow exception`() {
        val stack = Stack<Int>(capacity = 3)

        stack.push(1)
        stack.push(2)
        stack.push(3)

        assertFailsWith(StackOverflowException::class) { stack.push(4) }
    }

    @Test
    fun `peek on zero capacity stack throws underflow exception`() {
        assertFailsWith(Stack.StackUnderflowException::class) { Stack<Unit>().peek() }
    }

    @Test
    fun `peek does not affect last element`() {
        val stack = Stack<Int>(capacity = 2)

        stack.push(1)
        stack.push(2)

        assertEquals(2, stack.peek())
        assertEquals(2, stack.peek())
    }

    @Test
    fun `pop on zero capacity stack throws underflow exception`() {
        assertFailsWith(Stack.StackUnderflowException::class) { Stack<Unit>().pop() }
    }

    @Test
    fun `peek returns last pushed element`() {
        val expected = 69
        val stack = Stack<Int>(capacity = 1)
        stack.push(expected)

        val actual = stack.peek()

        assertEquals(expected, actual)
    }

    @Test
    fun `pop returns last pushed element`() {
        val expected = 69
        val stack = Stack<Int>(capacity = 1)
        stack.push(expected)

        val actual = stack.pop()

        assertEquals(expected, actual)
    }

    @Test
    fun `pop reduces stack size`() {
        val stack = Stack<Int>(capacity = 3)
        stack.push(1)
        stack.push(2)
        stack.push(3)

        stack.pop()
        stack.pop()
        stack.pop()
        assertFailsWith(Stack.StackUnderflowException::class) { stack.pop() }
    }

    @Test
    fun `pop returns elements in reverse order`() {
        val stack = Stack<Int>(capacity = 3)
        stack.push(1)
        stack.push(2)
        stack.push(3)

        assertEquals(3, stack.pop())
        assertEquals(2, stack.pop())
        assertEquals(1, stack.pop())
    }
}