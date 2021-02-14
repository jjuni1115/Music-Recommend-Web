package com.MrS.possible.domain;

import org.apache.ibatis.type.Alias;

@Alias("member")  // alias register, check in memberMapper.xml's resultType
public class Member {
    private Long ID;
    private String account;
    private String password;
    private String first_name;
    private String last_name;
    private int age;
    private String class_;
    private int money;
    private int sex;

    public Member(){}  //

    public Member(Long id, String account, String password, String first_name, String last_name, int age, String class_, int money, int sex) {
        this.ID = id;
        this.account = account;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.class_ = class_;
        this.money = money;
        this.sex = sex;
    }

    public Member(String account, String password, String first_name, String last_name, int age, String class_, int money, int sex) {
        this.ID = 0L;
        this.account = account;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.class_ = class_;
        this.money = money;
        this.sex = sex;
    }

//     Overloading
    public Member(String account, String password) {
        this.ID = 0L;
        this.account = account;
        this.password = password;
    }

    public Member(Long ID, String account) {
        this.ID = ID;
        this.account = account;
    }

    // for check_id
    public Member(String account) {
        this.ID = 0L;
        this.account = account;
    }

    public Member(Long ID, String Account, String first_name, String last_name){
        this.ID = ID;
        this.account = Account;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Member(Long ID, String first_name, String last_name){
        this.ID = ID;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Long getId() {
        return ID;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public int getAge() {
        return age;
    }

    public String getClass_() {
        return class_;
    }

    public int getMoney() {
        return money;
    }

    public int getSex() {
        return sex;
    }

    public void setId(Long ID) {
        this.ID = ID;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setClass_(String class_) {
        this.class_ = class_;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // log print Style
    @Override
    public String toString(){
        return "Member{" +
                "ID=" + ID +
                ", account='" + account + '\''+
                ", password='" + password + '\''+
                '}';
    }
}
