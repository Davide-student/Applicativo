package model;

public class User
{
		protected String password[30];
		protected String username[30]:
		private boolean judgeFlag;	//Flag per specificare se l'utente si è proposto come giudice o meno (true = sì, false = no)
		
		public User()
		{
			judgeFlag = false;
		}
		
		//getter e setter
		public void setPassword(String password)
		{
			this.password = password;
		}
		public void setUsername(String username)
		{
			this.username = username;
		}
		public void signAsJudge()	//L'utente sceglie se proporsi come giudice o meno
		{
			if(judgeFlag == false)
			{
				judgeFlag = true;
			}else
			{
				judgeFlag = false;
			}
		}
		public String getPassword()
		{
			return password;
		}
		public String getUsername()
		{
			return username;
		}
		//metodi del model
		public void subscribeToHackathon()
		{
			
		}





}