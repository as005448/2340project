package com.example.user.binarybeast;
import com.example.user.binarybeast.helper.ApplicationContextProvider;
import com.example.user.binarybeast.helper.Helper;
import com.example.user.binarybeast.model.UserData;

import junit.framework.TestCase;
/**
 * Created by Yan Chen on 2015/3/28.
 */
public class findUserTest extends TestCase {
    Helper helper;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        helper = new Helper(ApplicationContextProvider.getContext());
    }

    public void testInformationNull() throws Exception {
        try {
            UserData testUser = helper.findUser(null, "name");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }
    public void testTypeNull() throws Exception {
        try {
              UserData testUser = helper.findUser("user",null);
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }
    public void testWrongType() throws Exception {
        try {
            UserData testUser = helper.findUser("user","notAtype");
        } catch (IllegalArgumentException e) {
           assertNotNull(e);
        }
    }
    public void testNullUser() throws Exception{
        UserData testUser = helper.findUser("22222", "name");
        assertNull(testUser);
    }
    public void testRightUser() throws Exception{
        UserData defultUser = helper.findUser("user", "name");
        assertEquals(defultUser.getName(),"user");
        assertEquals(defultUser.getEmail(),"user@gatech.edu");
        assertEquals(defultUser.getPass(),"pass");
        assertEquals(defultUser.getId(),1);
        assertEquals(defultUser.getPost(),0);
        assertEquals(defultUser.getRate(),0);
    }
}
