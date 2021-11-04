package duke.stub;

import org.json.simple.JSONObject;

import duke.task.Task;

public class TaskStub extends Task {
    @Override
    public JSONObject toJsonObject() {
        return null;
    }

    @Override
    public int compareTo(Task o) {
        return 0;
    }
}
