package com.tasks.deep_into_flows

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class FlowViewModelTest {


    private lateinit var viewModel: FlowViewModel
    private lateinit var dispatcherProviderTest: DispatcherProviderTest

    @Before
    fun setUp() {
        dispatcherProviderTest = DispatcherProviderTest()
        viewModel = FlowViewModel(dispatcherProviderTest)

    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `count flow down, correctly counts from 5 to 0`() = runBlocking {

        viewModel.countDownFlow().test {

            for (i in 5 downTo 0) {
                dispatcherProviderTest.testCoroutineDispatcher.scheduler.advanceTimeBy(1000L)

                val emittedValue = awaitItem()

                assertEquals(i, emittedValue)
            }
            cancelAndConsumeRemainingEvents()


        }
    }



    @Test
    fun `square number, gives a right answer`()= runBlocking {



        val job =launch {
            viewModel.counterSharedFlow.test {


                val emission =awaitItem()
                assertThat(emission).isEqualTo(9)
                cancelAndConsumeRemainingEvents()

            }
        }
        viewModel.squaresNumbers(3)
        job.join()
        job.cancel()


    }

}