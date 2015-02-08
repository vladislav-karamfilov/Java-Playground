public class Main {
    public static void main(String[] args) {
        IQueue<Integer> queue = new Queue<>();
        queue.enqueue(1);
        queue.enqueue(3);
        queue.enqueue(8);
        queue.enqueue(-22);

        queue.deque(); // Removes the first item in the queue

        queue.enqueue(4);
        queue.enqueue(-19);
        queue.enqueue(7);

        // queue.deque(); // Removes the second item in the queue

        queue.enqueue(9);

        System.out.println(String.format("The size of the queue is: %d", queue.size()));

        // queue.clear();

        while (!queue.empty()) {
            System.out.println(String.format("Dequed: %d", queue.deque()));
        }

        System.out.println(String.format("The size of the queue is: %d", queue.size()));
    }
}
