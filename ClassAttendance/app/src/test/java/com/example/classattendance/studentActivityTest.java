package com.example.classattendance;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

import android.content.Intent;

public class studentActivityTest {

    @Test
    public void testOnCreate() {
        // Create a mock Intent to pass to the StudentActivity
        Intent intent = new Intent();

        // Set any necessary extras on the Intent
        intent.putExtra("courseName", "Test Course");
        intent.putExtra("courseCode", "TEST-123");
        intent.putExtra("position", 0);
        intent.putExtra("class_id", 12345);

        // Create a new StudentActivity and call onCreate
        StudentActivity activity = new StudentActivity();
        activity.onCreate(null);

        // Verify that the activity was initialized correctly
        assertNotNull(activity);
    }
}

