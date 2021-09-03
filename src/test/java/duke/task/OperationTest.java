package duke.task;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class OperationTest {

    @Test
    void getValue_getTodoOperationValue_gotCorrectly() {
        assertEquals("todo", Operation.TODO.getValue());
    }

    @Test
    void values_getAllOperations_gotCorrectly() {
        Operation[] operations = new Operation[]{
            Operation.TODO,
            Operation.DEADLINE,
            Operation.EVENT,
            Operation.LIST,
            Operation.DONE,
            Operation.DELETE,
            Operation.CLEAR,
            Operation.FIND,
            Operation.COMING,
            Operation.BYE
        };
        assertArrayEquals(operations, Operation.values());
    }

    @Test
    void valueOf_getTodoOperation_gotCorrectly() {
        assertEquals(Operation.valueOf("TODO"), Operation.TODO);
    }
}
