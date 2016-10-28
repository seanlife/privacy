/*
 * www.yiji.com Inc.
 * Copyright (c) 2016 All Rights Reserved
 */
/*
 * 修订记录:
 * kshujun@yiji.com 2016/10/28 16:13 创建
 *
 */
package onem.lyb.utils.common.tools;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Version 1.0
 * @Auther kshujun(kshujun@yiji.com)
 * @date 2016/10/28
 */
public class Person implements Serializable {
    private String name;
    private int age;
    private Date bornDatel;
    private double money;
    private long count;
    private List<Person> persons;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBornDatel() {
        return bornDatel;
    }

    public void setBornDatel(Date bornDatel) {
        this.bornDatel = bornDatel;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public String toString(){
        StringBuffer sb=new StringBuffer();
        sb.append("Person:");
        sb.append("name["+name+"]");
        sb.append("age["+age+"]");
        sb.append("bornDatel["+bornDatel+"]");
        sb.append("money["+money+"]");
        sb.append("count["+count+"]");
        return sb.toString();
    }
}
