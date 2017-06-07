import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;

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
            //create an object of the accountList class
            PlayerList playerList = new PlayerList();

            //variable for selected option
            String choice = ""; 

            //array to hold the different options
            String [] choices = {"Insert", "Sort", "Binary Search", "Load From File", "Write to File",  "Print", "Delete", "Quit"};

            while(true) //Infinite While Loop
            {
                    //Choose A Method To Test
                    choice = (String) JOptionPane.showInputDialog (null, "Choose A Method To Test", 
                                    "Job", JOptionPane.PLAIN_MESSAGE, null, choices, "Insert");

                    if (choice.equals("Quit")) //Quit
                    {
                    	break;
                    }
                    //Switch Case
                    switch(choice)
                    {
                    case "Insert": //Insert Information
                    {
                    	//variable for the record
                        String record = JOptionPane.showInputDialog(null, "Enter, <User Name>,<Wins>,<Losses>,<Win %> <Tokens>,"
                                        +  "Raman_2ESY, 5000, 25, 95, 15");

                        record += ";0;0";

                        //PlayerRecord Object
                        PlayerRecord testInfo = new PlayerRecord();

                        //separate record into different portions of data
                        testInfo.process(record);

                        //Insert If Space
                        if (!playerList.insert(testInfo))
                        {
                        	JOptionPane.showMessageDialog(null, "Not Added");
                        }
                        else
                        {
                        	JOptionPane.showMessageDialog(null, "Success");
                        }
                        break;
                    }
                    case "Print":
                    {
                    	//Text Area For Output
                        JTextArea display = new JTextArea(20,90);
                        JScrollPane scroller = new JScrollPane(display);
                        String text = "";

                        text += "Record #" + "\t" + "User Name" + "\t" + "Wins" + "\t" + "Losses" + "\t" + "Win %" + "\t" + "\n" + "Tokens";

                        //Loop Through And Display Info
                        for (int i = 0; i<playerList.getSize();i++)
                        {
                        	text += "Record #" + (i) + "\t" +  playerList.getList()[i].getUserName() + "\t" 
                                    + playerList.getList()[i].getUserName() + "\t" 
                                    + playerList.getList()[i].getWins() + "\t"
                                    + playerList.getList()[i].getLosses() + "\t"
                                    + playerList.getList()[i].getWinPercentage() + "\t"
                                    + playerList.getList()[i].getTokens() + "\n";
                        }

                        //set the text of the output to the text string and display editing
                        display.setText(text); 

                        //Set Size And Disable Editing
                        display.setTabSize(15);
                        display.setEditable(false);

                        //Display The Output
                        JOptionPane.showMessageDialog(null, scroller, null, JOptionPane.PLAIN_MESSAGE);
                        break;
                    }
                    case "Delete": //Delete
                    {
                    	//Sort In Alphabetical Order 
                        playerList.bubbleSortUser();

                        //Variable For Record
                        String record = JOptionPane.showInputDialog(null, "Enter, User Name>,<Wins>,<Losses>,<Win %>,"
                                        + "<Tokens>",
                                        "Raman_2ESY, 5000, 25, 95, 15");

                        //PlayerRecord Object
                        PlayerRecord testInfo = new PlayerRecord();

                        testInfo.process(record); //put it into array of data

                        if (!playerList.delete(testInfo))
                        {
                        	JOptionPane.showMessageDialog(null, "Item Not Found");
                        }
                        else
                        {
                        	JOptionPane.showMessageDialog(null, "Success");
                        }
                        break;
                    }
                    case "Sort": //Sort
                    {
                    	//Variable For Sort Order Choice
                        String chosenSortOrder = ""; 

                        //Variable For Sort Type Choice
                        String selectedSortType = ""; 

                        //Array For Sort Order Choices
                        String [] sortOrder = {"Top", "Bottom"};

                        //Array For Sort Type Choices
                        String [] sortType = {"Name", "Wins", "Losses", "Win Percentage", "Tokens"};

                        //Prompt For Sort Order
                        chosenSortOrder = (String) JOptionPane.showInputDialog (null, "Choose Sort Order", 
                                        "Sort Order", JOptionPane.PLAIN_MESSAGE, null, sortOrder, "Up");

                        //Prompt For Sort Type
                        selectedSortType = (String) JOptionPane.showInputDialog (null, "Choose Sort Type", 
                                        "Sort Type", JOptionPane.PLAIN_MESSAGE, null, sortType, "Name");

                        //Call insertionSort
                        playerList.insertionSort(chosenSortOrder, selectedSortType);

                        //Display Success Message
                        JOptionPane.showMessageDialog(null, "Insertion Sort Successful");
                        break;
                    }
                    case "Load From File": //Load From File
                    {
                    	//Variable For File Name 
                        String fileName = JOptionPane.showInputDialog(null, "Enter File Name To Read "
                                        + "From", "test.txt");

                        //Error Catching
                        try
                        {
                        	playerList.loadFile(fileName);
                            JOptionPane.showMessageDialog(null, "File Loaded Successfully");
                        }
                        catch (FileNotFoundException e)
                        {
                        	JOptionPane.showMessageDialog (null, "File Not Loaded Successfully");
                        }
                        catch (IOException e)
                        {
                        	JOptionPane.showMessageDialog (null, "File Corrupted");
                        }
                        catch (Exception e)
                        {
                        	JOptionPane.showMessageDialog (null, "Error");
                        }
                        break;
                    }
                    case "Binary Search": //Binary Search
                    {
                    	//Sort Alphabetically before Searching
                        playerList.bubbleSortUser();

                        //Variable For search Key
                        String searchKey = JOptionPane.showInputDialog(null, "Enter A User Name To Search For", "Raman_2ESY");

                        //Variable For Location Of Item
                        int location = playerList.binarySearchUser(searchKey);

                        if (location >=0)
                        {
                        	JOptionPane.showMessageDialog(null, searchKey + "Found At Location " + location + 
                                                "\nDetailed Information: " + playerList.getList()[location].toString());
                        }
                        else
                        {
                        	JOptionPane.showMessageDialog(null, searchKey + " Not Found. Try Again.");
                        }
                        break;
                    }
                    case "Write to File":
                    {
                    	//Variable For File name 
                        String fileName = JOptionPane.showInputDialog(null, "Enter The File You Want To Write To"
                                        ,"test.txt");

                        //Error Catching
                        try
                        {
                        	playerList.writeToFile(fileName);
                            JOptionPane.showMessageDialog(null, "File Written Successfully");
                        }
                        catch (FileNotFoundException e)
                        {
                        	JOptionPane.showMessageDialog (null, "File Not Loaded Successfully");
                        }
                        catch (IOException e)
                        {
                        	JOptionPane.showMessageDialog (null, "File Corrupted");
                        }
                        catch (Exception e)
                        {
                        	JOptionPane.showMessageDialog (null, "Error");
                        }
                        break;
                    }
                    }
            }//End While
            System.exit(0); //Exit
        } //End Main
} //End Program
