package com.hrc.administrator.myapp;

/**
 * Created by Administrator on 2016/9/26.
 */
public class ListViewlei {

    private String contexts;
    private String username;
    private String password;
    private String tel;
    private String email;

    public ListViewlei(String contexts,String username,String password){
        this.contexts=contexts;
        this.username=username;
        this.password=password;
        this.tel="空";
        this.email="空";
    }

    public ListViewlei(String contexts,String username,String password,String tel,String email){
        this(contexts, username, password);
        this.tel=tel;
        this.email=email;
    }

    public String getContexts() {
        return contexts;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getTel() {
        return tel;
    }
    public String getEmail() {
        return email;
    }
}
