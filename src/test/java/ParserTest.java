import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    void getIndex() throws BlitzException {
        assertEquals(9, Parser.getIndex("done 10", 11));
    }
}
