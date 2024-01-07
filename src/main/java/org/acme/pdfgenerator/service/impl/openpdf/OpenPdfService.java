package org.acme.pdfgenerator.service.impl.openpdf;

import com.lowagie.text.pdf.PdfWriter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.acme.pdfgenerator.service.PdfService;
import org.acme.pdfgenerator.util.JsoupParser;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.pdf.PDFCreationListener;

import java.io.ByteArrayOutputStream;

@ApplicationScoped
@Named("openpdf")
public class OpenPdfService implements PdfService {

    @Override
    public byte[] createPdf(String htmlContent) {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            String html = JsoupParser.parse(htmlContent).html();
            renderer.setDocumentFromString(html);
            renderer.layout();

            renderer.setListener(new PDFCreationListener() {
                @Override
                public void preOpen(ITextRenderer iTextRenderer) {
                    renderer.getWriter().setPageEvent(new HeaderFooterPageEvent());
                }

                @Override
                public void preWrite(ITextRenderer iTextRenderer, int i) {

                }

                @Override
                public void onClose(ITextRenderer iTextRenderer) {

                }
            });

            renderer.createPDF(os);
            return os.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }
}
