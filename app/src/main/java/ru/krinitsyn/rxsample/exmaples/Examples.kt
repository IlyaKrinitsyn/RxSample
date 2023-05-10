package ru.krinitsyn.rxsample.exmaples

import ru.krinitsyn.rxsample.Logger
import ru.krinitsyn.rxsample.rx.*

fun simpleEmitAndSubscribe(a: Int, b: Int) {
    Single
        .create(object : SingleOnSubscribe<Int> {
            override fun subscribe(emitter: SingleEmitter<Int>) {
                try {
                    val result = doAsyncWork(a, b)
                    emitter.onSuccess(result)
                } catch (exception: ArithmeticException) {
                    emitter.onError(throwable = exception)
                }
            }
        })
        .subscribeWithLogger(object : SingleObserver<Int> {
            override fun onSuccess(value: Int) {
                Logger.log(RX_TAG, "Результат = $value")
            }

            override fun onError(throwable: Throwable) {
                Logger.log(RX_TAG, "Ошибка = ${throwable.message}")
            }
        })
}

fun mapAndSubscribe(a: Int, b: Int) {
    Single
        .create(object : SingleOnSubscribe<Int> {
            override fun subscribe(emitter: SingleEmitter<Int>) {
                try {
                    val result = doAsyncWork(a, b)
                    emitter.onSuccess(result)
                } catch (exception: ArithmeticException) {
                    emitter.onError(throwable = exception)
                }
            }
        })
        .map { it.toString() }
        .subscribeWithLogger(object : SingleObserver<String> {
            override fun onSuccess(value: String) {
                Logger.log(RX_TAG, "Результат = $value")
            }

            override fun onError(throwable: Throwable) {
                Logger.log(RX_TAG, "Ошибка = ${throwable.message}")
            }
        })
}

fun flatMapAndSubscribe(a: Int, b: Int) {
    Single
        .create(object : SingleOnSubscribe<Int> {
            override fun subscribe(emitter: SingleEmitter<Int>) {
                try {
                    val result = doAsyncWork(a, b)
                    emitter.onSuccess(result)
                } catch (exception: ArithmeticException) {
                    emitter.onError(throwable = exception)
                }
            }
        })
        .flatMap { it ->
            Single.create(object : SingleOnSubscribe<String> {
                override fun subscribe(emitter: SingleEmitter<String>) {
                    try {
                        val result = doAsyncWork(it)
                        emitter.onSuccess(result)
                    } catch (exception: ArithmeticException) {
                        emitter.onError(throwable = exception)
                    }
                }
            })
        }
        .subscribeWithLogger(object : SingleObserver<String> {
            override fun onSuccess(value: String) {
                Logger.log(RX_TAG, "Результат = $value")
            }

            override fun onError(throwable: Throwable) {
                Logger.log(RX_TAG, "Ошибка = ${throwable.message}")
            }
        })
}

@Throws(ArithmeticException::class)
private fun doAsyncWork(a: Int, b: Int): Int = a / b

@Throws(ArithmeticException::class)
private fun doAsyncWork(it: Int): String = it.toString()