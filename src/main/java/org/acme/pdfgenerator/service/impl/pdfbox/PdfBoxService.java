package org.acme.pdfgenerator.service.impl.pdfbox;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.acme.pdfgenerator.service.PdfService;
import org.acme.pdfgenerator.util.JsoupParser;

import java.io.ByteArrayOutputStream;

@ApplicationScoped
@Named("pdfbox")
public class PdfBoxService implements PdfService {

    @Override
    public byte[] createPdf(String htmlContent) {

        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            String html = JsoupParser.parse(htmlContent).html();


            builder.usePageSupplier(new MyPageSupplier());


            builder.withHtmlContent(html, null);
            builder.toStream(os);
            builder.run();

            return os.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }

}
