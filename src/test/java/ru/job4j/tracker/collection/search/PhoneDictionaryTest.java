package ru.job4j.tracker.collection.search;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class PhoneDictionaryTest {

    @Test
    public void whenAdd() {
        Person person = new Person("Max", "Tsurkanov",
                "+786121", "Krasnodar");
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(person);
        String[] expected = { person.getName(),
                              person.getSurname(),
                              person.getPhone(),
                              person.getAddress(),
        };
        String[]rsl = { phones.getPersons().get(0).getName(),
                        phones.getPersons().get(0).getSurname(),
                        phones.getPersons().get(0).getPhone(),
                        phones.getPersons().get(0).getAddress(),
        };
        assertThat(rsl, is(expected));

    }

    @Test
    public void whenFindByNameIsSer() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("Igor", "Ivanov", "6616161", "Moscow"));
        phones.add(new Person("Sergey", "Petrov", "2178364", "Kursk"));
        ArrayList<Person> findPerson = phones.find("Ser");
        assertThat(findPerson.get(0).getSurname(), is("Petrov"));

    }

    @Test
    public void whenFindByPhoneIs632() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("Igor", "Ivanov", "6326161", "Moscow"));
        phones.add(new Person("Sergey", "Petrov", "63283264", "Kursk"));
        ArrayList<Person> person = phones.find("632");
        assertThat(person.get(0).getName(), is("Igor"));
        assertThat(person.get(1).getName(), is("Sergey"));
    }
}