package sample.htmx.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rife.database.Datasource;
import rife.database.DbQueryManager;
import rife.database.queries.Insert;
import rife.database.queries.Select;
import rife.database.queries.Update;
import rife.database.querymanagers.generic.GenericQueryManager;
import rife.database.querymanagers.generic.GenericQueryManagerFactory;
import rife.database.querymanagers.generic.RestoreQuery;
import sample.htmx.model.Todos;

import java.time.LocalDateTime;
import java.util.List;

public class TodoService {

    private static final Logger LOG = LoggerFactory.getLogger(TodoService.class);

    private final Datasource ds;
    private final GenericQueryManager<Todos> queryManager;
    private final DbQueryManager manager;

    public TodoService(Datasource ds) {
        queryManager = GenericQueryManagerFactory.instance(ds, Todos.class);
        manager = new DbQueryManager(ds);
        this.ds = ds;
    }

    public TodoService() {
        this(new Datasource("org.h2.Driver", "jdbc:h2:./todos", "sa", "", 5));
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

    public List<Todos> list(String q) throws Exception {
        LOG.info("list q={}", q);
        var select = new Select(ds)
                .from("todos")
                .where("lower(description)", "like", //
                        String.format("%s%s%s", "%", q, "%")); // FIXME safe?
        return queryManager.restore(new RestoreQuery(select));
    }

    public Todos find(Integer id) throws Exception {
        LOG.info("list id={}", id);
        var select = new Select(ds)
                .from("todos")
                .where("id", "=", id);
        var result = queryManager.restore(new RestoreQuery(select));
        if(result.isEmpty())
            throw new Exception("not found");
        return result.get(0);
    }

    public int insert(Todos todos) {
        LOG.info("insert todo={}", todos);
        var insert = new Insert(ds)
                .into("todos")
                .field("description", todos.getDescription())
                .field("done", todos.getDone());
        return manager.executeUpdate(insert);
    }

    public int update(Integer id, Todos todos) {
        LOG.info("update id={}, todo={}", id, todos);
        var update = new Update(ds)
                .table("todos")
                .field("description", todos.getDescription())
                .field("done", todos.getDone())
                .field("updated", LocalDateTime.now())
                .where("id", "=", id);
        return manager.executeUpdate(update);
//        return queryManager.update(todos);
    }

    public boolean delete(Integer id) {
        LOG.info("delete id={}", id);
//        var delete = new Delete(ds)
//                .from("todos")
//                .where("id", "=", id);
//        return manager.executeUpdate(delete);
        return queryManager.delete(id);
    }
}
