public class GenericLinkedList implements ILinkedList {
    private static class Node {
        String item;
        Node next;
    }
    private Node first = null;

    public boolean isEmpty() {
        return first == null;
    }
    public void push(String item) {
        Node second = first;
        first = new Node();
        first.item = item;
        first.next = second;
    }
    public String pop() {
        if(!isEmpty()) {
            String item = first.item;
            first = first.next;
            return item;
        } else {
            return null;
        }
    }
}
