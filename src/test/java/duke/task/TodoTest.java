package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] todo 1", new Todo("todo 1").toString());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testJsonConversion() {
        JSONObject object = new JSONObject();
        object.put("type", "todo");
        object.put("isDone", false);
        object.put("description", "todo 1");
        assertEquals(object.toJSONString(), new Todo("todo 1").toJsonObject().toJSONString());
    }

    @Test
    public void testFactoryMethod1() {
        assertEquals(new Todo("todo 1").toString(), Todo.of("todo 1").toString());
    }

    @Test
    public void testFactoryMethod2() {
        assertEquals(new Todo("todo 1", true).toString(), Todo.of("todo 1", true).toString());
    }
}
