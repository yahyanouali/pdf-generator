package org.acme.pdfgenerator.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.spi.CDI;
import org.acme.pdfgenerator.service.impl.pdfbox.PdfBoxService;
import org.acme.pdfgenerator.service.impl.openpdf.OpenPdfService;

@ApplicationScoped
public class PdfServiceFactory {
    public PdfService getPdfService(String converterType) {
        if ("pdfbox".equals(converterType)) {
            return CDI.current().select(PdfBoxService.class).get();
        } else if ("openpdf".equals(converterType)) {
            return CDI.current().select(OpenPdfService.class).get();
        } else {
            throw new IllegalArgumentException("Unknown PDF converter type: " + converterType);
        }
    }
}

