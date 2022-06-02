package ua.edu.sumdu.j2se.makarovskyi.tasks;

import java.lang.StringBuilder;

public class LinkedTaskList {

    private Node head;
    public int numberOfElem;

    private static class Node {
        public Task data;
        public Node next;

        public Node(Task data) {
            this.data = data;
            this.next = null;
        }
    }

    @Override
    public String toString() {
        return printLinkedlist(head);
    }

    private String printLinkedlist(Node startNode) {
        StringBuilder str = new StringBuilder();
        Node currNode = startNode;
        do {
            if(currNode == null) break;
            str.append(currNode.data.toString());
            currNode = currNode.next;
        } while (currNode != null);
        return "{" + str + "}";
    }

    public void add(Task task) {
        if(task == null) {
            System.out.println("Task cannot be null");
            return;
        }

        Node newNode = new Node(task);
        Node currNode = head;

        if(currNode == null) {
            head = newNode;
        } else {
            while (currNode.next != null) {
                currNode = currNode.next;
            }
            currNode.next = newNode;
        }
        numberOfElem++;
        System.out.println("Elements after adding " + printLinkedlist(head));
    }

    public boolean remove(Task task) {
        if (head != null) {
            boolean elementHasFound = false;
            Node currNode = head;
            Node prevNode = null;

            do {
                if (currNode.data == task) {
                    if (currNode == head) {
                        head = currNode.next;
                    } else {
                        prevNode.next = currNode.next;
                    }
                    elementHasFound = true;
                    break;
                }
                prevNode = currNode;
                currNode = currNode.next;
            } while (currNode != null);

            if (elementHasFound) {
                numberOfElem--;
                System.out.println("Elements after remove " + printLinkedlist(head));
                return true;
            }
        }
        return false;
    }

    public int size() {
        return numberOfElem;
    }

    public Task getTask(int index) throws IndexOutOfBoundsException, IllegalArgumentException {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("No task with such index");
        }
        if (head == null) {
            throw new IllegalArgumentException("List is empty");
        } else {
            Node currNode = head;
            int i = 0;
            while (currNode.next != null) {
                if (i==index) {
                    break;
                }
                i++;
                currNode = currNode.next;
            }
            return currNode.data;
        }
    }

    public LinkedTaskList incoming(int from, int to) {
        LinkedTaskList incomingTasks = new LinkedTaskList();

        for (int i = 0; i < numberOfElem; i++) {
            if (getTask(i) == null) {
                continue;
            }
            getTask(i).nextTimeAfter(from);

            if (from < getTask(i).nextTimeAfter(from) && getTask(i).nextTimeAfter(from) < to) {
                incomingTasks.add(getTask(i));
            }
        }
        return incomingTasks;
    }

}
