import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JTextField titleField, authorField, quantityField;
    private JButton addBookButton, borrowBookButton, returnBookButton;
    private JTextArea outputArea;

    public Main() {
        setTitle("Library Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("Author:"));
        authorField = new JTextField();
        inputPanel.add(authorField);

        inputPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);

        addBookButton = new JButton("Add Book");
        addBookButton.addActionListener(this);
        inputPanel.add(addBookButton);

        borrowBookButton = new JButton("Borrow Book");
        borrowBookButton.addActionListener(this);
        inputPanel.add(borrowBookButton);

        returnBookButton = new JButton("Return Book");
        returnBookButton.addActionListener(this);
        inputPanel.add(returnBookButton);

        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(inputPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBookButton) {
            addBook();
        } else if (e.getSource() == borrowBookButton) {
            borrowBook();
        } else if (e.getSource() == returnBookButton) {
            returnBook();
        }
    }

    private void addBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        int quantity = Integer.parseInt(quantityField.getText());

        // Use the LibrarySystem class to add the book
        LibrarySystem.addBooks(title, author, quantity);

        outputArea.append("Book added: " + title + " by " + author + ", Quantity: " + quantity + "\n");
        clearFields();
    }

    private void borrowBook() {
        String title = titleField.getText();
        int quantityToBorrow = Integer.parseInt(quantityField.getText());

        // Use the LibrarySystem class to borrow the book
        boolean success = LibrarySystem.borrowBooks(title, quantityToBorrow);

        if (success) {
            outputArea.append("Borrowed " + quantityToBorrow + " book(s) of " + title + "\n");
        } else {
            outputArea.append("Book not found or insufficient quantity.\n");
        }
        clearFields();
    }

    private void returnBook() {
        String title = titleField.getText();
        int quantityToReturn = Integer.parseInt(quantityField.getText());

        // Use the LibrarySystem class to return the book
        boolean success = LibrarySystem.returnBooks(title, quantityToReturn);

        if (success) {
            outputArea.append("Returned " + quantityToReturn + " book(s) of " + title + "\n");
        } else {
            outputArea.append("Book not found in the library.\n");
        }
        clearFields();
    }

    private void clearFields() {
        titleField.setText("");
        authorField.setText("");
        quantityField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Main gui = new Main();
                gui.setVisible(true);
            }
        });
    }
}
