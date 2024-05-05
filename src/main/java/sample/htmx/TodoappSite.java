package sample.htmx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rife.engine.PathInfoHandling;
import rife.engine.Router;
import rife.engine.Server;
import rife.engine.Site;
import sample.htmx.elements.*;
import sample.htmx.service.TodoService;

public class TodoappSite extends Site {

    private static final Logger LOG = LoggerFactory.getLogger(TodoappSite.class);

    private final TodoService todoService;

    public TodoappSite(TodoService todoService) {
        this.todoService = todoService;
        todoService.initDB();
        LOG.info("open to business http://localhost:8080");
    }

    public void setup() {
        get("/", new IndexElement(todoService));
        group("/todos", new Router() {
            @Override
            public void setup() {
                get("", () -> new ListTodoElement(todoService));
                post("", () -> new InsertTodoElement(todoService));
                get("", PathInfoHandling.CAPTURE, () -> new FindTodoElement(todoService));
                put("", PathInfoHandling.CAPTURE, () -> new UpdateTodoElement(todoService));
                delete("", PathInfoHandling.CAPTURE, () -> new DeleteTodoElement(todoService));
            }
        });
        LOG.info("Setting up site");
    }

    public static void main(String[] args) {
        new Server()
                .staticResourceBase("src/main/webapp")
                .start(new TodoappSite(new TodoService()))
        ;
    }
}
