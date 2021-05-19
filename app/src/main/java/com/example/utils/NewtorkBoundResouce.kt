package com.example.utils

import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean //= { true }
) = flow {
    val data = query().first()
    val flow=if (shouldFetch(data)) {
        emit(MySealed.Loading(data))
        try {
            saveFetchResult(fetch())
            query().map { MySealed.Success(it) }
        } catch (throwable: Throwable) {
            query().map { MySealed.Error(it,throwable) }
        }
    } else {
        query().map { MySealed.Success(it) }
    }
    emitAll(flow)
}