# Dude User Guide

![Image of UI](./Ui.png)
## Features
1. Cross platform compactible, only Java 11 JRE without any third party libraries is needed to run the same .jar executable
2. Add different types of tasks, ToDo, DeadLine and Event tasks.
3. Set deadlines or times for DeadLine and Event tasks.
4. View all tasks added to the list.
5. Tries to parse dates automatically, but silently defaults to storing their raw string values to allow users to use any format they choose
6. Mark tasks as done according to the list index
7. Delete tasks by their list index
8. Search for tasks matching a keyword


### Cross platform compactible

#### Instructions for running Dude
1. Ensure Java 11 JRE or JDK is installed on your computer.
2. Download the [latest release](https://github.com/mingyi456/ip/releases/download/A-Release/duke.jar) of the duke.jar file from the [releases page](https://github.com/mingyi456/ip/releases).
3. Double click on the .jar file provided. 
4. If double clicking does not work, open a terminal at the directory containing the duke.jar file and run `java -jar duke.jar`.

### Adding tasks

To add a task, enter `task_type` followed by the arguments. Further details are specified below.

#### ToDo tasks

ToDo tasks are specified simply by its textual description. Simply enter `todo description`.

## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
