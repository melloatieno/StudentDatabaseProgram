import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class StdDBGUI extends Application {

    private StudentDatabase studentDatabase1;
    Label StudentNumber, FamilyName, GivenName, Degree, TopicCode, Grade, Mark, ArtsMajor, ArtsMinor, MedicinePrizes, PrizeName, Template, NumberOfTopics;
    TextField StudentNumberTextField, FamilyNameTextField, GivenNameTextField, TopicCodeTextField, MarkTextField, ArtsMajorTextField, ArtsMinorTextField, PrizeNameTextField, TemplateTextField, NumberOfTopicsTextField;
    Button AddTopicResultsButton, FinfTopicResultsButton, AwardPrizesButton, AddStudentButton, FindStudentButton, PrintAllRecordsButton, ClearAllRecordsButton;
    ComboBox DegreeComboBox, GradeComboBox, MedicinePrizesCombo;
    TextFormatter<Integer> textFormatter = new TextFormatter<>(new IntegerStringConverter(), 0,
            change -> {
                String newText = change.getControlNewText();
                if (newText.matches("\\d*")) {
                    return change;
                }
                return null;
            });


    public void start(Stage stage) throws Exception{
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10));

        Label titleLabel = new Label("Student Database Program");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        GridPane studentDatabase = new GridPane();
        studentDatabase.setPadding(new Insets(10));
        studentDatabase.setHgap(10);
        studentDatabase.setVgap(10);

        borderPane.setTop(titleLabel);
        borderPane.setCenter(studentDatabase);

        GridPane studentDetailsPane = new GridPane();
        studentDetailsPane.setHgap(10);
        studentDetailsPane.setVgap(10);
        studentDetailsPane.setPadding(new Insets(5));

        StudentNumber = new Label("Student Number");
        StudentNumberTextField = new TextField();
        StudentNumberTextField.setTextFormatter(textFormatter);
        FamilyName = new Label("Family Name");
        FamilyNameTextField = new TextField();
        GivenName = new Label("Given Name(s)");
        GivenNameTextField = new TextField();
        Degree = new Label("Degree");
        DegreeComboBox = new ComboBox<>();
        DegreeComboBox.getItems().addAll("Arts", "Medicine", "Science");



        studentDetailsPane.add(StudentNumber, 0,0);
        studentDetailsPane.add(StudentNumberTextField, 1,0);
        studentDetailsPane.add(FamilyName, 0,1);
        studentDetailsPane.add(FamilyNameTextField, 1,1);
        studentDetailsPane.add(GivenName, 0,2);
        studentDetailsPane.add(GivenNameTextField, 1,2);
        studentDetailsPane.add(Degree, 0,3);
        studentDetailsPane.add(DegreeComboBox, 1,3);

        GridPane topicDetails = new GridPane();
        topicDetails.setHgap(10);
        topicDetails.setVgap(10);
        topicDetails.setPadding(new Insets(5));

        TopicCode = new Label("Topic Code");
        TopicCodeTextField = new TextField();
        Grade = new Label("Grade");
        GradeComboBox = new ComboBox<>();
        Mark = new Label("Mark");
        MarkTextField = new TextField();
        AddTopicResultsButton = new Button("Add Topic Result");
        FinfTopicResultsButton = new Button("Find Topic Result");

        topicDetails.add(TopicCode, 0,0);
        topicDetails.add(TopicCodeTextField, 1,0);
        topicDetails.add(Grade, 0,1);
        topicDetails.add(GradeComboBox, 1,1);
        topicDetails.add(Mark, 0,2);
        topicDetails.add(MarkTextField, 1,2);
        topicDetails.add(AddTopicResultsButton, 0,3);
        topicDetails.add(FinfTopicResultsButton, 1,3);

        GridPane DegreeOptions = new GridPane();
        DegreeOptions.setHgap(10);
        DegreeOptions.setVgap(10);
        DegreeOptions.setPadding(new Insets(5));

        ArtsMajor = new Label("Arts Major");
        ArtsMajorTextField = new TextField();
        ArtsMinor = new Label("Arts Minor");
        ArtsMinorTextField = new TextField();
        MedicinePrizes = new Label("Medicine Prizes");
        MedicinePrizesCombo = new ComboBox<>();

        DegreeOptions.add(ArtsMajor, 0,0);
        DegreeOptions.add(ArtsMajorTextField, 1,0);
        DegreeOptions.add(ArtsMinor, 0,1);
        DegreeOptions.add(ArtsMinorTextField, 1,1);
        DegreeOptions.add(MedicinePrizes, 0,2);
        DegreeOptions.add(MedicinePrizesCombo, 1,2);

        GridPane AwardPrizes = new GridPane();
        AwardPrizes.setHgap(10);
        AwardPrizes.setVgap(10);
        AwardPrizes.setPadding(new Insets(10));

        PrizeName = new Label("Prize Name");
        PrizeNameTextField = new TextField();
        Template = new Label("Template");
        TemplateTextField = new TextField();
        NumberOfTopics = new Label("Number of Topics");
        NumberOfTopicsTextField = new TextField();
        AwardPrizesButton = new Button("Award Prize");

        AwardPrizes.add(PrizeName, 0,0);
        AwardPrizes.add(PrizeNameTextField, 1,0);
        AwardPrizes.add(Template, 0,1);
        AwardPrizes.add(TemplateTextField, 1,1);
        AwardPrizes.add(NumberOfTopics, 0,2);
        AwardPrizes.add(NumberOfTopicsTextField, 1,2);
        AwardPrizes.add(AwardPrizesButton, 1,3);

        GridPane Details = new GridPane();
        Details.setHgap(10);
        Details.setVgap(10);
        Details.setPadding(new Insets(10));

        AddStudentButton =new Button("Add Student");
        FindStudentButton = new Button("Find Student");

        Details.add(AddStudentButton, 0,0);
        Details.add(FindStudentButton, 1,0);

        GridPane Records = new GridPane();
        Records.setHgap(10);
        Records.setVgap(10);
        Records.setPadding(new Insets(10));

        PrintAllRecordsButton = new Button("Print All Records");
        ClearAllRecordsButton = new Button("Clear All Records");

        Records.add(PrintAllRecordsButton, 0,0);
        Records.add(ClearAllRecordsButton, 1,0);

        studentDatabase.add(studentDetailsPane, 0,0);
        studentDatabase.add(topicDetails, 1,0);
        studentDatabase.add(DegreeOptions, 0,1);
        studentDatabase.add(AwardPrizes, 1,1);
        studentDatabase.add(Details, 0,2);
        studentDatabase.add(Records, 1,2);

        Scene scene = new Scene(borderPane,650, 450);
        scene.setFill(Color.BISQUE);

        stage.setTitle("Student Database Program");

        stage.setScene(scene);
        stage.show();
        AddStudentButton.setOnAction(e -> addStudentDetails());
        FindStudentButton.setOnAction(e -> findStudentDetails());


    }
    private void addStudentDetails() {
        // Retrieve the selected degree from the DegreeComboBox
        String selectedDegree = (String) DegreeComboBox.getValue();

        // Retrieve the student details from the input fields
        int studentNumber = Integer.parseInt(StudentNumberTextField.getText());
        String familyName = FamilyNameTextField.getText();
        String givenNames = GivenNameTextField.getText();

        // Create a student based on the selected degree
        Student student;
        switch (selectedDegree) {
            case "Arts":
                String major = ArtsMajorTextField.getText();
                String minor = ArtsMinorTextField.getText();
                student = new ArtsStudent(studentNumber, familyName, givenNames, major, minor);
                break;
            case "Medicine":
                List<String> prizes = null;
                student = new MedicineStudent(studentNumber, familyName, givenNames, prizes);
                prizes = Arrays.asList(PrizeNameTextField.getText().split(","));
                ((MedicineStudent) student).setPrizes(prizes);
                break;
            case "Science":
            default:
                student = new ScienceStudent(studentNumber, familyName, givenNames);
                break;
        }

        // Add the student to the student database
        StudentDatabase.addStudent(String.valueOf(student));

        clearFields();
        }

    private void findStudentDetails() {
        String studentNumberText = StudentNumberTextField.getText();

        if (studentNumberText.isEmpty()) {
            clearFields();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a student number.");
            alert.showAndWait();
            return;
        }

        try {
            int studentNumber = Integer.parseInt(studentNumberText);

            // Read the student details from the input.txt file
            List<String> lines = readLinesFromFile("input.txt");

            for (String line : lines) {
                // Split each line by comma to separate the student details
                String[] details = line.split(",");

                if (details.length >= 4) {
                    int fileStudentNumber = Integer.parseInt(details[0]);

                    if (fileStudentNumber == studentNumber) {
                        // Found the student in the file, populate the details in the text fields
                        FamilyNameTextField.setText(details[1]);
                        GivenNameTextField.setText(details[2]);

                        // Retrieve the degree based on the selected degree from the DegreeComboBox
                        String selectedDegree = (String) DegreeComboBox.getValue();

                        switch (selectedDegree) {
                            case "Arts":
                                ArtsMajorTextField.setText(details[3]);
                                ArtsMinorTextField.setText(details[4]);
                                break;
                            case "Medicine":
                                PrizeNameTextField.setText(details[3]);
                                break;
                            case "Science":
                            default:
                                // No additional fields for Science degree
                                break;
                        }

                        // Exit the method since the student has been found
                        return;
                    }
                }
            }

            // Student not found in the file
            clearFields();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Student not found.");
            alert.showAndWait();

        } catch (NumberFormatException e) {
            clearFields();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid student number. Please enter a valid integer.");
            alert.showAndWait();
        }
    }

    private void clearFields() {
        StudentNumberTextField.clear();
        FamilyNameTextField.clear();
        GivenNameTextField.clear();
        ArtsMajorTextField.clear();
        ArtsMinorTextField.clear();
        PrizeNameTextField.clear();
    }
    private List<String> readLinesFromFile(String filePath) {
        List<String> lines = new ArrayList<>();

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public static void main(String[] args){
        launch(args);
    }
}
