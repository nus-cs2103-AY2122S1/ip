package ui;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static pika.ui.Parser.parse;

import org.junit.jupiter.api.Test;

import pika.command.AddCommand;
import pika.exception.PikaException;

public class ParserTest {

    @Test
    public void test1() throws PikaException {
        assertTrue(parse("todo smile") instanceof AddCommand);
    }
}
