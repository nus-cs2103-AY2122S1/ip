package functionalities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bern.exception.BernException;
import bern.functionalities.Ui;
import bern.model.Task;

/**
 * A class to encapsulates tests for UI functions.
 */
public class UiTest {
    /**
     * A test for ifBye method.
     */
    @Test
    public void ifByeTest() {
        try {
            String temp = new Ui().ifBye("hehe", new ArrayList<Task>());
            assertEquals("Bye. Hope to see you soon and hope you found my service useful!", temp);
        } catch (BernException e) {
            e.printStackTrace();
        }
    }
}
