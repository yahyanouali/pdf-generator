package org.acme.pdfgenerator.api;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.pdfgenerator.html.QuteRendererService;
import org.acme.pdfgenerator.service.PdfService;
import org.acme.pdfgenerator.service.PdfServiceFactory;

import java.io.IOException;
import java.util.Map;

@Path("/pdf")
public class PdfResource {

    private final PdfServiceFactory pdfServiceFactory;

    private final QuteRendererService quteRendererService;

    public PdfResource(PdfServiceFactory pdfServiceFactory, QuteRendererService quteRendererService) {
        this.pdfServiceFactory = pdfServiceFactory;
        this.quteRendererService = quteRendererService;
    }

    @POST
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response createPdf(@QueryParam("template") String template,
                              @QueryParam("converter") String converter,
                              Map<String, Object> data) throws IOException {

        String htmlContent = quteRendererService.render(template, data);

        PdfService pdfService = pdfServiceFactory.getPdfService(converter);
        byte[] pdf = pdfService.createPdf(htmlContent);

        return Response.ok(pdf)
                .header("Content-Disposition", "attachment; filename=\"output.pdf\"")
                .type("application/pdf")
                .build();
    }
}