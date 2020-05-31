package ru.otus.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    // simple data types: of java.lang package + primitives
    private String name;
    private String lastName;
    private Integer mssidn;

    // Complex: Collection + Custom Object
    Address address;
    List<Person> nextOfKin = new ArrayList<>();

    public Person(String name, String lastName, Integer mssidn) {
        this.name = name;
        this.lastName = lastName;
        this.mssidn = mssidn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getMssidn() {
        return mssidn;
    }

    public void setMssidn(Integer mssidn) {
        this.mssidn = mssidn;
    }

    public void setNextOfKin(Person nextOfKin) {
        this.nextOfKin.add(nextOfKin);
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getMssidn().equals(person.getMssidn()) &&
                Objects.equals(getName(), person.getName()) &&
                Objects.equals(getLastName(), person.getLastName()) &&
                Objects.equals(address, person.address) &&
                Objects.equals(nextOfKin, person.nextOfKin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLastName(), getMssidn(), address, nextOfKin);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mssidn=" + mssidn +
                ", address=" + address +
                ", nextOfKin=" + nextOfKin +
                '}';
    }
}
