package model;



public class User
{
		protected String password;
		protected String username;

		
		public User(String username, String password)
		{
			this.username = username;
			this.password = password;
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
		public String getPassword()
		{
			return password;
		}
		public String getUsername()
		{
			return username;
		}
		//metodi del model
		public void subscribeToHackathon(Hackathon hackathon)
		{
			Participant participant = new Participant(this.username, this.password);
			hackathon.addParticipant(participant);
		}





}