//package MyInterface;


import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Statement;
//import MyInterface.MonModel;



public class InterfaceGestion extends JFrame {
    private JMenuBar barreMenus;
    private JMenu gestion, Rechercher, Couleurs;
    private JMenuItem profil, employe, employepro, quitter, CinEmploye, NomEmploye, Bleu, Rouge, Blanc;
    private JTabbedPane tabbedPane;
    private JTextField textField1;
    private JComboBox<String> comboBox1;
    private JTextField textField2;
    private JTextField textField3;
    private JFormattedTextField dateField;
    private JComboBox<String> comboBox2;
    private JTextField textField4;
    private JButton modificationButton1;
    private JButton modificationButton2;
    private JButton modificationButton3;
    private JButton modificationButton4;
    private JButton modificationButton5;
    private JButton ajouterButton;
    private JTextField textField;
    private JButton button1 ;
    private JButton button2 ;
    private JTextField textcin;
    private JTextField textnom;
    private JTextField textemail;
    private JTextField textprenom;
    private JFormattedTextField dateField1;
    private JComboBox<String> comboBoxgenre;
    private JComboBox<String> comboBoxprofil;
    private JLabel labelcin;
    private JLabel labelnom;
    private JLabel labelddn;
    private JLabel labelemail;
    private JLabel labelgenre;
    private JLabel labelprenom;
    private JLabel labelprofil;
    private JLabel labelprofil_liste;
    private JComboBox<String> comboBoxprofil_liste;
    private Connection connection;
    private List<Employe> employeeList;
    private int currentIndex = 0;
    private JTable table;
    private JScrollPane scrollPaneprofilemp;
    private DefaultTableModel tableModel;
    private JTable profileTable;
    private ResultSet employeeResultSet; 
    private ResultSet resultSet; 
    private MonModel model ;
    private int currentEmployeeIndex = 0;
    private int totalEmployees;
    
    


