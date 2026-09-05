package view;

import controller.*;
import exception.RegistrationException;
import exception.ValidationException;
import model.Student;
import model.Subject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainConsoleView {
    private final StudentController studentController;
    private final SubjectController subjectController;
    private final FacultyController facultyController;
    private final AcademicController academicController;
    private final RegistrationController registrationController;
    private final Scanner scanner;

    public MainConsoleView() {
        this.studentController = new StudentController();
        this.subjectController = new SubjectController();
        this.facultyController = new FacultyController();
        this.academicController = new AcademicController(subjectController);
        this.registrationController = new RegistrationController(subjectController);
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\n========== QUẢN LÝ THÔNG TIN SINH VIÊN ==========");
            System.out.println("1. Thêm sinh viên mới");
            System.out.println("2. Hiển thị danh sách sinh viên");
            System.out.println("3. Sắp xếp sinh viên theo tên (QuickSort)");
            System.out.println("4. Thêm môn học mới");
            System.out.println("5. Hiển thị danh sách môn học");
            System.out.println("6. Đăng ký môn học cho sinh viên");
            System.out.println("7. Hủy đăng ký môn học");
            System.out.println("8. Hoàn tác đăng ký (Undo)");
            System.out.println("9. Làm lại đăng ký (Redo)");
            System.out.println("10. Xem GPA và tín chỉ tích lũy của sinh viên");
            System.out.println("0. Thoát chương trình");
            System.out.print("Chọn chức năng (0-10): ");

            String choice = scanner.nextLine().trim();
            // Cú pháp switch-case truyền thống của Java 8
            switch (choice) {
                case "1":
                    addStudent();
                    break;
                case "2":
                    showStudents();
                    break;
                case "3":
                    sortStudentsByName();
                    break;
                case "4":
                    addSubject();
                    break;
                case "5":
                    showSubjects();
                    break;
                case "6":
                    registerCourse();
                    break;
                case "7":
                    dropCourse();
                    break;
                case "8":
                    undoRegistration();
                    break;
                case "9":
                    redoRegistration();
                    break;
                case "10":
                    showAcademicProgress();
                    break;
                case "0":
                    System.out.println("Đã thoát chương trình. Tạm biệt!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại!");
                    break;
            }
        }
    }

    private void addStudent() {
        try {
            System.out.print("Nhập mã sinh viên: ");
            String id = scanner.nextLine();
            System.out.print("Nhập tên sinh viên: ");
            String name = scanner.nextLine();
            System.out.print("Nhập ngày sinh (dd/MM/yyyy): ");
            String dob = scanner.nextLine();
            System.out.print("Nhập email: ");
            String email = scanner.nextLine();
            System.out.print("Nhập mã khoa: ");
            String facultyId = scanner.nextLine();

            studentController.addStudent(id, name, dob, email, facultyId);
            System.out.println("Thêm sinh viên thành công!");
        } catch (ValidationException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    private void showStudents() {
        List<Student> students = studentController.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("Danh sách sinh viên rỗng!");
            return;
        }
        System.out.println("\n--- DANH SÁCH SINH VIÊN ---");
        for (Student s : students) {
            System.out.println("ID: " + s.getId() + " | Name: " + s.getName() + " | Email: " + s.getEmail() + " | Faculty: " + s.getFacultyId());
        }
    }

    private void sortStudentsByName() {
        studentController.sortByName();
        System.out.println("Đã sắp xếp danh sách sinh viên theo tên bằng QuickSort!");
        showStudents();
    }

    private void addSubject() {
        try {
            System.out.print("Nhập mã môn học: ");
            String id = scanner.nextLine();
            System.out.print("Nhập tên môn học: ");
            String name = scanner.nextLine();
            System.out.print("Nhập số tín chỉ: ");
            int credits = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Nhập các môn tiên quyết (phân cách bởi dấu phẩy, nhấn Enter nếu không có): ");
            String prereqInput = scanner.nextLine();
            
            // Thay thế List.of() bằng new ArrayList<>() chuẩn Java 8
            List<String> prereqs = prereqInput.trim().isEmpty() ? 
                new ArrayList<String>() : Arrays.asList(prereqInput.split(","));

            subjectController.addSubject(id, name, credits, prereqs);
            System.out.println("Thêm môn học thành công!");
        } catch (ValidationException e) {
            System.out.println("Lỗi: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Tín chỉ phải là số nguyên!");
        }
    }

    private void showSubjects() {
        List<Subject> subjects = subjectController.getAllSubjects();
        if (subjects == null || subjects.isEmpty()) {
            System.out.println("Danh sách môn học rỗng!");
            return;
        }
        System.out.println("\n--- DANH SÁCH MÔN HỌC ---");
        for (Subject s : subjects) {
            System.out.println("ID: " + s.getSubjectId() + " | Name: " + s.getSubjectName() + " | Credits: " + s.getCredits() + " | Prereqs: " + s.getPrerequisiteIds());
        }
    }

    private void registerCourse() {
        try {
            System.out.print("Nhập mã sinh viên: ");
            String studentId = scanner.nextLine();
            Student student = studentController.findById(studentId);
            if (student == null) {
                System.out.println("Không tìm thấy sinh viên!");
                return;
            }

            System.out.print("Nhập mã môn học: ");
            String subjectId = scanner.nextLine();
            System.out.print("Nhập học kỳ (ví dụ: 2024.1): ");
            String semester = scanner.nextLine();

            registrationController.registerCourse(student, subjectId, semester);
            System.out.println("Đăng ký môn học thành công!");
        } catch (RegistrationException e) {
            System.out.println("Lỗi đăng ký: " + e.getMessage());
        }
    }

    private void dropCourse() {
        try {
            System.out.print("Nhập mã sinh viên: ");
            String studentId = scanner.nextLine();
            Student student = studentController.findById(studentId);
            if (student == null) {
                System.out.println("Không tìm thấy sinh viên!");
                return;
            }

            System.out.print("Nhập mã môn học: ");
            String subjectId = scanner.nextLine();
            System.out.print("Nhập học kỳ: ");
            String semester = scanner.nextLine();

            registrationController.dropCourse(student, subjectId, semester);
            System.out.println("Hủy đăng ký môn học thành công!");
        } catch (RegistrationException e) {
            System.out.println("Lỗi hủy môn: " + e.getMessage());
        }
    }

    private void undoRegistration() {
        try {
            registrationController.undo();
            System.out.println("Hoàn tác (Undo) thao tác đăng ký/hủy thành công!");
        } catch (RegistrationException e) {
            System.out.println("Lỗi Undo: " + e.getMessage());
        }
    }

    private void redoRegistration() {
        try {
            registrationController.redo();
            System.out.println("Làm lại (Redo) thao tác đăng ký/hủy thành công!");
        } catch (RegistrationException e) {
            System.out.println("Lỗi Redo: " + e.getMessage());
        }
    }

    private void showAcademicProgress() {
        System.out.print("Nhập mã sinh viên: ");
        String studentId = scanner.nextLine();
        Student student = studentController.findById(studentId);
        if (student == null) {
            System.out.println("Không tìm thấy sinh viên!");
            return;
        }

        double gpa = academicController.calculateGPA(student);
        int credits = academicController.calculateAccumulatedCredits(student);

        System.out.println("\n--- BÁO CÁO HỌC TẬP ---");
        System.out.println("Sinh viên: " + student.getName() + " (" + student.getId() + ")");
        System.out.println("GPA tích lũy: " + gpa);
        System.out.println("Tổng tín chỉ đã tích lũy: " + credits);
    }
}