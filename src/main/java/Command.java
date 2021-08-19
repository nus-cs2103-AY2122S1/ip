package main.java;

public abstract class Command {

    /**
     * An abstract function that creates and returns the reply according to the user input.
     *
     * @return A response corresponding to the user input / command.
     */
    abstract String reply();
}