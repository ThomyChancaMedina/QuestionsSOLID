package com.architectcoders.grupo2verano2020.ui.testFragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.architectcoders.grupo2verano2020.*
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers

class TestSolidFragmentTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val observer: Observer<TestSolidViewModel.UiModel> = mock()
    private val component: TestComponent = DaggerTestComponent.factory().create()
    private lateinit var testLocalSource: FakeTestLocalSource
    private lateinit var vm: TestSolidViewModel


    @Before
    fun setUp() {
        vm = component.plus(TestSolidModule()).testSolidViewModel
        testLocalSource = component.testLocalSource as FakeTestLocalSource
        testLocalSource.testQuestion = defaultFakeTest
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `observing LiveData finds the movie`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        vm.modelTest.observeForever(observer)

        verify(observer).onChanged(
            ArgumentMatchers.refEq(TestSolidViewModel.UiModel.Content(defaultFakeTest))
        )

    }

}