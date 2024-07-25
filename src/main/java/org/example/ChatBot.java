package org.example;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class ChatBot {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChatBot bot = new ChatBot();

        // Greeting the user (requirement 1)
        System.out.print("Hi there! I'm Mr. Chatbot! What's your name? ");
        String name = scanner.nextLine();

        // Asking the user to repeat their name (requirement 2)
        System.out.print("Mind repeating your name one more time for me? So I don't forget!!! ");
        String repeatName = scanner.nextLine();

        // Guess the user's age (requirement 3)
        bot.guessAge(scanner);

        // Count to the number specified by the user (requirement 4)
        bot.countToNumber(scanner);

        // Test user's programming knowledge
        bot.testProgrammingKnowledge(scanner);

        System.out.println("Well, " + name + ", it was nice to talk to you. Hope to hear from you again sometime!");
        scanner.close();
    }

    private void guessAge(Scanner scanner) {
        // Ask for the birth month and day
        System.out.println("Please enter the month you were born (1-12):");
        int birthMonth = scanner.nextInt();
        System.out.println("Please enter the day you were born (1-31):");
        int birthDay = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Narrow down the age range with a series of questions
        int age = narrowDownAge(scanner);

        // Determine if the user's birthday has already passed this year
        LocalDate currentDate = LocalDate.now();
        LocalDate birthdayThisYear = LocalDate.of(currentDate.getYear(), birthMonth, birthDay);
        boolean birthdayPassed = currentDate.isAfter(birthdayThisYear) || currentDate.isEqual(birthdayThisYear);

        // Adjust the birth year based on whether the user's birthday has passed this year
        int birthYear = birthdayPassed ? currentDate.getYear() - age : currentDate.getYear() - age - 1;

        // Calculate exact age to the day
        LocalDate birthDate = LocalDate.of(birthYear, birthMonth, birthDay);
        Period agePeriod = Period.between(birthDate, currentDate);

        System.out.println("You are " + agePeriod.getYears() + " years, " + agePeriod.getMonths() + " months, and " + agePeriod.getDays() + " days old.");
    }

    private int narrowDownAge(Scanner scanner) {
        int lowerBound = 0;
        int upperBound = 120;

        while (upperBound - lowerBound > 1) {
            int midPoint = (lowerBound + upperBound) / 2;
            System.out.println("Are you older than " + midPoint + "? (yes/no)");
            boolean older = scanner.nextLine().trim().equalsIgnoreCase("yes");

            if (older) {
                lowerBound = midPoint;
            } else {
                upperBound = midPoint;
            }
        }

        return lowerBound;
    }

    private void countToNumber(Scanner scanner) {
        System.out.println("I can count to any number you want. Please give me a number:");
        int number = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        for (int i = 0; i <= number; i++) {
            System.out.println(i);
        }
    }

    private void testProgrammingKnowledge(Scanner scanner) {
        System.out.println("Let's test your programming knowledge.");
        System.out.println("Which of the following is a correct way to declare a variable in Java?");
        System.out.println("1. int 1x = 10;");
        System.out.println("2. int x = 10;");
        System.out.println("3. float x = 10.0;");
        System.out.println("4. string x = \"10\";");

        boolean correctAnswer = false;

        while (!correctAnswer) {
            int answer = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (answer) {
                case 1:
                    System.out.println("Wrong");
                    break;
                case 2:
                    System.out.println("That's Right!");
                    correctAnswer = true;
                    break;
                case 3:
                    System.out.println("Wrong");
                    break;
                case 4:
                    System.out.println("Wrong");
                    break;
                default:
                    System.out.println("Enter a number between 1-4 to answer!");
                    break;
            }
        }
    }
}
