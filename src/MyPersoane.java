import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MyPersoane extends JFrame {


    private JLabel numeEticheta;
    private JTextField campNume;

    private JLabel prenumeEticheta;
    private JTextField campPrenume;

    private JLabel emailEticheta;
    private JTextField campEmail;

    private JLabel facultateEticheta;
    private JComboBox<String> campFaculatate;

    private JLabel studiiEticheta;
    private JComboBox<String> campStudii;

    private JLabel specializareEticheta;
    private JComboBox<String> campSpecializare;

    private JLabel anEticheta;
    private JComboBox<String> campAn;

    private JButton butonAdd;

    private List<Persoana> persoane;

    JTextArea textArea = new JTextArea();
    public MyPersoane() {
        super("Gestiune persoane");
        this.setSize(350, 600);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        persoane = new ArrayList<>();


        add(new JScrollPane(textArea));

        numeEticheta = new JLabel("Nume:");
        numeEticheta.setHorizontalAlignment(SwingUtilities.RIGHT);
        numeEticheta.setPreferredSize(new Dimension(60, 12));

        prenumeEticheta = new JLabel("Prenume:");
        prenumeEticheta.setHorizontalAlignment(SwingUtilities.RIGHT);
        prenumeEticheta.setPreferredSize(new Dimension(60, 12));

        emailEticheta = new JLabel("Email:");
        emailEticheta.setHorizontalAlignment(SwingUtilities.RIGHT);
        emailEticheta.setPreferredSize(new Dimension(60, 12));

        facultateEticheta = new JLabel("Facultate:");
        facultateEticheta.setHorizontalAlignment(SwingUtilities.RIGHT);
        facultateEticheta.setPreferredSize(new Dimension(60, 12));

        studiiEticheta = new JLabel("Studii:");
        studiiEticheta.setHorizontalAlignment(SwingUtilities.RIGHT);
        studiiEticheta.setPreferredSize(new Dimension(60, 12));

        specializareEticheta = new JLabel("Specializare:");
        specializareEticheta.setHorizontalAlignment(SwingUtilities.RIGHT);
        specializareEticheta.setPreferredSize(new Dimension(80, 12));

        anEticheta = new JLabel("An de Studii:");
        anEticheta.setHorizontalAlignment(SwingUtilities.RIGHT);
        anEticheta.setPreferredSize(new Dimension(80, 12));

        campNume = new JTextField(20);
        campPrenume = new JTextField(20);
        campEmail = new JTextField(20);
        campFaculatate = new JComboBox<>(new String[]{"Facultatea de Arte și Design", "Facultatea de Chimie, Biologie, Geografie",
                "Facultatea de Drept", "Facultatea de Economie și de Administrare a Afacerilor", "Facultatea de Educație Fizică și Sport",
                "Facultatea de Fizică", "Facultatea de Litere, Istorie și Teologie", "Facultatea de Matematică și Informatică",
                "Facultatea de Muzică și Teatru", "Facultatea de Sociologie și Psihologie",
                "Facultatea de Științe Politice, Filosofie și Științe ale Comunicării"});

        //campSpecializare = new JComboBox<>(new String[]{"Info", "Info Aplicata", "Info engleza"});
        campStudii = new JComboBox<>(new String[]{"Licenta", "Master", "Doctorat                                     "});

// Inițializează un model pentru anii de studiu
        DefaultComboBoxModel<String> modelAni = new DefaultComboBoxModel<>(new String[]{"I", "II", "III"});
        DefaultComboBoxModel<String> modelFacultate = new DefaultComboBoxModel<>(new String[]{"Arte plastice","Design","Arte Decorative"});
        campSpecializare= new JComboBox<>(modelFacultate);
        campAn = new JComboBox<>(modelAni);

        campStudii.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studiiSelectate = (String) campStudii.getSelectedItem();
                String facultateSelectata = (String) campFaculatate.getSelectedItem();
                // Schimbă modelul în funcție de selecția făcută în campStudii
                if (studiiSelectate.equals("Master")) {
                    modelAni.removeAllElements();
                    modelAni.addElement("I");
                    modelAni.addElement("II");
                } else {
                    modelAni.removeAllElements();
                    modelAni.addElement("I");
                    modelAni.addElement("II");
                    modelAni.addElement("III");
                }
                // Clear modelFacultate
                modelFacultate.removeAllElements();

                switch (facultateSelectata) {
                    case "Facultatea de Arte și Design":
                        if (studiiSelectate.equals("Licenta")) {
                            modelFacultate.addElement("Arte plastice");
                            modelFacultate.addElement("Design");
                            modelFacultate.addElement("Arte Decorative           ");
                        } else if (studiiSelectate.equals("Master")) {
                            modelFacultate.addElement("Design  interior de produs");
                            modelFacultate.addElement("Design grafic");
                            modelFacultate.addElement("Design vestimentar");
                        } else if (studiiSelectate.equals("Doctorat")) {

                        }
                        break;

                    case "Facultatea de Chimie, Biologie, Geografie":
                        if (studiiSelectate.equals("Licenta")) {
                            modelFacultate.addElement("Biologie");
                            modelFacultate.addElement("Chimie");
                            modelFacultate.addElement("Geografie                  ");
                        } else if (studiiSelectate.equals("Master")) {
                            modelFacultate.addElement("Biologie-chimie            ");
                            modelFacultate.addElement("Geografie");
                        } else if (studiiSelectate.equals("Doctorat")) {

                        }
                        break;

                    case "Facultatea de Drept":
                        if (studiiSelectate.equals("Licenta")) {
                            modelFacultate.addElement("Drept                      ");
                        } else if (studiiSelectate.equals("Master")) {
                            modelFacultate.addElement("Dreptul afacerilor         ");
                            modelFacultate.addElement("Stiinte penale");
                        } else if (studiiSelectate.equals("Doctorat")) {

                        }
                        break;
                    case "Facultatea de Economie și de Administrare a Afacerilor":
                        if (studiiSelectate.equals("Licenta")) {
                            modelFacultate.addElement("Economie");
                            modelFacultate.addElement("Informatica aplicata       ");
                        } else if (studiiSelectate.equals("Master")) {
                            modelFacultate.addElement("Economie                   ");
                        } else if (studiiSelectate.equals("Doctorat")) {

                        }
                        break;
                    case "Facultatea de Educație Fizică și Sport":
                        if (studiiSelectate.equals("Licenta")) {
                            modelFacultate.addElement("Educatie fizica si sportiva");
                            modelFacultate.addElement("Sport");
                        } else if (studiiSelectate.equals("Master")) {
                            modelFacultate.addElement("Sport                       ");
                        } else if (studiiSelectate.equals("Doctorat")) {

                        }
                        break;
                    case "Facultatea de Fizică":
                        if (studiiSelectate.equals("Licenta")) {
                            modelFacultate.addElement("Fizica                      ");
                        } else if (studiiSelectate.equals("Master")) {
                            modelFacultate.addElement("Fizica                      ");
                        } else if (studiiSelectate.equals("Doctorat")) {

                        }
                        break;
                    case "Facultatea de Litere, Istorie și Teologie":
                        if (studiiSelectate.equals("Licenta")) {
                            modelFacultate.addElement("Litere");
                            modelFacultate.addElement("Istorie");
                            modelFacultate.addElement("Teologie                    ");
                        } else if (studiiSelectate.equals("Master")) {
                            modelFacultate.addElement("Teologie                    ");
                        } else if (studiiSelectate.equals("Doctorat")) {

                        }
                        break;
                    case "Facultatea de Matematică și Informatică":
                        if (studiiSelectate.equals("Licenta")) {
                            modelFacultate.addElement("Infromatica romana");
                            modelFacultate.addElement("Infromatica aplicata");
                            modelFacultate.addElement("Infromatica engleza         ");
                        } else if (studiiSelectate.equals("Master")) {
                            modelFacultate.addElement("Infromatica                 ");
                        } else if (studiiSelectate.equals("Doctorat")) {

                        }
                        break;
                    case "Facultatea de Muzică și Teatru":
                        if (studiiSelectate.equals("Licenta")) {
                            modelFacultate.addElement("Muzica");
                            modelFacultate.addElement("Teatru                       ");
                        } else if (studiiSelectate.equals("Master")) {
                            modelFacultate.addElement("Muzica                       ");
                        } else if (studiiSelectate.equals("Doctorat")) {

                        }
                        break;
                    case "Facultatea de Sociologie și Psihologie":
                        if (studiiSelectate.equals("Licenta")) {
                            modelFacultate.addElement("Sociiologie                   ");
                            modelFacultate.addElement("Psihiatrie");
                        } else if (studiiSelectate.equals("Master")) {
                            modelFacultate.addElement("Psihiatrie                    ");
                        } else if (studiiSelectate.equals("Doctorat")) {

                        }
                        break;
                    case "Facultatea de Științe Politice, Filosofie și Științe ale Comunicării":
                        if (studiiSelectate.equals("Licenta")) {
                            modelFacultate.addElement("Științe Politice");
                            modelFacultate.addElement(" Filosofie");
                            modelFacultate.addElement("Științe ale Comunicării       ");
                        } else if (studiiSelectate.equals("Master")) {
                            modelFacultate.addElement("Științe ale Comunicării       ");

                        } else if (studiiSelectate.equals("Doctorat")) {
                        }
                        break;
                    default:

                        break;
                }

            }
        });


        campFaculatate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studiiSelectate = (String) campStudii.getSelectedItem();
                String facultateSelectata = (String) campFaculatate.getSelectedItem();

                // Clear modelFacultate
                modelFacultate.removeAllElements();
                switch (facultateSelectata) {
                    case "Facultatea de Arte și Design":
                        if (studiiSelectate.equals("Licenta")) {
                            modelFacultate.addElement("Arte plastice");
                            modelFacultate.addElement("Design");
                            modelFacultate.addElement("Arte Decorative           ");
                        } else if (studiiSelectate.equals("Master")) {
                            modelFacultate.addElement("Design  interior de produs");
                            modelFacultate.addElement("Design grafic");
                            modelFacultate.addElement("Design vestimentar");
                        } else if (studiiSelectate.equals("Doctorat")) {

                        }
                        break;

                    case "Facultatea de Chimie, Biologie, Geografie":
                        if (studiiSelectate.equals("Licenta")) {
                            modelFacultate.addElement("Biologie");
                            modelFacultate.addElement("Chimie");
                            modelFacultate.addElement("Geografie                  ");
                        } else if (studiiSelectate.equals("Master")) {
                            modelFacultate.addElement("Biologie-chimie            ");
                            modelFacultate.addElement("Geografie");
                        } else if (studiiSelectate.equals("Doctorat")) {

                        }
                        break;

                    case "Facultatea de Drept":
                        if (studiiSelectate.equals("Licenta")) {
                            modelFacultate.addElement("Drept                      ");
                        } else if (studiiSelectate.equals("Master")) {
                            modelFacultate.addElement("Dreptul afacerilor         ");
                            modelFacultate.addElement("Stiinte penale");
                        } else if (studiiSelectate.equals("Doctorat")) {

                        }
                        break;
                    case "Facultatea de Economie și de Administrare a Afacerilor":
                        if (studiiSelectate.equals("Licenta")) {
                            modelFacultate.addElement("Economie");
                            modelFacultate.addElement("Informatica aplicata       ");
                        } else if (studiiSelectate.equals("Master")) {
                            modelFacultate.addElement("Economie                   ");
                        } else if (studiiSelectate.equals("Doctorat")) {

                        }
                        break;
                    case "Facultatea de Educație Fizică și Sport":
                        if (studiiSelectate.equals("Licenta")) {
                            modelFacultate.addElement("Educatie fizica si sportiva");
                            modelFacultate.addElement("Sport");
                        } else if (studiiSelectate.equals("Master")) {
                            modelFacultate.addElement("Sport                       ");
                        } else if (studiiSelectate.equals("Doctorat")) {

                        }
                        break;
                    case "Facultatea de Fizică":
                        if (studiiSelectate.equals("Licenta")) {
                            modelFacultate.addElement("Fizica                      ");
                        } else if (studiiSelectate.equals("Master")) {
                            modelFacultate.addElement("Fizica                      ");
                        } else if (studiiSelectate.equals("Doctorat")) {

                        }
                        break;
                    case "Facultatea de Litere, Istorie și Teologie":
                        if (studiiSelectate.equals("Licenta")) {
                            modelFacultate.addElement("Litere");
                            modelFacultate.addElement("Istorie");
                            modelFacultate.addElement("Teologie                    ");
                        } else if (studiiSelectate.equals("Master")) {
                            modelFacultate.addElement("Teologie                    ");
                        } else if (studiiSelectate.equals("Doctorat")) {

                        }
                        break;
                    case "Facultatea de Matematică și Informatică":
                        if (studiiSelectate.equals("Licenta")) {
                            modelFacultate.addElement("Infromatica romana");
                            modelFacultate.addElement("Infromatica aplicata");
                            modelFacultate.addElement("Infromatica engleza         ");
                        } else if (studiiSelectate.equals("Master")) {
                            modelFacultate.addElement("Infromatica                 ");
                        } else if (studiiSelectate.equals("Doctorat")) {

                        }
                        break;
                    case "Facultatea de Muzică și Teatru":
                        if (studiiSelectate.equals("Licenta")) {
                            modelFacultate.addElement("Muzica");
                            modelFacultate.addElement("Teatru                       ");
                        } else if (studiiSelectate.equals("Master")) {
                            modelFacultate.addElement("Muzica                       ");
                        } else if (studiiSelectate.equals("Doctorat")) {

                        }
                        break;
                    case "Facultatea de Sociologie și Psihologie":
                        if (studiiSelectate.equals("Licenta")) {
                            modelFacultate.addElement("Sociiologie                   ");
                            modelFacultate.addElement("Psihiatrie");
                        } else if (studiiSelectate.equals("Master")) {
                            modelFacultate.addElement("Psihiatrie                    ");
                        } else if (studiiSelectate.equals("Doctorat")) {

                        }
                        break;
                    case "Facultatea de Științe Politice, Filosofie și Științe ale Comunicării":
                        if (studiiSelectate.equals("Licenta")) {
                            modelFacultate.addElement("Științe Politice");
                            modelFacultate.addElement(" Filosofie");
                            modelFacultate.addElement("Științe ale Comunicării       ");
                        } else if (studiiSelectate.equals("Master")) {
                            modelFacultate.addElement("Științe ale Comunicării       ");

                        } else if (studiiSelectate.equals("Doctorat")) {
                        }
                        break;
                    default:

                        break;
                }
            }
        });

        this.add(numeEticheta);
        this.add(campNume);
        this.add(prenumeEticheta);
        this.add(campPrenume);
        this.add(emailEticheta);
        this.add(campEmail);
        this.add(facultateEticheta);
        this.add(campFaculatate);
        this.add(studiiEticheta);
        this.add(campStudii);
        this.add(specializareEticheta);
        this.add(campSpecializare);
        this.add(anEticheta);
        this.add(campAn);

        butonAdd =new JButton("Save");
        JPanel panouStanga=  new JPanel();
        panouStanga.setLayout(new FlowLayout(FlowLayout.LEFT));
        panouStanga.setPreferredSize(new Dimension(300, 40));
        panouStanga.add(butonAdd);
        this.add(panouStanga);

        ActionListener addAction = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String nume = campNume.getText();
                String prenume = campPrenume.getText();
                String email = campEmail.getText();
                String facultate = (String) campFaculatate.getSelectedItem();
                String cicluStudii = (String) campStudii.getSelectedItem();
                String specializare = (String) campSpecializare.getSelectedItem();
                String anStudii = (String) campAn.getSelectedItem();

                if(nume.equals("") || prenume.equals("")){
                    nuSaSalvat();
                }else if(!isValidEmail(email))
                {
                    emailNotValid();
                }
                else {
                    Persoana student=new Persoana(nume,prenume,email,facultate,cicluStudii,specializare,anStudii);
                    salveazaStudent(student);
                    salvatCuSucces();
                }

            }
        };
        butonAdd.addActionListener(addAction);

        JMenuBar meniu = new JMenuBar();
        this.setJMenuBar(meniu);

        JMenu file = new JMenu("File");
        meniu.add(file);
        JMenuItem save = new JMenuItem(new AbstractAction("Save") {
            @Override
            public void actionPerformed(ActionEvent e) {
                File fisier = new File("date.txt");
                try {
                    FileOutputStream fos = new FileOutputStream(fisier);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(persoane);
                    oos.close();
                    fos.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            }
        });
        file.add(save);


        JMenu cerere = new JMenu("Cereri");


        JMenuItem cerereCamin = new JMenuItem(new AbstractAction("Cerere camin") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nume = campNume.getText();
                String prenume = campPrenume.getText();
                String email = campEmail.getText();
                String facultate = (String) campFaculatate.getSelectedItem();
                String cicluStudii = (String) campStudii.getSelectedItem();
                String specializare = (String) campSpecializare.getSelectedItem();
                String anStudii = (String) campAn.getSelectedItem();
                if(nume.equals("") || prenume.equals("")){
                    nuSaSalvat();
                }else if(!isValidEmail(email))
                {
                    emailNotValid();
                }else {
                    String modelCerereCamin = generareModelCerereCamin(nume, prenume, facultate, specializare, anStudii);
                    afiseazaModelCerere(modelCerereCamin);
                }
            }
        });

        JMenuItem cerereMarireNota = new JMenuItem(new AbstractAction("Cerere marire nota") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nume = campNume.getText();
                String prenume = campPrenume.getText();
                String email = campEmail.getText();
                String facultate = (String) campFaculatate.getSelectedItem();
                String cicluStudii = (String) campStudii.getSelectedItem();
                String specializare = (String) campSpecializare.getSelectedItem();
                String anStudii = (String) campAn.getSelectedItem();
                if(nume.equals("") || prenume.equals("")){
                    nuSaSalvat();
                }else if(!isValidEmail(email))
                {
                    emailNotValid();
                }else {
                    String modelCerereMarireNota = generareModelCerereMarireNota(nume, prenume, facultate, specializare, anStudii);
                    afiseazaModelCerere(modelCerereMarireNota);
                }
            }

        });

        cerere.add(cerereCamin);
        cerere.add(cerereMarireNota);


        JButton butonCopiere = new JButton("Copiază în Clipboard");
        butonCopiere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copiazaInClipboard(textArea.getText());
            }
        });

        meniu.add(cerere);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(butonCopiere, BorderLayout.SOUTH);



    }

    private void emailNotValid() {
        JOptionPane.showMessageDialog(this, "Te rog introdu mailul de forma ^[a-zA-Z]+\\.[a-zA-Z]+\\d{2}@e-uvt\\.ro$", "Salvare ", JOptionPane.INFORMATION_MESSAGE);

    }

    public static boolean isValidEmail(String email) {
        // Definirea expresiei regulate pentru validarea e-mailului
        String regex = "^[a-zA-Z]+\\.[a-zA-Z]+\\d{2}@e-uvt\\.ro$";

        // Crearea obiectului Pattern și Matcher
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        // Verificarea potrivirii expresiei regulate
        return matcher.matches();
    }
    private void nuSaSalvat() {
        JOptionPane.showMessageDialog(this, "Te rog introdu datele", "Salvare ", JOptionPane.INFORMATION_MESSAGE);

    }

    private void salvatCuSucces() {
        JOptionPane.showMessageDialog(this, "Salvat cu succes", "Salvare ", JOptionPane.INFORMATION_MESSAGE);

    }

    private void afiseazaModelCerere(String cerereEx) {
        textArea.setEditable(false);
        textArea.setText(cerereEx);
    }

    private String generareModelCerereCamin(String nume,String prenume,String facultate,String specializare,String an) {
        return "Subiect: Cerere pentru Cămin " + nume +" "+ prenume+" , Student la "+ facultate + "\n" +
                "\n" +
                "Stimate rector,\n" +
                "\n" +
                "Mă numesc " + nume +" "+ prenume+", student la "+ facultate + " de la Universitatea de Vest din Timișoara, " +
                "specializarea"+ specializare +"  și anul "+an+".\n" +
                "\n" +
                "Prin prezenta, aș dori să depun o cerere pentru a obține cazare în căminul universitar administrat de instituție" +
                " pentru acest an academic .\n" +
                "\n" +
                "Motivul pentru care solicit cazare în cămin este [specificăți aici motivul (opțional)]. În calitate de student " +
                "dedicat, consider că trăirea în căminul universitar ar contribui semnificativ la experiența mea universitară " +
                "și la implicarea în viața academică.\n" +
                "\n" +
                "Vă rog să-mi furnizați informații cu privire la procedura de depunere a cererii pentru cazare în cămin, " +
                "documentele necesare și orice alt detaliu relevant.\n" +
                "\n" +
                "Mulțumesc anticipat pentru amabilitatea și promptitudinea dumneavoastră în a răspunde acestei solicitări. " +
                "Sunt disponibil pentru orice informație suplimentară sau întrebări pe care le-ați putea avea.\n" +
                "\n" +
                "Cu deosebită considerație,\n" +
                nume + " "+ prenume +" \n" +
                "Student la"+ facultate+"\n" +
                "Universitatea de Vest din Timișoara";

    }
    private String generareModelCerereMarireNota(String nume,String prenume,String facultate,String specializare,String an){
        return "Subiect: Cerere pentru Mărire Notă -  "+ nume +" "+ prenume+" , Student la "+ facultate + "\n" +
                "\n" +
                "Stimate rector,\n" +
                "\n" +
                "Mă numesc " + nume +" "+ prenume+", student la "+ facultate + " de la Universitatea de Vest din Timișoara, " +
                "specializarea"+ specializare +"  și anul "+an+".\n" +
                "\n" +
                "Prin prezenta, aș dori să depun o cerere pentru reevaluarea notei la disciplina [Numele Disciplinei] " +
                "și solicitarea măririi acesteia.\n" +
                "\n" +
                "" +
                "Înțeleg importanța academică a notei și aș dori să discut posibilitatea revizuirii acesteia. " +
                "Am atașat la acest e-mail orice documente relevante sau argumente care susțin solicitarea mea.\n" +
                "\n" +
                "Vă rog să-mi furnizați informații cu privire la procedura de depunere a cererii pentru reevaluarea notei, " +
                "documentele necesare și orice alt detaliu relevant.\n" +
                "\n" +
                "Mulțumesc anticipat pentru amabilitatea și promptitudinea dumneavoastră în a răspunde acestei solicitări. " +
                "Sunt disponibil pentru orice informație suplimentară sau întrebări pe care le-ați putea avea.\n" +
                "\n" +
                "Cu deosebită considerație,\n" +
                 nume + " "+ prenume +" \n" +
                "Student la"+ facultate+"\n" +
                "Universitatea de Vest din Timișoara";
    }

    private void copiazaInClipboard(String text) {
        StringSelection selection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
        JOptionPane.showMessageDialog(this, "Textul a fost copiat în clipboard.", "Copiere realizată", JOptionPane.INFORMATION_MESSAGE);
    }


        public static void salveazaStudent(Persoana student) {
            try (Connection conexiune = ConexiuneBazadeDate.obtineConexiune()) {
                String sql = "INSERT INTO Studenti (Nume, Prenume,Email, Facultate,Studii, Specializare, An) VALUES (?, ?, ?, ?, ?,?,?)";
                try (PreparedStatement declaratie = conexiune.prepareStatement(sql)) {
                    declaratie.setString(1, student.getNume());
                    declaratie.setString(2, student.getPrenume());
                    declaratie.setString(3, student.getEmail());
                    declaratie.setString(4, student.getFacultate());
                    declaratie.setString(5, student.getProgramStudii());
                    declaratie.setString(6, student.getSpecializare());
                    declaratie.setString(7, student.getAn());
                    declaratie.executeUpdate();
                }
                System.out.println("Studentul a fost salvat în baza de date.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
