import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/*
 * Author: Raman Saini 
 * Date: May 2017
 * Desc: Player List Class For Pong
 * Method List;
 */

public class PlayerList {

        //Private Variable Deceleration 
        private int maxSize; //Max Size
        private int size; //Size
        private PlayerRecord playerRecordList []; //Player Record Array
      
        //Default Constructor
        public PlayerList() 
        {
        	maxSize = 1000;
            size = 0;
            playerRecordList = new PlayerRecord [maxSize];
        }
        //Constructor To Specify Max Size
        public PlayerList (int maxSize) 
        {
        	this.maxSize = maxSize;
            size = 0;
            playerRecordList = new PlayerRecord [maxSize];
        }
        //Method To Get Size
        public int getSize() 
        { 
        	return size;
        }
        //Method To Get Player Record List 
        public PlayerRecord[] getList() 
        {
        	return playerRecordList;
        }
        //Method To Set Max Size
        public void setMaxSize(int maxSize)
        {
        	this.maxSize = maxSize;
        }
        //Method To Set Size
        public void setSize(int size) 
        {
        	this.size = size;
        }
        //Method To Insert New Player Record
        public boolean insert (PlayerRecord record) 
        {
        	if (size<maxSize) //Check For Space
            {
        		size++;
                playerRecordList[size-1] = record;
                return true;
            }
            return false;
        }
        //Method To Delete Player Record
        public boolean delete (PlayerRecord record)
        {
        	//Get Location Of Record 
            int location = binarySearchUser(record.getUserName());

            //Swap With Last Element 
            if (location>=0)
            {
            	playerRecordList[location] = playerRecordList[size-1];
                size--;
                return true;
            }
            return false;   
        }
        //Method To Swap 
        public void swap (int first, int second) 
        {
        	PlayerRecord hold;
            hold = playerRecordList[first];
            playerRecordList[first] = playerRecordList[second]; //Swap Order
            playerRecordList[second] = hold;      
        }
        //Method To Bubble Sort (User Name)
        public void bubbleSortUser()
        {
            //Loop To Control Passes
            for (int j = 0; j < size - 1; j++)
            {
            	for (int i = 0; i < size-j-1; i++)
                {
                	if (playerRecordList[i].getUserName().compareToIgnoreCase(playerRecordList[i+1].getUserName())>0)
                    {
                    	swap (i, i+1);
                    }
                }
            }  
        }
        public void insertionSort (String order, String type) //method for insertion sort algorithm which sorts based on sort order and sort type
        {
                for (int j = 1; j<size; j++){
                        PlayerRecord playerTemp = playerRecordList[j]; 
                        int i = j;

                        //general functionality: checks previous element to see if greater or less than in alphabetical order and swaps
                        if (order.equalsIgnoreCase("Top")){ //Ascending Order
                                if (type.equalsIgnoreCase("User Name"))
                                {
                                	 while (i>0 && playerTemp.getUserName().compareToIgnoreCase(playerRecordList[i-1].getUserName()) < 0)
                                     {
                                		 playerRecordList[i] = playerRecordList[i-1];
                                         i--;
                                     }
                                     playerRecordList[i] = playerTemp; 
                                }
                                /*else if (type.equalsIgnoreCase("Wins"))
                                {
                                	while (i>0 && playerTemp.getWins().compareToIgnoreCase(playerRecordList[i-1].getWins()) < 0)
                                    {
                                    	playerRecordList[i] = playerRecordList[i-1];
                                        i--;
                                    }
                                    playerRecordList[i]=playerTemp;
                                }*/
                                else if (type.equalsIgnoreCase("Losses"))
                                {
                                	 while (i>0 && playerTemp.getLosses()<playerRecordList[i-1].getLosses())
                                     {
                                     	playerRecordList[i] = playerRecordList[i-1];
                                         i--;
                                     }
                                     playerRecordList[i]=playerTemp;
                                }
                                else if (type.equalsIgnoreCase("Win %"))
                                {
                                	while (i>0 && playerTemp.getWinPercentage()<playerRecordList[i-1].getWinPercentage())
                                    {
                                		playerRecordList[i] = playerRecordList[i-1];
                                        i--;
                                    }
                                    playerRecordList[i]=playerTemp;
                                }
                        }
                        else{ //Descending Order
                                if (type.equalsIgnoreCase("User Name"))
                                {
                                	while (i>0 && playerTemp.getUserName().compareToIgnoreCase(playerRecordList[i-1].getUserName()) > 0)
                                    {
                                		playerRecordList[i] = playerRecordList[i-1];
                                        i--;
                                    }
                                    playerRecordList[i]=playerTemp;
                                }
                               /* else if (type.equalsIgnoreCase("Wins"))
                                {
                                	while (i>0 && playerTemp.getWins().compareToIgnoreCase(playerRecordList[i-1].getWins()) > 0)
                                    {
                                    	playerRecordList[i] = playerRecordList[i-1];
                                        i--;
                                    }
                                    playerRecordList[i]=playerTemp;
                                }*/
                                else if (type.equalsIgnoreCase("Losses"))
                                {
                                	while (i>0 && playerTemp.getLosses()>playerRecordList[i-1].getLosses())
                                    {
                                		playerRecordList[i] = playerRecordList[i-1];
                                        i--;
                                    }
                                    playerRecordList[i]=playerTemp;
                                }
                                else if (type.equalsIgnoreCase("Win %"))
                                {
                                	while (i>0 && playerTemp.getWinPercentage()>playerRecordList[i-1].getWinPercentage())
                                    {
                                    	playerRecordList[i] = playerRecordList[i-1];
                                        i--;
                                    }
                                    playerRecordList[i]=playerTemp;
                                }
                        }//End Else
                }//End For
        }
        //Method For Binary Search (UserName)
        public int binarySearchUser(String searchKey)
        {
        	//Set Bottom And Top Of Array 
            int bottom = 0, top = size-1, middle;

            while (bottom<=top) 
            {
            	 middle = (top+bottom)/2; //Divide Array By 2
                 if (searchKey.equalsIgnoreCase(playerRecordList[middle].getUserName()))
                 {
                 	return middle; //Return Location
                 }
                 else if (searchKey.compareToIgnoreCase(playerRecordList[middle].getUserName())<0) //Narrow Search
                 {
                 	top = middle-1; 
                 }
                 else
                 {
                 	bottom = middle+1;
                 }
            }
            return -1; //If Nothing Found
        }
        //Method To Load File
        public void loadFile(String fileName) throws IOException //method to load from file into VehicleList array
        { 
                String playerRecord[]; //Declare String Array
  
                FileReader fr = new FileReader (fileName);
                BufferedReader inputFile = new BufferedReader (fr);

                int size = Integer.parseInt(inputFile.readLine()); //Get Size Of Array

                playerRecord = new String [size]; //Declare & Create String Array

                //Loop Through Array 
                for (int j = 0; j < playerRecord.length; j++) 
                {
                	 //Process Each Record And Insert Into PlayerList Array
                	 playerRecord[j] = inputFile.readLine();
                	 PlayerRecord playerInfo = new PlayerRecord();
                     playerInfo.process(playerRecord[j]);
                     insert(playerInfo);
                }
                inputFile.close(); //Close Input File
        }
        //Method To Write To File
        public void writeToFile (String fileName) throws IOException 
        {
        	//Open File To Read
            FileWriter file = new FileWriter (fileName);
            PrintWriter outputFile = new PrintWriter (file); 

            //Loop Through Array 
            for (int i = 0; i < size; i++) 
            {
            	outputFile.println(playerRecordList[i]);
            }
            outputFile.close(); //close input file
        }
        //Self-Testing Main
        static void main (String args[])
        {
        	
        } //End Main
} //End Program
