package sample.htmx.elements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rife.engine.Context;
import rife.engine.Element;
import rife.engine.annotations.PathInfo;
import rife.engine.annotations.Property;
import sample.htmx.model.Todo;
import sample.htmx.service.TodoService;

public class FindTodoElement implements Element {

    private static final Logger LOG = LoggerFactory.getLogger(FindTodoElement.class);

    @Property
    private TodoService todoService;

    @PathInfo
    private Long id;

    @Override
    public void process(Context c) throws Exception {
        LOG.info("find");
        Todo todo = todoService.find(id);
        var template = c.template("todos/detail");
        template.setValue("todo", todo.getDescription());
        c.print(template);
    }
}
