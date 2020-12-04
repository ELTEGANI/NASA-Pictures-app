package com.example.nasapicturesapp.images

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.nasapicturesapp.MainActivity
import com.example.nasapicturesapp.R
import com.example.nasapicturesapp.repository.ImagesRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject


/**
 * Integration test for the ImagesGridFragment screen.
 */
@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class ImagesGridFragmentTest{
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var imagesRepository: ImagesRepository

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun displayImages_whenRepositoryHasData(){
        //Given data already in the repository
        imagesRepository.retrieveAllJsonImages()

        //WHEN-On StartUp
        launchActivity()

        //THEN - Verify Images are displayed on Grid screen and icon mode is visible and clickable
        onView(withId(R.id.action_night_mode)).perform(click())
        onView(withId(R.id.photos_grid)).check(ViewAssertions.matches((isDisplayed())))
       onView(withId(R.id.photos_grid)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))

    }

    @Test
    fun noImagesDisplayed_whenRepositoryHasData(){
        //Given data already in the repository
        imagesRepository.retrieveAllJsonImages()

        //WHEN-On StartUp
        launchActivity()

        //THEN - Verify Images are displayed on Grid screen and icon mode is visible and clickable
        onView(withId(R.id.no_images_textView)).check(ViewAssertions.matches((isDisplayed())))
    }


    private fun launchActivity(): ActivityScenario<MainActivity>? {
        return launch(MainActivity::class.java)
    }

}