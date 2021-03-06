package edu.ithaca.dragon.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if(isAmountValid(startingBalance) == false)
            throw new IllegalArgumentException("Invalid starting balance");
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     *  If the withdraw amount is > the current balance, the balance will be set to 0
     */
    public void withdraw (double amount)  {
        if(isAmountValid(amount) == false)
            throw new IllegalArgumentException("Invalid amount");
        if(amount < balance)
            balance -= amount;
        else
            balance = 0;
    }

    /**
     * A valid email address:
     * (at least one character) + @ + (at least one character) + . + (at least one character)
     */
    public static boolean isEmailValid(String email){
        if (email.indexOf('@') < 1){
            return false;
        }
        int indexOAtSym = email.indexOf('@');
        if (email.lastIndexOf('@') != indexOAtSym){
            return false;
        }

        if (email.indexOf('.') < indexOAtSym + 2){
            return false;
        }
        return true;
    }

    /**
     * A valid amount is positive with two or less decimal places
     */
    public static boolean isAmountValid(double amount) {
        //check if negative
        if (amount < 0)
            return false;

        String amountStr = Double.toString(amount);

        //check if no decimal point
        if(amountStr.indexOf('.') == -1)
            return true;

        //check if 1 or 2 decimal places
        String decimals = amountStr.substring(amountStr.indexOf('.'), amountStr.length());

        if(decimals.length() > 3)
            return false;
        return true;
    }
}