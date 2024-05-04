package sample.htmx.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rife.database.Datasource;
import sample.htmx.model.Todo;

import java.time.LocalDateTime;
import java.util.List;

public class TodoService {

    private static final Logger LOG = LoggerFactory.getLogger(TodoService.class);

    private final Datasource ds;

    public TodoService(Datasource ds) {
        this.ds = ds;
    }

    public List<Todo> list(String q) {
        LOG.info("list q={}", q);
        return List.of(new Todo(1L, "teste", false, LocalDateTime.now(), LocalDateTime.now()));
    }
}
