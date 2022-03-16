package com.webkul.mobikul.mobikulstandalonepos.db.entity;


import android.arch.persistence.room.Ignore;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.j256.ormlite.table.DatabaseTable;
import com.webkul.mobikul.mobikulstandalonepos.db.dao.AdministratorDaoImpl;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@DatabaseTable( daoClass = AdministratorDaoImpl.class)
public class Administrator extends BaseObservable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "nadministrator_id_seq")
    private int uid;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Ignore
    private String newPassword;
    @Ignore
    private boolean displayError;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Bindable
    public String getFirstName() {
        if (firstName == null)
            return "";
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        //notifyPropertyChanged(BR.firstName);
    }

    @Bindable({"displayError", "firstName"})
    public String getFirstNameError() {
        if (!isDisplayError()) {
            return "";
        }
        if (getFirstName().isEmpty()) {
            return "FIRSTNAME IS EMPTY";
        }
        return "";
    }

    @Bindable
    public String getLastName() {
        if (lastName == null)
            return "";
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
       // notifyPropertyChanged(BR.lastName);
    }

    @Bindable({"displayError", "lastName"})
    public String getLastNameError() {
        if (!isDisplayError()) {
            return "";
        }
        if (getLastName().isEmpty()) {
            return "LASTNAME IS EMPTY";
        }
        return "";
    }

    @Bindable
    public String getEmail() {
        if (email == null)
            return "";
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
       // notifyPropertyChanged(BR.email);
    }

    @Bindable({"displayError", "email"})
    public String getEmailError() {
        if (!isDisplayError()) {
            return "";
        }
        if (getEmail().isEmpty()) {
            return "EMAIL IS EMPTY";
        }
//        if (!Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches()) {
//            return "PLEASE ENTER A VALID EMAIL!";
//        }
        return "";
    }

    @Bindable
    public String getPassword() {
        if (password == null)
            return "";
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        //notifyPropertyChanged(BR.password);
    }

    @Bindable({"displayError", "password"})
    public String getPasswordError() {
        if (!isDisplayError()) {
            return "";
        }
        if (getPassword().isEmpty()) {
            return "PASSWORD IS EMPTY";
        }
        return "";
    }

    @Bindable
    public boolean isDisplayError() {
        return displayError;
    }

    public void setDisplayError(boolean displayError) {
        this.displayError = displayError;
       // notifyPropertyChanged(BR.displayError);
    }

    @Bindable
    public String getUsername() {
        if (username == null)
            return "";
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        //notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getNewPassword() {
        if (newPassword == null)
            return "";
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        //notifyPropertyChanged(BR.newPassword);
    }
}