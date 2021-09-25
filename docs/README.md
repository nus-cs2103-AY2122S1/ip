# Welcome to Alice User Guide

> Let's make a slight change to create a big difference

### Screenshots:
<div>
    <img align="center" src="https://github.com/kanjitp/ip/blob/master/docs/screenshots/alice_0.03_1.png?raw=true" style="width:32%">
    <img align="center" src="https://github.com/kanjitp/ip/blob/master/docs/screenshots/alice_0.03_2.png?raw=true" style="width:32%"/>
    <img align="center" src="https://github.com/kanjitp/ip/blob/master/docs/screenshots/alice_0.03_3.png?raw=true" style="width:32%"/>

</div>
<br>
Alice is a virtual assistant started off as Duke task manager before evolving in to her own form.

## This User Guide contains:

- **Features:**

    **Alice Start Page**

    -   [Create, Open, Delete multiple save files for different occasions](#create-open-delete-save-files-for-different-occasions-in-the-start-page)
    -   [Navigating to different save files](#navigating-to-different-save-files-through-start-page)

    ---
 
    **Alice Chat Page**

    -   [Add **Todo** task](#add-todo-task)
    -   [Add **event** task](#add-event-task)
    -   [Add **deadline** task](#add-deadline-task)
    -   [Mark task as **done**](#mark-task-as-done)
    -   [**Delete** task](#delete-task)
    -   [Search certain tasks using **date** to find upcoming task till that date](#search-certain-tasks-using-date-to-find-upcoming-task-till-that-date)
    -   [Search certain tasks using **"keywords"**](#search-certain-tasks-using-keywords)
    -   [Teach Alice to **learn** how to respond to certain vocabulary or sentences](#teach-alice-to-learn-how-to-respond-to-certain-vocabulary-or-sentences)
    -   [Tell Alice to **unlearn** certain vocabulary or sentences](#tell-alice-to-unlearn-certain-vocabulary-or-sentences)
    -   [Asking Alice to **list** out all the commands](#asking-alice-to-list-out-all-the-commands)
    -   [**Saving** file and continue editing other save files](#saving-file-and-continue-editing-other-save-files)

## **Features:**

## **Features in Alice Start Page:**

---

### Create, Open, Delete save files for different occasions in the Start Page

When starting Alice, user will be presented with a window called Start Page for user to choose an existing save file or inputting a new name into the text field in order to create a new save file with the specified name.

1. User can create new save file by typing in a new save file name which is not shown on the list view at the top.
2. User can choose to open up certain save file by:
    - **Clicking the save file name → Click/ Press Enter**
    - **Typing in the file name manually → Click/Press Enter**
3. User can choose to delete the save file:
    - **Clicking the save file name → Click delete**
    - **Typing in the file name manually → Click delete**

Clicking clear will clear out the textfield for user to occupy it with new input.

### Usage

To organize the task into different save file location so that the user can visualize and organize the task they have according to different occasions.

**Example of usage:**

- Create save file for school work
- Create save file for tasks to do with family
- Create save file for tasks to do with different projects, or work from different modules

[GO BACK TO THE TOP](#this-user-guide-contains)

<div>
    <img align="center" src="https://github.com/kanjitp/ip/blob/master/docs/screenshots/create_file_demo.gif?raw=true" style="width:32%">
    <img align="center" src="https://github.com/kanjitp/ip/blob/master/docs/screenshots/enter_file_demo.gif?raw=true" style="width:32%"/>
    <img align="center" src="https://github.com/kanjitp/ip/blob/master/docs/screenshots/delete_file_demo.gif?raw=true" style="width:32%"/>
</div>


---

### Navigating to different save files through Start Page

The same work flow as opening a save file in the Start Page:

To open a save file, user can choose to do so by:

- **Clicking the save file name → Click/Press Enter**
- **Typing in the file name manually → Click/Press Enter**

User will then be prompt with the chat page with Alice in which the user can get back to the Start Page again by:

- **Typing 'bye' command in the textfield at the bottom → Click Send/ Press Enter**

### Usage

To allow user to continue editing different save files without having to close the app and restarting the app again to get to new save file location

**Example of usage:**

- Edit tasks in 'home' save file and then navigating back to start page before choosing a new save file to start editing 'school' save file
- Edit tasks in 'home' save file and then navigating back to start page before creating a new save file of new tasks that user could suddenly think of
  
[GO BACK TO THE TOP](#this-user-guide-contains)

<br>

<div>
    <img align="center" src="https://github.com/kanjitp/ip/blob/master/docs/screenshots/enter_file_demo.gif?raw=true" style="width:49.5%">
    <img align="center" src="https://github.com/kanjitp/ip/blob/master/docs/screenshots/bye_demo.gif?raw=true" style="width:49.5%"/>
</div>

---

## **Features in Alice Chat Page:**

**Format** refers to the format of the command to be input into text field at the bottom of Alice Chat page to perform certain action 

To perform command: 

- **Type in command into text field at the bottom → Click Send/ Press Enter**

---

### Add **Todo** task

**Format: `todo <task description>`**

Add a todo task to the list to be stored to this save file

**Usage**

Todo task is a type of task which doesn't have a date associated with it.

Therefore, user may consider to use this command to input task in which they have no specific deadline or not crucial in time.

**Example of usage:**

`todo do laundry` will add a todo task to the list with description **'do laundry'** 

`todo revise resume` will add a todo task to the list with description **'revise resume'**

[GO BACK TO THE TOP](#this-user-guide-contains)

<br>

![alice_0.03](https://github.com/kanjitp/ip/blob/master/docs/screenshots/todo_demo2.gif?raw=true)


---

### Add **event** task

**Format: `event <event description> /at <yyyy-MM-dd>`**

Add an event task to the list to be stored to this save file

**Usage**

Event task is a type of task which has a date associated with it.

Therefore, user may consider to use this command to input task in which they have specific date which they deem to find important.

**Example of usage:**

`event Jason Recital /at 2021-11-08` 

will add an event to the list with description **'Jason Recital'** and the time at **'Nov 8 2021'**

`event Interview with Stephanie /at 2021-10-21`

will add an event to the list with description **'Interview with Stephanie'** and the time at **'Oct 21 2021'**

[GO BACK TO THE TOP](#this-user-guide-contains)

<br>
![alice_0.03](https://github.com/kanjitp/ip/blob/master/docs/screenshots/event_demo.gif?raw=true)
---

### Add **deadline** task

**Format: `deadline <deadline description> /by <yyyy-MM-dd>`**

Add a deadline task to the list to be stored to this save file

**Usage**

Deadline task is a type of task which has a deadline date for that task.

Therefore, user may consider to use this command to input task in which they have specific date to be used as deadline for that task.

**Example of usage:**

`deadline finish CS2103T submission /by 2021-09-17`

will add a deadline to the list with description **'finish CS2103T submission'** with deadline on **'Nov 17 2021'**

`deadline finish reading NO RULES RULES by REED HASTINGS /by 2021-10-30`

will add a deadline to the list with description **'finish reading NO RULES RULES by REED HASTINGS'** with deadline on **'Oct 30 2021'**

[GO BACK TO THE TOP](#this-user-guide-contains)

<br>
![alice_0.03](https://github.com/kanjitp/ip/blob/master/docs/screenshots/deadline_demo2.gif?raw=true)
---

### **Mark task as done**

**Format: `done <task index shown in the list>`**

Mark the task at specific index in the list as done

**Usage**

Every task when created will have a '✘' mark indicating that that task has not been done yet. By marking the task as done, the '✘' mark will be replaced with a '✔ ' mark instead.

**Example of usage:**

```docker
1. [T][✘] do laundry
2. [D][✘] finish CS2103T submission (by: Nov 17 2021)
```

`done 1`

will mark task 1 as done

```docker
1. [T][✔] do laundry
2. [D][✘] finish CS2103T submission (by: Nov 17 2021)
```

`done 2`

will mark task 2 as done

```docker
1. [T][✔] do laundry
2. [D][✔] finish CS2103T submission (by: Nov 17 2021)
```

[GO BACK TO THE TOP](#this-user-guide-contains)

<br>

![alice_0.03](https://github.com/kanjitp/ip/blob/master/docs/screenshots/done_demo2.gif?raw=true)
---

### **Delete** task

**Format: `delete <task index shown in the list>`**

Delete the task at specific index. The index of other tasks below it will shift up. 

**Usage**

When user make a mistake inputting a faulty task (i.e. wrong spelling, wrong date) or has already mark the task as done, he/she may consider to delete the task to free up the space in the list.

**Example of usage:**

```docker
1. [T][✘] do laundry
2. [D][✘] finish CS2103T submission (by: Nov 17 2021)
3. [E][✘] Jason Recital (at: Nov 8 2021)
```

`delete 2`

will delete the second task and shift the third task to index 2

```docker
1. [T][✘] do laundry
2. [E][✘] Jason Recital (at: Nov 8 2021)
```

[GO BACK TO THE TOP](#this-user-guide-contains)

<br>

![alice_0.03](https://github.com/kanjitp/ip/blob/master/docs/screenshots/delete_demo.gif?raw=true)
---

### Ask Alice to **list out current tasks**

**Format: `list`**

list out the current task

**Usage**

To check the current status of the task list

**Example of usage:**

`list`

will list out all the task that the user have in this current save file
<br>
![alice_0.03](https://github.com/kanjitp/ip/blob/master/docs/screenshots/todo_demo.gif?raw=true)
---

### **Search** certain tasks using **date** to find upcoming task till that date

**Format: `date <yyyy-MM-dd>`**

Ask Alice to list out all the todo tasks, event task which happens on/before the date and deadline happening on/before the date

**Usage**

To filter out the tasks that the user might not have interest in by that date. One usage is to filter out all the tasks before certain date so the user know what to do, what is happening, what important deadlines are there on/before that date.

**Example of usage:**

```docker
1. [T][✘] do laundry
2. [D][✘] finish CS2103T submission (by: Nov 17 2021)
3. [E][✘] Jason Recital (at: Nov 8 2021)
4. [D][✘] Make a pull request - CS2103T (by: Nov 10 2021)
```

`date 2021-11-10`

will filter out the event and deadline happening **after** Nov 11 2021

```docker
1. [T][✘] do laundry
2. [E][✘] Jason Recital (at: Nov 8 2021)
3. [D][✘] Make a pull request - CS2103T (by: Nov 10 2021)
```
[GO BACK TO THE TOP](#this-user-guide-contains)

<br>

![alice_0.03](https://github.com/kanjitp/ip/blob/master/docs/screenshots/date_demo.gif?raw=true)
---

### Search certain tasks using **keywords**

**Format: `find <keyword>`**

Ask Alice to list out the tasks which has matching description with the keyword given by the user

**Usage**

To search up tasks and make it more manageable to find the task that the user is searching for

**Example of usage:**

```docker
1. [T][✘] do laundry
2. [D][✘] finish CS2103T submission (by: Nov 17 2021)
3. [E][✘] Jason Recital (at: Nov 8 2021)
4. [D][✘] Make a pull request - CS2103T (by: Nov 10 2021)
```

`find CS2103T`

will filter out other tasks that do not have matching keyword

```docker
1. [D][✘] finish CS2103T submission (by: Nov 17 2021)
2. [D][✘] Make a pull request - CS2103T (by: Nov 10 2021)
```

[GO BACK TO THE TOP](#this-user-guide-contains)

<br>

![alice_0.03](https://github.com/kanjitp/ip/blob/master/docs/screenshots/find_demo.gif?raw=true)
---

### Teach Alice to **learn** **how to respond to certain vocabulary** or sentences

**Format: `learn <phrase>`**

Ask Alice to learn new feedback to certain phrase so that she can learn new vocabulary

**Format (continued): `<feedback to learn to given phrase>`**

Type in feedback to be responded when the user cite out certain phrase

**Usage**

To teach Alice how to respond to certain vocabulary or sentences

**[IMPORTANT]** 

Alice cannot learn the phrase which has the same spelling as the default commands

**Example of usage:**

`learn say hi to prof`

will prompt Alice that she will learn how to respond to **'say hi to prof'** and she will ask back what should be her feedback in which the user type in

`Hello Prof Damith! Isn't it a beautiful day~`

Alice will now remember to respond to **'say hi to prof'** with the given phrase

So the next time user type in

`say hi to prof`

Alice will respond with

`Hello Prof Damith! Isn't it a beautiful day~`

[GO BACK TO THE TOP](#this-user-guide-contains)

<br>
![alice_0.03](https://github.com/kanjitp/ip/blob/master/docs/screenshots/learn_demo.gif?raw=true)
---

### Tell Alice to **unlearn certain vocabulary** or sentences

**Format: `unlearn <phrase>`**

Ask Alice to unlearn certain phrase

**Usage**

To have Alice unable to respond to the given phrase anymore and potentially learn a new way to respond to the given phrase using the command `learn <phrase>` again

**[IMPORTANT]** 

Alice cannot unlearn the phrase which has the same spelling as the default commands

**Example of usage:**

`unlearn say hi to prof`

will make Alice forget how to respond to **'say hi to prof'** and will respond that she doesn't know  how to respond to the given command instead

[GO BACK TO THE TOP](#this-user-guide-contains)

<br>
![alice_0.03](https://github.com/kanjitp/ip/blob/master/docs/screenshots/unlearn_demo.gif?raw=true)
---

### Asking Alice to **list out all the commands**

**Format: `commands` or `help` or `?`**

Ask Alice to list out all the possible commands that you can perform excluding the vocabulary that you have taught to Alice

**Usage**

To remind the user of what they can do with Alice or ask her what she can do

**Example of usage:**

`commands` or `help` or `?`

will have Alice list out all the possible commands

[GO BACK TO THE TOP](#this-user-guide-contains)

<br>
![alice_0.03](https://github.com/kanjitp/ip/blob/master/docs/screenshots/commands_demo.gif?raw=true)
---

### **Saving file and continue editing other save files**

**Format: `bye`**

Ask Alice to save the current tasks and exit to the Start Page

**Usage**

To save and exit to Start Page in order to continue editing other save file or close the application

**[IMPORTANT]** 

The task list will not be saved unless the user use the command **`bye`** and exit to Start Page

**Example of usage:**

`bye`

will have Alice save the current task list in to the current save file and exit to Start Page

[GO BACK TO THE TOP](#this-user-guide-contains)

<br>
![alice_0.03](https://github.com/kanjitp/ip/blob/master/docs/screenshots/bye_demo.gif?raw=true)
---
