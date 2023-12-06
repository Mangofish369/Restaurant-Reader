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
    /**
     * Constructor for objects of class RestaurantManager
     */
    public WaitList()
    {
        
    }
    public void act(){
        int yourChoice;
        boolean finished = false;
        loadFile();
        while(!finished){
            System.out.println("Select a number"); 
            System.out.println("1. Add Customer");
            System.out.println("6. Exit Program");
            
            yourChoice = scanner.nextInt();
            if(yourChoice == 1){
                
            }
            else if (yourChoice == 6){
                exit(finished);
            }
        }
        
    }
    
    public void loadFile(){
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
    
    public void saveFile(String fileName, ArrayList <Customer> currList){
        try{
            FileWriter out = new FileWriter(fileName, true);
            PrintWriter output = new PrintWriter(out);
            
        }
        catch(IOException e){
            System.out.println("Exception caught e");
        }
    }
    public void createCustomer(String input){
        StringTokenizer tokenizer = new StringTokenizer(input);
        
    }
    public void exit(boolean isFinished){
        isFinished = true;
    }
}
