package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class InstructionTest {

    @Test
    void testValueOfLabel() {
        assertEquals(Instruction.DEADLINE, Instruction.getValueOfLabel("deadline"));
    }
}
