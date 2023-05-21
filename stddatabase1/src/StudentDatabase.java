import java.io.*;
import java.util.*;

public class StudentDatabase {
    private static Map<Integer, Student> students;
    public List<Prize> prizes;

    public StudentDatabase() {
        students = new HashMap<>();
        prizes = new ArrayList<>();
    }

    public void readInputFile(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (!line.isEmpty()) {
                    processInputLine(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void processInputLine(String line) {
        if (line.startsWith("A") || line.startsWith("M") || line.startsWith("S")) {
            addStudent(line);
        } else if (line.startsWith("R")) {
            addTopicResult(line);
        } else if (line.startsWith("P")) {
            addPrize(line);
        }
    }

    public static void addStudent(String line) {
        String[] fields = line.split(",");

        char degreeCode = fields[0].charAt(0);
        int studentNumber = Integer.parseInt(fields[1]);
        String familyName = fields[2];
        String givenNames = fields[3];

        Student student = null;

        if (degreeCode == 'A') {
            String major = fields[5];
            String minor = fields[6];
            student = new ArtsStudent(studentNumber, familyName, givenNames, major, minor);
        } else if (degreeCode == 'M') {
            String[] prizeNames = fields[4].split(",");
            List<String> prizes = new ArrayList<>(Arrays.asList(prizeNames));
            student = new MedicineStudent(studentNumber, familyName, givenNames, prizes);
        } else if (degreeCode == 'S') {
            student = new ScienceStudent(studentNumber, familyName, givenNames);
        }

        if (student != null) {
            students.put(studentNumber, student);
        }
    }

    public void addTopicResult(String line) {
        String[] fields = line.split(",");

        int studentNumber = Integer.parseInt(fields[1]);
        String topicCode = fields[2];
        String grade = fields[3];

        TopicResult topicResult = null;

        if (fields.length > 4) {
            int mark = Integer.parseInt(fields[4]);
            topicResult = new TopicResult(topicCode, grade, mark);
        } else {
            topicResult = new TopicResult(topicCode, grade);
        }

        Student student = findStudent(studentNumber);
        if (student != null) {
            student.addTopicResult(topicResult);
        }
    }

    public void addPrize(String line) {
        String[] fields = line.split(",");

        String prizeName = fields[1];
        String topicPattern = fields[2];
        int minTopics = Integer.parseInt(fields[3]);

        Prize prize = new Prize(prizeName, topicPattern, minTopics);
        prizes.add(prize);
    }

    public void awardPrize(Prize prize) {
        for (Student student : students.values()) {
            if (student instanceof MedicineStudent) {
                MedicineStudent medicineStudent = (MedicineStudent) student;
                medicineStudent.calculatePrize(prize);
            }
        }
    }

    public void printRecords() {
        for (Student student : students.values()) {
            System.out.println("Academic record for " + student.getFullName() + " (" + student.getStudentNumber() + ")");
            System.out.println("Degree: " + student.getDegree());

            if (student instanceof ArtsStudent) {
                ArtsStudent artsStudent = (ArtsStudent) student;
                System.out.println("Major: " + artsStudent.getMajor());
                System.out.println("Minor: " + artsStudent.getMinor());
            }

            if (student instanceof MedicineStudent) {
                MedicineStudent medicineStudent = (MedicineStudent) student;
                for (String prize : medicineStudent.getPrizes()) {
                    System.out.println("Prize: " + prize);
                }
            }

            for (TopicResult topicResult : student.getTopicResults()) {
                String line = topicResult.getTopicCode() + " " + topicResult.getGrade();
                if (topicResult.hasMark()) {
                    line += " " + topicResult.getMark();
                }
                System.out.println(line);
            }

            System.out.println();
        }
    }

    public Map<Integer, Student> getStudents() {
        return students;
    }

    public List<Prize> getPrizes() {
        return prizes;
    }

    private Student findStudent(int studentNumber) {
        return students.get(studentNumber);
    }
    public void calculatePrizes() {
        for (Prize prize : prizes) {
            for (Student student : students.values()) {
                if (student instanceof MedicineStudent) {
                    MedicineStudent medicineStudent = (MedicineStudent) student;
                    medicineStudent.calculatePrize(prize);
                }
            }
        }
    }
    public void generateAcademicRecords(String outputFilePath) {
        try (PrintWriter writer = new PrintWriter(new File(outputFilePath))) {
            for (Student student : students.values()) {
                writer.println("Academic record for " + student.getFullName() + " (" + student.getStudentNumber() + ")");
                writer.println("Degree: " + student.getDegree());

                if (student instanceof ArtsStudent) {
                    ArtsStudent artsStudent = (ArtsStudent) student;
                    writer.println("Major: " + artsStudent.getMajor());
                    writer.println("Minor: " + artsStudent.getMinor());
                }

                if (student instanceof MedicineStudent) {
                    MedicineStudent medicineStudent = (MedicineStudent) student;
                    for (String prize : medicineStudent.getPrizes()) {
                        writer.println("Prize: " + prize);
                    }
                }

                for (TopicResult topicResult : student.getTopicResults()) {
                    String line = topicResult.getTopicCode() + " " + topicResult.getGrade();
                    if (topicResult.hasMark()) {
                        line += " " + topicResult.getMark();
                    }
                    writer.println(line);
                }

                writer.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
