package edu.ithaca.bhamula1.hotel;

/**
 * EmployeeIMPL Class
 * implements employee interface
 * @author DMF 4/7/18
 */
public class EmployeeIMPL implements Employee{

    private int e_tNum;
    private String e_Title;
    private String e_LName;
    private String e_FName;
    private String e_LogID;
    private String e_PWD;
    private boolean e_LoggedIn;
    private boolean e_Available;
    private boolean positionVacant;

    // Default constructor
    public EmployeeIMPL(){
        this.e_tNum = -1;
        this.e_Title = null;
        this.e_LName = null;
        this.e_FName = null;
        this.e_LogID = null;
        this.e_PWD = null;
        this.e_LoggedIn = false;
        this.e_Available = true;
        this.positionVacant = false;
    }

    /**
     * Employee Constructor
     * @param tn    Title number
     * @param t     Title
     * @param ln    Last name
     * @param fn    First name
     * @param lID   Login ID
     * @param p     Password
     */
    public EmployeeIMPL(int tn, String t, String ln, String fn, String lID, String p, boolean in, boolean a, boolean pa){
        this.e_tNum = tn;
        this.e_Title = t;
        this.e_LName = ln;
        this.e_FName = fn;
        this.e_LogID = lID;
        this.e_PWD = p;
        this.e_LoggedIn = in;
        this.e_Available = a;
        this.positionVacant = pa;
    }

    @Override
    public int getE_TitleNum() {return e_tNum;}

    @Override
    public void setE_TitleNum(int num) {this.e_tNum = num;}

    @Override
    public String getE_Title() {return e_Title;}

    @Override
    public void setE_Title(String t) {this.e_Title = t;}

    @Override
    public String getE_LastName() {return e_LName;}

    @Override
    public void setE_LastName(String ln) {this.e_LName = ln;}

    @Override
    public String getE_FirstName() {return e_FName;}

    @Override
    public void setE_FirstName(String fn) {this.e_FName = fn;}

    @Override
    public String getE_LogID() {return e_LogID;}

    @Override
    public void setE_LogID(String elID) {this.e_LogID = elID;}

    @Override
    public String getE_PWD() {return e_PWD;}

    @Override
    public void setE_PWD(String pwd) {this.e_PWD = pwd;}

    @Override
    public boolean checkE_LoginID(String eLog) {return eLog.equals(this.e_LogID);}

    @Override
    public boolean checkE_PWD(String ePWD) {return ePWD.equals(this.e_PWD);}

    @Override
    public boolean getE_LoggedIn() {return this.e_LoggedIn;}

    @Override
    public void setE_LoggedIn(boolean in) {this.e_LoggedIn = in;}

    @Override
    public boolean getE_Available() {return e_Available;}

    @Override
    public void setE_Available(boolean a) {this.e_Available = a;}

    @Override
    public boolean getPositionVacant() {return positionVacant;}

    @Override
    public void setPositionVacant(boolean v) {this.positionVacant = v;}

    @Override
    public String toString(){
        return String.format(this.e_tNum+" - "+this.e_Title+": \nEmployee Name: "+this.e_LName+", "+this.e_FName+"\nLogID: "+
                e_LogID+"  Pwd: "+e_PWD+"\nIs Logged In: "+e_LoggedIn+"\nIs Available: "+e_Available+"\nIs position vacant: "+positionVacant+"\n");
    }

}