    public InterfaceGestion() {
    	
    	
        super("Interface de Gestion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 1000);
        setLocationRelativeTo(null);
        
        

        textField1 = new JTextField(18);
        String[] genres1 = {"Feminin", "Masculin"};
        JComboBox<String> comboBox1 = new JComboBox<>(genres1);
        
        comboBox1.setPreferredSize(new Dimension(200, comboBox1.getPreferredSize().height));
        textField2 = new JTextField(18);
        textField3 = new JTextField(18);
        dateField = new JFormattedTextField();
        
        
       
        
        dateField.setPreferredSize(new Dimension(200, comboBox1.getPreferredSize().height));

        comboBox2 = new JComboBox<>();
        comboBox2.setPreferredSize(new Dimension(200, comboBox1.getPreferredSize().height));
        textField4 = new JTextField(18);
      
        modificationButton1 = new JButton("<");
        modificationButton2 = new JButton(">");
        modificationButton3 = new JButton("Modifier");
        modificationButton4 = new JButton("Supprimer");
        modificationButton5 = new JButton("Ajouter");
        ajouterButton = new JButton("Annuler");

        textcin = new JTextField(18);
        textnom = new JTextField(18);
        textemail = new JTextField(18);
        textprenom = new JTextField(18);
        
        dateField1 = new JFormattedTextField();
        comboBoxgenre = new JComboBox<>();
        comboBoxprofil = new JComboBox<>();
        
        labelcin = new JLabel("N CIN");
        labelnom = new JLabel("Nom");
        labelddn = new JLabel("Date de naissance");
        labelemail = new JLabel("Email");
        labelgenre = new JLabel("Genre");
        labelprenom = new JLabel("Prenom");
        labelprofil = new JLabel("Profil");

        labelprofil_liste = new JLabel("Profil");
        comboBoxprofil_liste = new JComboBox<>();
        textField = new JTextField(20);
        button1 = new JButton("Ajouter");
        button2 = new JButton("Annuler");

        barreMenus = new JMenuBar();

        gestion = new JMenu("Gestion");
        Rechercher = new JMenu("Rechercher");
        Couleurs = new JMenu("Couleurs");

        profil = new JMenuItem("Profil");
        employe = new JMenuItem("Employé");
        employe.add(new JSeparator());
        employepro = new JMenuItem("Employé/Profil");
        employepro.add(new JSeparator());
        quitter = new JMenuItem("quitter");
        quitter.add(new JSeparator());

        CinEmploye = new JMenuItem("Cin Employe");
        NomEmploye = new JMenuItem("Nom Employe");
        NomEmploye.add(new JSeparator());

        gestion.add(profil);
        gestion.add(employe);
        gestion.add(employepro);
        gestion.add(quitter);

        Rechercher.add(CinEmploye);
        Rechercher.add(NomEmploye);

        Bleu = new JMenuItem("Bleu");
        Rouge = new JMenuItem("Rouge");
        Blanc = new JMenuItem("Blanc");

        Couleurs.add(Bleu);
        Couleurs.add(Rouge);
        Couleurs.add(Blanc);

        barreMenus.add(gestion);
        barreMenus.add(Rechercher);
        barreMenus.add(Couleurs);
        
        
        String[] genres = {"Feminin", "Masculin"};
        JComboBox<String> comboBoxgenre = new JComboBox<>(genres);
        
        //String[] profill = {"manager", "student"};
        JComboBox<String> comboBoxprofil = new JComboBox<>();
        populateComboBoxFromDatabase(comboBoxprofil);
    
        
        

        setJMenuBar(barreMenus);
        Connection c = MyConnection.obtenirConnexion();
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Label");

        RechercheCIN rechercheCINAction = new RechercheCIN();
        CinEmploye.addActionListener(rechercheCINAction);
        
        RechercheNom rechercheNOMAction = new RechercheNom();
        NomEmploye.addActionListener(rechercheNOMAction);

        JButton deleteButton ;


        // Create the JTable with the DefaultTableModel
        profileTable = new JTable(tableModel);

        // Add the JTable to the right panel
        
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String libelle = textField.getText().trim();
                if (libelle.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Le champ libellé est obligatoire.", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                	pajouterProfil(libelle);
                	
                	populateComboBoxFromDatabase(comboBoxprofil);
                    populateComboBoxFromDatabase(comboBoxprofil_liste);
                    populateComboBoxFromDatabase(comboBox2);
                }
            }
        });
        
          button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reset specific text fields
                textField.setText("");          
            }
        });
          
          

        
        modificationButton5.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	
	        	 String libelleProfil = comboBoxprofil.getSelectedItem().toString();
	             String profil = comboBoxprofil.getSelectedItem().toString();

	             
	            
	             String genre = comboBoxgenre.getSelectedItem().toString();
	             // Convert java.util.Date to java.sql.Date
	             java.util.Date utilDate = (java.util.Date) dateField1.getValue();
	             java.sql.Date sqlDate = null; // Initialize as null

	             if (utilDate != null) {
	                 // Convert java.util.Date to java.sql.Date
	                 sqlDate = new java.sql.Date(utilDate.getTime());
	             }
	             String cin = textcin.getText().trim();
	             String nom = textnom.getText().trim();
	             String prenom = textprenom.getText().trim();
	           
	            
	            String email = textemail.getText().trim();
	           
	            // Vérifiez si tous les champs obligatoires sont remplis
	            if (cin.isEmpty() || nom.isEmpty() || prenom.isEmpty()  || email.isEmpty()) {
	                JOptionPane.showMessageDialog(null,
	                    "Tous les champs (CIN, nom, prénom, genre, date de naissance, email et ID de profil) sont obligatoires.",
	                    "Erreur",
	                    JOptionPane.ERROR_MESSAGE
	                );
	                return; // Ne faites rien si un champ obligatoire est vide
	            }
	            

	            // Vérifiez si le CIN existe dans la base de données
	            if (cinExisteDansLaBaseDeDonnees(cin)) {
	                JOptionPane.showMessageDialog(
	                    null,
	                    "Le CIN spécifié existe déjà dans la base de données.",
	                    "Erreur",
	                    JOptionPane.ERROR_MESSAGE
	                );
	                return; // Ne faites rien si le CIN existe déjà
	            }

	            // Ajoutez l'employé à la base de données
	            if (pajouterEmploye(cin,profil, nom, prenom,sqlDate, genre, email)) {
	            	JOptionPane.showMessageDialog(null, "L'employé a été ajouté avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
	            

	                // Réinitialisez les champs de saisie
	            	textcin.setText("");
	            	textnom.setText("");
	            	textprenom.setText("");
	            	comboBoxgenre.setSelectedIndex(0);
	                
	                textemail.setText("");
	                
	            } else {
	            	JOptionPane.showMessageDialog(null, "Une erreur", "Erreur", JOptionPane.ERROR_MESSAGE);
	            
	        }}});

        tabbedPane = new JTabbedPane();
        ajouterButton.addActionListener(new AnnulerButtonHandler());
        JPanel bienvenuePanel = new JPanel(new GridBagLayout());
        ImageIcon bienvenueImage = new ImageIcon("img\\img1.png");
        JLabel imageLabel = new JLabel(bienvenueImage);
     // Load your image file
        ImageIcon icon = new ImageIcon("img\\img1.png"); // Replace with the actual path to your image

        // Set the icon image for the JFrame
        setIconImage(icon.getImage());

        JLabel bienvenueLabel = new JLabel("Bienvenue dans la gestion des employes");
        bienvenueLabel.setFont(new Font("Algerian", Font.BOLD, 35));

        GridBagConstraints imageConstraints = new GridBagConstraints();
        imageConstraints.gridx = 0;
        imageConstraints.gridy = 0;
        imageConstraints.insets = new Insets(10, 10, 10, 10);
        bienvenuePanel.add(imageLabel, imageConstraints);

        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.gridx = 1;
        labelConstraints.gridy = 0;
        bienvenuePanel.add(bienvenueLabel, labelConstraints);

        tabbedPane.addTab("Bienvenue", bienvenuePanel);

        JPanel gestionProfilsPanel = new JPanel(new BorderLayout());

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setResizeWeight(0.25);

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.BLUE);
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel label = new JLabel("Libelle:");
        label.setForeground(Color.WHITE);

        deleteButton = new JButton("Delete");
        leftPanel.add(deleteButton);
        deleteButton.addActionListener(e -> {
            String selectedProfile = textField.getText().trim(); // Assuming the profile is in a textField

            if (!selectedProfile.isEmpty()) {
                // Check if the profile exists
                if (profileExists(selectedProfile)) {
                    int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to delete this profile and its associated employees?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        if (deleteProfile(selectedProfile) && deleteEmployeesWithProfile(selectedProfile)) {
                            // Deletion was successful
                        }
                        // Refresh the JTable data
                        loadProfileData();
                        // Refresh both ComboBox components
                        populateComboBoxFromDatabase(comboBoxprofil);
                        populateComboBoxFromDatabase(comboBoxprofil_liste);
                        populateComboBoxFromDatabase(comboBox2);
                    }
                } else {
                    // The profile doesn't exist, show a message
                    JOptionPane.showMessageDialog(null, "Profile does not exist.");
                }
            } else {
                // No profile entered in the textField, show a message
                JOptionPane.showMessageDialog(null, "Please enter a profile to delete.");
            }
        });

        leftPanel.add(label);
        leftPanel.add(textField);
        leftPanel.add(button1);
        leftPanel.add(button2);

        JPanel rightPanel = new JPanel();

        splitPane.setLeftComponent(leftPanel);
        splitPane.setRightComponent(rightPanel);

        gestionProfilsPanel.add(splitPane, BorderLayout.CENTER);

        tabbedPane.addTab("Gestion Profils", gestionProfilsPanel);
        
        rightPanel.add(new JScrollPane(profileTable));
        loadProfileData();


        JPanel gestionEmployesPanel = new JPanel(new BorderLayout()); // Use BorderLayout

        JPanel northPanel = new JPanel(new GridBagLayout());
        northPanel.setBackground(Color.BLUE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Component 1 - TextField1
        gbc.gridx = 0;
        northPanel.add(textField1, gbc);

        // Component 2 - ComboBox1
        gbc.gridx = 1;
        northPanel.add(comboBox1, gbc);

        // Component 3 - TextField2
        gbc.gridx = 2;
        northPanel.add(textField2, gbc);

        // Component 4 - TextField3
        gbc.gridx = 3;
        northPanel.add(textField3, gbc);

        // Component 5 - Date Field
        gbc.gridx = 4;
        northPanel.add(dateField, gbc);

        // Component 6 - ComboBox2
        gbc.gridx = 5;
        northPanel.add(comboBox2, gbc);

        // Component 7 - TextField4
        gbc.gridx = 6;
        northPanel.add(textField4, gbc);
        
        gbc.gridx = 1;
        northPanel.add(modificationButton1, gbc);
        modificationButton1.addActionListener(e -> showPreviousProfile());
        
        gbc.gridx = 2;
        northPanel.add(modificationButton2, gbc);
        modificationButton2.addActionListener(e -> showNextProfile());
        
        gbc.gridx = 4;
        northPanel.add(modificationButton3, gbc);
       
       
        gbc.gridx = 5;
        northPanel.add(modificationButton4, gbc);
        
        populateComboBoxFromDatabase(comboBox2);
        populateComboBoxFromDatabase(comboBoxprofil_liste);

      
              


       fillFieldsFromDatabase(textField1,textField2, comboBox1,comboBox2, textField3, dateField,   textField4);
       showProfile();
       
       modificationButton3.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
        	    
               updateEmployee(); // Appeler la fonction de mise à jour lorsque le bouton "Modifier" est cliqué
           }
       });
       
       modificationButton4.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	        deleteEmployee(); // Call the function for deletion when the "Modifier" button is clicked
    	    }
    	});
        
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(5, 5, 5, 5);

        gbc2.fill = GridBagConstraints.HORIZONTAL;

        gestionEmployesPanel.add(centerPanel, BorderLayout.CENTER);
        
        gbc2.gridx = 3;
        gbc2.gridy = 2;
        centerPanel.add(textcin, gbc2);
        
        gbc2.gridx = 3;
        gbc2.gridy = 3;
        centerPanel.add(textnom, gbc2);
        
        java.util.Date currentDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());

        dateField1.setValue(sqlDate);
        
        gbc2.gridx = 3;
        gbc2.gridy = 4;
        centerPanel.add(dateField1, gbc2);
        
        gbc2.gridx = 3;
        gbc2.gridy = 5;
        centerPanel.add(textemail, gbc2);
        
        gbc2.gridx = 7;
        gbc2.gridy = 3;
        centerPanel.add(textprenom, gbc2);
        
        
        gbc2.gridx = 7;
        gbc2.gridy = 2;
        centerPanel.add(comboBoxgenre, gbc2);
        
        gbc2.gridx = 7;
        gbc2.gridy = 4;
        centerPanel.add(comboBoxprofil, gbc2);
        
        gbc2.gridx = 2;
        gbc2.gridy = 2;
        centerPanel.add(labelcin, gbc2);
        
        gbc2.gridx = 2;
        gbc2.gridy = 3;
        centerPanel.add(labelnom, gbc2);
        
        gbc2.gridx = 2;
        gbc2.gridy = 4;
        centerPanel.add(labelddn, gbc2);
        
        gbc2.gridx = 2;
        gbc2.gridy = 5;
        centerPanel.add(labelemail, gbc2);
        
        gbc2.gridx = 6;
        gbc2.gridy = 2;
        centerPanel.add(labelgenre, gbc2);
        
        gbc2.gridx = 6;
        gbc2.gridy = 3;
        centerPanel.add(labelprenom, gbc2);
        
        gbc2.gridx = 6;
        gbc2.gridy = 4;
        centerPanel.add(labelprofil, gbc2);

        
     
        
        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.BLUE);
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridx = 1;
        southPanel.add(modificationButton5, gbc1);
        
        gbc.gridx = 1;
        gbc.gridx = 2;
        southPanel.add(ajouterButton, gbc1);
        
        gestionEmployesPanel.add(southPanel, BorderLayout.SOUTH);
        

        gestionEmployesPanel.add(northPanel, BorderLayout.NORTH); // Add northPanel to the north of gestionEmployesPanel

        tabbedPane.addTab("Gestion Employés", gestionEmployesPanel);
        
        getContentPane().add(tabbedPane);
        
        
        JPanel ListeEmpProfilPanel = new JPanel(new BorderLayout()); // Use BorderLayout

        JPanel upPanel = new JPanel(new GridBagLayout());
        upPanel.setBackground(Color.BLUE);
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        gbc3.insets = new Insets(5, 5, 5, 5);

        // Component 1 - TextField1
        gbc3.gridx = 4;
        upPanel.add(labelprofil_liste, gbc3);

        // Component 2 - ComboBox1
        gbc3.gridx = 5;
        upPanel.add(comboBoxprofil_liste, gbc3);
        
       
               
        ListeEmpProfilPanel.add(upPanel, BorderLayout.NORTH); // Add northPanel to the north of gestionEmployesPanel
        JPanel bottom_panel = new JPanel();
        table = new JTable();
        scrollPaneprofilemp = new JScrollPane(table); 
        bottom_panel.add(scrollPaneprofilemp, BorderLayout.CENTER);
        
        
       
           //update profilee
        
     // Set the default profile selection (e.g., "Manager") and update the table
        String defaultProfile = "Manager";
        comboBoxprofil_liste.setSelectedItem(defaultProfile);
        updateTable(defaultProfile);
        
        comboBoxprofil_liste.addItemListener(e -> {
            String selectedProfile = (String) comboBoxprofil_liste.getSelectedItem();
            updateTable(selectedProfile);
        });

        
		JSplitPane split_pane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, upPanel, bottom_panel);
        split_pane.setDividerLocation(50); 
        split_pane.setDividerSize(0);
        split_pane.setEnabled(false);
        split_pane.setPreferredSize(new Dimension(800, 600));

        ListeEmpProfilPanel.add(split_pane, BorderLayout.CENTER);
	    tabbedPane.addTab("Liste Emp/Profil", ListeEmpProfilPanel);
	    
	    tabbedPane.setEnabledAt(1, false); // "Gestion Profils" tab
        tabbedPane.setEnabledAt(2, false); // "Gestion Employés" tab
        tabbedPane.setEnabledAt(3, false); // "Liste Emp/profil" tab

	    
	    profil.addActionListener(e -> {
	        tabbedPane.setSelectedIndex(1); // Activate the "Gestion Profils" tab
	        tabbedPane.setEnabledAt(0, true); // Enable "Gestion Profils" tab
	        tabbedPane.setEnabledAt(1, true); // Disable "Gestion Employés" tab
	        tabbedPane.setEnabledAt(2, false); // Disable other tabs
	        tabbedPane.setEnabledAt(3, false); // Disable other tabs
	    });

	    employe.addActionListener(e -> {
	        tabbedPane.setSelectedIndex(2); // Activate the "Gestion Employés" tab
	        tabbedPane.setEnabledAt(0, true); // Disable "Gestion Profils" tab
	        tabbedPane.setEnabledAt(1, false); // Enable "Gestion Employés" tab
	        tabbedPane.setEnabledAt(2, true); // Disable other tabs
	        tabbedPane.setEnabledAt(3, false); // Disable other tabs
	    });

	    employepro.addActionListener(e -> {
	        tabbedPane.setSelectedIndex(3); // Activate the "Liste Emp/profil" tab
	        tabbedPane.setEnabledAt(0, true); // Disable "Gestion Profils" tab
	        tabbedPane.setEnabledAt(1, false); // Disable "Gestion Employés" tab
	        tabbedPane.setEnabledAt(2, false); 
	        tabbedPane.setEnabledAt(3, true); // Enable "Liste Emp/profil" tab
	    });
	    quitter.addActionListener(e -> {
	        int confirm = JOptionPane.showConfirmDialog(
	            this,
	            "Voulez-vous vraiment quitter l'application ?",
	            "Confirmation",
	            JOptionPane.YES_NO_OPTION
	        );

	        if (confirm == JOptionPane.YES_OPTION) {
	            int secondConfirm = JOptionPane.showConfirmDialog(
	                this,
	                "Êtes-vous sûr ? Cette action est irréversible.",
	                "Confirmation",
	                JOptionPane.YES_NO_OPTION
	            );

	            if (secondConfirm == JOptionPane.YES_OPTION) {
	                System.exit(0);
	            }
	        }
	    });
	    Bleu.addActionListener(new ActionListener() {
            public void actionPerformed1(ActionEvent e) {
                bienvenuePanel.setBackground(Color.BLUE);
                leftPanel.setBackground(Color.BLUE);
                northPanel.setBackground(Color.BLUE);
                southPanel.setBackground(Color.BLUE);
                upPanel.setBackground(Color.BLUE);

            }

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}

        });
        
        Rouge.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                bienvenuePanel.setBackground(Color.RED);
                leftPanel.setBackground(Color.RED);
                northPanel.setBackground(Color.RED);
                southPanel.setBackground(Color.RED);
                upPanel.setBackground(Color.RED);

            }
        });
        Blanc.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                bienvenuePanel.setBackground(Color.WHITE);
                leftPanel.setBackground(Color.WHITE);
                northPanel.setBackground(Color.WHITE);
                southPanel.setBackground(Color.WHITE);
                upPanel.setBackground(Color.WHITE);

                
                
            }
        });
        
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfaceGestion interfaceGestion = new InterfaceGestion();
            interfaceGestion.setVisible(true);
           
        });
    }
    
    
    
    
    
    class CinEmployeHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String employeecin = "";
            while (employeecin.isEmpty()) {
                employeecin = JOptionPane.showInputDialog("Entrez le cin de l'employé:");
                if (employeecin == null) {
                    break;
                }
            }

            if (employeecin != null && !employeecin.isEmpty()) {

            }
        }
    }
    
    
    
     class NomEmployeHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle the Nom Employé event here
            // You can access the event information through the 'e' parameter
            String employeeName = "";
            while (employeeName.isEmpty()) {
                employeeName = JOptionPane.showInputDialog("Entrez le nom de l'employé:");
                if (employeeName == null) {
                    // User clicked Cancel, so break out of the loop
                    break;
                }
            }

            if (employeeName != null && !employeeName.isEmpty()) {
                // Handle the employee name (perform search or other action)
                // You can add your code here to search for an employee by name
                // Example: Search for the employee in your data
            }
        }
    }
     public class AnnulerButtonHandler implements ActionListener {
 	    @Override
 	    public void actionPerformed(ActionEvent e) {
 	        // Reset all input fields in the central zone to their initial values
 	        resetComboBox(comboBox1);
 	        resetComboBox(comboBox2);
 	        resetComboBox(comboBoxgenre);
 	        resetComboBox(comboBoxprofil);
 	        resetTextField(textField1);
 	        resetTextField(textField2);
 	        resetTextField(textField3);
 	        resetTextField(textField4);
 	        resetTextField(textcin);
 	        resetTextField(textnom);
 	        resetTextField(dateField1);
 	        resetTextField(textemail);
 	        resetTextField(textprenom);
 	        resetTextField(dateField);
 	    }

 	    private void resetComboBox(JComboBox<String> comboBox) {
 	        if (comboBox.getItemCount() > 0) {
 	            comboBox.setSelectedIndex(0); // Reset combo box 
 	        }
 	    }

 	    private void resetTextField(JTextField textField) {
 	        textField.setText(""); // Reset text field
 	    }
 	}
  // Méthode pour ajouter un employé à la base de données avec tous les champs
     boolean pajouterEmploye(String cin, String profil, String nom, String prenom, Date datenais, String genre, String email) {
    	    Connection connexion = MyConnection.obtenirConnexion();
    	    PreparedStatement preparedStatement = null;
    	    boolean isValid = true; // Add a flag to check if all validations pass

    	    try {
    	        if (!isValidCIN(cin)) {
    	            JOptionPane.showMessageDialog(null, "Veuillez saisir un CIN valide (chiffres uniquement).", "Erreur", JOptionPane.ERROR_MESSAGE);
    	            isValid = false; // Set the flag to false, indicating a validation failure
    	        }

    	        // Vérifier si l'e-mail est au bon format
    	        if (!isValidEmail(email)) {
        	        JOptionPane.showMessageDialog(null, "Veuillez saisir une adresse e-mail valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        	        return false; // Return false immediately if email validation fails
        	    }


    	        String sql = "INSERT INTO employe (cin, profil, nom, prenom, datnais, genre, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
    	        preparedStatement = connexion.prepareStatement(sql);
    	        preparedStatement.setString(1, cin);
    	        preparedStatement.setString(2, profil);
    	        preparedStatement.setString(3, nom);
    	        preparedStatement.setString(4, prenom);
    	        preparedStatement.setDate(5, datenais);
    	        preparedStatement.setString(6, genre);
    	        preparedStatement.setString(7, email);

    	        int lignesAffectees = preparedStatement.executeUpdate();

    	        if (lignesAffectees > 0) {
    	            // Create a new employee object with the added data
    	            Employe newEmployee = new Employe();
    	            newEmployee.cin = cin;
    	            newEmployee.profil = profil;
    	            newEmployee.nom = nom;
    	            newEmployee.prenom = prenom;
    	            newEmployee.datnais = datenais;
    	            newEmployee.genre = genre;
    	            newEmployee.email = email;
    	            
    	            // Add the new employee to the in-memory list
    	            employeeList.add(newEmployee);

    	            // Display the added profile immediately
    	            showNextProfile();

    	            return true;
    	        }
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    } finally {
    	        // Fermez les ressources
    	        try {
    	            if (preparedStatement != null) {
    	                preparedStatement.close();
    	            }
    	            if (connexion != null) {
    	                connexion.close();
    	            }
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }
    	    }

 	    return false; // Retourne false par défaut (en cas d'erreur)
 	}
     
     private boolean isValidCIN(String cin) {
    	    String cinRegex = "\\d+"; // This regex ensures that cin contains only digits
    	    return cin.matches(cinRegex);
    	}

    	// Méthode pour vérifier si l'e-mail est au bon format
    	private boolean isValidEmail(String email) {
    		String emailRegex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+)\\.[A-Za-z]{2,5}$";
    	    return email.matches(emailRegex);
    	}
     
     public static int getIdProfilByLibelle(String libelleProfil) {
         Connection connexion = MyConnection.obtenirConnexion();
         PreparedStatement preparedStatement = null;

         try {
             String sql = "SELECT idprofil FROM profil WHERE libelle = ?";
             preparedStatement = connexion.prepareStatement(sql);
             preparedStatement.setString(1, libelleProfil);
             ResultSet resultSet = preparedStatement.executeQuery();

             if (resultSet.next()) {
                 return resultSet.getInt("idprofil");
             }
         } catch (SQLException e) {
             e.printStackTrace();
         } finally {
             try {
                 if (connexion != null) {
                     connexion.close();
                 }
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }

         return -1; // Retourne -1 en cas d'erreur
     }
     boolean cinExisteDansLaBaseDeDonnees(String cin) {
 	    Connection connexion = MyConnection.obtenirConnexion();
 	    
 	    PreparedStatement preparedStatement = null;
 	    ResultSet resultSet=null;

 	    try {
 	        String sql = "SELECT COUNT(*) FROM employe WHERE cin = ?";
 	        preparedStatement = connexion.prepareStatement(sql);
 	        preparedStatement.setString(1, cin);
 	        resultSet = preparedStatement.executeQuery();

 	        if (resultSet.next()) {
 	            int count = resultSet.getInt(1);
 	            return count > 0; // Retourne true si le CIN existe déjà
 	        }
 	    } catch (SQLException e) {
 	        e.printStackTrace();
 	    } finally {
 	        // Fermez les ressources
 	        try {
 	            if (preparedStatement != null) {
 	                preparedStatement.close();
 	            }
 	            if (connexion != null) {
 	                connexion.close();
 	            }
 	        } catch (SQLException e) {
 	            e.printStackTrace();
 	        }
 	    }

 	    return false; // Retourne false par défaut (en cas d'erreur)

 }
     boolean pajouterProfil(String libelle) {
 	    Connection connexion = MyConnection.obtenirConnexion();
 	    PreparedStatement preparedStatement = null;

 	    try {
 	        if (profileExists(libelle)) {
 	            JOptionPane.showMessageDialog(null, "This profile already exists.", "Error", JOptionPane.ERROR_MESSAGE);
 	            return false;
 	        }

 	        // Add the new profile to the database
 	        String sql = "INSERT INTO profil (libelle) VALUES (?)";
 	        preparedStatement = connexion.prepareStatement(sql);
 	        preparedStatement.setString(1, libelle);
 	        int lignesAffectees = preparedStatement.executeUpdate();

 	        if (lignesAffectees > 0) {
 	            // After successfully adding the profile to the database, update the table
 	            loadProfileData(); // Update the table with the new data
 	            // You can also display a success message here
 	            return true;
 	        } else {
 	            // Handle the case where adding the profile failed
 	            return false;
 	        }
 	    } catch (SQLException e) {
 	        e.printStackTrace();
 	        return false;
 	    } finally {
 	        // Close database resources
 	        try {
 	            if (preparedStatement != null) {
 	                preparedStatement.close();
 	            }
 	            if (connexion != null) {
 	                connexion.close();
 	            }
 	        } catch (SQLException e) {
 	            e.printStackTrace();
 	        }
 	    }
 	}

     private void resetTextField(JTextField textField5) {
		// Auto-generated method stub
		
	}


	private boolean profilExisteDansLaBaseDeDonnees(String libelle) {
    	    Connection connexion = MyConnection.obtenirConnexion();
    	    PreparedStatement preparedStatement = null;
    	    ResultSet resultSet = null;

    	    try {
    	        String sql = "SELECT COUNT(*) FROM profil WHERE libelle = ?";
    	        preparedStatement = connexion.prepareStatement(sql);
    	        preparedStatement.setString(1, libelle);
    	        resultSet = preparedStatement.executeQuery();

    	        if (resultSet.next()) {
    	            int count = resultSet.getInt(1);
    	            return count > 0; // Return true if the libelle already exists
    	        }
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    } finally {
    	        // Close resources
    	        try {
    	            if (preparedStatement != null) {
    	                preparedStatement.close();
    	            }
    	            if (connexion != null) {
    	                connexion.close();
    	            }
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }
    	    }

    	    return false; // Return false by default (in case of an error)
    	}
	private void loadProfileData() {
	    // Retrieve profile data from your database
	    List<Profile> profiles = retrieveProfileDataFromDatabase();

	    // Clear the table
	    tableModel.setRowCount(0);

	    // Populate the table with the retrieved data
	    for (Profile profile : profiles) {
	        tableModel.addRow(new Object[] { profile.getId(), profile.getLabel() });
	    }
	}
	private List<Profile> retrieveProfileDataFromDatabase() {
	    Connection connection = MyConnection.obtenirConnexion();
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    List<Profile> profiles = new ArrayList<>();

	    try {
	        String sql = "SELECT idprofil, libelle FROM profil";
	        preparedStatement = connection.prepareStatement(sql);
	        resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            int id = resultSet.getInt("idprofil");
	            String label = resultSet.getString("libelle");
	            profiles.add(new Profile(id, label));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close database resources
	        try {
	            
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return profiles;
	}
	 private static class Profile {
	        private int id;
	        private String label;

	        public Profile(int id, String label) {
	            this.id = id;
	            this.label = label;
	        }

	        public int getId() {
	            return id;
	        }

	        public String getLabel() {
	            return label;
	        }
	    }
	 
	 private static class Employe{
		 private String cin;
		 
		 private String prenom,nom,email,datenaiss,genre,profil;

		public Date datnais;
		 
		public Employe() {
			super();
		}
		public Employe(String cin, String prenom, String nom, String email, String datenaiss, String genre,
				String profil) {
			super();
			this.cin = cin;
			this.prenom = prenom;
			this.nom = nom;
			this.email = email;
			this.datenaiss = datenaiss;
			this.genre = genre;
			this.profil = profil;
		}
		public String getCin() {
			return cin;
		}
		public void setCin(String cin) {
			this.cin = cin;
		}
		public String getPrenom() {
			return prenom;
		}
		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getDatenaiss() {
			return datenaiss;
		}
		public void setDatenaiss(String datenaiss) {
			this.datenaiss = datenaiss;
		}
		public String getGenre() {
			return genre;
		}
		public void setGenre(String genre) {
			this.genre = genre;
		}
		public String getProfil() {
			return profil;
		}
		public void setProfil(String profil) {
			this.profil = profil;
		}
		 
		 
	 }
	 
	 
	 
	 class RechercheCIN implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	            String employeCIN;
	            boolean cinEntered = false;

	            while (!cinEntered) {
	                employeCIN = JOptionPane.showInputDialog("Enter the cin of an employee:");
	                if (employeCIN == null) {
	                    // User clicked "Cancel" or closed the input dialog
	                    break;
	                } else if (employeCIN.isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "Please enter a cin.", "Error", JOptionPane.ERROR_MESSAGE);
	                } else {
	                    // Check if the employee name exists in the database
	                    if (employeeExistsInDatabase(employeCIN)) {
	                        JOptionPane.showMessageDialog(null, "Employee with cin '" + employeCIN + "' exists.", "Exists", JOptionPane.INFORMATION_MESSAGE);
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Employee with cin '" + employeCIN + "' does not exist.", "Not Found", JOptionPane.INFORMATION_MESSAGE);
	                    }
	                    cinEntered = true;
	                }
	            }
	        }
	    }
	 class RechercheNom implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	            String employenom;
	            boolean nomEntered = false;
                
	            while (!nomEntered) {
	            	employenom = JOptionPane.showInputDialog("Enter the name of an employee:");

	                if (employenom == null) {
	                    // User clicked "Cancel" or closed the input dialog
	                    break;
	                } else if (employenom.isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "Entrer le nom dun employe.", "Error", JOptionPane.ERROR_MESSAGE);
	                } else {
	                    // Check if the employee name exists in the database
	                    if (employeeExistsByNameInDatabase(employenom)) {
	                        JOptionPane.showMessageDialog(null, "Employee avec nom '" + employenom + "' exist.", "Exists", JOptionPane.INFORMATION_MESSAGE);
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Employee avec nom '" + employenom + "' n'existe pas.", "Not Found", JOptionPane.INFORMATION_MESSAGE);
	                    }
	                    nomEntered = true;
	                }
	            }
	        }
	    }


	    private boolean employeeExistsInDatabase(String cin) {
	             Connection connection = MyConnection.obtenirConnexion();
	             if (connection != null) {
	                 try {
	                     String sql = "SELECT * FROM employe WHERE cin = ?";
	                     PreparedStatement preparedStatement = connection.prepareStatement(sql);
	                     preparedStatement.setString(1, cin);
	                     
	                     ResultSet result = preparedStatement.executeQuery();
	                     // Check if there are any rows with the given CIN
	                     return result.next();
	                 } catch (SQLException e) {
	                     e.printStackTrace();
	                 }
	             }
	             return false;
	         }
	    private boolean employeeExistsByNameInDatabase(String name) {
	        Connection connection = MyConnection.obtenirConnexion();
	        if (connection != null) {
	            try {
	                String sql = "SELECT * FROM employe WHERE nom like ?";
	                PreparedStatement preparedStatement = connection.prepareStatement(sql);
	                preparedStatement.setString(1, name);
	                ResultSet resultSet = preparedStatement.executeQuery();
	                return resultSet.next(); // Returns true if a record with the given name exists
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return false;
	    }
	 	 // Get the total count of employees
	    
	    
	 // Get the total count of employees
	    private int getTotalEmployeeCount() {
	        Connection connection = MyConnection.obtenirConnexion();
	        int count = 0;

	        if (connection != null) {
	            PreparedStatement preparedStatement = null;
	            ResultSet resultSet = null;
	            
	            try {
	                String sql = "SELECT COUNT(*) FROM employe"; // Replace with your table name
	                preparedStatement = connection.prepareStatement(sql);
	                resultSet = preparedStatement.executeQuery();

	                if (resultSet.next()) {
	                    count = resultSet.getInt(1);
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            } finally {
	                // Close the ResultSet, PreparedStatement, and Connection in the reverse order of opening
	               
	                
	                if (preparedStatement != null) {
	                    try {
	                        preparedStatement.close();
	                    } catch (SQLException e) {
	                        e.printStackTrace();
	                    }
	                }
	                
	                if (connection != null) {
	                    try {
	                        connection.close();
	                    } catch (SQLException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }
	        }

	        return count;
	    }

	    
	    
	    
	    private static void populateComboBoxFromDatabase( JComboBox<String> comboBox) {
	    	Connection connection = MyConnection.obtenirConnexion();
	    	
	    	 if (connection != null) {
		            PreparedStatement preparedStatement = null;
		            ResultSet resultSet = null;
	    	 
	    	try {
                // Créez une instruction SQL
                String sql = "SELECT   libelle  FROM  profil";
               
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();

                // Nettoyez le modèle du ComboBox
                comboBox.removeAllItems();

                // Remplissez le ComboBox avec les données de la base de données
                while (resultSet.next()) {
                    String value = resultSet.getString("libelle");
                    comboBox.addItem(value);
                }

	    	}catch (SQLException e) {
                e.printStackTrace();
	    	 
	    	}
	    	}
	    }
	    
	    


      private  void fillFieldsFromDatabase(JTextField textField2,JTextField textField1, JComboBox<String> comboBox1, JComboBox<String> comboBox2, JTextField textField3, JFormattedTextField dateField,  JTextField textField4) {
    		Connection connection = MyConnection.obtenirConnexion();
    		 if (connection != null) {
		            PreparedStatement preparedStatement = null;
		        
    	  
    	  // Exécutez une requête pour récupérer les données de l'employé
        String query = "SELECT * FROM employe ";
        try {
            if (connection != null) {
           
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();
                employeeList = new ArrayList<>();

                while (resultSet.next()) {
                    Employe employee = new Employe();
                    employee.cin = resultSet.getString("cin");
                    employee.genre = resultSet.getString("genre");
                    employee.nom = resultSet.getString("nom");
                    employee.prenom = resultSet.getString("prenom");
                    employee.email = resultSet.getString("email");
                    employee.datnais = resultSet.getDate("datnais");
                    employee.profil = resultSet.getString("profil");
                    employeeList.add(employee);
                }

                // Fermez les ressources de base de données lorsque vous avez terminé
                
                statement.close();

                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }}
    }
    
      public void showProfile() {
          if (currentIndex >= 0 && currentIndex < employeeList.size()) {
              Employe employee = employeeList.get(currentIndex);
              textField1.setText(employee.cin);
              comboBox2.setSelectedItem(employee.genre);
              textField3.setText(employee.nom);
              textField2.setText(employee.prenom);
              textField4.setText(employee.email);
              dateField.setValue(employee.datnais);
             // comboBox1.setSelectedItem(employee.profil);
          }
      }
    public void showNextProfile() {
          if (currentIndex < employeeList.size() - 1) {
              currentIndex++;
              showProfile();
          }
      }

      public void showPreviousProfile() {
          if (currentIndex > 0) {
              currentIndex--;
              showProfile();
          }
      }
      private boolean updateEmployee() {
    	    Connection connection = MyConnection.obtenirConnexion();
    	    boolean isValid = true; // Set isValid to true initially, assuming all validations pass
    	    String email = textField4.getText();

    	    // Check if the email is in the correct format
    	    if (!isValidEmail(email)) {
    	        JOptionPane.showMessageDialog(null, "Veuillez saisir une adresse e-mail valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
    	        return false; // Return false immediately if email validation fails
    	    }

    	    if (connection != null) {
    	        String query = "UPDATE employe SET cin=?, profil=?, nom=?, prenom=?, datnais=?, email=? WHERE cin = ?";

    	        try {
    	            PreparedStatement statement = connection.prepareStatement(query);
    	            statement.setString(1, textField1.getText());
    	            statement.setString(2, comboBox2.getSelectedItem().toString());
    	            statement.setString(3, textField2.getText());
    	            statement.setString(4, textField3.getText());
    	            statement.setDate(5, new java.sql.Date(((java.util.Date) dateField.getValue()).getTime()));
    	            statement.setString(6, textField4.getText());
    	            statement.setString(7, textField1.getText());

    	            int rowsUpdated = statement.executeUpdate();

    	            if (rowsUpdated > 0) {
    	            	
    	            
    	                int confirm = JOptionPane.showConfirmDialog(
    	                    this,
    	                    "Voulez-vous vraiment modifier l'employée ?",
    	                    "Confirmation",
    	                    JOptionPane.YES_NO_OPTION
    	                );

    	                if (confirm == JOptionPane.YES_OPTION) {
    	                    int secondConfirm = JOptionPane.showConfirmDialog(
    	                        this,
    	                        "Êtes-vous sûr ",
    	                        "Confirmation",
    	                        JOptionPane.YES_NO_OPTION
    	                    );

    	                    if (secondConfirm == JOptionPane.YES_OPTION) {
    	                        JOptionPane.showMessageDialog(null, "L'employé a été modifié avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
    	                    }
    	                }
    	            }
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	            isValid = false; // Set isValid to false in case of a SQL exception
    	        }
    	    }
    	    
    	    return isValid; // Return the isValid flag
    	}

      
      private void deleteEmployee() {
    	    Connection connection = MyConnection.obtenirConnexion();

    	    if (connection != null) {
    	        String cin = textField1.getText(); // Get the CIN of the employee to be deleted
    	        int confirm = JOptionPane.showConfirmDialog(
    	            this,
    	            "Voulez-vous vraiment supprimer cet employé ?",
    	            "Confirmation",
    	            JOptionPane.YES_NO_OPTION
    	        );

    	        if (confirm == JOptionPane.YES_OPTION) {
    	            try {
    	                String query = "DELETE FROM employe WHERE cin = ?";
    	                PreparedStatement statement = connection.prepareStatement(query);
    	                statement.setString(1, cin);

    	                int rowsDeleted = statement.executeUpdate();

    	                if (rowsDeleted > 0) {
    	                    JOptionPane.showMessageDialog(null, "L'employé a été supprimé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
    	                    // Clear the fields or update the UI as needed
    	                    textField1.setText(""); // Clear the CIN field
                            textField2.setText(""); 
                            textField3.setText("");
                            textField4.setText("");
                            dateField.setValue(null);
    	                }
    	            } catch (SQLException e) {
    	                e.printStackTrace();
    	            }
    	        }
    	    }
    	}
	    // Get employee data by index
	    private ResultSet getEmployeeDataByIndex(int index) throws SQLException {
	        model = new MonModel(new Object[0][4], new String[] {"CIN", "Nom", "Prenom", "Email"});
	        ResultSet resultSet ;
	        try (Connection connection = MyConnection.obtenirConnexion();
	             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employe WHERE cin = ?")) {
	            preparedStatement.setInt(1, index + 1); // Assuming your employee IDs are 1-based
	            resultSet = preparedStatement.executeQuery();
	        }
	        return resultSet;
	    }
	    
	    private boolean deleteEmployeesWithProfile(String profile) {
	        Connection connection = MyConnection.obtenirConnexion();
	        if (connection != null) {
	            try {
	                String sql = "DELETE FROM employe WHERE profil = ?";
	                PreparedStatement preparedStatement = connection.prepareStatement(sql);
	                preparedStatement.setString(1, profile);

	                int rowsAffected = preparedStatement.executeUpdate();

	                if (rowsAffected > 0) {
	                    // Deletion successful
	                    return true;
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	                // Handle exceptions appropriately
	            }
	        }

	        return false; // Deletion failed
	    }
	    
	    public static boolean profileExists(String profile) {
	        Connection connection = MyConnection.obtenirConnexion();
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;

	        try {
	            String sql = "SELECT COUNT(*) FROM profil WHERE libelle = ?";
	            preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setString(1, profile);
	            resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                int count = resultSet.getInt(1);
	                return count > 0;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            // Close resources here
	        }
	        return false; // Return false if there's an error or no results found
	    }
	    
	    private boolean deleteProfile(String profile) {
	        Connection connection = MyConnection.obtenirConnexion();
	        if (connection != null) {
	            try {
	                String sql = "DELETE FROM profil WHERE libelle = ?";
	                PreparedStatement preparedStatement = connection.prepareStatement(sql);
	                preparedStatement.setString(1, profile);

	                int rowsAffected = preparedStatement.executeUpdate();

	                if (rowsAffected > 0) {
	                    // Deletion successful
	                    return true;
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	                // Handle exceptions appropriately
	            }
	        }

	        return false; // Deletion failed
	    }
	    
	    public static boolean profileExists(String profile) {
	        Connection connection = MyConnection.obtenirConnexion();
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;

	        try {
	            String sql = "SELECT COUNT(*) FROM profil WHERE libelle = ?";
	            preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setString(1, profile);
	            resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                int count = resultSet.getInt(1);
	                return count > 0;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            // Close resources here
	        }
	        return false; // Return false if there's an error or no results found
	    }
	    
	    private void updateTable(String selectedProfile) {
	        Connection connection = MyConnection.obtenirConnexion();
	        if (connection != null) {
	            try {
	                String sql = "SELECT cin, nom, prenom, email FROM employe WHERE profil = ?";
	                PreparedStatement preparedStatement = connection.prepareStatement(sql);
	                preparedStatement.setString(1, selectedProfile);

	                ResultSet resultSet = preparedStatement.executeQuery();

	                // Create a list to hold the data
	                List<Object[]> data = new ArrayList<>();

	                while (resultSet.next()) {
	                    String cin = resultSet.getString("cin");
	                    String nom = resultSet.getString("nom");
	                    String prenom = resultSet.getString("prenom");
	                    String email = resultSet.getString("email");

	                    // Add data to the list
	                    data.add(new Object[] {cin, nom, prenom, email});
	                }

	                // Convert the list to a two-dimensional array
	                Object[][] dataArray = new Object[data.size()][4];
	                for (int i = 0; i < data.size(); i++) {
	                    dataArray[i] = data.get(i);
	                    
	                }
	  
	     
	        	    String profiltitle[] = {"cin", "nom","prenom","email"};
	        	    model =  new MonModel(dataArray, profiltitle);
                   
	                // Update the model with new data
	                table.setModel(model);
	            } catch (SQLException e) {
	                e.printStackTrace();
	                // Handle exceptions appropriately.
	            }
	        }
	    }


}
