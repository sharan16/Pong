/*
 * Author: Raman Saini 
 * Date: May 2017
 * Desc: PlayerRecord Class For Pong
 * Method List:
 */
public class PlayerRecord {
	
	private String userName, passWord;
	private int wins, losses, tokens;
	private double winPercentage;
	
	//Method To Set User Name
	public void setUserName (String userName)
	{
		this.userName = userName;
	}
	//Method To Set Pass Word 
	public void setPassWord (String passWord)
	{
		this.passWord = passWord;
	}
	//Method To Set Wins
	public void setWins (int wins)
	{
		this.wins = wins;
	}
	//Method To Set Wins
	public void setLosses (int losses)
	{
		this.losses = losses;
	}
	//Method To Set Win Percentage
	public void setWinPercentage (int winPercentage)
	{
		this.winPercentage = winPercentage;
	}
	//Method To Set Tokens 
	public void setTokens (int tokens)
	{
		this.tokens = tokens;
	}
	//Method To Get User Name 
	public String getUserName()
	{
		return userName;
	}
	//Method To Get Pass Word
	public String getPassWord()
	{
		return passWord;
	}
	//Method To Get Wins 
	public int getWins()
	{
		return wins;
	}
	public int getLosses()
	{
		return losses;
	}
	public double getWinPercentage()
	{
		return winPercentage;
	}
	//Method To Get Tokens 
	public int getTokens()
	{
		return tokens;
	}
	//Method To Process Record
	public void process(String record)
	{
		String word [];
        word = record.split(",");

        this.userName = word [0];
        this.passWord = word [1];
        this.wins = Integer.parseInt(word [2]);
        this.losses = Integer.parseInt(word [3]);
        this.winPercentage = Double.parseDouble(word [4]);
        this.tokens = Integer.parseInt(word [5]);
    }
	//Method To Return Processed Info As A String
    public String toString()
    { 
        return this.userName + "," + this.passWord + "," + this.wins + "," + this.losses + "," + this.winPercentage + "," + this.tokens;
    }
    //Self-Testing Main Method
    public static void main (String args[])
    {
    	//Variable For Record 
        String record  = "Raman_2ESY,pass123,50,0,100,56";

        //Create An PccountRecord Object
        PlayerRecord testRecord = new PlayerRecord();

        //--[Process Record Testing]----
        //Process The Record
        testRecord.process(record);
        
        //--[toString Testing]----
        //Display The Processed Record
        System.out.println("Processed Record:" + testRecord.toString());

        //--[Getter Method Testing]----
        System.out.println("User Name: " + testRecord.getUserName());
        System.out.println("Pass Word: " + testRecord.getPassWord());
        System.out.println("Wins: " + testRecord.getWins());
        System.out.println("Losses: " + testRecord.getLosses());
        System.out.println("Win %: " + testRecord.getWinPercentage());
        System.out.println("Tokens: " + testRecord.getTokens());
    } //End Main
} // End Program
