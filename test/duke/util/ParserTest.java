package duke.util;

import duke.command.CommandAdd;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    @Test
    void parseCommand_Todo_CommandAdd() throws DukeException {
        assertEquals(new CommandAdd(new Task("Test CommandAdd Todo")),
                Parser.parseCommand("todo Test CommandAdd Todo"));
    }

    @Test
    void parseCommand_Deadline_CommandAdd() throws DukeException {
        assertEquals(
                new CommandAdd(new Deadline("Test CommandAdd Deadline",
                LocalDateTime
                        .parse("2022/01/01 18:00", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")))),
                Parser.parseCommand("deadline Test CommandAdd Deadline /by 2022/01/01 18:00"));
    }

    @Test
    void parseCommand_Event_CommandAdd() throws DukeException {
        assertEquals(
                new CommandAdd(new Event("Test CommandAdd Event",
                        LocalDateTime
                                .parse("2022/01/01 18:00", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")),
                        LocalDateTime
                                .parse("2022/01/01 19:00", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")))),
                Parser.parseCommand("event Test CommandAdd Event /at 2022/01/01 18:00 19:00"));
    }

    @Test
    void parseIndex() throws DukeException {
        assertEquals(1, Parser.parseIndex(new String[] {"done", "1"}));
    }

    @Test
    void parseDescription() throws DukeException {
        assertEquals("description part 1 description part 2", Parser.parseDescription(new String[] {"taskType","description part 1", "description part 2"}));
    }

    @Test
    void encode() {
        assertEquals("T true todo 1\n",
                Parser.encode(List.of(new Task("todo 1", true))));
    }

    @Test
    void decode() {
        assertEquals(List.of(new Task("todo 1", true)),
                Parser.decode(new String[] {"T true todo 1"}));
    }
}