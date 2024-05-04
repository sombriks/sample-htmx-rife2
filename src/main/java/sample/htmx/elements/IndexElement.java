package sample.htmx.elements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rife.engine.Context;
import rife.engine.Element;
import sample.htmx.elements.processor.TemplateProcessor;
import sample.htmx.service.TodoService;

public class IndexElement implements Element {

    private static final Logger LOG = LoggerFactory.getLogger(IndexElement.class);
    private final TodoService todoService;

    public IndexElement(TodoService todoService) {
        this.todoService = todoService;
    }

    @Override
    public void process(Context c) throws Exception {
        LOG.info("index");
        var todos = todoService.list(c.parameter("q",""));
        var template = c.template("index");
        TemplateProcessor.populateList(template, todos);
        c.print(template);
    }
}
