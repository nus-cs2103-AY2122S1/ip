package duke.logic.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.logic.commands.UpdateCommand;
import duke.logic.commands.UpdateTaskDescriptorBuilder;

public class EventTest {
    private static final String DEFAULT_DESCRIPTION = "JUnit test iP";
    private static final boolean DEFAULT_DONE = true;
    private static final LocalDateTime DEFAULT_AT = LocalDateTime.of(2021, 1, 1, 0, 0);
    private static final LocalDateTime DEFAULT_END = LocalDateTime.of(2021, 12, 31, 0, 0);
    private static final String UPDATE_DESCRIPTION = "Test update description";
    private static final LocalDateTime UPDATE_AT = LocalDateTime.of(2022, 1, 1, 0, 0);
    private static final LocalDateTime UPDATE_END = LocalDateTime.of(2022, 12, 31, 0, 0);

    private Event e = new Event(DEFAULT_DESCRIPTION, DEFAULT_DONE, DEFAULT_AT, DEFAULT_END);

    @Test
    public void testCreateUpdatedCopy_emptyDescriptor_equalTaskReturned() {
        Task updatedTask = e.createUpdatedCopy(new UpdateTaskDescriptorBuilder().build());
        assertEquals(e, updatedTask);
    }

    @Test
    public void testCreateUpdatedCopy_updateAllFields_updatedTaskReturned() {
        UpdateTaskDescriptorBuilder builder = new UpdateTaskDescriptorBuilder();
        UpdateCommand.UpdateTaskDescriptor descriptor =
                builder.setDescription(UPDATE_DESCRIPTION).setAt(UPDATE_AT).setEnd(UPDATE_END).build();
        Task updatedTask = e.createUpdatedCopy(descriptor);
        assertEquals(new Event(UPDATE_DESCRIPTION, DEFAULT_DONE, UPDATE_AT, UPDATE_END), updatedTask);
    }

    @Test
    public void testCreateUpdatedCopy_updateNonExistentField_equalTaskReturned() {
        LocalDateTime by = LocalDateTime.of(2022, 1, 1, 0, 0);
        UpdateTaskDescriptorBuilder builder = new UpdateTaskDescriptorBuilder();
        UpdateCommand.UpdateTaskDescriptor descriptor = builder.setBy(by).build();
        Task updatedTask = e.createUpdatedCopy(descriptor);
        assertEquals(e, updatedTask);
    }

    @Test
    public void testSaveFormat() {
        Event e = new Event(DEFAULT_DESCRIPTION, DEFAULT_AT, null);
        assertEquals("E | 0 | JUnit test iP | 2021-01-01T00:00 | null", e.getSaveFormat());
    }

    @Test
    public void testToString_noEndTime_descriptionStartsWithAt() {
        Event e = new Event(DEFAULT_DESCRIPTION, DEFAULT_AT, null);
        assertEquals("[E][ ] JUnit test iP (at: 01 Jan 2021 12.00am)", e.toString());
    }

    @Test
    public void testToString_withEndTime_descriptionStartsWithFrom() {
        Event e = new Event(DEFAULT_DESCRIPTION, DEFAULT_DONE, DEFAULT_AT, DEFAULT_END);
        assertEquals("[E][X] JUnit test iP (from: 01 Jan 2021 12.00am — 31 Dec 2021 12.00am)", e.toString());
    }
}
