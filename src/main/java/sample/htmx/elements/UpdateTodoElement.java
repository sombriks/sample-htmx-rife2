package sample.htmx.elements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rife.engine.Context;
import rife.engine.Element;
import rife.engine.annotations.Parameter;
import rife.engine.annotations.ParametersBean;
import rife.engine.annotations.PathInfo;
import rife.engine.annotations.Property;
import sample.htmx.elements.processor.TemplateProcessor;
import sample.htmx.model.Todo;
import sample.htmx.service.TodoService;

public class UpdateTodoElement implements Element {

    private static final Logger LOG = LoggerFactory.getLogger(UpdateTodoElement.class);

    @Property
    private TodoService todoService;

    @Parameter
    private String q = "";

    @PathInfo
    private Long id;

    @ParametersBean
    private Todo todo;

    @Override
    public void process(Context c) throws Exception {
        LOG.info("update");
//        Todo todo = new Todo(
//                id,
//                c.parameter("description"),
//                c.parameterBoolean("done"),
//                null,
//                null
//        );
        todoService.update(id, todo);
        var todos = todoService.list(q);
        var template = c.template("todos/list");
        TemplateProcessor.populateList(template, todos);
        c.print(template);
    }
}
