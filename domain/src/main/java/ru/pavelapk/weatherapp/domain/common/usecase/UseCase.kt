package ru.pavelapk.weatherapp.domain.common.usecase

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.pavelapk.weatherapp.domain.BuildConfig

interface UseCase<in P, out R> {
    suspend operator fun invoke(
        param: P,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Result<R> = withContext(dispatcher) {
        try {
            execute(param)
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) {
                Log.e(this.javaClass.simpleName, null, e)
            }
            Result.failure(e)
        }
    }

    suspend fun execute(param: P): Result<R>
}

interface UseCaseWithoutParam<out R> {
    suspend operator fun invoke(dispatcher: CoroutineDispatcher = Dispatchers.IO) =
        withContext(dispatcher) {
            try {
                execute()
            } catch (e: Exception) {
                if (BuildConfig.DEBUG) {
                    Log.e(this.javaClass.simpleName, null, e)
                }
                Result.failure(e)
            }
        }

    suspend fun execute(): Result<R>
}
