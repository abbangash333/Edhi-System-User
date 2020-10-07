package com.example.finalyearprojectu;
import com.example.finalyearprojectu.logIn.logInPattern.model.UserLogInModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserLogInModelTest {
    private UserLogInModel userLogInModel;
    @Before //This is executed before the @Test executes
    public void setUp(){
        userLogInModel = new UserLogInModel("3339601461");
        System.out.println("Ready for testing");
    }
    @After //This is executed before the @Test executes
    public void tearDown(){
        userLogInModel = new UserLogInModel("3339601461");
        System.out.println("Done with test");
    }
    @Test
    public void testChecValidity()
    {
        String number ="3339601461";
        int result = userLogInModel.checkValidity(number);
        assertEquals("The length is equal to 10",number.length(),10);

    }

}
