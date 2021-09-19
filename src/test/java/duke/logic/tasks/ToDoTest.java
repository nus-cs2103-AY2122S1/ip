package duke.logic.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.logic.commands.UpdateCommand.UpdateTaskDescriptor;
import duke.logic.commands.UpdateTaskDescriptorBuilder;

public class ToDoTest {
    private ToDo t = new ToDo("JUnit test iP", true);

    @Test
    public void testCreateUpdatedCopy_emptyDescriptor_equalTaskReturned() {
        Task updatedTask = t.createUpdatedCopy(new UpdateTaskDescriptorBuilder().build());
        assertEquals(t, updatedTask);
    }

    @Test
    public void testCreateUpdatedCopy_updateDescription_taskWithUpdatedDescriptionReturned() {
        String s = "Test update description";
        UpdateTaskDescriptorBuilder builder = new UpdateTaskDescriptorBuilder();
        UpdateTaskDescriptor descriptor = builder.setDescription(s).build();
        Task updatedTask = t.createUpdatedCopy(descriptor);
        assertEquals(new ToDo(s, true), updatedTask);
    }

    @Test
    public void testCreateUpdatedCopy_updateNonExistentField_equalTaskReturned() {
        LocalDateTime by = LocalDateTime.of(2021, 9, 11, 10, 30);
        LocalDateTime at = LocalDateTime.of(2021, 10, 10, 6, 30);
        UpdateTaskDescriptorBuilder builder = new UpdateTaskDescriptorBuilder();
        UpdateTaskDescriptor descriptor = builder.setBy(by).setAt(at).build();
        Task updatedTask = t.createUpdatedCopy(descriptor);
        assertEquals(t, updatedTask);
    }

    @Test
    public void testContainsKeyword() {
        assertTrue(t.containsKeyword("test"));
        assertFalse(t.containsKeyword("tP"));
    }

    @Test
    public void testSaveFormat() {
        assertEquals("T | 1 | JUnit test iP", t.getSaveFormat());
    }

    @Test
    public void testToString() {
        assertEquals("[T][X] JUnit test iP", t.toString());
    }
}
