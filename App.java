import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws IOException {
        LinkedList list = new LinkedList();
        ListIterator iterator = list.getIterator();
        long value;

        iterator.insertAfter(20);
        iterator.insertAfter(40);
        iterator.insertAfter(80);
        iterator.insertBefore(60);

        while (true) {
            System.out.print("Enter first letter of show, reset, ");
            System.out.print("next, get, before, after, delete: ");

            System.out.flush();

            int choice = getChar();

            switch (choice) {
                case 's':
                    if (!list.isEmpty()) {
                        list.display();
                    } else {
                        System.out.println("List is Empty!");
                    }

                    break;
                case 'r':
                    iterator.reset(); break;
                case 'n':
                    if (!list.isEmpty() && !iterator.atEnd()) {
                        iterator.next();
                    } else {
                        System.out.println("Can't go to next node");
                    }

                    break;
                case 'g':
                    if (!list.isEmpty()) {
                        value = iterator.getCurrent().data;

                        System.out.println("Returned " + value);
                    } else {
                        System.out.println("List is Empty!");
                    }

                    break;
                case 'b':
                    System.out.print("Enter value to insert: ");
                    System.out.flush();

                    value = getInt();

                    iterator.insertBefore(value);

                    break;
                case 'a':
                    System.out.print("Enter value to insert: ");
                    System.out.flush();

                    value = getInt();

                    iterator.insertAfter(value);

                    break;
                case 'd':
                    if (!list.isEmpty()) {
                        value = iterator.deleteCurrent();

                        System.out.println("Deleted " + value);
                    } else {
                        System.out.println("Can't delete");
                    }

                    break;
                default:
                    System.out.println("Invalid entry!");
            }
        }
    }

    public static String getString() throws IOException {
        InputStreamReader stream = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(stream);

        return in.readLine();
    }

    public static char getChar() throws IOException {
        String str = getString();

        return str.charAt(0);
    }

    public static int getInt() throws IOException {
        String str = getString();

        return Integer.parseInt(str);
    }
}

class Node {
    public long data;
    public Node next;

    public Node(long data) {
        this.data = data;
    }

    public void display() {
        System.out.print(data + " ");
    }
}

class LinkedList {
    private Node first;

    public LinkedList() {
        this.first = null;
    }

    public Node getFirst() {
        return first;
    }

    public void setFirst(Node node) {
        this.first = node;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public ListIterator getIterator() {
        return new ListIterator(this);
    }

    public void display() {
        Node current = first;

        while (current != null) {
            current.display();

            current = current.next;
        }

        System.out.println("");
    }
}

class ListIterator {
    private Node current;
    private Node previous;
    private LinkedList list;

    public ListIterator(LinkedList list) {
        this.list = list;

        reset();
    }

    public void reset() {
        current = list.getFirst();
        previous = null;
    }

    public boolean atEnd() {
        return current.next == null;
    }

    public void next() {
        previous = current;
        current = current.next;
    }

    public Node getCurrent() {
        return current;
    }

    public void insertAfter(long data) {
        Node node = new Node(data);

        if (list.isEmpty()) {
            list.setFirst(node);

            current = node;
        } else{
            node.next = current.next;
            current.next = node;

            next();
        }
    }

    public void insertBefore(long data) {
       Node node = new Node(data);

       if (previous == null) {
           node.next = list.getFirst();
           list.setFirst(node);

           reset();
       } else {
           node.next = previous.next;
           previous.next = node;
           current = node;

           reset();
       }
    }

    public long deleteCurrent() {
        long value = current.data;

        if (previous == null) {
            list.setFirst(current.next);

            reset();
        } else {
            previous.next = current.next;

            if (atEnd()) {
                reset();
            } else {
                current = current.next;
            }
        }

        return value;
    }
}