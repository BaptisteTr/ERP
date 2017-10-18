package métier;

public class Stock_item {

	int item;
	
	public void setItem(int item)
	{
		this.item = item;
	}
	public int getItem()
	{
		return item;
	}
	
	public void soustraireItem( int item)
	{
		this.item = this.item-item;
	}
	
	public void ajouterItem(int item)
	{
		this.item = this.item+item;
	}
}
