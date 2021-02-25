package com.study.demo01.pojo;

import java.util.*;

public class Student {
    private String name;
    private Address address;
    private String[] hobbies;
    private List<String> games;
    private Map<String,Object> score;
    private Set<String> subject;
    private String work;
    private Properties studentInfo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

    public List<String> getGames() {
        return games;
    }

    public void setGames(List<String> games) {
        this.games = games;
    }

    public Map<String, Object> getScore() {
        return score;
    }

    public void setScore(Map<String, Object> score) {
        this.score = score;
    }

    public Set<String> getSubject() {
        return subject;
    }

    public void setSubject(Set<String> subject) {
        this.subject = subject;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public Properties getStudentInfo() {
        return studentInfo;
    }

    public void setStudentInfo(Properties studentInfo) {
        this.studentInfo = studentInfo;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", address=" + address +
                ", hobbies=" + Arrays.toString(hobbies) +
                ", games=" + games +
                ", score=" + score +
                ", subject=" + subject +
                ", work='" + work + '\'' +
                ", studentInfo=" + studentInfo +
                '}';
    }
}
