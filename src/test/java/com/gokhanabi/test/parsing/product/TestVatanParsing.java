package com.gokhanabi.test.parsing.product;

import com.mephalay.model.ParserVatanProduct;
import com.mephalay.parser.ProductPriceParser;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.fail;


/**
 * Created by GokhanOzgozen on 12/14/2015.
 */
@RunWith(JMockit.class)
public class TestVatanParsing {

    @Test
    public void testObtainingVatanProducts() {
        try {
            List<ParserVatanProduct> vatanProductList = ProductPriceParser.parseForVatanOEMPrices();
            assertTrue(vatanProductList != null && vatanProductList.size() > 100);
            System.out.println(vatanProductList.size());
            System.out.println(vatanProductList);
        } catch (Throwable t) {
            t.printStackTrace();
            fail(t.getMessage());
        }
    }

    @Test
    public void testObtainingVatanMonitors() {
        try {
            List<ParserVatanProduct> vatanProductList = ProductPriceParser.parseForVatanMonitorPrices();
            assertTrue(vatanProductList != null && vatanProductList.size() > 100);
            System.out.println(vatanProductList.size());
            System.out.println(vatanProductList);
        } catch (Throwable t) {
            t.printStackTrace();
            fail(t.getMessage());
        }
    }

    @Test
    public void testObtainingVatanLaptops() {
        try {
            List<ParserVatanProduct> vatanProductList = ProductPriceParser.parseForVatanLaptopPrices();
            assertTrue(vatanProductList != null && vatanProductList.size() > 100);
            System.out.println(vatanProductList.size());
            System.out.println(vatanProductList);
        } catch (Throwable t) {
            t.printStackTrace();
            fail(t.getMessage());
        }
    }

    @Test
    public void testObtainingVatanPhones() {
        try {
            List<ParserVatanProduct> vatanProductList = ProductPriceParser.parseVatanPhoneList();
            assertTrue(vatanProductList != null && vatanProductList.size() > 100);
            System.out.println(vatanProductList.size());
            System.out.println(vatanProductList);
        } catch (Throwable t) {
            t.printStackTrace();
            fail(t.getMessage());
        }
    }


}