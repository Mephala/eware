package com.gokhanabi.test.parsing.passmark;

import com.mephalay.model.GpuBenchmark;
import com.mephalay.parser.BenchmarkParser;
import mockit.integration.junit4.JMockit;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by GokhanOzgozen on 12/14/2015.
 */
@RunWith(JMockit.class)
public class TestPassMarkParsing {

    @Ignore
    public void testParsingPasssMarkGraph() {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet getMethod = new HttpGet("http://www.videocardbenchmark.net/high_end_gpus.html");
            HttpResponse response = httpClient.execute(getMethod);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder result = new StringBuilder();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            String passmarkHtml = result.toString();
            int tableStartIndex = passmarkHtml.indexOf("<table class=\"chart\" cellspacing=\"0\" cellpadding=\"0\" summary=\"PassMark - G3D Mark\">");
            if (tableStartIndex == -1)
                fail("tableStartIndex is -1!!!!");
            passmarkHtml = passmarkHtml.substring(tableStartIndex);
            int firstTableEndIndex = passmarkHtml.indexOf("</tr>") + "</tr>".length();
            while (firstTableEndIndex != -1) {
                passmarkHtml = passmarkHtml.substring(firstTableEndIndex);
                int productNameStartIndex = passmarkHtml.indexOf("id=\">") + "id=\">".length();
                passmarkHtml = passmarkHtml.substring(productNameStartIndex);
                int tagStopIndex = passmarkHtml.indexOf("</a>");
                String productName = passmarkHtml.substring(0, tagStopIndex);
                productName = productName.trim();
                passmarkHtml = passmarkHtml.substring(tagStopIndex + "</a>".length());
                int spanStopIndex = passmarkHtml.indexOf("</span>") + "</span>".length();
                passmarkHtml = passmarkHtml.substring(spanStopIndex);
                int divStopindex = passmarkHtml.indexOf("</div>");
                String score = passmarkHtml.substring(0, divStopindex);
                score = score.trim();
                int priceStartIndex = passmarkHtml.indexOf("&amp;id=#price\">") + "&amp;id=#price\">".length();
                passmarkHtml = passmarkHtml.substring(priceStartIndex);
                int priceEndIndex = passmarkHtml.indexOf("</a>");
                String price = passmarkHtml.substring(0, priceEndIndex);
                price = price.trim();
                firstTableEndIndex = passmarkHtml.indexOf("<tr>");
                int tbodyIndex = passmarkHtml.indexOf("<td class=\"chart\">PassMark Software &copy; 2008-2015</td>");
                System.out.println(productName + ", " + score + ", " + price);
                if (firstTableEndIndex + 10 > tbodyIndex) {
                    break;
                }
            }
            System.out.println("End of Test");
        } catch (Throwable t) {
            t.printStackTrace();
            fail(t.getMessage());
        }

    }


    @Test
    public void testBenchmarkingViewStaticMethod() {
        List<GpuBenchmark> benchmarkList = BenchmarkParser.parseForPassmarkHighEndCpus();
        assertTrue(benchmarkList != null && benchmarkList.size() > 1);
        System.out.println(benchmarkList);
    }

    @Test
    public void testMidEndBenchmarking() {
        List<GpuBenchmark> benchmarkList = BenchmarkParser.parseForPassmarkMidEndGpus();
        assertTrue(benchmarkList != null && benchmarkList.size() > 1);
        System.out.println(benchmarkList);
    }

    @Test
    public void testLowMidRangeBenchmarking() {
        List<GpuBenchmark> benchmarkList = BenchmarkParser.parseForPassmarkLowMidRange();
        assertTrue(benchmarkList != null && benchmarkList.size() > 1);
        System.out.println(benchmarkList);
    }


}
