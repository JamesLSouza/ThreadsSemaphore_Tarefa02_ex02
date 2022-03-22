package Controller;

import java.util.concurrent.Semaphore;

public class Kitchen extends Thread
{
	private int Id;
	private Semaphore semaforo;
	String Prato;
	
	public Kitchen(int threadId, Semaphore semaforo)
	{
		this.Id = threadId;
		this.semaforo = semaforo;
	}
	
	public void run()
	{
		Cozimento();
		Entrega();
	}

	private void Entrega() 
	{
		
		if (Id % 2 == 0)
		{
			Prato = "Lasanha a bolonhesa";	
		}
		else 
		{
			Prato = "Sopa de cebola";
		}
		
		try 
		{
			semaforo.acquire();
			System.out.println(Prato +" está sendo entregue.");
			Thread.sleep(500);
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
			
		}
		finally
		{
			semaforo.release();
		}
		System.out.println(Prato +" foi entregue.");
	}
	
	private void Cozimento()
	{
		int temp = 0;
		int cont = 0;
		
		if (Id % 2 == 0)
		{
			Prato = "Lasanha a bolonhesa";
			System.out.println("Iniciando o preparo da Lasanha a bolonhesa.");
			temp = (int) ((int) ((Math.random() * 601) + 600));
		}
		else
		{
			Prato = "Sopa de cebola"; 
			System.out.println("Iniciando o preparo da Sopa de cebola.");
			temp = (int) ((int) ((Math.random() * 301) + 500));
		}
		
		
		
		while (cont < temp) 
		{
			try
			{
				if (cont + 100 <= temp)
				{
					cont += 100;
				}
				else
				{
					cont += temp - cont;
				}
				System.out.println("Cozinheiro " +Id + " - " + Prato + " " + (cont * 100) / temp + "%");
				sleep(500);
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			System.out.println("A " +Prato+" está pronta.");
		}
	}
}