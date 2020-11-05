package com.architectcoders.grupo2verano2020.ui.testFragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.architectcoders.grupo2verano2020.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.architectcoders.grupo2verano2020.ui.testFragment.TestSolidViewModel
import com.architectcoders.grupo2verano2020.ui.testFragment.TestSolidModule
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TestSolidFragmentTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()



    private val component: TestProjectComponent = DaggerTestProjectComponent.factory().create()
    private lateinit var testLocalSource: FakeTestLocalSource
    private lateinit var vm: TestSolidViewModel

    @Before
    fun setUp() {
        vm = component.plus(TestSolidModule()).testSolidViewModel
        testLocalSource = component.testLocalSource as FakeTestLocalSource
        testLocalSource.testQuestionT = defaultFakeTest
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `data is loaded from server when local source is empty`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {


            vm.calculateResult()


        }

}


