package ru.otus.example;

import java.util.Objects;

public class Address {
    private String state;
    private String city;
    private String street;
    private int zipIndex;

    public Address(String state, String city, String street, int zipIndex) {
        this.state = state;
        this.city = city;
        this.street = street;
        this.zipIndex = zipIndex;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getZipIndex() {
        return zipIndex;
    }

    public void setZipIndex(int zipIndex) {
        this.zipIndex = zipIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return getZipIndex() == address.getZipIndex() &&
                Objects.equals(getState(), address.getState()) &&
                Objects.equals(getCity(), address.getCity()) &&
                Objects.equals(getStreet(), address.getStreet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getState(), getCity(), getStreet(), getZipIndex());
    }

    @Override
    public String toString() {
        return "Address{" +
                "state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", zipIndex=" + zipIndex +
                '}';
    }
}
