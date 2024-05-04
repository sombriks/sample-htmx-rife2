package sample.htmx;

import rife.engine.*;

public class TodoappSite extends Site {

    public void setup() {
        var index = get("/", c -> c.print(c.template("index")));

        var todos = get("/todos", c -> c.print(c.template("todos/list")));
        var newTodo = post("/todos", c -> c.print(c.template("todos/list")));

        var todo = get("/todos", c -> c.print(c.template("todos/detail")));
        var updateTodo = put("/todos", c -> c.print(c.template("todos/list")));
        var delTodo = delete("/todos", c -> c.print(c.template("todos/list")));
    }

    public static void main(String[] args) {
        new Server()
            .staticResourceBase("src/main/webapp")
            .start(new TodoappSite());
    }
}
