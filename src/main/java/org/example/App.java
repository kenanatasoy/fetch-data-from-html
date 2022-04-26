package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.InetSocketAddress;
import java.net.Proxy;

public class App {

    public static void main(String[] args){

        try {

            String urlStr = "https://www.hepsiburada.com/vestfrost-vf-cf-6001-x-600-lt-no-frost-buzdolabi-p-hbv00000twl4n";

            String fileName = "C:\\Intellij-WorkSpaces\\fetch-data-from-html\\src\\main\\resources\\product.txt";

            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

            Proxy proxy = new Proxy(Proxy.Type.HTTP,
                    new InetSocketAddress("5.39.189.39",3128));

            Document doc = Jsoup.connect(urlStr)
                    .header("Accept","*/*")
                    .proxy(proxy)
                    .get();

            Elements sections1 = doc.select(".product-name");
            Element productTitle = sections1.first();

            Elements sections2 = doc.select("a[itemprop=\"item\"] span");
            Element productCategory = sections2.get(2);

            Elements productDescription = doc.select("div[id=\"tabProductDesc\"]");

            writer.append("Ürün: ").append(productTitle.text()).append("\n\n");
            writer.append("Kategori: ").append(productCategory.text()).append("\n\n");
            writer.append("Açıklama: ").append(productDescription.text()).append("\n\n\n");
            writer.close();

        }

        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }


}