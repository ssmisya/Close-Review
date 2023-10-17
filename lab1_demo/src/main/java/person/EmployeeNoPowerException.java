package person;

public class EmployeeNoPowerException extends RuntimeException{
    EmployeeNoPowerException(Long id) {
        super("The Employee " + id + " has no super powerful to manage the system.");
    }
}
