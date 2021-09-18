import duke.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TodoTest {
    @Test
    public void testStringOutput(){
        assertEquals("[T][ ] Finish iP Tasks", new Todo("Finish iP Tasks").toString());
    }

}
