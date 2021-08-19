package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OperationTest {

    @Test
    void getValue() {
        assertEquals("todo", Operation.TODO.getValue());
    }

    @Test
    void values() {
        Operation[] operations = new Operation[]{
            Operation.TODO,
            Operation.DEADLINE,
            Operation.EVENT,
            Operation.LIST,
            Operation.DONE,
            Operation.DELETE,
            Operation.CLEAR,
            Operation.BYE
        };
        assertArrayEquals(operations, Operation.values());
    }

    @Test
    void valueOf() {
        assertEquals(Operation.valueOf("TODO"), Operation.TODO);
    }
}
