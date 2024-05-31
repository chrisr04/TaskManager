package com.example.taskmanager.core.utils.types

sealed class Option<out S> {

    data object None : Option<Nothing>()

    data class Some<out S>(val some: S) : Option<S>()

    fun isNone(): Boolean = this is None

    fun isSome(): Boolean = this is Some

    fun someOrNull(): S? = if (this is Some) this.some else null

    fun <T> fold(onNone: () -> T, onSome: (S) -> T): T = when (this) {
        is None -> onNone()
        is Some -> onSome(some)
    }
}