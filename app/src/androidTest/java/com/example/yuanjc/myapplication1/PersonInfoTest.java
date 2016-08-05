package com.example.yuanjc.myapplication1;

import android.support.test.espresso.intent.Checks;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.yuanjc.myapplication1.bean.Fund;
import com.example.yuanjc.myapplication1.view.activity.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
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
    @Test
    public void allFragmentChangeButtonTest(){
        onView(withId(R.id.all)).perform(click());
        onView(withId(R.id.topic_tv)).check(matches(withText("全部")));
        onView(withId(R.id.allFragment)).check(matches(isDisplayed()));
        onView(withId(R.id.type_tv)).check(matches(withText("类型")));
    }

    @Test
    public void allFragmentTypeSelectTest(){
        onView(withId(R.id.type_tv)).perform(click());
        onView(withId(R.id.listview))
                .check(matches(isDisplayed()));
        onView(withId(R.id.type_list))
                .inRoot(isPlatformPopup())
                .check(matches(isDisplayed()));
//        onView(withContentDescription("类型选择窗口")).inRoot(isPlatformPopup())
//                .check(matches(isDisplayed()));
//        onData(withId(R.id.gou))
//                .inAdapterView(allOf(withId(R.id.type_list),isDisplayed()))
//                .atPosition(0)
//                .check(matches(isDisplayed()));
//        onData(withFundName(Fund.Type.HUNHE.toString()))
//                .inAdapterView(allOf(isAssignableFrom(ListView.class)))
//                .perform(click());
//        onView(withId(R.id.topic_tv)).perform(click());
//        onData(withFundName(Fund.Type.HUNHE.toString()))
//                .inAdapterView(allOf(isAssignableFrom(ListView.class)))
//                .onChildView(withId(R.id.gou))
//                .check(matches(isDisplayed()));
//        onView(withId(R.id.topic_tv)).check(matches(withText(Fund.Type.HUNHE.toString())));
    }

    @Test
    public void allFragmentValueType(){}

    /**
     * 匹配RefreshAndLoadListView的item项
     * @param s
     * @return
     */
    public static Matcher<Object> withFundName(final String s){
        Checks.checkNotNull(s);
        return new BoundedMatcher<Object,Fund>(Fund.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("").appendText(s);
            }

            @Override
            protected boolean matchesSafely(Fund item) {
                return item.getName().equals(s);
            }
        };
    }

    /**
     * 匹配typeSelectPopWindows的子项
     * @param type
     * @return
     */
    public static Matcher<Object> withTypeName(final Fund.Type type){
        Checks.checkNotNull(type);
        return new BoundedMatcher<Object,String>(String.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("the select item's name: ").appendText(type.toString());
            }

            @Override
            protected boolean matchesSafely(String item) {
                return item.equals(type.toString());
            }
        };

    }

}
