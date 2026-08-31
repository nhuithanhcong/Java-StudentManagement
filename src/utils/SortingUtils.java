package utils;

import model.Student;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortingUtils {

    private static void merge(List<Student> students, int left, int mid, int right, Comparator<Student> comparator) {

        List<Student> leftList = new ArrayList<>(students.subList(left, mid + 1));
        List<Student> rightList = new ArrayList<>(students.subList(mid + 1, right + 1));

        int i = 0, j = 0;
        int k = left;

        while (i < leftList.size() && j < rightList.size()) {
            if (comparator.compare(leftList.get(i), rightList.get(j)) <= 0) {
                students.set(k++, leftList.get(i++));
            } else {
                students.set(k++, rightList.get(j++));
            }
        }

        while (i < leftList.size()) {
            students.set(k++, leftList.get(i++));
        }

        while (j < rightList.size()) {
            students.set(k++, rightList.get(j++));
        }
    }

    private static void mergeSort(List<Student> students, int left, int right, Comparator<Student> comparator) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(students, left, mid, comparator);
            mergeSort(students, mid + 1, right, comparator);
            
            merge(students, left, mid, right, comparator);
        }
    }

    public static void sortStudents(List<Student> students, Comparator<Student> comparator) {
        if (students == null || students.size() <= 1) {
            return;
        }
        mergeSort(students, 0, students.size() - 1, comparator);
    }
}