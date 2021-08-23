package duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {
    Command command;
    CommandKeyword keyword;

    @BeforeEach
    void setUp() {
        this.keyword = CommandKeyword.DONE;
        this.command = new Command(keyword, "3");
    }
    @Test
    void getKeyword() {
        assertEquals(this.keyword, this.command.getKeyword());
    }

    @Test
    void getRestOfCommand() {
        assertEquals("3", this.command.getRestOfCommand());
    }
}