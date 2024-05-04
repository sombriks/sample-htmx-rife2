package sample.htmx.elements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rife.engine.Context;
import rife.engine.Element;
import sample.htmx.service.TodoService;

public class InsertTodoElement implements Element {

    private static final Logger LOG = LoggerFactory.getLogger(InsertTodoElement.class);
    private final TodoService todoService;

    public InsertTodoElement(TodoService todoService) {
        this.todoService = todoService;
    }

    @Override
    public void process(Context c) throws Exception {
        LOG.info("insert");
        var template = c.template("todos/list");
        c.print(template);
    }
}
