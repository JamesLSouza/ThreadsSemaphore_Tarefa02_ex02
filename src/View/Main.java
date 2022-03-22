package View;
import java.util.concurrent.Semaphore;

import Controller.Kitchen;

public class Main 
{

	public static void main(String[] args) 
	{
			Semaphore semaforo =  new Semaphore(1);
			
			for(int id = 1; id < 6; id++)
			{
				Thread cozinha = new Kitchen(id, semaforo);
				cozinha.start();
				
				
			}
			
		}

	}