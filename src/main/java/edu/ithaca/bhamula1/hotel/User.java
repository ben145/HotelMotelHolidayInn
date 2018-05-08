package edu.ithaca.bhamula1.hotel;

public class User implements UserInterface {
    private String nameFirst;
    private String nameLast;
    private String userType;

    public User(String f_name,String l_name, String uT){
        nameFirst = f_name;
        nameLast = l_name;
        userType = uT;
    }

    public String getLastName(){return nameLast;}

    @Override
    public void setLastName(String n) {nameLast = n;}

    @Override
    public String getFirstName() {return nameFirst;}

    @Override
    public void setFirstName(String n) {nameFirst = n;}
}
