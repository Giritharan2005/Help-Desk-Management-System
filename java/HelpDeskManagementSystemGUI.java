import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class HelpDeskManagementSystemGUI {
    private Map<Integer, Ticket> tickets;
    private int ticketIdCounter;
    private Map<String, String> userDatabase; // Stores username and password

    // GUI Components
    private JFrame frame;
    private JTextField customerNameField, ticketIdField, usernameField;
    private JPasswordField passwordField;
    private JTextArea outputArea;

    public HelpDeskManagementSystemGUI() {
        tickets = new HashMap<>();
        ticketIdCounter = 1;
        userDatabase = new HashMap<>();
        // Adding a default admin user
        userDatabase.put("USER", "USER@123");

        showLoginScreen();
    }

    // Login Screen
    private void showLoginScreen() {
        frame = new JFrame("Login - Help Desk Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 5, 5));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        loginButton.addActionListener(e -> handleLogin());
        registerButton.addActionListener(e -> handleRegistration());

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    // Handle Login
    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
            JOptionPane.showMessageDialog(frame, "Login Successful!");
            frame.dispose();
            createUI();
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid Username or Password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Handle New User Registration
    private void handleRegistration() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Username or Password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (userDatabase.containsKey(username)) {
            JOptionPane.showMessageDialog(frame, "Username already exists.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            userDatabase.put(username, password);
            JOptionPane.showMessageDialog(frame, "User Registered Successfully!");
        }
    }

    // Main Help Desk Management System UI
    private void createUI() {
        frame = new JFrame("Help Desk Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2, 5, 5));

        JLabel customerNameLabel = new JLabel("Customer Name:");
        customerNameField = new JTextField();
        JLabel ticketIdLabel = new JLabel("Ticket ID:");
        ticketIdField = new JTextField();

        JButton createButton = new JButton("Create Ticket");
        createButton.addActionListener(e -> createTicket());

        JButton assignButton = new JButton("Assign Ticket");
        assignButton.addActionListener(e -> assignTicket());

        JButton closeButton = new JButton("Close Ticket");
        closeButton.addActionListener(e -> closeTicket());

        JButton displayButton = new JButton("Display Tickets");
        displayButton.addActionListener(e -> displayTickets());

        inputPanel.add(customerNameLabel);
        inputPanel.add(customerNameField);
        inputPanel.add(ticketIdLabel);
        inputPanel.add(ticketIdField);
        inputPanel.add(createButton);
        inputPanel.add(assignButton);
        inputPanel.add(closeButton);
        inputPanel.add(displayButton);

        mainPanel.add(inputPanel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }

    private void createTicket() {
        String customerName = customerNameField.getText();
        if (customerName.isEmpty()) {
            outputArea.append("Customer name cannot be empty.\n");
            return;
        }
        Ticket ticket = new Ticket(ticketIdCounter++, customerName);
        tickets.put(ticket.getId(), ticket);
        outputArea.append("Ticket created: ID " + ticket.getId() + " for " + customerName + "\n");
        customerNameField.setText("");
    }

    private void assignTicket() {
        try {
            int ticketId = Integer.parseInt(ticketIdField.getText());
            Ticket ticket = tickets.get(ticketId);
            if (ticket != null) {
                ticket.setStatus("in progress");
                outputArea.append("Ticket " + ticketId + " assigned.\n");
            } else {
                outputArea.append("Ticket not found.\n");
            }
        } catch (NumberFormatException e) {
            outputArea.append("Invalid Ticket ID.\n");
        }
        ticketIdField.setText("");
    }

    private void closeTicket() {
        try {
            int ticketId = Integer.parseInt(ticketIdField.getText());
            Ticket ticket = tickets.get(ticketId);
            if (ticket != null) {
                ticket.setStatus("closed");
                outputArea.append("Ticket " + ticketId + " closed.\n");
            } else {
                outputArea.append("Ticket not found.\n");
            }
        } catch (NumberFormatException e) {
            outputArea.append("Invalid Ticket ID.\n");
        }
        ticketIdField.setText("");
    }

    private void displayTickets() {
        outputArea.setText("Ticket Status:\n");
        for (Ticket ticket : tickets.values()) {
            outputArea.append("ID: " + ticket.getId() + 
                              ", Customer: " + ticket.getCustomerName() + 
                              ", Status: " + ticket.getStatus() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HelpDeskManagementSystemGUI::new);
    }
}