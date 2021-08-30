package parser;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import processor.IProcessor;
import processor.ProcessorStub;

public class ParserTest {

    private static Parser parser;

    @BeforeEach
    public void setUpEnvironment() {
        IProcessor processor = new ProcessorStub();
        this.parser = new Parser(processor);
    }

    @Test
    public void processCommand_validInput_returnSuccessfully() {
        //Solution below adapted from https://stackoverflow.com/a/16066777
        try {
            String command = "todo something";
            System.setIn(new ByteArrayInputStream(command.getBytes()));

            Scanner scanner = new Scanner(System.in);
        } catch (Exception error) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    public void processCommand_invalidInput_returnSuccessfully() {
        try {
            String command = "deadline something";
            System.setIn(new ByteArrayInputStream(command.getBytes()));

            Scanner scanner = new Scanner(System.in);
        } catch (Exception error) {
            fail("Should not have thrown any exception");
        }
    }

}
