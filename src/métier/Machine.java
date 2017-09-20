package métier;

public class Machine {

	
	int idMachine;
	String libelleMachine;
	int tempsDeProduction;
	boolean enFonctionnement;
	
	public void Machine()
	{
		
	}
	
	public void Machine(int idMachine, String libelleMachine, int tempsDeProduction, boolean enFonctionnement)
	{
		this.idMachine = idMachine;
		this.libelleMachine = libelleMachine;
		this.tempsDeProduction = tempsDeProduction;
		this.enFonctionnement = enFonctionnement;
	}
	
	public void setIdMachine(int idMachine)
	{
		this.idMachine = idMachine;
	}
	public void setLibelleMachine(String libelleMachine)
	{
		this.libelleMachine = libelleMachine;
	}
	public void setTempsDeProduction(int tempsDeProduction)
	{
		this.tempsDeProduction = tempsDeProduction;
	}
	public void setEnFonctionnement(boolean enFonctionnement)
	{
		this.enFonctionnement = enFonctionnement;
	}
	
	public int getIdMachine()
	{
		return idMachine;
	}
	public String getLibelleMachine()
	{
		return libelleMachine;
	}
	public int getTempsDeProduction()
	{
		return tempsDeProduction;
	}
	public boolean getEnFonctionnement()
	{
		return enFonctionnement;
	}
	
	
}
