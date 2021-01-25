package Lab_2.Preparation;

public class BiNode
{
    public String item;
    public BiNode left;
    public BiNode right;

    BiNode(String item) {
        this(item, null, null);
    }

    BiNode(String item, BiNode left, BiNode right) {
        this.item = item;
        this.right = right;
        this.left = left;
    }

    public String getItem() {
        return item;
    }

    public BiNode getLeft() {
        return left;
    }
    public BiNode getRight() {
        return right;
    }
}