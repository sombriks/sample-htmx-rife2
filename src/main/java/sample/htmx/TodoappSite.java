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
        LOG.info("open to business http://localhost:8080");
        properties().put("todoService", todoService);
        this.todoService = todoService;
        todoService.initDB();
    }

    public void setup() {
        get("/", IndexElement.class);
        group("/todos", new Router() {
            @Override
            public void setup() {
                get("", ListTodoElement.class);
                post("", InsertTodoElement.class);
                get("", PathInfoHandling.CAPTURE, FindTodoElement.class);
                put("", PathInfoHandling.CAPTURE, UpdateTodoElement.class);
                delete("", PathInfoHandling.CAPTURE, DeleteTodoElement.class);
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
