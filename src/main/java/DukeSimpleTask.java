class DukeSimpleTask extends DukeTask {
    DukeSimpleTask(String name) {
        super(name);
    }

    public DukeSimpleTask(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toSerializedString() {
        return String.format("%s/%d/simple", name, isDone ? 1 : 0);
    }
}
