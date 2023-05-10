package ru.krinitsyn.rxsample.rx

import ru.krinitsyn.rxsample.Logger

class SingleMap<T, R>(
    private val source: Single<T>,
    private val mapper: (T) -> R
) : Single<R> {

    override fun subscribe(downstream: SingleObserver<R>) {
        Logger.log(RX_TAG, "$this.subscribe | Зашли в subscribe SingleMap")

        source.subscribe(object : SingleObserver<T> {
            override fun onSuccess(value: T) {
                Logger.log(RX_TAG, "$this.onSuccess | Зашли в onSuccess для маппинга")

                val result: R

                try {
                    result = mapper.invoke(value)
                    Logger.log(RX_TAG, "$this.onSuccess | Маппинг отработал")
                    downstream.onSuccess(result)

                } catch (throwable: Throwable) {
                    Logger.log(RX_TAG, "$this.onSuccess | Словили ошибку")
                    downstream.onError(throwable)
                }
            }

            override fun onError(throwable: Throwable) {
                Logger.log(RX_TAG, "$this.onError | Словили ошибку")
                downstream.onError(throwable)
            }
        })
    }

}