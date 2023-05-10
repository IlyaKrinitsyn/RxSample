package ru.krinitsyn.rxsample.rx

import ru.krinitsyn.rxsample.Logger

class SingleFlatMap<T, R>(
    private val source: Single<T>,
    private val mapper: (T) -> Single<R>
) : Single<R> {

    override fun subscribe(downstream: SingleObserver<R>) {
        Logger.log(RX_TAG, "$this.subscribe | Зашли в subscribe SingleFlatMap")

        source.subscribe(object : SingleObserver<T> {
            override fun onSuccess(value: T) {
                Logger.log(RX_TAG, "$this.onSuccess | Зашли в onSuccess для флэт маппинга")

                val result: Single<R>

                try {
                    result = mapper.invoke(value)
                    Logger.log(RX_TAG, "$this.onSuccess | Флэт маппинг отработал")

                    result.subscribe(object : SingleObserver<R> {
                        override fun onSuccess(value: R) {
                            Logger.log(RX_TAG, "$this.onSuccess | Флэт маппинг отработал")
                            downstream.onSuccess(value)
                        }

                        override fun onError(throwable: Throwable) {
                            Logger.log(RX_TAG, "$this.onError | Словили ошибку")
                            downstream.onError(throwable)
                        }
                    })

                } catch (throwable: Throwable) {
                    Logger.log(RX_TAG, "$this.onError | Словили ошибку")
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