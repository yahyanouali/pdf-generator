package org.acme.pdfgenerator.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class JsoupParser {

    public static Document parse(String htmlContent) {
        Document document = Jsoup.parse(htmlContent, "UTF-8");
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        return document;
    }

}
