package sample.htmx;

import rife.database.Datasource;
import rife.engine.Server;
import sample.htmx.service.TodoService;

public class TodoappSiteUber extends TodoappSite {
    public TodoappSiteUber(TodoService todoService) {
        super(todoService);
    }

    public static void main(String[] args) {
        Datasource db = new Datasource("org.h2.Driver", "jdbc:h2:./todos", "sa", "", 5);
        new Server()
                .staticUberJarResourceBase("webapp")
                .start(new TodoappSiteUber(new TodoService(db)));
    }
}
