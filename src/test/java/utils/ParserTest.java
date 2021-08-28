package utils;

import commands.ExitCommand;
import exceptions.InvalidInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    @Test
    public void parseTest_exit_exitCommandReturned(){
        try {
            assertTrue(parser.parse("bye") instanceof ExitCommand);
        } catch (InvalidInputException e) {
            Assertions.fail();
        }

    }

    @Test
    public void parseTest_invalidInput_invalidInputExceptionThrown(){
        Assertions.assertThrows(InvalidInputException.class, () -> parser.parse("hehexd"));
    }
    
}
