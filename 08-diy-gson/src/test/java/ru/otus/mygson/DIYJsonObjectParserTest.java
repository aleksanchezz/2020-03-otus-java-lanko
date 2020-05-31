package ru.otus.mygson;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import ru.otus.example.Address;
import ru.otus.example.Person;

import static org.assertj.core.api.Assertions.assertThat;

class DIYJsonObjectParserTest {
    /**
     * Легенда следующая:
     * есть 5 персон:
     *  - капитан Кирк
     *  - коммандер Спок
     *  - доктор Боунс (МакКой)
     *  - лейтенант Ухура
     *  - энсин Чехов
     *
     * между ними следующие связи (ближайшие родственники -- их может быть несколько (List)):
     *  У Спока --> Кирк и Ухура
     *  У Кирка --> Боунс
     *  У Боунса --> Чехов
     *
     * у Спока и Чехова указаны адреса (у персоны может быть 1 адрес)
     */
    @Test
    public void mainTest() throws Exception {
        // People
        Person kirk = new Person("James", "Kirk", 123456789);
        Person spock = new Person(null, "Spock", 987654321);
        Person bones = new Person("Leonard", "MacCoy", 654321987);
        Person uhura = new Person("Nyota", "Uhura", 258741963);
        Person checkov = new Person("Pavel", "Checkov", 517534682);
        // Addresses
        Address starFleetBase = new Address("California", "San-Francisco", "Main Street", 25858);
        Address CCMKorolev = new Address("Russia", "Korolev", "Kosmicheskaya Street", 54654);
        // Connections
        bones.setNextOfKin(checkov);
        kirk.setNextOfKin(bones);
        spock.setNextOfKin(uhura);
        spock.setNextOfKin(kirk);

        spock.setAddress(starFleetBase);
        checkov.setAddress(CCMKorolev);

        // Process of serialization
        DIYJsonObjectParser spockParser = new DIYJsonObjectParser(spock).parse();
        String spockJson = spockParser.buildJson();

        DIYJsonObjectParser kirkParser = new DIYJsonObjectParser(kirk).parse();
        String kirkJson = kirkParser.buildJson();

        DIYJsonObjectParser bonesParser = new DIYJsonObjectParser(bones).parse();
        String bonesJson = bonesParser.buildJson();

        // Process of deserialization
        Gson gson = new Gson();

        Person spockThroughReflection = gson.fromJson(spockJson, Person.class);
        Person kirkThroughReflection = gson.fromJson(kirkJson, Person.class);
        Person bonesThroughReflection = gson.fromJson(bonesJson, Person.class);

        // Assertions
        assertThat(spockThroughReflection).isEqualTo(spock);
        assertThat(kirkThroughReflection).isEqualTo(kirk);
        assertThat(bonesThroughReflection).isEqualTo(bones);
    }
}
