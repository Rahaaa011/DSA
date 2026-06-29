class CircularQueue {

    int arr[] = new int[5];
    int front = -1;
    int rear = -1;

    // ENQUEUE
    void enqueue(int data) {

        if ((rear + 1) % 5 == front) {
            System.out.println("Queue Overflow");
            return;
        }

        if (front == -1)
            front = 0;

        rear = (rear + 1) % 5;
        arr[rear] = data;
    }

    // DEQUEUE
    int dequeue() {

        if (front == -1) {
            System.out.println("Queue Underflow");
            return -1;
        }

        int value = arr[front];

        if (front == rear) {
            front = rear = -1;
        } else {
            front = (front + 1) % 5;
        }

        return value;
    }

    // DISPLAY
    void display() {

        if (front == -1) {
            System.out.println("Queue is Empty");
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

// MAIN CLASS
public class Main {

    public static void main(String[] args) {

        CircularQueue q = new CircularQueue();

        q.enqueue(10);
        q.display();

        q.enqueue(20);
        q.display();

        q.enqueue(30);
        q.display();

        q.enqueue(40);
        q.display();

        q.enqueue(50);
        q.display();

        q.dequeue();
        q.display();

        q.dequeue();
        q.display();

        q.enqueue(60);
        q.display();

        q.enqueue(70);
        q.display();
    }
}