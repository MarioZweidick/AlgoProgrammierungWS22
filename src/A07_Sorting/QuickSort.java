package A07_Sorting;


public class QuickSort implements PersonenSort
{

    /**
     * Sortier-Funktion
     */
    public void sort(Person[] personen)
    {
        sort(personen, 0, personen.length - 1);
    }

    private void sort(Person[] personen, int left, int right)
    {
        if (left >= right)
            return;

        int p = partition(personen, left, right); //Pivotelement an die richtige Stelle verschieben --> in 2 Teile geteilt
        sort(personen, left, p - 1);
        sort(personen, p + 1, right);

    }

    public int partition(Person[] personen, int left, int rechts)
    {
        Person pivotPerson = personen[rechts]; //Vergleichselement
        int i = left - 1;
        int k = rechts;

        do
        {
            do
            {
                i++;
            } while (personen[i].compareTo(pivotPerson) < 0);

            do
            {
                k--;
            } while (k>= 0 && personen[k].compareTo(pivotPerson) > 0); //weil k irgendwann kleiner 0 wird...ersten Teil der Bedingung unbedingt berücksichtigen.

            if (i < k)
            {
                Person temp = personen[i];
                personen[i] = personen[k];
                personen[k] = temp;
            }
        } while (i < k);

		Person temp = personen[i];
		personen[i] = pivotPerson;
		personen[k] = temp;
    }

}
