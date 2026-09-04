package controller;

import command.Command;
import command.DropCourseCommand;
import command.RegisterCourseCommand;
import exception.RegistrationException;
import model.Enrollment;
import model.Student;
import model.Subject;
import repository.EnrollmentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RegistrationController {
    private final EnrollmentRepository enrollmentRepo;
    private final List<Enrollment> enrollmentList;
    private final SubjectController subjectController;
    private final Stack<Command> undoStack;
    private final Stack<Command> redoStack;

    private static final int MAX_CREDITS_PER_SEMESTER = 24;

    public RegistrationController(SubjectController subjectController) {
        this.enrollmentRepo = new EnrollmentRepository();
        this.enrollmentList = enrollmentRepo.loadAll();
        this.subjectController = subjectController;
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }

    public void registerCourse(Student student, String subjectId, String semester) throws RegistrationException {
        Subject subject = subjectController.findById(subjectId);
        if (subject == null) {
            throw new RegistrationException("Không tìm thấy môn học có mã: " + subjectId);
        }

        // 1. Kiểm tra trùng đăng ký
        for (Enrollment e : enrollmentList) {
            if (e.getStudentId().equalsIgnoreCase(student.getId()) &&
                e.getSubjectId().equalsIgnoreCase(subjectId) &&
                e.getSemester().equalsIgnoreCase(semester) &&
                e.getStatus().equalsIgnoreCase("REGISTERED")) {
                throw new RegistrationException("Sinh viên đã đăng ký môn học này trong học kỳ!");
            }
        }

        // 2. Kiểm tra giới hạn tín chỉ học kỳ
        int currentCredits = 0;
        for (Enrollment e : enrollmentList) {
            if (e.getStudentId().equalsIgnoreCase(student.getId()) &&
                e.getSemester().equalsIgnoreCase(semester) &&
                e.getStatus().equalsIgnoreCase("REGISTERED")) {
                Subject s = subjectController.findById(e.getSubjectId());
                if (s != null) currentCredits += s.getCredits();
            }
        }
        if (currentCredits + subject.getCredits() > MAX_CREDITS_PER_SEMESTER) {
            throw new RegistrationException("Vượt quá số tín chỉ tối đa (" + MAX_CREDITS_PER_SEMESTER + " tín chỉ) trong một học kỳ!");
        }

        // Thực thi Command
        Enrollment newEnrollment = new Enrollment(student.getId(), subjectId, semester, "REGISTERED");
        Command registerCmd = new RegisterCourseCommand(enrollmentList, newEnrollment, enrollmentRepo);
        registerCmd.execute();

        undoStack.push(registerCmd);
        redoStack.clear(); // Xóa lịch sử redo khi có hành động mới
    }

    public void dropCourse(Student student, String subjectId, String semester) throws RegistrationException {
        Enrollment target = null;
        for (Enrollment e : enrollmentList) {
            if (e.getStudentId().equalsIgnoreCase(student.getId()) &&
                e.getSubjectId().equalsIgnoreCase(subjectId) &&
                e.getSemester().equalsIgnoreCase(semester)) {
                target = e;
                break;
            }
        }

        if (target == null) {
            throw new RegistrationException("Không tìm thấy thông tin đăng ký môn học để hủy!");
        }

        Command dropCmd = new DropCourseCommand(enrollmentList, target, enrollmentRepo);
        dropCmd.execute();

        undoStack.push(dropCmd);
        redoStack.clear();
    }

    public void undo() throws RegistrationException {
        if (undoStack.isEmpty()) {
            throw new RegistrationException("Không có thao tác nào để Hoàn tác (Undo)!");
        }
        Command cmd = undoStack.pop();
        cmd.undo();
        redoStack.push(cmd);
    }

    public void redo() throws RegistrationException {
        if (redoStack.isEmpty()) {
            throw new RegistrationException("Không có thao tác nào để Làm lại (Redo)!");
        }
        Command cmd = redoStack.pop();
        cmd.execute();
        undoStack.push(cmd);
    }
}