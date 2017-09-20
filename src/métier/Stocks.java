package métier;

public class Stocks {

	int boulon;
	
	public void setBoulon(int boulon)
	{
		this.boulon = boulon;
	}
	public int getBoulon()
	{
		return boulon;
	}
	
	public void soustraireBoulon( int boulon)
	{
		this.boulon = this.boulon-boulon;
	}
	
	public void ajouterBoulon(int boulon)
	{
		this.boulon = this.boulon+boulon;
	}
}
