package com.ocean.mvc.entity;

import java.util.List;

/**
 * 功能描述
 *
 * @author chy
 * @date 2020/7/6 0006
 */
public class PersonModel {

    private List<Person> persons;

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public PersonModel() {
        super();
        // TODO Auto-generated constructor stub
    }

    public PersonModel(List<Person> persons) {
        super();
        this.persons = persons;
    }
}
