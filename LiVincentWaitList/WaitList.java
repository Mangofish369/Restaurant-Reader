import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
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
    private static StringTokenizer tokenizer;
    private static File currFile;
    private static ArrayList <Customer> customerList = new ArrayList<>();
    
    /**
     * Constructor for objects of class RestaurantManager
     */
    public WaitList()
    {
        
    }
    
    public static void main(){
        int pickNum;
        boolean finished = false;
        
        loadFile();
        
        
        while(!finished){
            System.out.println("Select a number"); 
            System.out.println("1. Add Customer");
            System.out.println("2. Remove Customer");
            System.out.println("3. View List");
            System.out.println("4. Clear Current List");
            System.out.println("5. Save List");
            System.out.println("6. Exit Program");
            
            pickNum = scanner.nextInt();
            if(pickNum == 1){
                addCustomer();                
            }
            else if (pickNum == 2){
                customerList.remove(0);
            }
            else if (pickNum == 3){
                viewList();
            }
            else if (pickNum == 4){
                customerList.clear();
            }
            
            else if(pickNum == 5){
                saveFile(customerList);
            }
            else if (pickNum == 6){
                finished = exit();
            }
        }
        
    }
    
    public static void loadFile(){
        boolean finished = false;
        while(!finished){
            try{
                System.out.println("Load new file: (Yes/No)");
                String choice = scanner.nextLine();
                if(choice.equals("Yes")){
                    try{
                        System.out.println("Type the file name please: ");
                        String fileName = scanner.nextLine();
                        currFile = new File(fileName);
                        
                        
                        finished = readFile();
                    }
                    catch(InputMismatchException e){
                        System.out.println("Please enter a String");
                        
                    }
                }
                if(choice.equals("No")){
                    finished = true;
                }
            }
            catch(InputMismatchException e){
                System.out.println("Please enter a String");
            }
        }
    }
    
    public static void saveFile(ArrayList <Customer> currList){
        Scanner input = new Scanner(System.in);
        try{
            System.out.println("Enter your choosen file name: ");
            String fileName = input.nextLine();
            try{
                FileWriter out = new FileWriter(fileName, true);
                PrintWriter writer = new PrintWriter(out);
                for(int i = 0; i< currList.size(); i++){
                    writer.println(currList.get(i).toString());
                }
                writer.close();
            }
            catch(IOException e){
                System.out.println("Exception caught e");
            }
        }
        catch(InputMismatchException e){
            System.out.println("Error, please enter a string");
        }
    }
    
    /**
     * Method to create Customer Object without knowing any details
     * Ask the user for details about the Customer
     */
    public static void addCustomer(){
        Scanner input = new Scanner(System.in);
        String [] questions = {"First Name: ", "Last Name: ","Party Size: ", "Special Needs: "};
        String [] details = new String[5];
        
        try{
            for(int i = 0; i < questions.length; i++){
                System.out.println(questions[i]);
                String ans = input.nextLine();
                details[i] = ans; 
            }
            
            String firstName = details[0];
            String lastName = details[1];
            int partySize = Integer.valueOf(details[2]);
            String specialNeeds = details[3];
            
            customerList.add(new Customer(firstName,lastName,partySize,specialNeeds));
        }
        catch(InputMismatchException e){
            System.out.println("Error: "+e);
        }
    }
    
    /**
     * Method to create a Customer if details of the Customer is known
     * - Grab the details of the Customer and create the Object
     */
    public static void addCustomer(String [] details){
        String firstName = details[0];
        String lastName = details[1];
        int partySize = Integer.valueOf(details[2]);
        String specialNeeds = details[3]; 
        
        customerList.add(new Customer(firstName,lastName,partySize,specialNeeds));
    }
    
    public static void viewList(){
        for(Customer c : customerList){
            System.out.println(c);
        }
    }
    
    public static boolean readFile(){
        Scanner s; 
        boolean moreLines = true;
        try{
            s = new Scanner(currFile);
            while(moreLines){
                try{
                    readLine(s.nextLine());
                    
                }
                catch (NoSuchElementException e){
                    moreLines = false;
                }
            }
            return true;
        }
        catch (FileNotFoundException e){
            System.out.println("Error file not found");
            return false;
        }
    }
    
    public static void readLine(String s){
        System.out.println(s);
        String [] details = new String [5];
        tokenizer = new StringTokenizer(s, ",");
        int index = 0;
        while(tokenizer.hasMoreTokens()){
            details[index] = tokenizer.nextToken();
            index++; 
        }
        addCustomer(details);
    }
    
    public static boolean exit(){
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to save your current list: (Yes/No)");
        String ans = input.nextLine();
        
        if(ans.equals("Yes")){
            saveFile(customerList);
        }
        return true;
    }
}
