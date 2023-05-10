package ru.krinitsyn.rxsample.rx

interface SingleObserver<T> {

    fun onSuccess(value: T)

    fun onError(throwable: Throwable)

}