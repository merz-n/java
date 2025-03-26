import java.util.*;


public class Lessonneu5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ////////////////1///////////////////

        System.out.println("________________Erste task ___________");
        System.out.print("Enter the erste length the massiv: ");
        int k = scanner.nextInt();
        Lesson5 list = new Lesson5(k);

        System.out.println("Imput numbers(else 'stop'):");
        while (scanner.hasNextInt()) {
            list.add(scanner.nextInt());
        }
        scanner.next();
        list.printList();
        System.out.print("Imput a number to add: ");
        if (scanner.hasNextInt()) {
            list.add(scanner.nextInt());
        }
        list.printList();
        System.out.print("Imput index for serch: ");
        int index = scanner.nextInt();
        System.out.println("Element: " + list.get(index));
        System.out.println("List lenght: " + list.length());

        ////////////////2///////////////////
        System.out.println("________________Second task ___________");
        System.out.print("Enter the size of the queue: ");
        int size = scanner.nextInt();
        int[] queue = new int[size];
        int front = 0;
        int rear = 0;

        // Добавление элементов в очередь
        System.out.println("Input numbers (else 'stop'):");
        while (scanner.hasNextInt()) {
            int value = scanner.nextInt();
            if (rear == size) {
                System.out.println("Queue is full!");
                break;
            }
            queue[rear++] = value;
        }

        printQueue(queue, front, rear);

        System.out.println("Add element:");
        int em = scanner.nextInt();
        if (rear < size) {
            queue[rear++] = em;
        } else {
            System.out.println("Queue is full!");
        }
        printQueue(queue, front, rear);

        // ИзвлечениеFIFO
        System.out.println("Polling first element: " + dequeue(queue, front, rear));

        scanner.close();
    }
    ///////1////////
    private int[] data;
    private int size;


    public Lessonneu5(int k) {
        data = new int[k];
        size = 0;
    }
    public void add(int value) {
        if (size < data.length) {
            data[size] = value;
            size++;}
        else
            System.out.println("Error");
    }

    public int get(int index) {
        if (index >= 0 && index < size) {
            return data[index];
        } else {
            System.out.println("Error");
            return -1;
        }
    }

    public int length() {
        return size;
    }

    public void printList() {
        System.out.print("List: ");
        for (int i = 0; i < size; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }
    ///////2///////
    public static int dequeue(int[] queue, int front, int rear) {
        if (front == rear) {
            System.out.println("Queue is empty!");
            return -1;
        }
        int value = queue[front];
        front++;
        return value;
    }


    public static void printQueue(int[] queue, int front, int rear) {
        System.out.println("Queue elements:");
        for (int i = front; i < rear; i++) {
            System.out.print(queue[i] + " ");
        }
        System.out.println();
    }

}

/////////////////////////////////////////////////////////////////////////////
