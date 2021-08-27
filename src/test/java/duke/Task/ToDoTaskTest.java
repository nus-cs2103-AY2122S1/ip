package duke.Task;
import org.junit.Test;
import org.junit.Assert;


public class ToDoTaskTest {
    @Test
    public void writeToFileTest() {
        Assert.assertEquals("TODO | 0 | return a book\n", new ToDoTask("return a book").writeToFile());
    }


    @Test
    public void toStringTest() {
        Assert.assertEquals("[T][ ] return a book", new ToDoTask("return a book").toString());
    }
}