// src/Ticket.java

public class Ticket {
    private int id;
    private String customerName;
    private String status;

    public Ticket(int id, String customerName) {
        this.id = id;
        this.customerName = customerName;
        this.status = "open";
    }

    public int getId() { return id; }
    public String getCustomerName() { return customerName; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}