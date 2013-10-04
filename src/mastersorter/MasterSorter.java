/*
 * This program creates a file filled with random numbers. It then sorts it based on
 * the user's favourite sorting algorithm
 * I created this program to learn and practice sorting algorithms
 */
package mastersorter;

/**
 *
 * @author Raunaq
 */
import java.util.Scanner;
import java.io.*;

public class MasterSorter {
    private static final int EXIT =0;
    private static final int AMOUNT_OF_NUMBERS=100;
    private static final int MAX_NUMBER=100;
    private int[] numberHolder = new int[AMOUNT_OF_NUMBERS];
    
    public static void main(String[] args) {
        MasterSorter program = new MasterSorter();
        program.run();
    }
    public void run(){
        Scanner input = new Scanner(System.in); //Allows user to input information
        String unsortedFileName;
        //User inputs the name of the random file they wish to create
        System.out.println("Please enter the name of the file which you wish to create.");
        System.out.print("It will be populated with random integers. Make sure to include the file extension: ");
        
        unsortedFileName=input.nextLine();
        createFile(unsortedFileName);
        
        //Asks user to input in the sort they wish to do
        System.out.println("Please enter the type of sort you wish to happen.");
        System.out.println("1. Enter 1 for insertion sort.");
        System.out.println("2. Enter 2 for bubble sort.");
        System.out.println("3. Enter 3 for quick sort.");
        System.out.println("Enter "+EXIT+" to exit the program");
        
        //User inputs in their choice
        int userChoice= input.nextInt();
        boolean arraySorted = false;
        
        //Does the sort based on the user's choice
        switch(userChoice){
            
            case 1: 
                insertionSort(numberHolder); 
                arraySorted = true;
                break;
                
            case 2:
                bubbleSort(numberHolder);
                arraySorted = true;
                break;
                
            case 3:
                quickSort(0,numberHolder.length-1,numberHolder);
                arraySorted = true;
                break;
            
        } 
        //If the array is sorted, then the final file is written
        if(arraySorted){writeFinalFile();} 
       
       
    }
    
    
    private void createFile(String fileName){
        int randNum;
        try{
            //Creates the file to be written in to
            File file = new File(fileName);
            file.createNewFile();
            
            //Opens writing stream
            FileWriter out = new FileWriter(file);
            BufferedWriter writeFile = new BufferedWriter(out);
           
            //Writes into the file
            for(int i=0; i<AMOUNT_OF_NUMBERS;i++){
                //Generates a random number between 1 and the max number
                randNum = (int)(Math.random()*MAX_NUMBER+1);
                writeFile.write(Integer.toString(randNum));
                writeFile.newLine();
            }
            
            //Closes writing streams
            writeFile.close();
            out.close();
            
            //The program then reads a file into an array so it can sort
            FileReader in = new FileReader(file);
            BufferedReader readFile = new BufferedReader(in);
            
            //Reads the file
            for(int i=0; i<numberHolder.length;i++){
                //Reads the file and then parses it into an integer before storing it
                numberHolder[i]=Integer.parseInt(readFile.readLine());
            }
            readFile.readLine(); //Reads past end of line
            
            //Closes reading streams
            readFile.close();
            in.close();
            
        }catch (IOException e){
            System.err.println("Error: "+e.getMessage());
            
        }
    }
    //This method does an insertion sort of the array
    private void insertionSort(int[]arr){
        /**@param arr the array that needs to be sorted
         * 
         */
        int key, j;
        for(int i=1; i<arr.length; i++){
            key=arr[i]; //Sets the value of the key to the value at i
            j=i; //Sets the value of j to i
            while(j>0 && arr[j-1]>key){
                /*The sort first compares the value of the array at one item below j to the key
                 * if the value is larger, then the positions of the values of the array are swapped
                 * This continues until the loop iterates through the entire array
                 */
                arr[j]=arr[j-1];
                j--;
            }
            arr[j]=key;
        }
        
    }
    private void bubbleSort(int[] arr){
        /**@param arr The array that needs to be sorted
         * 
         */
        //Does a bubble sort of an array
        //It is not as efficient as insertion sort for a large list
        //But then again, insertion sort isn't that efficient for large lists either
        boolean isSwapped=true; //The flag is set to be initially true in order to complete the loop
        int tempHolder; //Temporary holds a number
                while(isSwapped){
                    isSwapped=false; //Set to false in order to continue the sort
                    /*The program cycles through the array and compares adjacent indices
                     * If the values in the indices are not in ascending order
                     * they are swapped.
                     */
                    for(int i=0; i<(arr.length-1); i++){
                        if(arr[i]>arr[i+1]){
                            tempHolder=arr[i+1];
                            arr[i+1]=arr[i];
                            arr[i]=tempHolder;
                            isSwapped=true;
                        }
                        
                    }
                }
    }
    private void quickSort(int low, int high, int[]arr){
      
    }
    private void writeFinalFile() {
       //The following method prints the sorted array into a final file called "sortedFile.txt"
        try{
            //Creates the file to be written in to
            File file = new File("sortedFile.txt");
            file.createNewFile();
            
            //Opens writing stream
            FileWriter out = new FileWriter(file);
            BufferedWriter writeFile = new BufferedWriter(out);
           
            //Writes into the file
            for(int i=0; i<numberHolder.length;i++){
                //Generates a random number between 1 and the max number
                writeFile.write(Integer.toString(numberHolder[i]));
                writeFile.newLine();
            }
            
            //Closes writing streams
            writeFile.close();
            out.close();
            
        }catch(IOException e){
            System.err.println("Error: "+e.getMessage());
        }
    }
}
