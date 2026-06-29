package circularqueue1;

class CircularQueue {

    String arr[] = new String[5];
    int front = -1;
    int rear = -1;

    // Enqueue
    void enqueue(String data) {

        if ((rear + 1) % 5 == front) {
            System.out.println("Queue Full");
            return;
        }

        if (front == -1)
            front = 0;

        rear = (rear + 1) % 5;
        arr[rear] = data;
    }

    // Dequeue
    String dequeue() {

        if (front == -1) {
            System.out.println("Queue Empty");
            return null;
        }

        String value = arr[front];

        if (front == rear) {
            front = rear = -1;
        } else {
            front = (front + 1) % 5;
        }

        return value;
    }

    // Display
    void display() {

        if (front == -1) {
            System.out.println("Queue Empty");
            return;
        }

        int i = front;

        while (true) {

            System.out.print(arr[i] + " ");

            if (i == rear)
                break;

            i = (i + 1) % 5;
        }

        System.out.println();
    }
}

public class Main {

    public static void main(String[] args) {

        CircularQueue q = new CircularQueue();

        System.out.println("Enqueue Doc1");
        q.enqueue("Doc1");
        q.display();

        System.out.println("Enqueue Doc2");
        q.enqueue("Doc2");
        q.display();

        System.out.println("Enqueue Doc3");
        q.enqueue("Doc3");
        q.display();

        System.out.println("Dequeue (Doc1 Finished)");
        q.dequeue();
        q.display();

        System.out.println("Enqueue Doc4");
        q.enqueue("Doc4");
        q.display();
    }
}