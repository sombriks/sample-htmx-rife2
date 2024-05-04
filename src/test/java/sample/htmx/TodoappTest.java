package sample.htmx;

import org.junit.jupiter.api.Test;
import rife.database.Datasource;
import rife.test.MockConversation;
import sample.htmx.service.TodoService;

import static org.junit.jupiter.api.Assertions.*;

public class TodoappTest {

    Datasource db = new Datasource("org.h2.Driver", "jdbc:h2:mem:test", "sa", "", 5);
    TodoService service = new TodoService(db);

    @Test
    void verifyRoot() {
        var m = new MockConversation(new TodoappSite(service));
        var result = m.doRequest("/");
        assertEquals(result.getStatus(), 200);
        assertTrue(result.getText().contains("TODO List"));
    }

}
