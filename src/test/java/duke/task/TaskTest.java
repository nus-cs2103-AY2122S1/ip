package duke.task;
import org.junit.Test;
import org.junit.Assert;


public class TaskTest {
    @Test
    public void getDescriptionTest() {
        Assert.assertEquals("return a book", new Task("return a book").getDescription());
    }

    @Test
    public void getIsCompletedTest() {
        Assert.assertEquals("0", new Task("return a book").getIsCompleted());
    }

    @Test
    public void getStatusIconTest() {
        Assert.assertEquals(" ", new Task("return a book").getStatusIcon());
    }

    @Test
    public void writeToFileTest() {
        Assert.assertEquals("TASK | 0 | return a book\n", new Task("return a book").writeToFile());
    }

    @Test
    public void toStringTest() {
        Assert.assertEquals("[ ] return a book", new Task("return a book").toString());
    }
}