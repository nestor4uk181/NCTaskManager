package ua.edu.sumdu.j2se.makarovskyi.tasks;

import java.util.Arrays;

/**
 * This class contains the logic of creating a tasks list
 */

public class ArrayTaskList {

    private Task[] arrayOfTasks;
    private int index = 0;
    private static final int capacity = 15;


    public ArrayTaskList() {
        arrayOfTasks = new Task[capacity];
    }


    /**
     * Method used to add the task to the list
     */
    public void add(Task task) {
        if (index == arrayOfTasks.length) {
            double newLength = arrayOfTasks.length * 1.5;
            arrayOfTasks = Arrays.copyOf(arrayOfTasks, (int) newLength);
        }
        arrayOfTasks[index++] = task;
    }

    /**
     * Method used to remove the task from the list
     */
    public boolean remove(Task task) {
        boolean result = false;
        Task[] temp = new Task[Math.max(capacity, arrayOfTasks.length - 1)];
        for (int a = 0; a < arrayOfTasks.length; a++) {
            if (arrayOfTasks[a].equals(task)) {
                for (int index = 0; index < a; index++) {
                    temp[index] = arrayOfTasks[index];
                }
                for (int b = a; b < arrayOfTasks.length - 1; b++) {
                    temp[b] = arrayOfTasks[b + 1];
                }
                arrayOfTasks = Arrays.copyOf(temp, temp.length);
                result = true;
                index--;
                break;
            }
        }
        return result;
    }

    /**
     * Method used to allow us to see the actual size of the list (amount of tasks in the list)
     */
    public int size() {
        return index;
    }

    /**
     * Method used to get a particular task from the list by the index (starts from 0)
     */
    public Task getTask(int index) {
        return arrayOfTasks[index];
    }


    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList incomingArray = new ArrayTaskList();
        for (int i = 0; i < arrayOfTasks.length; i++) {
            if (arrayOfTasks[i] != null) {
                if (arrayOfTasks[i].nextTimeAfter(from) != (-1)) {
                    if (arrayOfTasks[i].nextTimeAfter(from) < to) {
                        incomingArray.add(arrayOfTasks[i]);
                    }
                }
            }
        }
        return incomingArray;
    }

}
