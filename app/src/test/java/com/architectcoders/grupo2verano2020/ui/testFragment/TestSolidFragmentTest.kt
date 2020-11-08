package com.architectcoders.grupo2verano2020.ui.testFragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.architectcoders.grupo2verano2020.CoroutineTestRule
import com.architectcoders.grupo2verano2020.FakeTestLocalSource
import com.architectcoders.grupo2verano2020.TestUserComponent
import com.architectcoders.grupo2verano2020.defaultFakeTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class TestSolidFragmentTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

//    private val component: TestUserComponent = DaggerTestUserComponent.factory().create()
    private lateinit var testLocalSource: FakeTestLocalSource
    private lateinit var vm: TestSolidViewModel


    @Before
    fun setUp(){
//        vm=component.plus(TestSolidModule()).testSolidViewModel
//        testLocalSource= component.testLocalSource as FakeTestLocalSource
//        testLocalSource.testQuestion= defaultFakeTest
    }
    @ExperimentalCoroutinesApi
    @Test
    fun `observing LiveData finds the movie`()=coroutinesTestRule.testDispatcher.runBlockingTest {
//        vm.model.observeForever()

    }

}