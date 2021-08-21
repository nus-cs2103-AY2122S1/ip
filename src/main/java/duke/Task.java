package duke;

abstract class Task {
    String description;
    Boolean isDone = false;

    void setDone() {
        isDone = true;
    }
}
