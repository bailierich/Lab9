package lab9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Lab9 {

	private static Scanner scnr;
	private static Map<String, Double> menu = new TreeMap<>();
	private static List<String> orderItem = new ArrayList<>();
	private static List<Double> orderItemPrice = new ArrayList<>();

	public static void main(String[] args) {
		scnr = new Scanner(System.in);
		int i = 0;
		System.out.println("Welcome to BellaRenee's Fish Market!");

		putItemsInMenu();
		do {
			getOrder();

		} while (getYesNo());
		
		System.out.println("Thank you for your order!\n"
				+ "Here's what you got: ");
		System.out.println("Item          |       Price");
		System.out.println("==============================");
		while (i < orderItem.size()) {
			System.out.printf("%-13s |       $%.2f\n",orderItem.get(i),orderItemPrice.get(i) );
			i++;
		}
		
		System.out.printf("%s $%.2f\n","The average of your cart is :", getAverage());
		System.out.printf("%s $%.2f\n","The most expensive item in your cart is :", Collections.max(orderItemPrice));
		System.out.printf("%s $%.2f","The least expensive item in your cart is :", Collections.min(orderItemPrice));

	}
//puts items in menu
	private static void putItemsInMenu() {
		menu.put("lobster", 45.99);
		menu.put("scallops", 13.45);
		menu.put("shrimp", 15.00);
		menu.put("catfish", 29.00);
		menu.put("tuna", 35.00);
		menu.put("crablegs", 15.00);
		menu.put("flounder", 8.00);
		menu.put("snapper", 24.00);
	}
//prints menu 
	private static void printMenu() {
		System.out.println("Item        |    Price");
		System.out.println("===============================");

		for (Map.Entry<String, Double> entry : menu.entrySet()) {
			System.out.printf("%-11s |\t $%.2f\n", entry.getKey(), entry.getValue());

		}
	}

	private static void getOrder() {
		printMenu();
		Double itemPrice;
		String customerInput;
		System.out.println();
		System.out.println("What item would you like to order?");
		customerInput = scnr.next();//gets the shoppers input
		itemPrice = menu.get(customerInput.toLowerCase());
		
		//above looks for the input in the list key
		//if the key isn't found the value is null
		//if null is found they receive error message 
		//and this method is called again to get a new entry to compare

		if (itemPrice == null) {
			System.out.println("Sorry, the item you've searched for is not in stock!\n");
			getOrder();
		} else {
			System.out.printf("\n%s%s%s$%.2f\n", "Adding ", customerInput.toLowerCase(), " to cart for ", itemPrice);
			orderItem.add(customerInput);
			orderItemPrice.add(itemPrice);}

		
	}

	private static boolean getYesNo() {
		System.out.print("Would you like to add another item to your cart (yes or no)?");
		String yesOrNo = scnr.next();
		boolean goAgain = true;
		if (yesOrNo.equalsIgnoreCase("yes") || yesOrNo.equalsIgnoreCase("no")) {
			if (yesOrNo.equalsIgnoreCase("yes")) {
				return goAgain;
			} else {
				return goAgain = false;
			}

		} else {
			System.out.println("Opps! It doesn't seem like you gave a valid answer. Try again.");
			return getYesNo();
		}
	}
	
	private static double getAverage() {
		double sum = 0;
		double average = 0;
		for (double items: orderItemPrice) {
			sum +=items;
		}
		average = sum / (double) orderItemPrice.size();
		return(average);
	}
	private static int getMinIndex(){
		return orderItemPrice.indexOf(Collections.min(orderItemPrice));
	}
	private static void getMaxIndex() {
		orderItemPrice.indexOf(Collections.max(orderItemPrice));
	}
	
}
