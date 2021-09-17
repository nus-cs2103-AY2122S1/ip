# User Guide

Welcome to the Duke user guide!

Tired of keeping track of the many tasks in your mind? Duke has got you covered!

![Duke](https://github.com/cedricongjh/ip/blob/master/docs/Ui.png)

# Table of contents:

- [Getting started](#getting-started)

- [Commands](#commands)

# Getting started

## Adding a task

To add a task, simply type `todo <task_name>` E.g. todo Finish assignment

## Listing your tasks

To list your tasks, type `list` and Duke will show you your list of tasks

## Marking a task as done

To mark a task as done, type `done <task_number>` E.g. done 1 

This will mark the 1st task in your task list as done

## Deleting a task

To delete a task, type `delete <task_number>` E.g. delete 2

This will delete the 2nd task in your task list

## Closing Duke

To close Duke, simply type `bye`.

# Commands

### `todo` - Creates a Task

Example of usage: 

`todo Make breakfast`

### `event` - Creates an event

Creates an event with the date of that event

The date format is in `YYYY-MM-DD`

Example of usage: 

`event Dinner with friends /at 2021-09-03`

### `deadline` - Creates a deadline

Creates a deadline with a date

Example of usage: 

`deadline CS2100 assignment /by 2021-09-04`

### `list` - Lists out your tasks

Example of usage: 

`list sortByDate desc`

Note that sortByDate and desc is optional

By default, the list will be shown in the order one saves their tasks in

`list sortByDate` will show the earliest task first, while `list sortByDate desc` will show the latest task first

### `done` - Mark a task as done

Example of usage: 

`done 1`

Marks the 1st task in the user's task list as done. Take note that the 1st task is the 1st task the user added in. (Sorting does not affect the order)

### `delete` - Deletes a task

Example of usage: 

`delete 2`

Deltes the 2nd task in the user's task list. Take note that the 2nd task is the 2nd task the user added in. (Sorting does not affect the order)

### `bye` - Exits the application

Example of usage: 

`bye`
