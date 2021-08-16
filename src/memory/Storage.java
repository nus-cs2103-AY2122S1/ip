package memory;

import java.util.Arrays;

public class Storage {
    public static class Task {
        private final String description;
        private boolean isDone;

        Task(String entry) {
            this.description = entry;
        }

        void markDone() {
            isDone = true;
        }

        void markIncomplete() {
            isDone = false;
        }

        public String getDescription() {
            return description;
        }

        public String getStatus() {
            return isDone ? "x" : " ";
        }
    }
    private final Task[] storage;
    private int index;

    public Storage() {
        storage = new Task[100];
        index = 0;
    }

    public boolean push(String value) {
        if (index >= storage.length) {
            return false;
        }
        storage[index] = new Task(value);
        index += 1;
        return true;
    }

    public Task[] getStorage() {
        return Arrays.copyOfRange(storage, 0, index);
    }
}