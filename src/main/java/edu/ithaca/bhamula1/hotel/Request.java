package edu.ithaca.bhamula1.hotel;

import java.util.ArrayList;
import java.util.List;

public class Request {
    private int roomNumber;
    private String employeeID;
    private String orderable;
    private boolean isTaken;
    private List<Request> madeRequests = new ArrayList<>();

    public Request(int rn, String eid, String o) {
        roomNumber = rn;
        employeeID = eid;
        orderable = o;
        isTaken = false;
    }

    public Request createRequest() {
        int roomNum = this.roomNumber;
        String employeeId = ""; // TODO: get specified employee to be assigned
        String order = "";
        Request newRequest = new Request(roomNum, employeeId, order);
        return newRequest;
    }

    public void addRequest() {
        madeRequests.add(this);
    }

}
