package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ShowActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> scenario = new ActivityScenarioRule<>(MainActivity.class);

    // Helper method to add cities
    private void addCity(String name) {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText(name));
        onView(withId(R.id.button_confirm)).perform(click());
    }

    @Test
    public void testSwitchToShowActivity() {
        addCity("Edmonton");

        // taps the first row in the ListView
        onData(is("Edmonton"))
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        // ShowActivity should be visible (its TextView proves we're there)
        onView(withId(R.id.text_city)).check(matches(isDisplayed()));
    }

    @Test
    public void testShowsCorrectCityName() {
        addCity("Vancouver");

        onData(is("Vancouver"))
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        onView(withId(R.id.text_city)).check(matches(withText("Vancouver")));
    }

    @Test
    public void testBackButton() {
        addCity("Burnaby");

        onData(is("Burnaby"))
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        // Press back button
        onView(withId(R.id.button_back)).perform(click());

        // Check that we're back on MainActivity (ListView present)
        onView(withId(R.id.city_list)).check(matches(isDisplayed()));
    }
}
