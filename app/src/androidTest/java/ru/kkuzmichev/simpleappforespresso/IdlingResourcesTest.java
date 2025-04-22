package ru.kkuzmichev.simpleappforespresso;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class IdlingResourcesTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void registerIdlingResources() {
        IdlingRegistry.getInstance().register(ru.kkuzmichev.simpleappforespresso.EspressoIdlingResources.idlingResource);
    }
    @After
    public void unregisterIdlingResources() {
        IdlingRegistry.getInstance().unregister(ru.kkuzmichev.simpleappforespresso.EspressoIdlingResources.idlingResource);
    }

    @Test
    public void testOpenGallery() {
        ViewInteraction menu = onView(isAssignableFrom(AppCompatImageButton.class));
        menu.check(matches(isDisplayed()));
        menu.perform(click());

        ViewInteraction gallery = onView(withId(R.id.nav_gallery));
        gallery.perform(click());

        ViewInteraction itemSeven = onView(allOf(withId(R.id.item_number), withText("7")));
        itemSeven.check(matches(withText("7")));
    }
}