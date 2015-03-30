package com.example.user.binarybeast;
import com.example.user.binarybeast.helper.ApplicationContextProvider;
import com.example.user.binarybeast.helper.Helper;

import junit.framework.TestCase;
/**
 * Created by marcus on 3/29/15.
 */
public class addSaleTestMarcus extends TestCase {
    Helper helper;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        helper = new Helper(ApplicationContextProvider.getContext());
        helper.addUser("abc", "123", "abc", "abc@gmail.com");
    }

    public void testNullName() throws Exception {
        try {
            assertTrue(helper.login("abc", "123"));
            assertTrue(helper.addSale(null, "category1", "1.00", "atlanta"));
            helper.signOut();
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }

    public void testNullCategory() throws Exception {
        try {
            assertTrue(helper.login("abc", "123"));
            assertTrue(helper.addSale("saleName", null, "1.01", "atlanta"));
            helper.signOut();
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }

    public void testNullPrice() throws Exception {
        try {
            assertTrue(helper.login("abc", "123"));
            assertTrue(helper.addSale("saleName2", "category2", null, "atlanta"));
            helper.signOut();
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }

    public void testNullLocation() throws Exception {
        try {
            assertTrue(helper.login("abc", "123"));
            assertTrue(helper.addSale("saleName3", "category3", "1.03", null));
            helper.signOut();
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }

    public void testNoUserLogin() throws Exception {
        try {
            helper.signOut();
            assertNotNull(helper.addSale("saleName4", "category4", "1.04", "atlanta"));
        } catch (NullPointerException np) {
            assertNotNull(np);
        }
    }
}
