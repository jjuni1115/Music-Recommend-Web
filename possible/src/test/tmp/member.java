package com.jinwoo.possible;

// input or return
public class member {
    private String member_id;
    private String member_pw;

    private Long id;
    private String account;
    private String password;

    public member(){}

    public member(Long id, String account, String password) {
        this.id = id;
        this.account = account;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return "Member{" +
                "id=" + id +
                ", account='" + account + '\''+
                ", password='" + password + '\''+
                '}';
    }

    public String getM_id(){
        return member_id;
    }

    public String getM_pw(){
        return member_pw;
    }

    public void setM_id(String member_id){
        this.member_id = member_id;
    }

    public void setM_pw(String member_pw){
        this.member_pw = member_pw;
    }

}
