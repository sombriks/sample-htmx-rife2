package sample.htmx.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rife.database.Datasource;
import rife.database.DbQueryManager;
import rife.database.queries.*;
import rife.engine.exceptions.RespondException;
import sample.htmx.model.Todo;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TodoService {

    private static final Logger LOG = LoggerFactory.getLogger(TodoService.class);

    private final Datasource ds;
    private final DbQueryManager manager;

    public TodoService(Datasource ds) {
        this.ds = ds;
        manager = new DbQueryManager(ds);
    }

    public void initDB() {
        ds.getConnection().getPreparedStatement("""
                create table if not exists todos (
                    id integer primary key auto_increment,
                    description text not null,
                    done boolean default false,
                    created timestamp not null default now(),
                    updated timestamp not null default now()
                );
                """).executeUpdate();
        LOG.info("todos table created");
    }

    public List<Todo> list(String q) throws Exception {
        LOG.info("list q={}", q);
        var select = new Select(ds)
                .from("todos")
                .where("lower(description)", "like", String.format("%s%s%s", "%", q, "%"));
        var todos = new ArrayList<Todo>();
        manager.executeFetchAll(select, rp -> {
            todos.add(new Todo(
                    rp.getLong("id"),
                    rp.getString("description"),
                    rp.getBoolean("done"),
                    Instant.ofEpochMilli(rp.getDate("created").getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime(),
                    Instant.ofEpochMilli(rp.getDate("updated").getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime()
            ));
        });
        return todos;
    }

    public Todo find(Long id) throws Exception {
        LOG.info("list id={}", id);
        var select = new Select(ds).from("todos").where("id", "=", id);
        var todos = new ArrayList<Todo>();
        var result = manager.executeFetchFirst(select, rp -> todos.add(new Todo(
                rp.getLong("id"),
                rp.getString("description"),
                rp.getBoolean("done"),
                Instant.ofEpochMilli(rp.getDate("created").getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime(),
                Instant.ofEpochMilli(rp.getDate("updated").getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime()
        )));
        if (result) return todos.get(0);
        throw new Exception("not found");
    }

    public int insert(Todo todo) {
        LOG.info("insert todo={}", todo);
        var insert = new Insert(ds)
                .into("todos")
                .field("description", todo.description())
                .field("done", todo.done());
        return manager.executeUpdate(insert);
    }

    public int update(Long id, Todo todo) {
        LOG.info("update id={}, todo={}", id, todo);
        var update = new Update(ds)
                .table("todos")
                .field("description", todo.description())
                .field("done", todo.done())
                .field("updated", LocalDateTime.now())
                .where("id", "=", id);
        return manager.executeUpdate(update);
    }

    public int delete(Long id) {
        LOG.info("delete id={}", id);
        var delete = new Delete(ds)
                .from("todos")
                .where("id", "=", id);
        return manager.executeUpdate(delete);
    }
}
