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
        System.out.println("4. Enter 4 for selection sort.");
        System.out.println("5. Enter 5 for merge sort.");
        System.out.println("6. Enter 6 for shell sort.");
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
             case 4:
            	selectionSort(numberHolder);
            	arraySorted = true;
            	break;
            case 5:
            	mergeSort(numberHolder);
            	arraySorted = true;
            	break;
            case 6:
            	shellSort(numberHolder);
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
    
    private void quickSort(int[] arr){//randomly selects elements and places them to the left or right of the array depending on if its larger or smaller than the size that its being compared to
    	int lenD = arr.length;
    	int pivot = 0;
    	int ind = lenD/2;//middle of the array (or near it in case of odd length)
    	int i,j = 0,k = 0; //temporary integers, j is length of L array, k is length of R array
    	if(lenD>=2){//if the size of the array is one, there is no point is sorting it
    	  int[] L = new int[lenD];// two empty arrays half the size of the one being sorted
    	  int[] R = new int[lenD];//Represents left and right arrays
    	  int[] sorted = new int[lenD];
    	  pivot = arr[ind];//value to be compared
    	  for(i=0;i<lenD;i++){
    	    if(i!=ind){
    	      if(arr[i]<pivot){
    	        L[j] = arr[i];//sets it equal to earlier in the array
    	        j++;//keep track of L array
    	      }
    	      else{
    	        R[k] = arr[i];//sets it equal to later in the array
    	        k++;//keeps track of length of R array
    	      }
    	    }
    	  }
    	  int[] sortedL = new int[j];
    	  int[] sortedR = new int[k];
    	  System.arraycopy(L, 0, sortedL, 0, j);//transfers all elements from L to sortedL
    	  System.arraycopy(R, 0, sortedR, 0, k);//transfers all elements from R to sortedR
    	  quickSort(sortedL);//further quick sorts the left array
    	  quickSort(sortedR);//further quick sorts the right array
    	  System.arraycopy(sortedL, 0, sorted, 0, j);//first half of array added to sorted
    	  sorted[j] = pivot;//middle value
    	  System.arraycopy(sortedR, 0, sorted, j+1, k);//second half of array added to sorted
    	  //essentially, the array arr[] is being reconstructed by having its values changed starting from both ends of the array to the middle
    	  arr = sorted;//sets arr equal to sorted array
    	}
    }
    
    public void mergeSort(int[] arr){
    	  int lenD = arr.length;
    	  if(lenD>1){//array of size one does not need to be sorted
    	    int[] sorted = new int[lenD];
    	    int middle = lenD/2;
    	    int rem = lenD-middle;
    	    int[] L = new int[middle];
    	    int[] R = new int[rem];
    	    System.arraycopy(arr, 0, L, 0, middle);//sets array that is equal to left half of array that is being sorted
    	    System.arraycopy(arr, middle, R, 0, rem);//sets array that is equal to right half of array that is being sorted
    	    this.mergeSort(L);//sorts each array separately (can be done using any other sorting method as well)
    	    this.mergeSort(R);
    	    sorted = merge(L, R);//combines left and right array
    	    sorted = arr;//sets array that is sorted to array that user will work with
    	  }
    	}
    	 
    	public int[] merge(int[] L, int[] R){
    	  int lenL = L.length;
    	  int lenR = R.length;
    	  int[] merged = new int[lenL+lenR];
    	  int i = 0;
    	  int j = 0;
    	  while(i<lenL||j<lenR){
    	    if(i<lenL & j<lenR){
    	      if(L[i]<=R[j]){
    	        merged[i+j] = L[i];
    	        i++;//starts sorting from the left of the array
    	      }
    	      else{
    	        merged[i+j] = R[j];
    	        j++;//starts sorting from the right of the array
    	      }
    	    }
    	    else if(i<lenL){
    	      merged[i+j] = L[i];//in case of right side being sorted before left side
    	      i++;
    	    }
    	    else if(j<lenR){
    	      merged[i+j] = R[j];//in case of left side being sorted before right side
    	      j++;
    	     }
    	   }
    	   return merged;
    	}
    
    public void shellSort(int[] arr){//basically its a better, more generalised version of insertions sort
    		int lenD = arr.length;
    		int inc = lenD/2;//set in the middle of the array
    		while(inc>0){
    			for(int i=inc;i<lenD;i++){//loops from mid to end
    				int tmp = arr[i];//temporary
    		        int j = i;
    		        	while(j>=inc && arr[j-inc]>tmp){//Comparison
    		        		arr[j] = arr[j-inc];
    		        		j = j-inc;
    		        	}
    		        arr[j] = tmp;//swapped
    		    }
    		inc = (inc /2);//set back from original position
    		}
    	}
}
