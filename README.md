# Duke project template

![image](https://user-images.githubusercontent.com/66522537/131479345-bb3dfaa2-87e6-425c-aab8-c17ba279f79e.png)
Product Screenshot

## Description
Duke is an interactive chatbot that keeps track of and manages your tasks for you. It is targeted for users who prefer typing commands. This project is part of the greenfield individual-project for CS2103.

## Technology
1. Java 11
2. JavaFX 11
3. Gradle

## Usage

### Starting Duke
Download the latest release of the Duke.jar file.

Please make sure that you have Java 11 installed. Enter `java -jar <duke-filename>` into your computer's terminal in the same directory as your Duke.jar file.

Upon starting Duke, the application will attempt to load the previous task history from `./data/duke.txt`. If the file is missing, Duke will create a new storage file there.

### Creating Tasks
You can create and store following tasks in Duke.
1. **ToDo** Tasks
1. **Deadline** Tasks
1. **Event** Tasks

#### Creating ToDo Tasks
ToDo tasks are simple tasks with only a specified description.

*Command*
```
todo <description>
```
*Some examples*
```
todo finish cs3230 assignment 3
todo review lecture
```

#### Creating Deadline Tasks
Deadline tasks are tasks with a specified description and a text determining when it's due **_by_**. If the text specifying the due date is in the format `yyyy-mm-dd`, it will be formatted into a text in the form `MMM-dd-yyyy`, where MMM is the first 3 characters of the month name.

*Command*
```
deadline <description> /by <date>
```
*Some examples*
```
deadline buy bread /by tomorrow evening
deadline buy coffee /by 2021-09-30
```

#### Creating Event Tasks
Event tasks are tasks with a specified description and a text determining the period/location it is **_at_**.

*Command*
```
event <description> /at <period>
```
*Some examples*
```
event marathon /at central park
event josh's birthday party /at noon next sunday
```

### Listing Tasks
After creating your tasks, you may view the list of tasks that you have added into Duke. The list of tasks are also stored in an editable text file at `./data/duke.txt`.

*Command*
```
list
```
*Expected Output*
```
Here are the tasks in your list:
These are your tasks:
1. <task-info>
...
```
*Empty Task List Output*
```
Your list of tasks is empty!
```

### Searching Tasks
After creating your tasks, you can search for tasks that contain a particular string in its description.

*Command*
```
find <string-to-match>
```
*Expected Output*
```
Alrighty, I found some tasks! Here they are:
1. <task-info>
2. <task-info>
...
```
*No Matching Tasks Output*
```
Ah dang it! There are no matching tasks ):
```

### Completing Tasks
Once you have finished your task, you can update the task and mark them as done. Refer to the task number that is given in the `list` command.

*Command*
```
done <task-number>
```

### Deleting Tasks
You can choose to delete any task from Duke at any time. Refer to the task number that is given in the `list` command.

*Command*
```
delete <task-number>
```

### Finding Tasks
You can search your list of tasks for matching tasks to a specified input text.

*Command*
```
delete <text-pattern>
```

### Closing Duke
You can exit duke by issueing the following command.

*Command*
```
bye
```
