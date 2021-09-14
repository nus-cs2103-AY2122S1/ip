package duke.logic.tasks;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import duke.logic.commands.UpdateCommand.UpdateTaskDescriptor;
import duke.logic.commands.UpdateTaskDescriptorBuilder;

public class TaskListTest {
    private TaskList list = new TaskList();

    @Test
    public void testAdd() {
        list.add(new TaskStub(1));
        assertEquals(1, list.size());
        assertEquals("[S][X] Stub task 1", list.get(1).toString());
    }

    @Test
    public void testDelete() {
        for (int i = 1; i < 4; i++) {
            list.add(new TaskStub(i));
        }
        Task del = list.delete(2);
        assertEquals(2, list.size());
        assertEquals("[S][X] Stub task 2", del.toString());
    }

    @Test
    public void testMarkAsDone() {
        list.add(new TaskStub(1));
        assertFalse(list.markAsDone(1)); // stub task is always already done
    }

    @Test
    public void testUpdate() {
        list.add(new TaskStub(1));
        list.add(new TaskStub(2));
        Task updatedTask = list.update(1, new UpdateTaskDescriptorBuilder().build());
        assertEquals(2, list.size());
        assertEquals(new TaskStub(11), updatedTask);
    }

    @Test
    public void testFind() {
        for (int i = 1; i < 5; i++) {
            list.add(new TaskStub(i));
        }
        list.add(new TaskStub(4));
        assertEquals(2, list.find(new String[]{"4"}).size());
        list.add(new TaskStub(3));
        assertEquals(4, list.find(new String[]{"3", "4", "random"}).size());
        assertEquals(6, list.find(new String[]{"Stub", "tas", "3", "4"}).size());
    }

    @Test
    public void testSaveFormat() {
        String[] data = new String[4];
        for (int i = 1; i < 5; i++) {
            list.add(new TaskStub(i));
            data[i - 1] = String.format("S | 1 | Stub task %d", i);
        }
        assertArrayEquals(data, list.getSaveFormat().toArray());
    }

    @Test
    public void testToString_emptyList() {
        assertEquals("You have no tasks!", list.toString());
    }

    @Test
    public void testToString_nonEmptyList() {
        StringBuilder s = new StringBuilder("Current tasks:\n");
        for (int i = 1; i < 5; i++) {
            list.add(new TaskStub(i));
            s.append(String.format("%d.[S][X] Stub task %d\n", i, i));
        }
        assertEquals(s.toString(), list.toString());
    }

    /**
     * A stub for Task.
     */
    public static class TaskStub extends Task {
        private final int stubNo;

        public TaskStub(int stubNo) {
            super("Stub task " + stubNo, true);
            this.stubNo = stubNo;
        }

        @Override
        public Task createUpdatedCopy(UpdateTaskDescriptor updateDescriptor) {
            return new TaskStub(stubNo + 10);
        }

        @Override
        public String getStatusIcon() {
            return "X";
        }

        @Override
        public String getSaveFormat() {
            return "S | 1 | Stub task " + stubNo;
        }

        @Override
        public String toString() {
            return "[S][X] Stub task " + stubNo;
        }

        @Override
        public boolean equals(Object obj) {
            return obj == this
                    || (obj instanceof TaskStub && stubNo == ((TaskStub) obj).stubNo);
        }
    }

}
