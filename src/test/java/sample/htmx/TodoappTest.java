package sample.htmx;

import org.junit.jupiter.api.Test;
import rife.test.MockConversation;

import static org.junit.jupiter.api.Assertions.*;

public class TodoappTest {
    @Test
    void verifyRoot() {
        var m = new MockConversation(new TodoappSite());
        var result = m.doRequest("/");
        assertEquals(result.getStatus(), 200);
//        assertEquals(result.getTemplate().getValue("title"), 200);
    }

}
