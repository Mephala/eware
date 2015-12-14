package com.gokhanabi.test.parsing.miner;

import com.mephalay.model.ParserVatanProduct;
import com.mephalay.parser.ProductPriceParser;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by GokhanOzgozen on 12/14/2015.
 */
@RunWith(JMockit.class)
public class TestDataMining {

    @Test
    public void testHashingProductNames() {
        List<ParserVatanProduct> vatanProductList = ProductPriceParser.parseForVatanOEMPrices();
        Map<String, ParserVatanProduct> nameToProductMap = new HashMap<>();
        for (ParserVatanProduct vatanProduct : vatanProductList) {
            nameToProductMap.put(vatanProduct.getName(), vatanProduct);
        }
        System.out.println("Map size:" + nameToProductMap.size());
        String searchText = "R9";
        for (String pName : nameToProductMap.keySet()) {
            if (pName.toLowerCase().contains(searchText.toLowerCase())) {
                System.out.println(nameToProductMap.get(pName));
            }
        }
    }
}
