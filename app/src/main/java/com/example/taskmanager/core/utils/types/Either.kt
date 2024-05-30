package com.example.taskmanager.core.utils.types

sealed class Either<out L, out R> {

    data class Left<out L>(val left: L) : Either<L, Nothing>()

    data class Right<out R>(val right: R) : Either<Nothing, R>()

    fun isLeft(): Boolean = this is Left

    fun isRight(): Boolean = this is Right

    fun getLeft(): L? = if (this is Left) (this as Left).left else null

    fun getRight(): R? = if (this is Right) (this as Right).right else null

    fun <T> fold(onLeft: (L) -> T, onRight: (R) -> T): T = when (this) {
        is Left -> onLeft(left)
        is Right -> onRight(right)
    }
}