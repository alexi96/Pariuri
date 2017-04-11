package test;

import hiper.anotations.HiperEntity;
import hiper.anotations.HiperField;
import hiper.anotations.HiperPrimaryKey;
import java.io.Serializable;

@HiperEntity("user")
public class UserDB implements Serializable, Comparable<UserDB> {

    @HiperPrimaryKey
    @HiperField
    protected Integer id;
    @HiperField
    protected String username;
    @HiperField
    protected String password;
    @HiperField("first_name")
    protected String firstName;
    @HiperField("last_name")
    protected String lastName;
    @HiperField
    protected String email;

    public UserDB() {
    }

    public UserDB(Integer id) {
        this.id = id;
    }

    public UserDB(Integer id, String username, String password, String firstName, String lastName, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public UserDB(String username, String password, String firstName, String lastName, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int compareTo(UserDB o) {
        return this.username.compareTo(o.username);
    }

    @Override
    public String toString() {
        return this.username;
    }
}
