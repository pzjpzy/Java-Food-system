/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin;

/**
 *
 * @author Nour
 */
public class TopUpRequest {
    private String CustomerID;
    private double Amount;
    private String Status; // "Pending", "Approved", "Declined"
    
    
    // Constructor
    public TopUpRequest(String customerID, double amount) {
        this.CustomerID = customerID;
        this.Amount = amount;
        this.Status = "Pending"; // Default status
    }
    
        // Constructor for loading requests (includes status)
    public TopUpRequest(String customerID, double amount, String status) {
        this.CustomerID = customerID;
        this.Amount = amount;
        this.Status = status;
    }

    // Getters and Setters
    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        this.CustomerID = customerID;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        this.Amount = amount;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }
    
}
