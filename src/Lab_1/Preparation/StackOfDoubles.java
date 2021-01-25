public class StackOfDoubles {
    private String[] arrayOfStrings;
    private int capacity = 0;

    public StackOfDoubles(int capacity) {
        arrayOfStrings = new String[capacity];
    }
    public boolean isEmpty() {
        return capacity == 0;
    }
    public void push(String item) {
        arrayOfStrings[capacity++] = item;
    }
    public String pop() {
        String item = arrayOfStrings[capacity - 1];
        arrayOfStrings[capacity - 1] = null;
        capacity--;
        return item;
    }

    public static void main(String[] args) {
        StackOfDoubles stackOfDoubles = new StackOfDoubles(5);
        stackOfDoubles.push("Hello");
        stackOfDoubles.push("Hello 2");
        System.out.println("capacity after push: " + stackOfDoubles.capacity);
        System.out.println(stackOfDoubles.pop());
        System.out.println("capacity after pop: " + stackOfDoubles.capacity);
    }
}
