package ru.krinitsyn.rxsample.rx

import ru.krinitsyn.rxsample.Logger

class SingleCreate<T>(private val source: SingleOnSubscribe<T>) : Single<T> {

    override fun subscribe(downstream: SingleObserver<T>) {
        Logger.log(RX_TAG, "$this.subscribe | Зашли в subscribe SingleCreate")

        val emitter = object : SingleEmitter<T> {
            override fun onSuccess(value: T) {
                Logger.log(RX_TAG, "$this.onSuccess | Эмитим начальное значение после выполнения работы")
                downstream.onSuccess(value)
            }

            override fun onError(throwable: Throwable) {
                Logger.log(RX_TAG, "$this.onError | Эмитим ошибку после выполнения работы")
                downstream.onError(throwable)
            }
        }
        Logger.log(RX_TAG, "$this.subscribe | Создали эмиттер")

        Logger.log(RX_TAG, "$this.subscribe | Подписываемся на эмиттер")
        source.subscribe(emitter as SingleEmitter<T>)

        Logger.log(RX_TAG, "$this.subscribe | Подписались на эмиттер")
    }

}