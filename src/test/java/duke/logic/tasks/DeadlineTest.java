package duke.logic.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.logic.commands.UpdateCommand;
import duke.logic.commands.UpdateTaskDescriptorBuilder;

public class DeadlineTest {
    private static final String DEFAULT_DESCRIPTION = "JUnit test iP";
    private static final boolean DEFAULT_DONE = true;
    private static final LocalDateTime DEFAULT_BY = LocalDateTime.of(2021, 1, 1, 0, 0);
    private static final String UPDATE_DESCRIPTION = "Test update description";
    private static final LocalDateTime UPDATE_BY = LocalDateTime.of(2022, 1, 1, 0, 0);

    private Deadline dl = new Deadline(DEFAULT_DESCRIPTION, DEFAULT_DONE, DEFAULT_BY);

    @Test
    public void testCreateUpdatedCopy_emptyDescriptor_equalTaskReturned() {
        Task updatedTask = dl.createUpdatedCopy(new UpdateTaskDescriptorBuilder().build());
        assertEquals(dl, updatedTask);
    }

    @Test
    public void testCreateUpdatedCopy_updateAllFields_updatedTaskReturned() {
        UpdateTaskDescriptorBuilder builder = new UpdateTaskDescriptorBuilder();
        UpdateCommand.UpdateTaskDescriptor descriptor =
                builder.setDescription(UPDATE_DESCRIPTION).setBy(UPDATE_BY).build();
        Task updatedTask = dl.createUpdatedCopy(descriptor);
        assertEquals(new Deadline(UPDATE_DESCRIPTION, DEFAULT_DONE, UPDATE_BY), updatedTask);
    }

    @Test
    public void testCreateUpdatedCopy_updateNonExistentField_equalTaskReturned() {
        LocalDateTime at = LocalDateTime.of(2022, 1, 1, 0, 0);
        LocalDateTime end = LocalDateTime.of(2022, 1, 1, 12, 0);
        UpdateTaskDescriptorBuilder builder = new UpdateTaskDescriptorBuilder();
        UpdateCommand.UpdateTaskDescriptor descriptor = builder.setAt(at).setEnd(end).build();
        Task updatedTask = dl.createUpdatedCopy(descriptor);
        assertEquals(dl, updatedTask);
    }

    @Test
    public void testSaveFormat() {
        assertEquals("D | 1 | JUnit test iP | 2021-01-01T00:00", dl.getSaveFormat());
    }

    @Test
    public void testToString() {
        assertEquals("[D][X] JUnit test iP (by: 01 Jan 2021 12.00am)", dl.toString());
    }

}
