package edu.ithaca.bhamula1.hotel;

public class CustomerUI implements CustomerUIInterface {

    public CustomerUI(){

    }

    @Override
    public void run() {

    }

    /***
     * Take in a string of input from the user and upper and lower bounds of what number the input should be and
     * output a 0 if the input was invalid and an int betweeb lowestChoice and highestChoice if it was valid
     * @param input String input from the user to be checked for validity
     * @param lowestChoice lowest possible option to be selected in the part of the menu
     * @param higestChoice highest number option to be selected in the part of the menu
     * @return 0 if the entry was invalid and the int version of the input if the input was valid
     */
    public int checkChoiceInput(String input, int lowestChoice, int higestChoice){
        try{
            int choice = Integer.parseInt(input);
            if(choice>=lowestChoice&&choice<=higestChoice){
                return choice;
            }
            else{
                return 0;
            }
        }catch(NumberFormatException e){
            System.out.println("Invalid input.");
            return 0;
        }
    }
}
