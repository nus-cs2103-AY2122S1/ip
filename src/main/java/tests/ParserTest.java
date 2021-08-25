import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void getIndex() throws BlitzException {
        assertEquals(9, Parser.getIndex("done 10", 11));
    }
}