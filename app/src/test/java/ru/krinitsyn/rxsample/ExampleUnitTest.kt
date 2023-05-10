package ru.krinitsyn.rxsample

import org.junit.Test

import org.junit.Assert.*
import ru.krinitsyn.rxsample.exmaples.flatMapAndSubscribe
import ru.krinitsyn.rxsample.exmaples.mapAndSubscribe
import ru.krinitsyn.rxsample.exmaples.simpleEmitAndSubscribe

class ExampleUnitTest {
    @Test
    fun `simpleEmitAndSubscribe with correct value`() {
        simpleEmitAndSubscribe(1, 2)
    }

    @Test
    fun `simpleEmitAndSubscribe with exception`() {
        simpleEmitAndSubscribe(1, 0)
    }

    @Test
    fun `mapAndSubscribe with correct value`() {
        mapAndSubscribe(1, 2)
    }

    @Test
    fun `flatMapAndSubscribe with correct value`() {
        flatMapAndSubscribe(1, 2)
    }
}