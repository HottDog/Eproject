package com.example.yuanjc.myapplication1;

import android.app.Fragment;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.yuanjc.myapplication1.view.activity.MainActivity;
import com.example.yuanjc.myapplication1.view.mainFragment.AllFragment;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by yuanjc on 2016/8/5.
 */
@RunWith(AndroidJUnit4.class)
public class PersonInfoTest {
    private static final String TAG = PersonInfoTest.class.getName();
    @Rule
    public ActivityTestRule<MainActivity> rule=
            new ActivityTestRule<MainActivity>(MainActivity.class);
    @Test
    public void hotFragmentChangeTest(){
        onView(withId(R.id.hot)).perform(click());
        onView(withId(R.id.topic_tv)).check(matches(withText("热销")));
    }
}
