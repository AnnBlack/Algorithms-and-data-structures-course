package Lab_6.Report;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonList {

    private List<Person> personList;

    public PersonList() {
        personList = new ArrayList<Person>();
    }

    @Override
    public String toString() {
        String result = "";
        personList.forEach(new Consumer<Person>() // Instantiate anonymous class implementing Consumer
        {
            public void accept(Person p) {
                System.out.println(p.name);
            }
        });
        return result;

    }

    public double getAverageOfAges() {
        return personList.stream()
        .mapToDouble(p -> p.age)
        .peek(x -> {
            System.out.println(x);
        })
        .average()
        .getAsDouble();
    }

    public List<Person> getPersonsByCondition(Predicate<Person> condition) {
        return personList.stream()
        .filter(condition)
        .collect(Collectors.toList());
    }

    public int getAverageOfAges(Predicate<Person> condition){
        return (int) personList.stream()
        .filter(condition)
        .mapToDouble( x -> x.age )
        .average()
        .getAsDouble();
    }

    public static void main(String[]args){
        PersonList p = new PersonList();
        p.personList.add(new Person("John", 16, "male"));
        p.personList.add(new Person("Michael", 32, "male"));
        p.personList.add(new Person("Richard", 40, "male"));
        p.personList.add(new Person("Rachael", 7, "female"));
        p.personList.add(new Person("Monica", 22, "female"));
        p.personList.add(new Person("Louie", 55, "male"));
        p.personList.add(new Person("Wallace", 98, "male"));

        p.toString();
        double result = p.getAverageOfAges();
        System.out.println("Average of ages = " + result);
        
        List<Person> pList = p.getPersonsByCondition(
            x -> (x.age > 18 && x.gender == "female")
        );

        pList.forEach( x -> System.out.println(x.name) );

        int averageAges = p.getAverageOfAges( x -> (x.age > 18 && x.gender == "female"));

        System.out.println("Average of ages of the filtered List: " + averageAges);
    }

}

class Person{
    String name;
    double age;
    String gender;
    public Person(String name, double age, String gender){
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}

