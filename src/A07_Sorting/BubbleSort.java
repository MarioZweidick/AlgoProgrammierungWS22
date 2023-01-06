package A07_Sorting;


public class BubbleSort implements PersonenSort {

	/**
	 * Sortier-Funktion
	 */
	public void sort(Person[] personen) {

		for (int i = 0; i < personen.length; i++)
		{
			for (int j = i; j < personen.length; j++)
			{
				if(personen[i].getNachname().compareTo(personen[j].getNachname()) > 0)
				{
					Person temp = personen[i];
					personen[i] = personen[j];
					personen[j] = temp;
				}
				else if(personen[i].getNachname().compareTo(personen[j].getNachname()) == 0)
				{
					if(personen[i].getVorname().compareTo(personen[j].getVorname()) > 0)
					{
						Person temp = personen[i];
						personen[i] = personen[j];
						personen[j] = temp;
					}

				}
			}
		}

	}
	
}
