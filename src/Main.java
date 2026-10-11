import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<student> students = List.of(
                new student(101, "Rahim", department.CSE, new int[]{80, 75, 90}),
                new student(102, "Karim", department.CSE, new int[]{60, 65, 70}),
                new student(103, "Ayesha", department.EEE, new int[]{90, 95, 92}),
                new student(104, "Nusrat", department.EEE, new int[]{50, 55, 60}),
                new student(105, "Sakib", department.BBA, new int[]{75, 80, 85}),
                new student(106, "Mim", department.BBA, new int[]{40, 45, 50})
        );

        displayStudents(students);

        System.out.println("\n---Results---\n");

        for (student s : students) {
            processAndDisplayStudent(s);
        }


        System.out.println("Department wise Grade count:\n");
        for (department dept : department.values()) {
            System.out.println(dept.getGradeSummary());
        }
    }

    private static void displayStudents(List<student> students) {
        System.out.println("Student Information:");
        for (student s : students) {
            System.out.println(s);
        }
    }

    private static void processAndDisplayStudent(student s) {
        int[] totalMarks = new int[1];
        double[] averageMarks = new double[1];

        // Thread 1:Total Marks
        Thread totalThread = new Thread(() -> {
            int sum = 0;
            for (int mark : s.marks()) {
                sum += mark;
            }
            totalMarks[0] = sum;
        });

        // Thread 2:Average Marks
        Thread avgThread = new Thread(() -> {
            int sum = 0;
            for (int mark : s.marks()) {
                sum += mark;
            }
            averageMarks[0] = (double) sum / s.marks().length;
        });

        totalThread.start();
        avgThread.start();

        try {
            totalThread.join();
            avgThread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }

        double avg = averageMarks[0];
        String letterGrade = calculateLetterGrade(avg);
        double gradePoint = calculateGradePoint(avg);

        // Thread 3:gradecountThread
        Thread gradeCountThread = new Thread(() -> {
            s.department().countGrade(letterGrade);
        });

        gradeCountThread.start();
        try {
            gradeCountThread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }

        System.out.println("Total Marks For " + s.name() + " : " + totalMarks[0]);
        System.out.printf("Average Marks for %s: %.2f\n", s.name(), avg);
        System.out.println("Letter grade for " + s.name() + ":" + letterGrade);
        System.out.printf("Grade point of %s : %.2f\n\n", s.name(), gradePoint);
    }

    private static String calculateLetterGrade(double average) {
        if (average >= 90) return "A+";
        if (average >= 85) return "A";
        if (average >= 80) return "B+";
        if (average >= 75) return "B";
        if (average >= 70) return "C+";
        if (average >= 65) return "C";
        if (average >= 60) return "D+";
        if (average >= 50) return "D";
        return "F";
    }

    private static double calculateGradePoint(double average) {
        if (average >= 90) return 4.00;
        if (average >= 85) return 3.75;
        if (average >= 80) return 3.50;
        if (average >= 75) return 3.25;
        if (average >= 70) return 3.00;
        if (average >= 65) return 2.75;
        if (average >= 60) return 2.50;
        if (average >= 50) return 2.25;
        return 0.00;
    }
}