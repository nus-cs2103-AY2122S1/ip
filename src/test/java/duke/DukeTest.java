package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the initialization of a simple Duke object.
 */
public class DukeTest {

    @Test
    public void Duke(){
        Duke duke = new Duke("test.txt");
        System.out.println(duke);
    }

}
