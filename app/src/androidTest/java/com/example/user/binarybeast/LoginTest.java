package com.example.user.binarybeast;

import com.example.user.binarybeast.helper.ApplicationContextProvider;
import com.example.user.binarybeast.helper.Helper;
import com.example.user.binarybeast.model.UserData;

import junit.framework.TestCase;

import java.util.NoSuchElementException;


public class LoginTest extends TestCase {

    private Helper helper;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        helper = new Helper(ApplicationContextProvider.getContext());
    }

    public void testLoginTest() throws Exception {

        helper = new Helper(ApplicationContextProvider.getContext());

        helper.addUser("abc", "123", "abc", "abc@gmail.com");
        helper.addUser("bcd", "234", "bcd", "bcd@gmail.com");
        helper.addUser("cde", "345", "cde", "cde@gmail.com");
        helper.addUser("def", "456", "def", "def@gmail.com");
        helper.addUser("efg", "567", "efg", "efg@gmail.com");


        assertTrue(helper.login("abc", "123"));


        assertFalse(helper.currUser.getUser().equals("bcd"));
        assertTrue(helper.currUser.getUser().equals("abc"));


        assertFalse(helper.currUser.getPass().equals("234"));
        assertTrue(helper.currUser.getPass().equals("123"));

        assertFalse(helper.currUser.getName().equals("bcd"));
        assertTrue(helper.currUser.getName().equals("abc"));


        assertFalse(helper.currUser.getEmail().equals("bcd@gmail.com"));
        assertTrue(helper.currUser.getEmail().equals("abc@gmail.com"));


        helper.signOut();

        UserData fakeUser = new UserData("abc", "123", "abc", "abc@gmail.com");

        assertFalse(helper.currUser == fakeUser);
        assertTrue(helper.currUser == null);

    }

    @SuppressWarnings("UnusedAssignment")
    public void testNoSuchElementException() throws Exception {
        try {
            Boolean a = helper.login("nicholas", "abc");
        } catch (NoSuchElementException e) {
            assertNotNull(e);
        }
    }



}