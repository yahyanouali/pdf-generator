package org.acme.pdfgenerator.service.impl.openpdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

public class HeaderFooterPageEvent extends PdfPageEventHelper {

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        Rectangle pageSize = document.getPageSize();

        // Header
        ColumnText.showTextAligned(writer.getDirectContent(),
                Element.ALIGN_CENTER,
                new Phrase("This is header", FontFactory.getFont(FontFactory.HELVETICA, 12)),
                pageSize.getWidth() / 2,
                pageSize.getTop() - 20, // Adjust the value as needed
                0);

        // Footer
        ColumnText.showTextAligned(writer.getDirectContent(),
                Element.ALIGN_CENTER,
                new Phrase("Page " + document.getPageNumber(), FontFactory.getFont(FontFactory.HELVETICA, 10)),
                pageSize.getWidth() / 2,
                pageSize.getBottom() + 20, // Adjust the value as needed
                0);
    }
}
