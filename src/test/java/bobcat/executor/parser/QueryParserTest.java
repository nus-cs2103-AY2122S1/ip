package bobcat.executor.parser;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import bobcat.exception.CommandArityException;
import bobcat.exception.UnknownCommandException;


public class QueryParserTest {
    private final bobcat.executor.parser.QueryParser parser = new QueryParser();

    @Test
    public void todoCommandParser() {
        assertArrayEquals(parser.parse("todo eat breakfast"), new String[]{"todo", "eat breakfast"});
        assertThrows(CommandArityException.class, () -> parser.parse("todo"));
    }

    @Test
    public void deadlineCommandParser() {
        assertAll("CommandArityException thrown", () -> {
            assertArrayEquals(parser.parse("deadline eat breakfast /by 2021-10-01"),
                    new String[]{"deadline", "eat breakfast", "2021-10-01"});
            assertThrows(CommandArityException.class, () -> parser.parse("deadline eat breakfast /at 2021-10-01"));
            assertThrows(CommandArityException.class, () -> parser.parse("deadline eat breakfast"));
            assertThrows(CommandArityException.class, () -> parser.parse("deadline"));
        });
    }

    @Test
    public void eventCommandParser() {
        assertArrayEquals(parser.parse("event eat breakfast /at 2021-10-01"),
                new String[]{"event", "eat breakfast", "2021-10-01"});
        assertThrows(CommandArityException.class, () -> parser.parse("event eat breakfast /by 2021-10-01"));
        assertThrows(CommandArityException.class, () -> parser.parse("event eat breakfast"));
        assertThrows(CommandArityException.class, () -> parser.parse("event"));
    }

    @Test
    public void unknownCommandParser() {
        assertThrows(UnknownCommandException.class, () -> parser.parse("evet eat breakfast /by 2021-10-01"));
        assertThrows(UnknownCommandException.class, () -> parser.parse("even eat breakfast"));
        assertThrows(UnknownCommandException.class, () -> parser.parse("deadlined"));
        assertThrows(UnknownCommandException.class, () -> parser.parse("tod"));
    }
}
