package app.toilets.domain

import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher

object TestDispatcher {

    fun getTestDispatcher(): TestDispatcher {
        val scheduler = TestCoroutineScheduler()
        return UnconfinedTestDispatcher(scheduler = scheduler)
    }
}