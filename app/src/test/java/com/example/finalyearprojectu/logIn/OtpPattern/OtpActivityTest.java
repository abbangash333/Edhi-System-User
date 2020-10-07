package com.example.finalyearprojectu.logIn.OtpPattern;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OtpActivityTest {

@Test
    public void checkValidity()
{
    String number ="123456";
    assertEquals("The length is equal to 6",number.length(),6);
}

}