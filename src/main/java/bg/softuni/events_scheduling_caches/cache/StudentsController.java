package bg.softuni.events_scheduling_caches.cache;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentsController {

    private final StudentService studentService;

    public StudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/cache")
    public String getAll() {

        studentService
                .getAllStudents()
                .forEach(System.out::println);

        System.out.println("------");

        studentService
                .updateStudents();

        System.out.println("------");

        studentService
                .getAllStudents()
                .forEach(System.out::println);

        System.out.println("------");

        studentService
                .removeStudentsFromCache();

        System.out.println("------");

        studentService
                .getAllStudents()
                .forEach(System.out::println);



        return "OK";
    }
}
