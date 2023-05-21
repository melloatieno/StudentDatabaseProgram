import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

public class StudentDatabaseTest {
    private StudentDatabase database;

    @BeforeEach
    public void setUp() {
        database = new StudentDatabase();
    }

    @Test
    public void testStudentDatabase() {
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        // Create the input file
        createInputFile(inputFile);

        // Read the input file
        database.readInputFile(inputFile);

        // Test if the students were added correctly
        Map<Integer, Student> students = database.getStudents();
        Assertions.assertEquals(2, students.size());
        Assertions.assertTrue(students.containsKey(123));
        Assertions.assertTrue(students.containsKey(456));

        // Test if the topic results were added correctly
        Student student1 = students.get(123);
        Student student2 = students.get(456);
        List<TopicResult> topicResults1 = student1.getTopicResults();
        List<TopicResult> topicResults2 = student2.getTopicResults();

        Assertions.assertEquals(2, topicResults1.size());
        Assertions.assertEquals(1, topicResults2.size());

        TopicResult topicResult1 = topicResults1.get(0);
        TopicResult topicResult2 = topicResults1.get(1);
        TopicResult topicResult3 = topicResults2.get(0);

        Assertions.assertEquals("MATH101", topicResult1.getTopicCode());
        Assertions.assertEquals("A", topicResult1.getGrade());
        Assertions.assertEquals(95, topicResult1.getMark());

        Assertions.assertEquals("ENG101", topicResult2.getTopicCode());
        Assertions.assertEquals("B", topicResult2.getGrade());
        Assertions.assertEquals(85, topicResult2.getMark());

        Assertions.assertEquals("PHYS101", topicResult3.getTopicCode());
        Assertions.assertEquals("C", topicResult3.getGrade());
        Assertions.assertEquals(75, topicResult3.getMark());

        // Test if the prizes were added correctly
        List<Prize> prizes = database.getPrizes();
        Assertions.assertEquals(2, prizes.size());

        Prize prize1 = prizes.get(0);
        Prize prize2 = prizes.get(1);

        Assertions.assertEquals("Prize 1", prize1.getPrizeName());
        Assertions.assertEquals("MATH%", prize1.getTopicPattern());
        Assertions.assertEquals(3, prize1.getMinTopics());

        Assertions.assertEquals("Prize 2", prize2.getPrizeName());
        Assertions.assertEquals("ENG%", prize2.getTopicPattern());
        Assertions.assertEquals(2, prize2.getMinTopics());

        // Award prizes
        database.calculatePrizes();

        // Test if the prizes were awarded correctly
        // Test if the prizes were awarded correctly
        Assertions.assertFalse(student1 instanceof MedicineStudent);
        Assertions.assertFalse(student2 instanceof MedicineStudent);

        Assertions.assertEquals(1, ((MedicineStudent) student1).getPrizes().size());
        Assertions.assertEquals(1, ((MedicineStudent) student2).getPrizes().size());


        // Generate academic records
        database.generateAcademicRecords(outputFile);

        // Test if the output file was generated correctly
        File output = new File(outputFile);
        Assertions.assertTrue(output.exists());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        // Print the records and capture the output
        database.printRecords();
        String expectedOutput = "Academic record for John Smith (123)\n" +
                "Degree: Computer Science\n" +
                "Major: Computer Science\n" +
                "Minor: Mathematics\n" +
                "MATH101 A 95\n" +
                "ENG101 B 85\n\n" +
                "Academic record for Jane Doe (456)\n" +
                "Degree: Medicine\n" +
                "Prize: Prize 2\n" +
                "PHYS101 C 75\n\n";

        // Assert the expected output with the captured output
        Assertions.assertEquals(expectedOutput, outputStream.toString());
    }

    private void createInputFile(String inputFile) {
        try (PrintStream printStream = new PrintStream(new File(inputFile))) {
            printStream.println("A,123,Smith,John,Computer Science,Mathematics");
            printStream.println("R,123,MATH101,A,95");
            printStream.println("R,123,ENG101,B,85");
            printStream.println("A,456,Doe,Jane,Medicine");
            printStream.println("R,456,PHYS101,C,75");
            printStream.println("P,Prize 1,MATH%,3");
            printStream.println("P,Prize 2,ENG%,2");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
