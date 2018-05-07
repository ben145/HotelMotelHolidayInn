package edu.ithaca.bhamula1.hotel;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import edu.ithaca.bhamula1.hotel.Hotel;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

class ActiveRequest{
    String request;
    int roomNumber;
    String employeeId;
    boolean active;

    //constructor
    public ActiveRequest(String request, int roomNumber){
        this.request = request;
        this.roomNumber = roomNumber;
        employeeId = "";
        active = false;
    }

    //some getters with bad but very specific names so they're not confused with other getters
    public String getActiveRequest(){
        return request;
    }
    public int getRequestRoomNumber(){
        return roomNumber;
    }
    public String getActiveRequestEmployeeId(){
        return employeeId;
    }
    public boolean requestActive(){
        return active;
    }

    //setters with equally bad names
    public void setActiveRequest(String newRequest){
        request = newRequest;
    }
    public void setRequestRoomNumber(int newRoomNumber){
        roomNumber = newRoomNumber;
    }
    public void setActiveRequestEmployeeId(String newEmployeeId){
        employeeId = newEmployeeId;
    }
    public void activateRequest(){
        if(active){
            active = false;
        }
        else{
            active = true;
        }
    }
}
class RoomService{
    String request;
    double associatedPrice;
    int numRequirements;
    ArrayList<String> requirements;

    //constructor without requirement list
    public RoomService(String request, double associatedPrice, int numRequirements){
        this.request = request;
        this.associatedPrice = associatedPrice;
        this.numRequirements = numRequirements;
        if(numRequirements > 0){
            Scanner scanner = new Scanner(System.in);
            String input;
            for(int i=0; i<numRequirements; i++){
                System.out.println("Enter requirement " + (i+1) +": ");
                input = scanner.nextLine();
                requirements.add(input);
            }
        }
        else{
            requirements = null;
        }
    }

    //constructor with requirement list
    public RoomService(String request, double associatedPrice, int numRequirements, ArrayList<String> requirements){
        this.request = request;
        this.associatedPrice = associatedPrice;
        this.numRequirements = numRequirements;
        this.requirements = new ArrayList<String>(requirements);
    }

    //some getters
    public String getRequestName(){
        return request;
    }
    public double getAssociatedPrice(){
        return associatedPrice;
    }
    public int getNumRequirements(){
        return numRequirements;
    }
    public ArrayList getRequirements(){ return requirements; }

    //some setters
    public void setRequestName(String newRequest){
        request = newRequest;
    }
    public void setAssociatedPrice(double newPrice){ associatedPrice = newPrice; }
    public void addRequirement(String newRequirement){
        if (requirements == null) {
            this.requirements = new ArrayList<String>();
        }
        boolean found = false;
        for(Inventory item: Hotel.inventory) {

            if(item.getItem() == newRequirement){
                found = true;
                requirements.add(newRequirement);
                numRequirements++;
            }

        }
        if(!found){
            System.out.println(newRequirement + " not found in inventory. Please restock and try again.");
        }
    }
    public void removeRequirement(String toDelete){
        if((requirements != null) && (requirements.contains(toDelete))){
            requirements.remove(toDelete);
            numRequirements--;
            if(numRequirements == 0){
                requirements = null;
            }
        }
        else{
            System.out.println("requirement not found");
        }
    }
}
public class Requests implements RequestsInterface{
    public ArrayList<RoomService> requests = new ArrayList<RoomService>();

    public Requests(){
        loadRecs();
    }

