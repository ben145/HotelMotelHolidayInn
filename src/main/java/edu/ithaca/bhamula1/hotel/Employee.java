package edu.ithaca.bhamula1.hotel;


public interface Employee {


    int getRoleNum();
    void setRoleNum(int num);

    String getRoleClass();
    void setRoleClass(String c);

    String getE_LastName();
    void setELastName(String ln);

    String getE_FirstName();
    void setEFirstName(String fn);

    String getE_LogID();
    void setE_LogID(String elID);

    String getE_PWD();
    void setE_PWD(String pwd);

    boolean checkE_LoginID(String eLog);
    boolean checkE_PWD(String ePWD);

    boolean getE_Available();
    boolean setE_Available();



}
