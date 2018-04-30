package edu.ithaca.bhamula1.hotel;

import java.io.*;
import java.util.Scanner;
// + 74 minutes

public class Rating {

    private int rating1;
    private int rating2;
    private int rating3;
    private int rating4;
    private int rating5;
    private String comments;

    private static final double NUM_QUESTIONS = 5.0;
    //review/rate stay on checkout (star system) optional survey
    public Rating() {
        rating1 = -1;
        rating2 = -1;
        rating3 = -1;
        rating4 = -1;
        rating5 = -1;
        comments = "";
    }

    public double getAvgRating() {
        return getRatingTotal() / NUM_QUESTIONS;
    }
    public int getRating1() {
        return rating1;
    }
    public int getRating2() {
        return rating2;
    }
    public int getRating3() {
        return rating3;
    }
    public int getRating4() {
        return rating4;
    }
    public int getRating5() {
        return rating5;
    }
    public int getRatingTotal() {
        return rating1 + rating2 + rating3 + rating4 + rating5;
    }
    public void setRating1(int rating) {
        rating1 = rating;
    }
    public void setRating2(int rating) {
        rating2 = rating;
    }
    public void setRating3(int rating) {
        rating3 = rating;
    }
    public void setRating4(int rating) {
        rating4 = rating;
    }
    public void setRating5(int rating) {
        rating5 = rating;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Prompts user again for input if needed by convertRating based
     *      on poor input
     */
    private void askAgain() {
        Scanner s = new Scanner(System.in);
        System.out.print("Your answer -> ");
        convertRating(s.nextLine());
    }

    /**
     * Takes in user input parameter and outputs message based on "format" of inpu
     *      according to number of *'s
     * @param rawRating String passed in intended to contain *'s
     * @return int value of number of *'s in rawRating
     */
    private int convertRating(String rawRating) {

        int evalInt = 0;
        if (rawRating.contains("*")) {
            for (int i = 0; i < rawRating.length(); i++) {
                if (rawRating.substring(i,i+1).equals("*"))
                    evalInt++;
            }
            if (evalInt > 5) {
                System.out.println("Too many *'s were entered (max. 5); please try again.\n");
                askAgain();
            }
            else
                System.out.println("(Rating of "+evalInt+" recorded.)"); //\nThank you for your time! We appreciate your input.\n");
        }
        else {
            System.out.println("No *'s were found in your rating; please try again.\n");
            askAgain();
        }
        return evalInt;
    }

    public String createComments() {
        Scanner s = new Scanner(System.in);
        System.out.println("\nIf there is anything you would like to say about your experience with us, please do so now.");
        String comment = s.nextLine();
        comment = "\"" + comment + "\"";
        setComments(comment);
        return comment;
    }

    public void startRating() {
        System.out.println("Thank you for choosing to complete this optional survey.");
        System.out.println("We use our customer responses to improve future experiences.");
        System.out.println("\nFor the following questions, please respond with a response of zero (0) to five (5) stars (*)");
        System.out.println("(If you wish to give a '0' as your rating, enter any non-* character and press 'Enter')");
        ratingSuite();
    }

    public void ratingSuite() {
        Scanner s = new Scanner(System.in);
        System.out.print("\n1. Satisfaction with reserved room's amenities.\nYour answer -> ");
        setRating1(convertRating(s.nextLine()));
        System.out.print("\n2. Satisfaction with employee interactions.\nYour answer -> ");
        setRating2(convertRating(s.nextLine()));
        System.out.print("\n3. Satisfaction with range of room choices.\nYour answer -> ");
        setRating3(convertRating(s.nextLine()));
        System.out.print("\n4. Satisfaction with ease of setting reservation date.\nYour answer -> ");
        setRating4(convertRating(s.nextLine()));
        System.out.print("\n5. Satisfaction with overall stay.\nYour answer -> ");
        setRating5(convertRating(s.nextLine()));
        createComments();
    }


    public String toString(CustomerInterface c) {

        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < Math.round(getAvgRating()); i++) {
            stars.append("*");
        }
        String toReturn = "\nCustomer:\t"+c.getName()+"\nRating:\t\t"+stars;
        toReturn += " ("+Double.toString(getAvgRating())+"/"+NUM_QUESTIONS+")\nComments:\t"+getComments();
        return toReturn;
    }


    public void saveRating(CustomerInterface c) {

        try {
            OutputStream file = new FileOutputStream("./src/main/resources/r.txt",true);
            OutputStreamWriter write = new OutputStreamWriter(file);
            BufferedWriter bw = new BufferedWriter(write);

            String line = c.getFName()+" "+c.getLName()+"\n"+getRating1()+" "+getRating2()+" "+
                    getRating3()+" "+getRating4()+" "+getRating5()+"\n"+getComments();
            bw.write(line);
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        Rating r = new Rating();
        r.startRating();
        Customer c = new Customer("John","Smith","JS12345",4,false);
        System.out.println(r.toString(c));
        r.saveRating(c);
    }

}

