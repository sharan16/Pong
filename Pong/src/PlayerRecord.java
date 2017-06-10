/*
 * Author: Raman Saini 
 * Date: May 2017
 * Desc: PlayerRecord Class For Pong
 * Method List:
 */

public class PlayerRecord {
	
	private String userName, passWord;
	private int score, tokens;
	
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
	//Method To Set Score
	public void setScore (int score)
	{
		this.score = score;
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
	public String getPassword()
	{
		return passWord;
	}
	//Method To Get Score 
	public int getScore()
	{
		return score;
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
        this.score = Integer.parseInt(word [2]);
        this.tokens = Integer.parseInt(word [3]);
    }
	//Method To Return Processed Info As A String
    public String toString()
    { 
        return getUserName() + "," + this.score + "," + this.tokens;
    }
    //Self-Testing Main Method
    static void main (String args[])
    {
    	//
    } //End Main
} // End Program
