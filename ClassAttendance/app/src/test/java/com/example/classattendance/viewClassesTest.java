package com.example.classattendance;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class viewClassesTest {

    @Test
    public void testOnCreate() {
        // Create a new viewClasses and call onCreate
        viewClasses activity = new viewClasses();
        activity.onCreate(null);

        // Verify that the activity was initialized correctly
        assertNotNull(activity);
    }
}
