package ru.pavelapk.weatherapp.domain.common.usecase

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import ru.pavelapk.weatherapp.domain.BuildConfig

interface FlowUseCase<in P, out R> {
    operator fun invoke(param: P, dispatcher: CoroutineDispatcher = Dispatchers.IO) =
        execute(param).handleOn(dispatcher)

    fun execute(param: P): Flow<Result<R>>
}

interface FlowUseCaseWithoutParam<out R> {
    operator fun invoke(dispatcher: CoroutineDispatcher = Dispatchers.IO) =
        execute().handleOn(dispatcher)

    fun execute(): Flow<Result<R>>
}

private fun <T> Flow<Result<T>>.handleOn(dispatcher: CoroutineDispatcher) = this
    .catch { error ->
        if (BuildConfig.DEBUG) {
            Log.e(this.javaClass.simpleName, null, error)
        }
        emit(Result.failure(error))
    }
    .flowOn(dispatcher)
