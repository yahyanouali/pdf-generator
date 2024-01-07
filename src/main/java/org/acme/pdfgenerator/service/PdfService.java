package org.acme.pdfgenerator.service;

import java.io.IOException;

public interface PdfService {
    byte[] createPdf(String htmlContent);
}
