package duke.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import duke.logic.commands.UpdateCommand.UpdateTaskDescriptor;

public class UpdateTaskDescriptorTest {
    private static final String DEFAULT_DESCRIPTION = "JUnit test iP";
    private static final LocalDateTime DEFAULT_BY = LocalDateTime.of(2021, 1, 1, 0, 0);
    private static final LocalDateTime DEFAULT_AT = LocalDateTime.of(2021, 1, 1, 0, 0);
    private static final LocalDateTime DEFAULT_END = LocalDateTime.of(2021, 12, 31, 0, 0);

    private static final String ALTERNATIVE_DESCRIPTION = "Test description";
    private static final LocalDateTime ALTERNATIVE_BY = LocalDateTime.of(2000, 1, 1, 0, 0);
    private static final LocalDateTime ALTERNATIVE_AT = LocalDateTime.of(2000, 1, 1, 0, 0);
    private static final LocalDateTime ALTERNATIVE_END = LocalDateTime.of(2000, 12, 31, 0, 0);

    private UpdateTaskDescriptor desc = new UpdateTaskDescriptor(
            DEFAULT_DESCRIPTION, DEFAULT_BY, DEFAULT_AT, DEFAULT_END);
    private UpdateTaskDescriptorBuilder emptyBuilder = new UpdateTaskDescriptorBuilder();
    private UpdateTaskDescriptorBuilder defaultBuilder = new UpdateTaskDescriptorBuilder(desc);

    @Test
    public void testForNonNullFields() {
        UpdateTaskDescriptor nullDescriptor = emptyBuilder.build();
        assertFalse(nullDescriptor.isAnyFieldNonNull());

        UpdateTaskDescriptor nonNullDescriptor = emptyBuilder.setDescription(DEFAULT_DESCRIPTION).build();
        assertTrue(nonNullDescriptor.isAnyFieldNonNull());
    }

    @Test
    public void testGetDescription() {
        Optional<String> emptyDescription = emptyBuilder.build().getDescription();
        assertThrows(NoSuchElementException.class, emptyDescription::get);

        Optional<String> description = emptyBuilder.setDescription(DEFAULT_DESCRIPTION).build().getDescription();
        assertDoesNotThrow(description::get);
        assertEquals(DEFAULT_DESCRIPTION, description.get());
    }

    @Test
    public void testGetBy() {
        Optional<LocalDateTime> emptyBy = emptyBuilder.build().getBy();
        assertThrows(NoSuchElementException.class, emptyBy::get);

        Optional<LocalDateTime> by = emptyBuilder.setBy(DEFAULT_BY).build().getBy();
        assertDoesNotThrow(by::get);
        assertEquals(DEFAULT_BY, by.get());
    }

    @Test
    public void testGetAt() {
        Optional<LocalDateTime> emptyAt = emptyBuilder.build().getAt();
        assertThrows(NoSuchElementException.class, emptyAt::get);

        Optional<LocalDateTime> at = emptyBuilder.setAt(DEFAULT_AT).build().getAt();
        assertDoesNotThrow(at::get);
        assertEquals(DEFAULT_AT, at.get());
    }

    @Test
    public void testGetEnd() {
        Optional<LocalDateTime> emptyEnd = emptyBuilder.build().getEnd();
        assertThrows(NoSuchElementException.class, emptyEnd::get);

        Optional<LocalDateTime> end = emptyBuilder.setEnd(DEFAULT_END).build().getEnd();
        assertDoesNotThrow(end::get);
        assertEquals(DEFAULT_END, end.get());
    }

    @Test
    public void testEquals_sameObject() {
        assertEquals(desc, desc);
    }

    @Test
    public void testEquals_typeMismatch() {
        assertNotEquals(null, desc);
        assertNotEquals(3, desc);
    }

    @Test
    public void testEquals_sameValue() {
        UpdateTaskDescriptor sameValueDescriptor = defaultBuilder.build();
        assertEquals(desc, sameValueDescriptor);
    }

    @Test
    public void testEquals_differentDescription() {
        UpdateTaskDescriptor diffValueDescriptor = defaultBuilder.setDescription(ALTERNATIVE_DESCRIPTION).build();
        assertNotEquals(desc, diffValueDescriptor);
    }

    @Test
    public void testEquals_differentBy() {
        UpdateTaskDescriptor diffValueDescriptor = defaultBuilder.setBy(ALTERNATIVE_BY).build();
        assertNotEquals(desc, diffValueDescriptor);
    }

    @Test
    public void testEquals_differentAt() {
        UpdateTaskDescriptor diffValueDescriptor = defaultBuilder.setAt(ALTERNATIVE_AT).build();
        assertNotEquals(desc, diffValueDescriptor);
    }

    @Test
    public void testEquals_differentEnd() {
        UpdateTaskDescriptor diffValueDescriptor = defaultBuilder.setEnd(ALTERNATIVE_END).build();
        assertNotEquals(desc, diffValueDescriptor);
    }
}
