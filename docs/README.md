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

## Usage

### Adding tasks

To add a task, enter `task_type` followed by the arguments. Further details are specified below.

#### ToDo tasks

ToDo tasks are specified simply by its textual description. Simply enter `todo {description}`, such as:

`todo CS2103 iP Final Submission`

#### DeadLine tasks

DeadLine tasks are specified by a textual description field as well as a deadline (i.e. `deadline {description} /by {deadline}`), as follows: 

`deadline CS2103 iP Final Submission /by today midnight`

#### Event tasks

Event tasks are specified similarly to DeadLine tasks, except that the `/by` token is replaced by the `/by` option, as follows:

`event CS2103 iP Final Submission /at 22nd September 2021 2359HRS`

### Date format parsing 

If the deadline or time field of a DeadLine or Event task is entered in `yyyy-mm-dd` format, it is automatically recognised, parsed and converted into `MMM dd yyyy` format. Or else, the deadline field silently defaults to storing the string as-is without any modifications:

`deadline CS2103 iP Final Submission /by 2021-22-09`


### Viewing tasks

To view the list of tasks added to Dude, enter:

`list`

The tasks are displayed in a list according to the order they are added to Dude, and identified by their index in this list.

### Saving tasks

To save the current list of tasks, use the command:

`save`

This writes to a `dude_data.txt` file.

### The following commands recognise a task by its index on the task list

#### Marking tasks as done



#### Searchiing for task by keyword


#### Deleting tasks


