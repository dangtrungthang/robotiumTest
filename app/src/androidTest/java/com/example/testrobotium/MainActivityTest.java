package com.example.testrobotium;

import org.junit.Test;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import android.app.Instrumentation;
import android.content.Context;
import android.widget.TextView;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    private Context context;
    private Solo solo;

    @Before
    public void setUp() throws Exception {
        //setUp() is run before a test case is started.'8/
        //This is where the solo object is created.
        solo = new Solo(androidx.test.platform.app.InstrumentationRegistry.getInstrumentation(), activityTestRule.getActivity());
        context = InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
    }
    @After
    public void tearDown() throws Exception {
        //tearDown() is run after a test case has finished.
        //finishOpenedActivities() will finish all the activities that have been opened during the test execution.
        solo.finishOpenedActivities();
    }

    @Test
    public void testAdd() throws Exception {
        for(int i = 0; i < 10; i++){

            solo.clearEditText(0);
            solo.clearEditText(1);
            int first = new Random().nextInt(200);
            int second = new Random().nextInt(200);
            solo.enterText(0, String.valueOf(first));
            solo.enterText(1, String.valueOf(second));
            solo.clickOnView(solo.getView(R.id.btnGetResult));
            TextView textResult = (TextView) solo.getView(R.id.txtResult);
            int actualResult = Integer.parseInt(textResult.getText().toString());

            int expectedResult = first + second;

            assertEquals(expectedResult, actualResult);

            TextView textComment = (TextView) solo.getView(R.id.txtComment);

            TextView toast = (TextView) solo.getView(android.R.id.message);
            if (expectedResult % 2 == 0) {
                assertEquals("toast is not showing", "Đây là số chẵn", toast.getText().toString());
                assertEquals("Đây là số chẵn", textComment.getText().toString());
            } else {
                assertEquals("toast is not showing", "Đây là số lẻ", toast.getText().toString());
                assertEquals("Đây là số lẻ", textComment.getText().toString());
            }

        }

    }

    @Test
    public void testZCalculatorBlackBox() {
        // Enter 5 in first number field
        this.solo.enterText(0, "5");
        // Enter 4 in second number field
        this.solo.enterText(1, "4");
        // Press Addition Spinner Item
        this.solo.pressSpinnerItem(0, 0);
        // Click on get result button
        this.solo.clickOnButton(0);
        // Verify that resultant of 5 + 4
        assertTrue(this.solo.searchText("9"));
        // Press Subtraction Spinner Item
        this.solo.pressSpinnerItem(0, 1);
        // Click on get result button
        this.solo.clickOnButton(0);
        // Verify that resultant of 5 – 4
        assertTrue(this.solo.searchText("1"));

    }

}