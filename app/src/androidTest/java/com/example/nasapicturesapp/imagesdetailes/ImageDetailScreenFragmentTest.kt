package com.example.nasapicturesapp.imagesdetailes

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.nasapicturesapp.R
import com.example.nasapicturesapp.launchFragmentInHiltContainer
import com.example.nasapicturesapp.repository.ImagesRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject


/**
 * Integration test for the ImageDetailScreenFragment screen.
 */
@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class ImageDetailScreenFragmentTest{
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var imagesRepository: ImagesRepository

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun displayImageDetail_whenNavigateFrom() {
        //Given data already in the repository
        val imagesDetailes =  imagesRepository.retrieveAllJsonImages()

        //WHEN-Details fragment launched to display
        val bundle = imagesDetailes?.let { ImageDetailScreenFragmentArgs(it[0]).toBundle() }
        launchFragmentInHiltContainer<ImageDetailScreenFragment>(bundle)

        //THEN - Verify Images are displayed with descriptions displayed
        onView(ViewMatchers.withId(R.id.slider)).check(ViewAssertions.matches((ViewMatchers.isDisplayed())))

    }

}