package org.example;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class ChatBot {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChatBot bot = new ChatBot();

        while (true) {
            // Greeting the user (requirement 1)
            System.out.print("Hi there! I'm Mr. Chatbot! What's your name? ");
            String name = scanner.nextLine();

            // Asking the user to repeat their name (requirement 2)
            System.out.print("Mind repeating your name one more time for me? So I don't forget!!! ");
            String repeatName = scanner.nextLine();

            // Check if the repeated name matches the original name
            if (!name.equalsIgnoreCase(repeatName)) {
                System.out.println("Oooh, somebody's pants are on fire. Let's try this again!");
                System.out.println();
                continue;
            }

            // Guess the user's age (requirement 3)
            int age = bot.guessAge(scanner);
            System.out.println();

            // Tell a story about the user based on their birth year (Level 2 requirement)
            bot.tellStory(name, age);
            System.out.println();

            // Bot will output a phrase about the day of the week based on users input (Level 3 requirement)
            bot.weekDay(scanner);
            System.out.println();

            // Count to the number specified by the user (requirement 4)
            bot.countToNumber(scanner);
            System.out.println();

            // Test user's programming knowledge
            bot.testProgrammingKnowledge(scanner);
            System.out.println();

            System.out.println("Well, " + name + ", it was nice to talk to you. Hope to hear from you again sometime!");
            scanner.close();
            break;
        }
    }
    private int guessAge(Scanner scanner) {
        // Ask for the birth month and day with validation
        int birthMonth = 0;
        int birthDay = 0;

        while (birthMonth < 1 || birthMonth > 12) {
            System.out.println("Please enter the month you were born (1-12):");
            if (scanner.hasNextInt()) {
                birthMonth = scanner.nextInt();
                if (birthMonth < 1 || birthMonth > 12) {
                    System.out.println("That's not valid. Please enter a value between 1 and 12.");
                }
            } else {
                System.out.println("That's not going to work. Please enter a numeric value.");
                scanner.next(); // Clear invalid input
            }
        }

        while (birthDay < 1 || birthDay > 31) {
            System.out.println("Please enter the day you were born (1-31):");
            if (scanner.hasNextInt()) {
                birthDay = scanner.nextInt();
                if (birthDay < 1 || birthDay > 31) {
                    System.out.println("You've got to pick a value between 1 and 31.");
                }
            } else {
                System.out.println("Numbers please!");
                scanner.next(); // Clear invalid input
            }
        }
        scanner.nextLine();

        // Narrow down the age range with a series of questions
        int age = narrowDownAge(scanner);

        // Determine if the user's birthday has already passed this year
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        LocalDate birthdayThisYear = LocalDate.of(currentYear, birthMonth, birthDay);
        boolean birthdayPassed = currentDate.isAfter(birthdayThisYear) || currentDate.isEqual(birthdayThisYear);

        // Adjust the birth year based on whether the user's birthday has passed this year
        int birthYear = birthdayPassed ? currentYear - age : currentYear - age - 1;

        // Calculate exact age to the day
        LocalDate birthDate = LocalDate.of(birthYear, birthMonth, birthDay);
        Period agePeriod = Period.between(birthDate, currentDate);

        System.out.println("You must be " + agePeriod.getYears() + " years, " + agePeriod.getMonths() + " months, and " + agePeriod.getDays() + " days old.");
        return agePeriod.getYears();
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

    private void tellStory(String name, int age) {
        PopCultureReferences popCultureReferences = new PopCultureReferences();
        int birthYear = LocalDate.now().getYear() - age;
        String reference = popCultureReferences.getReference(birthYear);

        System.out.println("Wow, " + name + "! You were born in " + birthYear + ". That was the year of " + reference + " It's amazing to see how much has happened since then!");
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

    private void weekDay(Scanner scanner) {
        boolean validDay = false;
        while (!validDay) {
            System.out.println("If you ask me about a day of the week, I'll tell you my favorite thing about that day!");
            String day = scanner.nextLine().trim().toLowerCase();

            switch (day) {
                case "sunday":
                    System.out.println("Sundays are cool, they always feel like the calmest and most relaxing day of the week.");
                    validDay = true;
                    break;
                case "monday":
                    System.out.println("Mondays are great! It always feels like the rest of the weeks are downhill!");
                    validDay = true;
                    break;
                case "tuesday":
                    System.out.println("Tuesdays are awesome because Monday is over with!!!!");
                    validDay = true;
                    break;
                case "wednesday":
                    System.out.println("Wednesdays are wonderful, it somehow feels like the perfect transition day to reset for the rest of the week!");
                    validDay = true;
                    break;
                case "thursday":
                    System.out.println("Thursday has the best nickname: 'Little Friday'");
                    validDay = true;
                    break;
                case "friday":
                    System.out.println("I love Fridays because without it, we would never have the hit song 'Working for the Weekend' by 'Lover Boy'");
                    validDay = true;
                    break;
                case "saturday":
                    System.out.println("Saturdays are without a doubt the best, it feels like you've got time to literally go to the moon and back!");
                    validDay = true;
                    break;
                default:
                    System.out.println("You sure about that?! You sure that's a day of the week?!");
            }
        }
    }
}
