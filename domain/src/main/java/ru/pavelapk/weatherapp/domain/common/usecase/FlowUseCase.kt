package ru.pavelapk.weatherapp.domain.common.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface FlowUseCase<out R> {
    operator fun invoke(dispatcher: CoroutineDispatcher = Dispatchers.IO) =
        execute().flowOn(dispatcher)

    fun execute(): Flow<R>
}
