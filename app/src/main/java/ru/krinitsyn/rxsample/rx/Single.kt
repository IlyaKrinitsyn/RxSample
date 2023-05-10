package ru.krinitsyn.rxsample.rx

import ru.krinitsyn.rxsample.Logger

interface Single<T> {

    fun subscribe(singleObserver: SingleObserver<T>)

    fun subscribeWithLogger(singleObserver: SingleObserver<T>) {
        Logger.log(RX_TAG, "$this.subscribeWithLogger")
        subscribe(singleObserver)
    }

    fun <R> map(mapper: (T) -> R): Single<R> {
        Logger.log(RX_TAG, "$this.map")
        return SingleMap(this, mapper)
    }

    fun <R> flatMap(mapper: (T) -> Single<R>): Single<R> {
        Logger.log(RX_TAG, "$this.flatMap")
        return SingleFlatMap(this, mapper)
    }

    companion object {
        fun <T> create(singleOnSubscribe: SingleOnSubscribe<T>): Single<T> =
            SingleCreate(singleOnSubscribe)
    }

}