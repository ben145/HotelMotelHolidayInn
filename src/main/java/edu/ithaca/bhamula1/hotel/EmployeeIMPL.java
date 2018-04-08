package edu.ithaca.bhamula1.hotel;

/**
 * EmployeeIMPL Class
 * implements employee interface
 * @author DMF 4/7/18
 */
public class EmployeeIMPL implements Employee{

    private int e_Role;
    private String e_Title;
    private String e_LName;
    private String e_FName;
    private String e_LogID;
    private String e_PWD;


    /**
     * Default Constructor
     */
    public EmployeeIMPL(){
        this.e_Role = -1;
        this.e_Title = null;
        this.e_LName = null;
        this.e_FName = null;
        this.e_LogID = null;
        this.e_PWD = null;
    }

    /**
     * Constructor
     * @param r
     * @param t
     * @param ln
     * @param fn
     * @param lID
     * @param p
     */
    public EmployeeIMPL(int r, String t, String ln, String fn, String lID, String p){
        this.e_Role = r;
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
    public int getRoleNum() {
        return 0;
    }

    /**
     *
     * @param num
     */
    @Override
    public void setRoleNum(int num) {

    }

    /**
     *
     * @return
     */
    @Override
    public String getRoleClass() {
        return null;
    }

    /**
     *
     * @param c
     */
    @Override
    public void setRoleClass(String c) {

    }

    /**
     *
     * @return
     */
    @Override
    public String getE_LastName() {
        return null;
    }

    /**
     *
     * @param ln
     */
    @Override
    public void setELastName(String ln) {

    }

    /**
     *
     * @return
     */
    @Override
    public String getE_FirstName() {
        return null;
    }

    /**
     *
     * @param fn
     */
    @Override
    public void setEFirstName(String fn) {

    }

    /**
     *
     * @return
     */
    @Override
    public String getE_LogID() {
        return null;
    }

    /**
     *
     * @param elID
     */
    @Override
    public void setE_LogID(String elID) {

    }

    /**
     *
     * @return
     */
    @Override
    public String getE_PWD() {
        return null;
    }

    /**
     *
     * @param pwd
     */
    @Override
    public void setE_PWD(String pwd) {

    }

    /**
     *
     * @param eLog
     * @return
     */
    @Override
    public boolean checkE_LoginID(String eLog) {
        return false;
    }

    /**
     *
     * @param ePWD
     * @return
     */
    @Override
    public boolean checkE_PWD(String ePWD) {
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean getE_Available() {
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean setE_Available() {
        return false;
    }
}
