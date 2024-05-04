package sample.htmx.elements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rife.engine.Context;
import rife.engine.Element;
import sample.htmx.elements.processor.TemplateProcessor;
import sample.htmx.model.Todo;
import sample.htmx.service.TodoService;

public class UpdateTodoElement implements Element {

    private static final Logger LOG = LoggerFactory.getLogger(UpdateTodoElement.class);
    private final TodoService todoService;

    public UpdateTodoElement(TodoService todoService) {
        this.todoService = todoService;
    }

    @Override
    public void process(Context c) throws Exception {
        LOG.info("update");
        Long id = Long.parseLong(c.pathInfo());
        Todo todo = new Todo(
                id,
                c.parameter("description"),
                c.parameterBoolean("done"),
                null,
                null
        );
        todoService.update(id, todo);
        var todos = todoService.list(c.parameter("q", ""));
        var template = c.template("todos/list");
        TemplateProcessor.populateList(template, todos);
        c.print(template);
    }
}
