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
		maxSize = 50;
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
	public boolean insert (PlayerRecord record,boolean overwrite) 
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
		//get location of the record using the binary search method
		int location = binarySearchUser(record.getUserName());

		//swap the particular record with the last element
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
	//Method To Bubble Sort
	public void bubbleSortUser()
	{
		//Loop To Control Passes
		for (int j = 0; j< size - 1;j++)
		{
			for (int i = 0; i< size-j-1; i++)
			{
				if (playerRecordList[i].getUserName().compareToIgnoreCase(playerRecordList[i+1].getUserName())>0)
				{
					swap (i, i+1);
				}
			}
		}  
	}
	public void increment(PlayerRecord record,boolean win){
		if(win){
			playerRecordList[linearSearch(record.getUserName())].setWins(playerRecordList[linearSearch(record.getUserName())].getWins()+1);
		}
		else{
			playerRecordList[linearSearch(record.getUserName())].setLosses(playerRecordList[linearSearch(record.getUserName())].getLosses()+1);
		}
	}
	//Method To Ripple Sort (User Name)
	public void rippleSortUser()
	{
		for (int i = 0; i <= size -2;i++)
		{
			for (int j = i + 1; j <= size - 1; j++)
			{
				//Ascending Alphabetical Order
				if (playerRecordList[j].getUserName().compareToIgnoreCase(playerRecordList[i].getUserName())<0)
				{
					//Swap If Not In Order
					swap (j, i);
				}
			}
		}
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
	public void writeFile(){
		PrintWriter file;
		try {
			file = new PrintWriter(new FileWriter("Highscores.txt"));
			for (int i = 0; i < size; i++) {
				file.println(playerRecordList[i].toString());
			}
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int linearSearch (String searchKey)
	{
		for (int i = 0; i < size; i++) {
			if (playerRecordList[i].getUserName().equalsIgnoreCase(searchKey))
			{
				return i;
			} 
		}
		return -1; 
	}
	//Method To Load File
	public void loadFile(String fileName) throws IOException //method to load from file into VehicleList array
	{ 
		String playerRecord[]; //Declare String Array
		int lines = 0;

		// setting up the file reader to read the file
		BufferedReader file=new BufferedReader(new FileReader(fileName));

		// while there is info to read
		while(file.readLine() != null)
		{
			// increment the lines variable by 1
			lines++;
		}

		// print out the lines of data
		System.out.println(lines);

		// closing the file 
		file.close();

		FileReader fr = new FileReader (fileName);
		BufferedReader inputFile = new BufferedReader (fr);


		playerRecord = new String [lines]; //Declare & Create String Array

		//Loop Through Array 
		for (int j = 0; j < playerRecord.length; j++) 
		{
			//Process Each Record And Insert Into PlayerList Array
			playerRecord[j] = inputFile.readLine();
			PlayerRecord playerInfo = new PlayerRecord();
			playerInfo.process(playerRecord[j]);
			System.out.println(insert(playerInfo));
			System.out.println(playerRecordList[j].toString()+"\n"+size);
			
		}
		inputFile.close(); //Close Input File
	}
	//Self-Testing Main
	public static void main (String args[])
	{
		PlayerList plist=new PlayerList();
		try {
			plist.loadFile("Highscores.txt");
			System.out.println(plist.getList()[0].toString());
			System.out.println(plist.getList()[1].toString());
			System.out.println(plist.linearSearch("Boss"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} //End Main
} //End Program
