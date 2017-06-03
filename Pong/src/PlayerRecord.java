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
	//Method To Set Losses
	public void setLosses (int losses)
	{
		this.losses = losses;
	}
	//Method To Set Win %
	public void setWinPercentage (double winPercentage)
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
	//Method To Get Losses
	public int getLosses()
	{
		return losses;
	}
	//Method To Get Win %
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
		return this.userName + "," + this.passWord + "," + this.wins + "," + this.losses + "," 
                            + this.winPercentage + "," + this.tokens;
    }
    //Self-Testing Main Method
    public static void main (String args[])
    {
    	//
    } //End Main
} // End Program

      
        
       

