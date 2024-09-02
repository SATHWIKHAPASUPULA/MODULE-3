import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class School {
    private String name;

    public School(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

abstract class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

class Teacher extends Person {
    private String subject;

    public Teacher(String name, int age, String subject) {
        super(name, age);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public String teach() {
        return "Teaching subject: " + subject;
    }
}

class MathTeacher extends Teacher {
    public MathTeacher(String name, int age) {
        super(name, age, "Mathematics");
    }

    @Override
    public String teach() {
        return "Teaching mathematics with formulas and equations.";
    }
}

class ScienceTeacher extends Teacher {
    public ScienceTeacher(String name, int age) {
        super(name, age, "Science");
    }

    @Override
    public String teach() {
        return "Teaching science with experiments and theories.";
    }
}

class Student extends Person {
    private String grade;

    public Student(String name, int age, String grade) {
        super(name, age);
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }
}

public class SchoolManagementSystem {
    private JFrame frame;
    private JTextField nameField, ageField, gradeField;
    private JComboBox<String> typeCombo;
    private JTextArea displayArea;
    private ArrayList<Person> people;

    public SchoolManagementSystem() {
        people = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("School Management System");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 2));

        frame.add(new JLabel("Name:"));
        nameField = new JTextField();
        frame.add(nameField);

        frame.add(new JLabel("Age:"));
        ageField = new JTextField();
        frame.add(ageField);

        frame.add(new JLabel("Type:"));
        String[] types = {"Math Teacher", "Science Teacher", "Student"};
        typeCombo = new JComboBox<>(types);
        frame.add(typeCombo);

        frame.add(new JLabel("Grade (if Student):"));
        gradeField = new JTextField();
        frame.add(gradeField);

        JButton addButton = new JButton("Add");
        frame.add(addButton);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        frame.add(scrollPane);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPerson();
            }
        });

        frame.setVisible(true);
    }

    private void addPerson() {
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String type = (String) typeCombo.getSelectedItem();
        String grade = gradeField.getText();

        if (type.equals("Math Teacher")) {
            people.add(new MathTeacher(name, age));
        } else if (type.equals("Science Teacher")) {
            people.add(new ScienceTeacher(name, age));
        } else if (type.equals("Student")) {
            people.add(new Student(name, age, grade));
        }

        updateDisplay();
    }

    private void updateDisplay() {
        displayArea.setText("");
        for (Person person : people) {
            displayArea.append(person.getName() + ", Age: " + person.getAge() + "\n");
            if (person instanceof Teacher) {
                displayArea.append(((Teacher) person).teach() + "\n");
            } else if (person instanceof Student) {
                displayArea.append("Grade: " + ((Student) person).getGrade() + "\n");
            }
            displayArea.append("\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SchoolManagementSystem::new);
    }
}
