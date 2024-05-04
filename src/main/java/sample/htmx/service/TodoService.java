package sample.htmx.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rife.database.Datasource;
import rife.database.DbQueryManager;
import rife.database.queries.CreateTable;
import rife.database.queries.Select;
import sample.htmx.model.Todo;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

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
        var result = manager.executeQuery(select).getResultSet();
        var todos = new ArrayList<Todo>();
        while (result.next()) {
            todos.add(new Todo(
                    result.getLong("id"),
                    result.getString("description"),
                    result.getBoolean("done"),
                    Instant.ofEpochMilli(result.getDate("created").getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime(),
                    Instant.ofEpochMilli(result.getDate("updated").getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime()
            ));
        }
        return todos;
    }
}
