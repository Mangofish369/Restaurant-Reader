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
 * @version December 6, 2023
 */
public class WaitList
{
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
        Scanner input = new Scanner (System.in);
        int pickNum;
        boolean finished = false;
        
        loadFile();
        
        // Main loop runs until user chooses to exit
        while(!finished){
            // Print out options for user 
            System.out.println("\n");
            System.out.println("Select a number"); 
            System.out.println("1. Add Customer");
            System.out.println("2. Remove Customer");
            System.out.println("3. View List");
            System.out.println("4. Clear Current List");
            System.out.println("5. Save List");
            System.out.println("6. Load List");
            System.out.println("7. Exit Program");
            
            pickNum = input.nextInt();
            if(pickNum == 1){
                addCustomer();                
            }
            else if (pickNum == 2){
                removeCustomer();
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
                loadFile();
            }
            else if (pickNum == 7){
                finished = exit();
            }
        }
    }   
    
    /**
     * Method to load file from storage to RAM
     */
    public static void loadFile(){
        Scanner input = new Scanner (System.in);
        boolean finished = false;
        
        // If the file fails to load, keep asking the user if they want to try again
        while(!finished){
            try{
                System.out.println("Load new file: (Yes/No)");
                String choice = input.nextLine();
                if(choice.equals("Yes")){
                    try{
                        System.out.println("Type the file name please: ");
                        String fileName = input.nextLine();
                        
                        // Check that the file name meets the regex requriements, 
                        //to prevent users from accessing other directories
                        if(fileName.contains("#%&{}\\<>*?/ $!\":@+`|=")){
                            System.out.println("Invalid file name");
                            return;
                        }
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
    
    /**
     * Convert ArrayList into a file, move from RAM to storage
     */
    public static void saveFile(ArrayList <Customer> currList){
        Scanner input = new Scanner(System.in);
        try{
            System.out.println("Enter your choosen file name: ");
            String fileName = input.nextLine();
            
            // Prevent file naming of illegal windows characters
            if(fileName.contains("#%&{}\\<>*?/ $!\":@+`|=")){
                System.out.println("Invalid file name");
                return;
            }
            
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
        String [] questions = {"First Name: ", "Last Name: ","Party Size: ", "Special Needs: "}; // Store the questions in a loop
        String [] details = new String[5];
        
        try{
            // Iterate through the loop of questions to save lines of code
            for(int i = 0; i < questions.length; i++){
                System.out.println(questions[i]);
                String ans = input.nextLine();
                details[i] = ans; 
            }
            
            // Convert answer into variables that can be added to the Customer.class parameters
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
     * Simple Constructor for Customer, used to convert file information into Customer Objects
     */
    public static void addCustomer(String [] details){
        // Convert String array into usable variables for Customer constructor
        String firstName = details[0];
        String lastName = details[1];
        int partySize = 0;
        try{
            partySize = Integer.valueOf(details[2]);
        }
        catch (NumberFormatException e){
            
        }
        String specialNeeds = details[3]; 
        
        customerList.add(new Customer(firstName,lastName,partySize,specialNeeds));
    }
    
    /**
     * Method to remove Customers from the ArrayList at any index
     */
    public static void removeCustomer(){
        Scanner input = new Scanner (System.in);
        viewList(); // Print out the current list, so the user can see which customer to remove (to be seated)
        System.out.println("\n");
        try{
            System.out.println("Which customer do you want to remove? (List Number)");
            int index = input.nextInt() - 1; // Real life Lists start at 1, java lists start at 0
            
            try{
                customerList.remove(index);
            }
            catch(IndexOutOfBoundsException e){
                System.out.println("That was not a valid number"); 
            }
        }
        catch (InputMismatchException e){
            System.out.println("Please enter an integer");
        }
        
    }
    
    /**
     * Loop through current list of customers and print them out in numeric order
     */
    public static void viewList(){
        int count = 1; //Real life lists start at index 1
        
        System.out.println("\n");
        if(customerList.isEmpty()){
            System.out.println("Customer list is empty");
        }
        else{
            for(Customer c : customerList){
                System.out.println(count + ": "+c); //Print the number in line + customer details
                count ++;
            }
        }
    }
    
    /**
     * Loop through the file and output each line as a String
     */
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
    
    /**
     * Get a string input, use string tokenizer to break up the string input into a String array
     */
    public static void readLine(String s){
        String [] details = new String [5];
        tokenizer = new StringTokenizer(s, ",");
        int index = 0   ;
        while(tokenizer.hasMoreTokens()){
            details[index] = tokenizer.nextToken();
            index++; 
        }
        addCustomer(details);
    }
    
    /**
     * Method to exit program, ask the user if they want to save their file before exiting
     */
    public static boolean exit(){
        Scanner input = new Scanner(System.in);
        System.out.println("\n");
        System.out.println("Would you like to save your current list: (Yes/No)");
        String ans = input.nextLine();
        
        if(ans.equals("Yes")){
            saveFile(customerList);
        }
        return true;
    }
}
