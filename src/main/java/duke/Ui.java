package duke;

public class Ui {
    public void dukeGreeting() {
        String name = "JARVIS";
        System.out.println("Hello I am " + name + ".\n"
                + "Is there anything I can do for you Sir?\n");
    }

    public void unusualRequest() {
        System.out.println("That is a rather unusual looking request sir.\n"
                + "Perhaps you might want to specify "
                + "the kind of task you would like to add.\n");
    }

    public void dukeResponse(String output) {
        System.out.println(output);
    }

    public void farewellMessage() {
        System.out.println("Goodbye Sir! Will take good "
                + "care of your garden in the meantime.");
    }

}
