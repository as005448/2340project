package com.example.user.binarybeast;
import com.example.user.binarybeast.helper.ApplicationContextProvider;
import com.example.user.binarybeast.helper.Helper;
import com.example.user.binarybeast.model.Interest;

import junit.framework.TestCase;

import java.util.List;

/**
 * @author zhenluhu
 * @version 1.0
 */
public class addInterestTest extends TestCase {
    private Helper helper;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        helper = new Helper(ApplicationContextProvider.getContext());
    }

    public void testInterest() throws Exception {
        helper = new Helper(ApplicationContextProvider.getContext());
        helper.addUser("abc", "123", "abc", "abc@gmail.com");
        helper.login("abc", "123");
        helper.addInterest("abc", "food","3");
        helper.addInterest("bcd", "phone","1100");
        helper.addInterest("cde", "clothes", "50");

        List<Interest> interest = helper.getInterestListByOwner(helper.currUser.getId());
        assertTrue(interest.get(0).getName().equals("abc"));
        assertFalse(interest.get(0).getName().equals("cba"));

        assertTrue(interest.get(1).getName().equals("bcd"));
        assertFalse(interest.get(1).getName().equals("dcb"));

        assertTrue(interest.get(2).getName().equals("cde"));
        assertFalse(interest.get(2).getName().equals("edc"));


    }
}
