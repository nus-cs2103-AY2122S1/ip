import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dummyTest(){
        Duke testSubject = new Duke("invalid path");
        assertEquals(2, 2);
    }

}