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
    private boolean e_Available;
    private boolean positionVacant;


    /**
     * Default Constructor
     */
    public EmployeeIMPL(){
        this.e_tNum = -1;
        this.e_Title = null;
        this.e_LName = null;
        this.e_FName = null;
        this.e_LogID = null;
        this.e_PWD = null;
        this.e_Available = false;
        this.positionVacant = true;
    }

    /**
     * Constructor
     * @param tn
     * @param t
     * @param ln
     * @param fn
     * @param lID
     * @param p
     */
    public EmployeeIMPL(int tn, String t, String ln, String fn, String lID, String p){
        this.e_tNum = tn;
        this.e_Title = t;
        this.e_LName = ln;
        this.e_FName = fn;
        this.e_LogID = lID;
        this.e_PWD = p;
    }

    /**
     *
     * @return
     */
    @Override
    public int getE_TitleNum() {
        return e_tNum;
    }

    /**
     *
     * @param num
     */
    @Override
    public void setE_TitleNum(int num) {
        this.e_tNum = num;

    }

    /**
     *
     * @return
     */
    @Override
    public String getE_Title() {
        return e_Title;
    }

    /**
     *
     * @param t
     */
    @Override
    public void setE_Title(String t) {
        this.e_Title = t;
    }

    /**
     *
     * @return
     */
    @Override
    public String getE_LastName() {
        return e_LName;
    }

    /**
     *
     * @param ln
     */
    @Override
    public void setE_LastName(String ln) {
        this.e_LName = ln;
    }

    /**
     *
     * @return
     */
    @Override
    public String getE_FirstName() {
        return e_FName;
    }

    /**
     *
     * @param fn
     */
    @Override
    public void setE_FirstName(String fn) {
        this.e_FName = fn;
    }

    /**
     *
     * @return
     */
    @Override
    public String getE_LogID() {
        return e_LogID;
    }

    /**
     *
     * @param elID
     */
    @Override
    public void setE_LogID(String elID) {
        this.e_LogID = elID;
    }

    /**
     *
     * @return
     */
    @Override
    public String getE_PWD() {
        return e_PWD;
    }

    /**
     *
     * @param pwd
     */
    @Override
    public void setE_PWD(String pwd) {
        this.e_PWD = pwd;
    }

    /**
     *
     * @param eLog
     * @return
     */
    @Override
    public boolean checkE_LoginID(String eLog) {
        return eLog.equals(this.e_LogID);
    }

    /**
     *
     * @param ePWD
     * @return
     */
    @Override
    public boolean checkE_PWD(String ePWD) {
        return ePWD.equals(this.e_PWD);
    }

    /**
     *
     * @return
     */
    @Override
    public boolean getE_Available() {
        return e_Available;
    }

    /**
     *
     * @return
     */
    @Override
    public void setE_Available(boolean a) {
        this.e_Available = a;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean getPositionVacant(){
        return positionVacant;
    }

    /**
     *
     * @param v
     */
    @Override
    public void setPositionVacant(boolean v){
        this.positionVacant = v;
    }

    @Override
    public String toString(){
        return String.format(this.e_tNum+" - "+this.e_Title+": \n"+this.e_LName+", "+this.e_FName+"\nLogID: "+
                e_LogID+"  Pwd: "+e_PWD+"\nIs Available: "+e_Available+"\nIs position vacant: "+positionVacant+"\n");
    }
}
