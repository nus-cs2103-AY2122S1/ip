import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import taubot.Parser;

class ParserTest {

    @Test
    void getFirstCommand() {
        Parser p = new Parser();
        p.interpretCommand("event work meeting for new proejct /at 2000-10-10 1000");
        assertEquals("event", p.getFirstCommand());
    }

    @Test
    void findCommandIndex() {
        Parser p = new Parser();
        p.interpretCommand("delete 5");
        assertEquals(5, p.findCommandIndex());
    }

    @Test
    void findDateInCommand() {
        Parser p = new Parser();
        p.interpretCommand("event work meeting for new proejct /at 2000-10-10 1000");
        assertEquals("2000-10-10 1000", p.findDateInCommand());
    }

    @Test
    void findTaskDescription() {
        Parser p = new Parser();
        p.interpretCommand("event work meeting for new project /at 2000-10-10 1000");
        assertEquals("work meeting for new project ", p.findTaskDescription());
    }
}
