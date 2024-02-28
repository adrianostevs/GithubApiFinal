package com.example.core.extension

import com.example.core.base.AppResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn

suspend inline fun <T> connection(crossinline run: suspend () -> AppResult<T>) = channelFlow {
    try {
        send(run())
    } catch (e: Exception) {
        send(AppResult.Error(e.message.orEmpty()))
    }
}.flowOn(Dispatchers.IO)