package ru.krinitsyn.rxsample.rx

interface SingleOnSubscribe<T> {
    fun subscribe(emitter: SingleEmitter<T>)
}