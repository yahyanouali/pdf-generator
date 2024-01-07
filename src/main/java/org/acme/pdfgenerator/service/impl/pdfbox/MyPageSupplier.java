package org.acme.pdfgenerator.service.impl.pdfbox;

import com.openhtmltopdf.pdfboxout.PageSupplier;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.Color;
import java.io.IOException;

public class MyPageSupplier implements PageSupplier {
    @Override
    public PDPage requestPage(PDDocument doc, float pageWidth, float pageHeight, int pageNumber, int shadowPageNumber) {
        PDPage page = new PDPage();
        doc.addPage(page);

        try (PDPageContentStream contentStream = new PDPageContentStream(doc, page)) {
            // Draw header background
            contentStream.setNonStrokingColor(Color.LIGHT_GRAY); // Header background color
            contentStream.fillRect(50, pageHeight - 110, pageWidth - 100, 50); // Adjust size as needed

            // Add header text
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
            contentStream.setNonStrokingColor(Color.WHITE);
            contentStream.newLineAtOffset(60, pageHeight - 90);
            contentStream.showText("This is the header for page " + (pageNumber + 1));
            contentStream.endText();

            // Draw footer background
            contentStream.setNonStrokingColor(Color.LIGHT_GRAY);
            contentStream.fillRect(50, 30, pageWidth - 100, 50);
            // Add footer text
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
            contentStream.setNonStrokingColor(Color.WHITE);
            contentStream.newLineAtOffset(60, 50);
            contentStream.showText("This is the footer for page " + (pageNumber + 1));
            contentStream.endText();

        } catch (Exception e) {
            throw new RuntimeException("Error adding header/footer", e);
        }

        return page;
    }
}
