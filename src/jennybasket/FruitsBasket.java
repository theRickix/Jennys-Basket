package jennybasket;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FruitsBasket {
	private ArrayList<Fruit> basket = new ArrayList<Fruit>();
	private int[] counter;
	private int size;
	private static final int max = 500;
	private int nsolutions = 0;

	private FruitsBasket() throws FileNotFoundException {
		loadFile();
		size = this.basket.size();
		counter = new int[size];
		getCombinations(size, 0);
		System.out.print("Number of Solutions: " + nsolutions);
	}

	private void getCombinations(int sizetemp, int sum) {
		int index = size - sizetemp;
		int sumtemp = 0;
		for (int i = 0; sumtemp <= max; i++) {

			counter[index] = i;

			sumtemp = sum + basket.get(index).getPrice() * i;

			if (sumtemp == 500) {
				nsolutions++;
				for (int j = 0; j < size; j++) {
					if (counter[j] != 0) {
						if (counter[j] == 1) {
							System.out.print(counter[j] + " " + basket.get(j).getName() + ", ");
						} else if (counter[j] > 1) {
							System.out.print(counter[j] + " " + basket.get(j).getName() + "s, ");
						}
					}

				}
				System.out.println();
			} else {
				if (sizetemp > 1) {
					getCombinations(sizetemp - 1, sumtemp);
				}
			}
			counter[index] = 0;
		}
	}

	private void loadFile() throws FileNotFoundException {
		Scanner s = new Scanner(new File("fruits.txt"));
		String line;

		String name;
		int price;

		while (s.hasNextLine()) {
			while (s.hasNext()) {
				name = s.next();
				price = s.nextInt();
				//System.out.println(name + " " + price);
				this.basket.add(new Fruit(name, price));
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		new FruitsBasket();
	}
}
