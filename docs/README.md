# Duke User Guide
Duke is an excellent task manager. Just type the given commands to put what you want to do, what you will do, and what you have to do(ok...) into his task list. He can quickly record and keep track of them just as you expected!

## Navigation
1. [Get Started](#get-started)
2. [Features](#features)
   1. [`todo`](#todo)
   2. [`deadline`](#deadline)
   3. [`event`](#event)
   4. [`list`](#list)
   5. [`done`](#done)
   6. [`delete`](#delete)
   7. [`date`](#date)
   8. [`find`](#find)
3. [Exiting the app](#exiting-the-app)
4. [Showing Reminders](#showing-reminders)
5. [Reporting an issue](#reporting-an-issue)

## Get Started
Open the program, and picture below is what you tend to see.

![image](https://user-images.githubusercontent.com/77196303/133784210-988657f1-c286-489c-9532-8fef663f4c06.png)

**Note:**
1. Make sure java 11 is installed and correctly run the jar file.

## Features
Here are some commands to use with Duke. The commands are also case-sensitive and you should type the command in exact format given otherwise Duke can't understand you. Square brackets `[]` has the content which can replaced following the format given.

### `todo`
Adds a task you tend to do in the list.

**General format:** 

`todo [task description]`

**Example of usage:**

`todo play games`

Expected outcome:

![image](https://user-images.githubusercontent.com/77196303/133786552-7a8e6dcf-1b43-4920-8066-60cf897c0b8d.png)

Now you have a `todo` with description of `play games` in the list of tasks!

### `deadline`
Adds a task with a deadline in the list.

**General format:** 

`deadline [task description] /by [dd/mm/yyyy]`
or
`deadline [task description] /by [dd/mm/yyyy] [hhmm]`

**Example of usage:**

`deadline homework1 /by 11/9/2021`
`deadline homework1 /by 11/9/2021 2359`

Expected outcome:

![image](https://user-images.githubusercontent.com/77196303/133787282-9b63f485-048c-424b-bae0-ea2232d319bc.png)

Now you have a `deadline` with description of `homework1` in the list of tasks!

**Note:**
1. [hhmm] means the time in 24hrs format.
2. Be careful about the spaces.

### `event`
Adds a task with a certain time in the list.

**General format:** 

`event [task description] /at [dd/mm/yyyy]`
or
`event [task description] /at [dd/mm/yyyy] [hhmm]`

**Example of usage:**

`event homework2 /at 12/9/2021`
`event homework2 /at 12/9/2021 2359`

Expected outcome:

![image](https://user-images.githubusercontent.com/77196303/133788214-62b811f9-dfea-498b-acb4-2d9c1b90e6b1.png)

Now you have a `event` with description of `homework2` in the list of tasks!


### `list`
Shows all your tasks as a list.

**Example:** `list`

**Expected outcome:**

![image](https://user-images.githubusercontent.com/77196303/133788886-1ea4773c-d126-4a4e-bd6e-7a45c4dcc503.png)

### `done`
Marks the task at the specified index as done. If the task is marked as done, it's denoted with `[X]`.

**Format:** 

`done [number]`

**Expected outcome:**

![image](https://user-images.githubusercontent.com/77196303/133789183-45258017-03a2-4a6c-bc49-93f89d42ffbb.png)

**Caution:** 

Once you mark a task as done, no redo is allowed.

### `delete`
Removes the task at the specified index from the list.

**Format:** 

`delete [number]`

**Expected outcome:**

![image](https://user-images.githubusercontent.com/77196303/133789445-8636e5e1-5622-41ba-891e-20bdc32850aa.png)

**Caution:** 

No recovery of task if it's deleted!

### `date`
Displays the list of tasks with certain date. The list will not contain Todos as they do not have a date.

**Format:** 

`date [date]`

**Expected outcome:**

![image](https://user-images.githubusercontent.com/77196303/133790369-26d5958e-5363-416a-992b-ad4e009d42f1.png)

**Caution:** 

Only exact match can be found,eg 11/11/2020 doesn't match 11/11/2020 1644.

### `find`
Lists all the tasks that contains the keyword you are searching for.

**Format:** 

`find [keyword]`

**Expected outcome:**

![image](https://user-images.githubusercontent.com/77196303/133790584-96cf915b-7500-4be4-8895-485da8e87c94.png)


## Exiting the App
To exit Duke, just type `bye`. Here's what you'll expect to see:

![image](https://user-images.githubusercontent.com/77196303/133791045-8068333d-1004-4c2d-93f8-6f66530dcf76.png)

## Showing Reminders
Once you stored the tasks that happen in the following month or week, they will be shown as you open Duke next time.
The following will occur if you add these tasks already and the date now is 17/09/2021.

![image](https://user-images.githubusercontent.com/77196303/133791530-02a2b5c9-0bae-4dcd-9fbe-8685de45139c.png)


## Reporting an Issue
If you get something mad just post [here](https://github.com/qinguorui2001/ip/issues) with the accompanying screenshot of the problem.
