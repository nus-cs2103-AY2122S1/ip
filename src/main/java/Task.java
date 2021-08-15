public abstract class Task {
    public abstract void setState(boolean state);

    public abstract boolean getState();

    public abstract String getTaskName();

    public abstract String getSymbol();

    @Override
    public String toString() {
        return "";
    }
}
