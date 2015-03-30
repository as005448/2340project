package com.example.user.binarybeast;

import com.example.user.binarybeast.helper.ApplicationContextProvider;
import com.example.user.binarybeast.helper.Helper;
import com.example.user.binarybeast.model.Sale;

import junit.framework.TestCase;

/**
 * @author Guoziyang
 * @version 1.0
 */
public class addSale extends TestCase {
    private Helper helper;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        helper = new Helper(ApplicationContextProvider.getContext());


    }
    public void testInterest() throws Exception {
        helper.addUser("abc", "123", "abc", "abc@gmail.com");
        helper.login("abc", "123");
        helper.addSale("abc", "food", "3", "NewYork");
        helper.addSale("bcd", "phone", "1100", "Chicago");
        helper.addSale("cde", "clothes", "50", "Atlanta");

        Sale sale1 = helper.getSaleByName("abc");
        Sale sale2 = helper.getSaleByName("bcd");
        Sale sale3 = helper.getSaleByName("cde");

        assertTrue(sale1.getName().equals("abc"));
        assertFalse(sale1.getName().equals("xxx"));

        assertTrue(sale2.getName().equals("bcd"));
        assertFalse(sale2.getName().equals("xxx"));

        assertTrue(sale3.getName().equals("cde"));
        assertFalse(sale3.getName().equals("xxx"));
    }
}
