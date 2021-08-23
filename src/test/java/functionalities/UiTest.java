package functionalities;

import bern.exception.BernException;
import bern.functionalities.Ui;
import bern.model.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
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
