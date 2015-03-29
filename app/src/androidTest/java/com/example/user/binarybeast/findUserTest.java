package com.example.user.binarybeast;

import com.example.user.binarybeast.helper.Helper;
import com.example.user.binarybeast.model.UserData;
import com.example.user.binarybeast.view.MainActivity;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Created by Administrator on 2015/3/28.
 */
public class findUserTest extends TestCase{
    Helper helper;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        helper = MainActivity.helper;

    }
    @Test(expected = java.lang.IllegalArgumentException.class)
    public void informationNull() {
        UserData testUser = helper.findUser(null,"name");
    }
    @Test(expected = java.lang.IllegalArgumentException.class)
    public void typeNull() {
        UserData testUser = helper.findUser("user",null);
    }
    @Test(expected = java.lang.IllegalArgumentException.class)
    public void wrongType() {
        UserData testUser = helper.findUser("user","notAtype");
    }
    public void nullUser() {
        UserData testUser = helper.findUser("22222", "name");
        assertNull(testUser);
    }
    public void rightUser() {
        UserData defultUser = helper.findUser("user", "name");
        assertEquals(defultUser.getName(),"user");
        assertEquals(defultUser.getEmail(),"user@gatech.edu");
        assertEquals(defultUser.getPass(),"pass");
        assertEquals(defultUser.getId(),1);
        assertEquals(defultUser.getPost(),0);
        assertEquals(defultUser.getRate(),0);
    }
}
