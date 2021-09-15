import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Parser;

public class ParserTest {
    @Test
    void get_command_word() {
        String input = "done 3";
        Parser parser = new Parser(input);
        assertEquals(parser.getCommandWord(), "done");
    }

    @Test
    void get_done_number() throws DukeException {
        String input = "done 3";
        Parser parser = new Parser(input);
        assertEquals(parser.getNumber(), 2);
    }

    @Test
    void get_task_date() throws DukeException {
        String input = "deadline return book /by 10/3/2020 1400";

        Parser parser = new Parser(input);
        parser.getTaskName();
        assertEquals(parser.getTaskDate(), LocalDateTime.of(2020, 3, 10, 14, 0));
    }
}
