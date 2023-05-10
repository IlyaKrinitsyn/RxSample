package ru.krinitsyn.rxsample.rx

interface SingleEmitter<T> {

    fun onSuccess(value: T)

    fun onError(throwable: Throwable)

}