package com.example.user.binarybeast;

import com.example.user.binarybeast.helper.Helper;
import com.example.user.binarybeast.view.MainActivity;

import junit.framework.TestCase;


public class LoginTest extends TestCase {

    MainActivity mainActivity = new MainActivity();
    Helper helper;

    public void testLoginTest() throws Exception {

        helper = new Helper(ApplicationContextProvider.getContext());

        helper.addUser("abc", "123", "abc", "abc@gmail.com");
        helper.addUser("bcd", "234", "bcd", "bcd@gmail.com");
        helper.addUser("cde", "345", "cde", "cde@gmail.com");
        helper.addUser("def", "456", "def", "def@gmail.com");
        helper.addUser("efg", "567", "efg", "efg@gmail.com");


        assertTrue(helper.login("abc", "123"));

        assertFalse(helper.currUser.getId() == 2);
        assertEquals(true, MainActivity.helper.currUser.getId() == 1);


//        assertFalse(helper.currUser.getUser().equals("bcd"));
//        assertTrue(helper.currUser.getUser().equals("abc"));
//
//
//        assertFalse(currUser.getPass().equals("234"));
//        assertTrue(currUser.getPass().equals("123"));
//
//        assertFalse(currUser.getName().equals("bcd"));
//        assertTrue(currUser.getName().equals("abc"));
//
//
//        assertFalse(currUser.getEmail().equals("bcd@gmail.com"));
//        assertTrue(currUser.getEmail().equals("abc@gmail.com"));
//
//
//        helper.signOut();
//
//        assertFalse(currUser == new UserData("abc", "123", "abc", "abc@gmail.com"));
//        assertTrue(currUser == null);

    }

    public void testNoSuchElementException() throws Exception {
        assertEquals(null,helper.login("nicholas", "abc"));
    }



}