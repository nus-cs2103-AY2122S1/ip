package tiger.components;

import tiger.exceptions.storage.TigerStorageLoadException;

abstract public class Task {
    protected String taskDescription;
    protected boolean done;

    protected Task(String taskDescription, boolean done) {
        this.taskDescription = taskDescription;
        this.done = done;
    }

    abstract public Task markDone();

    abstract protected String getStorageRepresentation();

    protected static Task getTaskListFromStringRepresentation(String s) throws TigerStorageLoadException {
        String[] stringArray = s.split(";");
        switch (stringArray[0]) {
        case "T":
            return ToDo.getTaskFromStringRepresentation(s);
        case "E":
            return Event.getTaskFromStringRepresentation(s);
        case "D":
            return DeadLine.getTaskFromStringRepresentation(s);
        }
        throw new TigerStorageLoadException("");
    }

}
