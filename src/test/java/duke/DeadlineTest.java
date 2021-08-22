package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testTaskToString(){
        assertEquals("[D][ ] placeholder (by: Jun 6 2022)",
                new Deadline("placeholder", "2022-06-06").toString());
    }

    @Test
    public void testEventMarkAsDone(){
        Task temp = new Deadline("placeholder", "2022-06-06");
        temp.markAsDone();
        assertEquals("[D][X] placeholder (by: Jun 6 2022)", temp.toString());
    }
}
