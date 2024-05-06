package sample.htmx.elements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rife.engine.Context;
import rife.engine.Element;
import rife.engine.annotations.Parameter;
import rife.engine.annotations.PathInfo;
import rife.engine.annotations.Property;
import sample.htmx.elements.processor.TemplateProcessor;
import sample.htmx.service.TodoService;

public class DeleteTodoElement implements Element {

    private static final Logger LOG = LoggerFactory.getLogger(DeleteTodoElement.class);

    @Property
    private TodoService todoService;

    @PathInfo
    private Integer id;

    @Override
    public void process(Context c) throws Exception {
        LOG.info("delete");
        todoService.delete(id);
        var todos = todoService.list(c.parameter("q",""));
        var template = c.template("todos/list");
        TemplateProcessor.populateList(template, todos);
        c.print(template);
    }
}
