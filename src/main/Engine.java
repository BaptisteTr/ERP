package main;

public class Engine {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		int i=1;
		int production=0;
		int tpsProduction=0;
		while (true)
		{
			tpsProduction+=7;
			if(i==1 || i ==2)
			{
				if(tpsProduction>8)
				{
					production+=1000;
					tpsProduction-=8;
				}
			}
			else
			{
				if(tpsProduction>6)
				{
					production+=1000;
					tpsProduction-=6;
					if(tpsProduction>6)
					{
						production+=1000;
						tpsProduction-=6;
					}
				}
			}
			System.out.println("jour : "+i+" temps de production : "+tpsProduction+" production : "+production);
			Thread.sleep(1000);
			i++;
			
		}
	}

}
