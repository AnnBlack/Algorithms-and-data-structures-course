package Lab_6.Report;

public enum InputCase {
    AVERAGE("Average"),

    SORTED("Presorted"),

    REVERSE("Reverse"),

    RANDOM("Random example");

    String name;

    InputCase(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}