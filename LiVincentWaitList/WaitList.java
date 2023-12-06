import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * This is the WaitList Class
 *
 * @author Vincent Li
 * @version December 6, 2929
 */
public class WaitList
{
    private static Scanner scanner = new Scanner(System.in); 
    private File currFile;
    private static ArrayList <Customer> customerList = new ArrayList<>();
    private static String [] question1 = {"First Name: ", "Last Name: ","Party Size: ", "Special Needs: "};
    /**
     * Constructor for objects of class RestaurantManager
     */
    public WaitList()
    {
        
    }
    
    public static void main(){
        String [] reponse = new String[5]; 
        int pickNum;
        String yourReponse;
        boolean finished = false;
        loadFile();
        while(!finished){
            System.out.println("Select a number"); 
            System.out.println("1. Add Customer");
            System.out.println("6. Exit Program");
            
            pickNum = scanner.nextInt();
            if(pickNum == 1){
                for(int i = 1; i < question1.length; i++){
                    System.out.println(question1[i]);
                    yourReponse = scanner.nextLine();
                    reponse[i]= yourReponse;
                    System.out.println(yourReponse);
                }
                addCustomer(reponse);                
            }
            else if (pickNum == 6){
                finished = exit();
            }
        }
        
    }
    
    public static void loadFile(){
        try{
            System.out.println("Load new file: (Yes/No)");
            String choice = scanner.nextLine();
            if(choice.equals("Yes")){
                try{
                    System.out.println("Type the file name please: ");
                    String fileName = scanner.nextLine();
                    File currFile = new File(fileName);
                }
                catch(InputMismatchException e){
                    System.out.println("Please enter a String");
                }
            }
        }
        catch(InputMismatchException e){
            System.out.println("Please enter a String");
        }
    }
    
    public static void saveFile(String fileName, ArrayList <Customer> currList){
        try{
            FileWriter out = new FileWriter(fileName, true);
            PrintWriter output = new PrintWriter(out);
            
        }
        catch(IOException e){
            System.out.println("Exception caught e");
        }
    }
    public static void addCustomer(String [] array){
        String firstName = array[0];
        String lastName = array[1];
        int partySize = Integer.valueOf(array[2]);
        String specialNeeds = array[3];
        
        customerList.add(new Customer(firstName,lastName,partySize,specialNeeds));
    }
    public static boolean exit(){
        return true;
    }
}
