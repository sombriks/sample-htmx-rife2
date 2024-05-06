package sample.htmx.elements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rife.engine.Context;
import rife.engine.Element;
import rife.engine.annotations.Parameter;
import rife.engine.annotations.ParametersBean;
import rife.engine.annotations.Property;
import sample.htmx.elements.processor.TemplateProcessor;
import sample.htmx.model.Todo;
import sample.htmx.service.TodoService;

public class InsertTodoElement implements Element {

    private static final Logger LOG = LoggerFactory.getLogger(InsertTodoElement.class);

    @Property
    private TodoService todoService;

    @Parameter
    private String q = "";

    @ParametersBean
    private Todo todo;

    @Override
    public void process(Context c) throws Exception {
        LOG.info("insert");
        todoService.insert(todo);
        var todos = todoService.list(q);
        var template = c.template("todos/list");
        TemplateProcessor.populateList(template, todos);
        c.print(template);
    }
}
