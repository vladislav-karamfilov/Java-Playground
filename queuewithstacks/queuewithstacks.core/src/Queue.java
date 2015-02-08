import java.util.NoSuchElementException;
import java.util.Stack;

public class Queue<T> implements IQueue<T> {
    private Stack<T> enqueueStack;
    private Stack<T> dequeStack;

    public Queue() {
        this.enqueueStack = new Stack<T>();
        this.dequeStack = new Stack<T>();
    }

    @Override
    public void enqueue(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Item to be enqueued cannot be null!");
        }

        this.moveItems(this.dequeStack, this.enqueueStack);
        this.enqueueStack.push(item);
    }

    @Override
    public T deque() {
        if (this.enqueueStack.empty() && this.dequeStack.empty()) {
            throw new NoSuchElementException("Cannot deque an empty queue!");
        }

        this.moveItems(this.enqueueStack, this.dequeStack);

        return this.dequeStack.pop();
    }

    @Override
    public int size() {
        return this.enqueueStack.empty() ? this.dequeStack.size() : this.enqueueStack.size();
    }

    @Override
    public boolean empty() {
        return this.enqueueStack.empty() && this.dequeStack.empty();
    }

    @Override
    public void clear() {
        this.enqueueStack.clear();
        this.dequeStack.clear();
    }

    private void moveItems(Stack<T> sourceStack, Stack<T> destinationStack) {
        while (!sourceStack.empty()) {
            destinationStack.push(sourceStack.pop());
        }
    }
}
