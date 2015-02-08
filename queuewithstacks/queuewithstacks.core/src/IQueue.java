public interface IQueue<T> {
    void enqueue(T item);

    T deque();

    int size();

    boolean empty();

    void clear();
}
