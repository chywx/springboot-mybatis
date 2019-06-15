package com.ocean.mvc.entity;

public class Wolf {
    private Integer id;
    private String wolfName;
    private Integer age;

    public Wolf(String wolfName, Integer age) {
        this.wolfName = wolfName;
        this.age = age;
    }

    public Wolf() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWolfName() {
        return wolfName;
    }

    public void setWolfName(String wolfName) {
        this.wolfName = wolfName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Wolf{" +
                "id=" + id +
                ", wolfName='" + wolfName + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
