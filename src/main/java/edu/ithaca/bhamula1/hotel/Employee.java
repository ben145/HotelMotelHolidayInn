package edu.ithaca.bhamula1.hotel;


public interface Employee {




    int getE_TitleNum();
    void setE_TitleNum(int num);

    String getE_Title();
    void setE_Title(String t);

    String getE_LastName();
    void setE_LastName(String ln);

    String getE_FirstName();
    void setE_FirstName(String fn);

    String getE_LogID();
    void setE_LogID(String elID);

    String getE_PWD();
    void setE_PWD(String pwd);

    boolean checkE_LoginID(String eLog);
    boolean checkE_PWD(String ePWD);

    boolean getE_LoggedIn();
    void setE_LoggedIn(boolean in);

    boolean getE_Available();
    void setE_Available(boolean a);

    boolean getPositionVacant();
    void setPositionVacant(boolean v);



}
