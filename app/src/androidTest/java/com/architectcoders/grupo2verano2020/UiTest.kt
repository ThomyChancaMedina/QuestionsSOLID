package com.architectcoders.grupo2verano2020

import android.app.Application
import android.content.Intent
import android.os.SystemClock
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import kotlin.time.ExperimentalTime
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test


class UiTest {

    @ExperimentalTime
    @get:Rule
    val activityTestRule: ActivityTestRule<NavHostActivity> =
        ActivityTestRule(NavHostActivity::class.java, false, false)

    @get:Rule
    val grantPermissionRule: GrantPermissionRule =
        GrantPermissionRule.grant(
            "android.permission.ACCESS_COARSE_LOCATION"
        )

    private lateinit var mockWebServer: MockWebServer
    private lateinit var resource: OkHttp3IdlingResource

    @ExperimentalTime
    @Before
    fun setUp() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val app = instrumentation.targetContext.applicationContext as Application
        val component = DaggerUiTestComponent.factory().create(app)

        mockWebServer = component.mockWebServer

        resource = OkHttp3IdlingResource.create("OkHttp", component.questionDB.okHttpClient)
        IdlingRegistry.getInstance().register(resource)

        val intent = Intent(instrumentation.targetContext, NavHostActivity::class.java)

        activityTestRule.launchActivity(intent)
    }

    @Test
    fun click_Navigates_question_RecycleView() {

        val recycleView = Espresso.onView(withId(R.id.recycler))



        SystemClock.sleep(1000)

        recycleView.check(ViewAssertions.matches(isDisplayed()))

        recycleView.perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5)
        )

        onView(withText("6. ¿si no soy capaz de escribir mi test unitarios tengo que mirar mis interfaces como guiá?")).check(
            ViewAssertions.matches(isDisplayed())
        )

    }


    @After
    fun tearDown() {
        mockWebServer.close()
        mockWebServer.shutdown()
        IdlingRegistry.getInstance().unregister(resource)
    }


}


