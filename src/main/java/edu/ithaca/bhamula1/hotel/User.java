package edu.ithaca.bhamula1.hotel;

public class User implements UserInterface {
    private String nameFirst;
    private String nameLast;

    public User(String firstname,String lastname){
        nameFirst = firstname;
        nameLast = lastname;
    }

    public String getLastName(){
        return this.nameLast;
    }

    @Override
    public void setLastName(String n) {

    }

    @Override
    public String getFirstName() {
        return null;
    }

    @Override
    public void setFirstName(String n) {

    }
}