    //some requests any guest can make
    public void loadRecs(){
        RoomService requestOption;
        ArrayList<String> requirements = new ArrayList<>();
        try {
            InputStream file = this.getClass().getResourceAsStream("/demands.txt");
            InputStreamReader read = new InputStreamReader(file);
            BufferedReader br = new BufferedReader(read);
            String line;

            while((line = br.readLine())!= null) {
                String [] sArr = line.split(",");
                int weSureUseThisNumberABunch = Integer.parseInt(sArr[2]);
                if(weSureUseThisNumberABunch == 0){
                    requestOption = new RoomService(sArr[0],Double.parseDouble(sArr[1]),weSureUseThisNumberABunch);
                }
                else{
                    for(int i=0; i< weSureUseThisNumberABunch; i++){
                        requirements.add(sArr[i+3]);
                    }
                    requestOption = new RoomService(sArr[0],Double.parseDouble(sArr[1]),weSureUseThisNumberABunch, requirements);
                }

                requests.add(requestOption);
            }
        }catch (IOException e) {
            System.out.println(e);
        }
    }

/*
this is where saveReqs() would go if i had the patience
or not; what's up
 */
    public void saveReqs(){
        try {
            OutputStream file = new FileOutputStream("./src/main/resources/demands.txt");
            OutputStreamWriter write = new OutputStreamWriter(file);
            BufferedWriter bw = new BufferedWriter(write);

            for(int i=0; i<requests.size(); i++){
                RoomService req = requests.get(i);
                String line;
                int onceAgainBoysAndGerms = req.getNumRequirements();
                if(onceAgainBoysAndGerms == 0) {
                    line = req.getRequestName() + "," + req.getAssociatedPrice() + "," + onceAgainBoysAndGerms;
                }
                else{

                    line = req.getRequestName() + "," + req.getAssociatedPrice() + "," + onceAgainBoysAndGerms;
                    ArrayList reqsArr = req.getRequirements();
                    for(int j=0; j<onceAgainBoysAndGerms; j++){
                        line += ",";
                        line += reqsArr.get(j);
                    }
                }
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
            bw.close();
        }catch (IOException e){
            System.err.println(e);
        }
    }
    //view the requests a customer can make
    public void viewRequests(){
        for(int i=0; i<requests.size();i++){
            System.out.println((i+1)+") "+requests.get(i).getRequestName());
        }
    }

    //allows authorized staff to add a request to they system
    public void addRequest(){
        ArrayList<String> reqs = new ArrayList<>();

        //check authorization
        //request name
        System.out.println("Enter request to add: ");
        Scanner scanner = new Scanner(System.in);
        String req = scanner.nextLine();
        //request price
        System.out.println("Enter additional charge associated with request: ");
        String input = scanner.nextLine();
        double price = Double.parseDouble(input);
        //request requirements
        System.out.println("Does request require inventory items? (y/n)");
        input = scanner.nextLine();
        int num = 0;
        if(input.equals("y")) {
            System.out.println("Enter number of Requirements: ");
            input = scanner.nextLine();
            num = Integer.parseInt(input);
        }

        RoomService newReq = new RoomService(req,price,num);
        //adds to current list
        requests.add(newReq);

        //reqs.add("because it's needed");
        //int num = requests.size()+1;
        //
        //System.out.println(" in adding requests ");
        //
        //System.out.println(" in adding requests "+requests.size());

        //adds to textfile
        try(FileWriter fw = new FileWriter("./src/main/resources/demands.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            String line;
            if(num == 0) {
                line = req + "," + price + "," + num;
            }
            else{
                line = req + "," + price + "," + num;
                ArrayList reqsArr = newReq.getRequirements();
                for(int i=0; i<num; i++){
                    line += ",";
                    line += reqsArr.get(i);
                }
            }
            out.println(line);
        }catch (IOException e){
            System.err.println(e);
        }
        System.out.println("Request added to options list");
    }

    //allows authorized staff to remove a request to the system
    public void removeRequest(){
        //check authorization

        //remove requests
        viewRequests();
        System.out.println("Enter request number to delete: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        requests.remove(input);

        System.out.println("Request removed from options list");
    }

    //allows a customer to make a request
    public String makeRequest(int roomNumber){
        doYouHearWhatIHear("src/main/resources/four_rooms_RoomService.wav");
        System.out.println("Enter request number: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        try {
            int inputInt = (Integer.parseInt(input));
            if(inputInt>0&&inputInt<=requests.size()) {
                //compensating for 0
                input = requests.get(inputInt-1).getRequestName();
                ActiveRequest newRequest = new ActiveRequest(input, roomNumber);
                Hotel.activeRequests.add(newRequest);

                System.out.println("Your request has been accepted and will be fulfilled as soon as possible");
                if(requests.get(inputInt-1).getNumRequirements() > 0){
                    for(int i=0;i<requests.get(inputInt-1).getNumRequirements(); i++){
                        for(int j=0;j<Hotel.inventory.size();j++){
                            if(Hotel.inventory.get(i).getItem() == requests.get(inputInt-1).getRequirements().get(i)){
                                Hotel.inventory.get(i).setQuantity(Hotel.inventory.get(i).getQuantity() - 1);
                            }
                        }
                    }
                }
                return input + "," + requests.get(inputInt-1).getAssociatedPrice();
            }
            else{
                System.out.println("Invalid Choice.");
            }
        }catch (NumberFormatException e){
            System.out.println("Invalid Input.");
        }
        return "";
    }

    /**
     * Finds first available request to fulfill (earliest among requests gets priority)
     *  and that is then assigned to employee upon entering their ID
     * @author Ben H.
     * @post chomething sanges
     */
    public void acceptRequest() {
        if (Hotel.activeRequests.size() > 0) {
            int i = 0;
            ActiveRequest actReq = Hotel.activeRequests.get(i);
            while (actReq.requestActive()) {
                i++;
                actReq = Hotel.activeRequests.get(i);
            }
            Scanner scan = new Scanner(System.in);
            boolean authorized = false;
            String acceptID = "";
            while (!authorized) {
                System.out.print("Enter your employee ID: ");
                String idInput = scan.nextLine();
                for (Employee e: Hotel.employees) {
                    if (e.getE_LogID().equals(idInput)) {
                        if (e.getE_Available()) {
                            authorized = true;
                            acceptID = e.getE_LogID();
                            e.setE_Available(false);
                            System.out.println("\nAuthorization successful.\n");
                        }
                    }
                }
                if (!authorized)
                    System.out.println("\nEmployee ID not recognized - please try again.\n");
            }
            System.out.println("\nYou have been assigned the next available request, "+actReq.getActiveRequest()+
                    " for Room "+actReq.getRequestRoomNumber()+".");
            // employee accepts request to fulfill, request is made activate, then removed as completed
            actReq.setActiveRequestEmployeeId(acceptID);
            boolean fulfilled = fulfillRequest(actReq);
            if (fulfilled)
                Hotel.activeRequests.remove(actReq);
            else
                System.out.println("Request has not been fulfilled. It remains to be completed.");
        }
        else
            System.out.println("Currently there are no requests to be fulfilled.");
    }

    /**
     * Attempt at fulfilling request is made, following acceptance of request by employee
     * @author Ben H.
     * @param actReq request to be completed
     * @return true if request is successfully completed, false otherwise
     */
    public boolean fulfillRequest(ActiveRequest actReq) {
        String requestItem = actReq.getActiveRequest();
        for (Inventory inv : Hotel.inventory) {
            if (inv.getItem().equals(requestItem)) {
                if (inv.getQuantity() > 0) {
                    actReq.activateRequest();
                    // subtract 1 instance from current count
                    inv.setQuantity(inv.getQuantity() - 1);
                    return true;
                }
            }
        }
        return false;
    }

    //i said i would take this function, and i have
    public void doYouHearWhatIHear(String wav){
        try
        {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(wav)));
            clip.start();
            while (!clip.isRunning())
                Thread.sleep(10);
            while (clip.isRunning())
                Thread.sleep(10);
            clip.close();
        }
        catch (Exception exc)
        {
            exc.printStackTrace(System.out);
        }
    }
}