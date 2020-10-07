package com.example.finalyearprojectu;


import com.example.finalyearprojectu.logIn.OtpPattern.OtpActivity;

import org.junit.Rule;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;


@RunWith(AndroidJUnit4.class)

public class OTPTest {
    @Rule
    public ActivityScenarioRule<OtpActivity> activityScenarioRule
            = new ActivityScenarioRule<>(OtpActivity.class);


}
