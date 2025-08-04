package org.example;

import java.io.PrintStream;
import java.util.Scanner;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class InteractiveOrderProcessor {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Interactive Order Processor!");
        System.out.println("--- Enter Order Details ---");

        System.out.print("Enter unit price: ");
        double unitPrice = input.nextDouble();
        input.nextLine();

        System.out.print("Enter quantity: ");
        int quantity = input.nextInt();
        input.nextLine();

        System.out.print("Is customer a member (true/false)?: ");
        boolean isMember = input.nextBoolean();
        input.nextLine();

        if(!isMember){
            System.exit(0);
        }

        System.out.print("Enter customer tier (Regular, Silver, Gold): ");
        String customerTier = input.nextLine().trim();

        System.out.print("Enter shipping zone (ZoneA, ZoneB, ZoneC, Unknown): ");
        String shippingZone = input.nextLine().trim().toLowerCase();
        System.out.print("Enter discount code (SAVE10, FREESHIP, or \"\" for none): ");
        String discountCode = input.nextLine().trim();

        System.out.println("--- Order Details ---");
        System.out.printf("Unit Price: $%.2f%n", unitPrice);
        System.out.println("Quantity: " + quantity);
        System.out.println("Is Member: " + isMember);
        System.out.println("Customer Tier: " + customerTier);
        System.out.println("Shipping Zone: " + shippingZone);
        System.out.println("Discount Code: " + discountCode);
        System.out.println();

        System.out.println("--- Calculation Steps ---");
        double currentTotal = unitPrice * quantity;
        System.out.printf("Initial Subtotal: $%.2f%n" , currentTotal);

        if(customerTier.equalsIgnoreCase("gold")){
            currentTotal -= currentTotal*0.15;
            System.out.printf("After Tier Discount (" +customerTier +" - 15%%): $%.2f%n", currentTotal);
        } else if (customerTier.equalsIgnoreCase("silver")) {
            currentTotal -= currentTotal*0.10;
            System.out.printf("After Tier Discount (" +customerTier +" - 10%%): $%.2f%n", currentTotal);
        }else{
            System.out.printf("After Tier Discount (" +customerTier +" - 0%%): $%.2f%n", currentTotal);
        }


        if(quantity >= 5){
            currentTotal -= currentTotal*0.05;
        }

        System.out.printf("After Quantity Discount (5%% for >=5 items): $%.2f%n", currentTotal);

        boolean hasFreeShipping = false;

        if(discountCode.equals("SAVE10") && currentTotal > 75.00){
            currentTotal -= 10.00;
            System.out.printf("After Promotional Code (SAVE10 for >$75): $%.2f%n", currentTotal);
        } else if (discountCode.equalsIgnoreCase("FREESHIP")) {
            System.out.printf("After Promotional Code (SAVE10 for Free Shipping): $%.2f%n", currentTotal);
            hasFreeShipping = true;
        }else{
            System.out.printf("After Promotional Code (None): $%.2f%n", currentTotal);
        }

        currentTotal = currentTotal < 25.00 ? currentTotal + 3.00 : currentTotal;

        String message = currentTotal - 3.00 < 25.00 ? "After Small Order Surcharge(if applicable): $%.2f%n" :
                "After Small Order Surcharge (if applicable): $%.2f (No surcharge) %n";
        System.out.printf(message, currentTotal);


        if(!hasFreeShipping){
            switch(shippingZone){
                case "zonea":
                    System.out.println("Shipping Cost: $5.00 (ZoneA)");
                    currentTotal += 5.00;
                    break;
                case "zoneb":
                    System.out.println("Shipping Cost: $12.50 (ZoneB)");
                    currentTotal += 12.50;
                    break;
                case "zonec":
                    System.out.println("Shipping Cost: $20.00 (ZoneC)");
                    currentTotal += 20.00;
                    break;
                default:
                    System.out.println("Shipping Cost: $25.00 (default)");
                    currentTotal += 25.00;
                    break;
            }
        }else{
            System.out.println("Shipping Cost: $0.00 (FREE SHIPPING)");
        }

        System.out.printf("Final Order Total: $%.2f%n", currentTotal);


        System.out.println("--- String Equality Demo ---");
        System.out.print("Enter first string for comparison: ");
        String string1 = input.nextLine();
        System.out.print("Enter second string for comparison: ");
        String string2 = input.nextLine();

        System.out.println("String 1: " + string1);
        System.out.println("String 2: " + string2);

        if(string1 == string2){
            System.out.println("String 1 == String 2: true (Both strings reference the same object)");
        }else{
            System.out.println("String 1 == String 2: false (The 2 strings reference different objects)");
        }

        if(string1.equals(string2)){
            System.out.println("String 1.equals() String 2: true (Both strings have the same exact value (Case-sensitive)");

        }else{
            System.out.println("String 1.equals() String 2: false (The value of the 2 strings are different due to the case)");
        }

        if(string1.equalsIgnoreCase(string2)){
            System.out.println("String 1.equalsIgnoreCase() String 2: true (Both strings have the same exact value (Case-insensitive)");

        }else{
            System.out.println("String 1.equalsIgnoreCase() String 2: false (The value of the 2 strings are different)");
        }
    }


}