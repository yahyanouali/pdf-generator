package org.acme.pdfgenerator.html;

import io.quarkus.qute.Engine;
import io.quarkus.qute.Template;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Map;

@ApplicationScoped
public class QuteRendererService {

    private final Engine quteEngine;

    public QuteRendererService(Engine quteEngine) {
        this.quteEngine = quteEngine;
    }

    public String render(String templateName, Map<String, Object> data) {
        Template template = quteEngine.getTemplate(templateName);
        if (template == null) {
            throw new IllegalArgumentException("Template not found: " + templateName);
        }
        return template.data(data).render();
    }

}
