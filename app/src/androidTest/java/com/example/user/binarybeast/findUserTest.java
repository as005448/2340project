package com.example.user.binarybeast;
import com.example.user.binarybeast.helper.ApplicationContextProvider;
import com.example.user.binarybeast.helper.Helper;
import com.example.user.binarybeast.model.UserData;

import junit.framework.TestCase;
/**
 * @author Yan Chen
 * @version 1.0
 */
public class findUserTest extends TestCase {
    private Helper helper;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        helper = new Helper(ApplicationContextProvider.getContext());
    }

    @SuppressWarnings("UnusedAssignment")
    public void testInformationNull() throws Exception {
        try {
            UserData testUser = helper.findUser(null, "name");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }
    @SuppressWarnings("UnusedAssignment")
    public void testTypeNull() throws Exception {
        try {
              UserData testUser = helper.findUser("user",null);
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }
    @SuppressWarnings("UnusedAssignment")
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
        UserData defaultUser = helper.findUser("user", "name");
        assertEquals(defaultUser.getName(),"user");
        assertEquals(defaultUser.getEmail(),"user@gatech.edu");
        assertEquals(defaultUser.getPass(),"pass");
        assertEquals(defaultUser.getId(),1);
        assertEquals(defaultUser.getPost(),0);
        assertEquals(defaultUser.getRate(),0);
    }
}
