package ru.krinitsyn.rxsample

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.Test
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class QuestionsExamplesTest {
    @Test
    @Throws(Exception::class)
    fun `switchMap test`() {
        val scheduler = TestScheduler()
        Observable.just("a", "b", "c", "d", "e", "f")
            .switchMap { s ->
                val delay = Random.nextLong(10)
                println("Delay for $s = $delay")
                Observable.just(s + "x")
                    .delay(delay, TimeUnit.SECONDS, scheduler)
            }
            .toList()
            .doOnSuccess(System.out::println)
            .subscribe()
        scheduler.advanceTimeBy(1, TimeUnit.MINUTES)
    }

    @Test
    @Throws(Exception::class)
    fun `flatMap test`() {
        val scheduler = TestScheduler()
        Observable.just("a", "b", "c", "d", "e", "f")
            .flatMap { s ->
                val delay = Random.nextLong(10)
                println("Delay for $s = $delay")
                Observable.just(s + "x")
                    .delay(delay, TimeUnit.SECONDS, scheduler)
            }
            .toList()
            .doOnSuccess(System.out::println)
            .subscribe()
        scheduler.advanceTimeBy(1, TimeUnit.MINUTES)
    }

    @Test
    @Throws(Exception::class)
    fun `concatMap test`() {
        val scheduler = TestScheduler()
        Observable.just("a", "b", "c", "d", "e", "f")
            .concatMap { s ->
                val delay = Random.nextLong(10)
                println("Delay for $s = $delay")
                Observable.just(s + "x")
                    .delay(delay, TimeUnit.SECONDS, scheduler)
            }
            .toList()
            .doOnSuccess(System.out::println)
            .subscribe()
        scheduler.advanceTimeBy(1, TimeUnit.MINUTES)
    }

}