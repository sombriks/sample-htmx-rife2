package sample.htmx.elements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rife.engine.Context;
import rife.engine.Element;
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
        var template = c.template("index");
        todoService.list(c.parameter("q","")).forEach(x -> {
            template.appendBlock("todos", "todo");
            template.setValue("id", x.id());
            template.setValue("description", x.description());
            template.setValue("ifTrue", x.done() ? "selected" : "");
            template.setValue("ifFalse", !x.done() ? "selected" : "");
        });

        c.print(template);
    }
}
