package duke.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InstructionTest {

    @Test
    void testValueOfLabel() {
        assertEquals(Instruction.DEADLINE, Instruction.valueOfLabel("deadline"));
    }
}