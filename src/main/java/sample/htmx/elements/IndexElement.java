package sample.htmx.elements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rife.engine.Context;
import rife.engine.Element;
import rife.engine.annotations.Parameter;
import rife.engine.annotations.Property;
import sample.htmx.elements.processor.TemplateProcessor;
import sample.htmx.service.TodoService;

public class IndexElement implements Element {

    private static final Logger LOG = LoggerFactory.getLogger(IndexElement.class);

    @Property
    private TodoService todoService;

    @Parameter
    private String q = "";

    @Override
    public void process(Context c) throws Exception {
        LOG.info("index");
        var todos = todoService.list(q);
        var template = c.template("index");
        TemplateProcessor.populateList(template, todos);
        c.print(template);
    }
}
