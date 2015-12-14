package com.mephalay.parser;

import com.mephalay.model.ParserVatanProduct;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GokhanOzgozen on 12/14/2015.
 */
public class ProductPriceParser {
    public static List<ParserVatanProduct> parseForVatanOEMPrices() {
        List<ParserVatanProduct> vatanProductList = new ArrayList<>();
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet getMethod = new HttpGet("http://www.vatanbilgisayar.com//bilgisayar-bilesenleri/?ps=14560");
            HttpResponse response = httpClient.execute(getMethod);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder result = new StringBuilder();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            String vatanProductsHtml = result.toString();

            int productListIndex = vatanProductsHtml.indexOf("<div class=\"productListHolder\"><div class=\"searchResult\">");
            vatanProductsHtml = vatanProductsHtml.substring(productListIndex);
            int productClearfixIndex = vatanProductsHtml.indexOf("<div class=\"product clearfix\">");
            vatanProductsHtml = vatanProductsHtml.substring(productClearfixIndex);
            final String productNameSpan = "<span class=\"prdName\">";
            final int prodcutNameSpanLength = productNameSpan.length();
            int productNameContainerIndex = vatanProductsHtml.indexOf(productNameSpan);
            while (productNameContainerIndex != -1) {
                vatanProductsHtml = vatanProductsHtml.substring(productNameContainerIndex + prodcutNameSpanLength);
                int aTagCloseIndex = vatanProductsHtml.indexOf("\">");
                vatanProductsHtml = vatanProductsHtml.substring(aTagCloseIndex + "\">".length());
                int pNameEndIndex = vatanProductsHtml.indexOf("</a>");
                String productName = vatanProductsHtml.substring(0, pNameEndIndex);
                vatanProductsHtml = vatanProductsHtml.substring(pNameEndIndex + "</a>".length());
                String priceEndString = "class=\"urunListe_satisFiyat\">";
                int priceEndLength = priceEndString.length();
                int priceStartIndex = vatanProductsHtml.indexOf(priceEndString);
                vatanProductsHtml = vatanProductsHtml.substring(priceStartIndex + priceEndLength);
                int priceEndIndex = vatanProductsHtml.indexOf("<span");
                String priceString = vatanProductsHtml.substring(0, priceEndIndex);
                BigDecimal price = new BigDecimal(priceString);
                ParserVatanProduct pvp = new ParserVatanProduct();
                pvp.setName(productName);
                pvp.setPrice(price);
                vatanProductList.add(pvp);
                productNameContainerIndex = vatanProductsHtml.indexOf(productNameSpan);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return vatanProductList;
    }
}
