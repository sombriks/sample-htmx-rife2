package sample.htmx.elements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rife.engine.Context;
import rife.engine.Element;
import sample.htmx.elements.processor.TemplateProcessor;
import sample.htmx.service.TodoService;

public class ListTodoElement implements Element {

    private static final Logger LOG = LoggerFactory.getLogger(ListTodoElement.class);
    private final TodoService todoService;

    public ListTodoElement(TodoService todoService) {
        this.todoService = todoService;
    }

    @Override
    public void process(Context c) throws Exception {
        LOG.info("list");
        var todos = todoService.list(c.parameter("q",""));
        var template = c.template("todos/list");
        TemplateProcessor.populateList(template, todos);
        c.print(template);
    }
}
