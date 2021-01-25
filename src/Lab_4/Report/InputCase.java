package Lab_4.Report;

public enum InputCase {
    BSTCASE("BST"),

    REDBLACK("Red-Black");

    String name;

    InputCase(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}