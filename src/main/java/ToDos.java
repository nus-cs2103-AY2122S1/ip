public class ToDos extends Task {
    ToDos(String name, boolean done){
        super(name, done);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", (getDone())? "X" : " ", getName());
    }

    @Override
    Task markDone() {
        return new ToDos(getName(), true);
    }

    public static String getNameInput(String input) {
        return input.split("todo")[1].strip();
    }

    public static void isLegitInput(String input) throws NotEnoughInfoException {
        if (input.strip().equals("todo")){
            throw new NotEnoughInfoException("Could not find name of Task");
        }
    }
}
