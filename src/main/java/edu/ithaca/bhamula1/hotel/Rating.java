package edu.ithaca.bhamula1.hotel;

import java.util.Scanner;

public class Rating {

    private int rating;
    private String comments;

    //review/rate stay on checkout (star system) optional survey
    public Rating() {
        rating = -1;
        comments = "";
    }

    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }

    public int convertRating() {
        Scanner s = new Scanner(System.in);
        System.out.print("Please enter your rating using our patented *-rating system: ");
        String eval = s.next();
        int evalInt = 0;
        if (eval.contains("*")) {
            for (int i = 0; i < eval.length(); i++) {
                if (eval.substring(i,i+1).equals("*"))
                    evalInt++;
            }
            if (evalInt > 5) {
                System.out.println("Too many *'s were entered (max. 5); please try again.\n");
                convertRating();
            }
            else {
                System.out.println("(Rating of "+evalInt+" recorded.)\nThank you for your time! We appreciate your input.");
            }
        }
        else {
            System.out.println("No *'s were found in your rating; please try again.\n");
            convertRating();
        }
        return evalInt;
    }

    public static void main(String[] args) {
        Rating r = new Rating();
        r.convertRating();
    }
}