package ru.otus;

import com.google.gson.Gson;
import ru.otus.example.Address;
import ru.otus.example.Person;
import ru.otus.mygson.DIYJsonObjectParser;

public class MainStarter {
    public static void main(String[] args) throws Exception {
        // People
        Person kirk = new Person("James", "Kirk", 123456789);
        Person spock = new Person(null, "Spock", 987654321);
        Person bones = new Person("Leonard", "MacCoy", 654321987);
        Person uhura = new Person("Nyota", "Uhura", 258741963);
        Person checkov = new Person("Pavel", "Checkov", 517534682);
        // Addresses
        Address starFleetBase = new Address("California", "San-Francisco", "Main Street", 25858);
        Address CCMKorolev = new Address("Russian", "Korolev", "Lenina Street", 54654);
        // Connections
        bones.setNextOfKin(checkov);
        kirk.setNextOfKin(bones);
        spock.setNextOfKin(uhura);
        spock.setNextOfKin(kirk);

        spock.setAddress(starFleetBase);
        checkov.setAddress(CCMKorolev);

        // Process of serialization
        DIYJsonObjectParser parser = new DIYJsonObjectParser(spock).parse();
        String json = parser.buildJson();
        System.out.println(json);
        // Process of deserialization
        Gson gson = new Gson();
        Person spockThroughReflection = gson.fromJson(json, Person.class);
        // Assertion
        System.out.println(spock);
        System.out.println(spockThroughReflection);
        System.out.println(spockThroughReflection.equals(spock));
    }
}
