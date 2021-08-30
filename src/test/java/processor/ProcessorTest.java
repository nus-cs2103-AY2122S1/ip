package processor;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.Command;
import storage.IStorage;
import storage.StorageStub;

public class ProcessorTest {

    private static Processor processor;

    @BeforeEach
    public void setUpEnvironment() {
        IStorage storage = new StorageStub();
        processor = new Processor(storage);
    }

    @Test
    public void processCommand_validInput_returnSuccessfully() {
        try {
            processor.processCommand(Command.DEFAULT, new ArrayList<>(Arrays.asList("todo test".split(" "))));
        } catch (Exception error) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    public void processDone_validInput_returnSuccessfully() {
        try {
            processor.processDone("1");
        } catch (Exception error) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    public void processDelete_validInput_returnSuccessfully() {
        try {
            processor.processDelete("1");
        } catch (Exception error) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    public void processBye_validInput_returnSuccessfully() {
        try {
            processor.processBye();
        } catch (Exception error) {
            fail("Should not have thrown any exception");
        }
    }
}
