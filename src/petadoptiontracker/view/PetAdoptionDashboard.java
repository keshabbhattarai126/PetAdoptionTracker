import javax.swing.*;
import java.awt.*;

public class PetAdoptionDashboard extends JFrame {

    public PetAdoptionDashboard() {
        // Window settings
        setTitle("Pet Adoption Dashboard");
        setSize(1200, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Add sections
        add(createSidebar(), BorderLayout.WEST);
        add(createMainPanel(), BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(10, 1, 0, 10));
        sidebar.setBackground(new Color(44, 62, 80));
        sidebar.setPreferredSize(new Dimension(200, getHeight()));

        String[] buttons = {"Dashboard", "My Request", "Profile Setting", "Sign Out"};

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFocusPainted(false);
            button.setBackground(new Color(52, 73, 94));
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Arial", Font.PLAIN, 16));
            sidebar.add(button);
        }

        return sidebar;
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Top bar
        mainPanel.add(createTopBar(), BorderLayout.NORTH);

        // Center content
        JPanel centerContent = new JPanel();
        centerContent.setLayout(new BorderLayout());

        JLabel title = new JLabel("Recommended Pets");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

        centerContent.add(title, BorderLayout.NORTH);
        centerContent.add(createPetCards(), BorderLayout.CENTER);

        mainPanel.add(centerContent, BorderLayout.CENTER);
        return mainPanel;
    }

    private JPanel createTopBar() {
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JPanel left = new JPanel(new GridLayout(2, 1));
        JLabel hello = new JLabel("Hello!");
        JLabel name = new JLabel("Name Surname");
        hello.setFont(new Font("Arial", Font.BOLD, 16));
        name.setFont(new Font("Arial", Font.PLAIN, 14));
        left.add(hello);
        left.add(name);

        JPanel right = new JPanel();
        JTextField searchField = new JTextField("Search", 20);
        JButton filterButton = new JButton("Filters");
        JButton searchButton = new JButton("Search");
        right.add(searchField);
        right.add(filterButton);
        right.add(searchButton);

        topBar.add(left, BorderLayout.WEST);
        topBar.add(right, BorderLayout.EAST);

        return topBar;
    }

    private JPanel createPetCards() {
        JPanel petCards = new JPanel();
        petCards.setLayout(new GridLayout(1, 2, 20, 0));
        petCards.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        petCards.add(createPetCard("Buddy", "Dog, Golden Retriever", "Friendly and active", "Loves to play fetch"));
        petCards.add(createPetCard("Mittens", "Cat, Siamese", "Quiet and loving", "Enjoys sunbathing and naps"));

        return petCards;
    }

    private JPanel createPetCard(String name, String breed, String desc, String details) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(200, 150));
        imagePanel.setBackground(Color.LIGHT_GRAY); // Placeholder for image

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        textPanel.add(new JLabel(name));
        textPanel.add(new JLabel(breed));
        textPanel.add(new JLabel(desc));
        textPanel.add(new JLabel(details));

        JButton requestButton = new JButton("Request");
        requestButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        textPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        textPanel.add(requestButton);

        card.add(imagePanel, BorderLayout.NORTH);
        card.add(textPanel, BorderLayout.CENTER);

        return card;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PetAdoptionDashboard::new);
    }
}
