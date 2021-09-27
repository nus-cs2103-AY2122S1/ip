package sora.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TaskNotFoundExceptionTest {
    @Test
    public void constructor_success() {
        assertEquals("There is no such tasks with this task number!",
                new TaskNotFoundException().getMessage());
    }
}
