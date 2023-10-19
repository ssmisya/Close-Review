//package CloseReview.registration
//
//import java.lang.annotation.Documented;
//import java.lang.annotation.Retention;
//import java.lang.annotation.Target;
//
//import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
//import static java.lang.annotation.ElementType.FIELD;
//import static java.lang.annotation.RetentionPolicy.RUNTIME;
//
//@Target({TYPE, FIELD, ANNOTATION_TYPE})
//@Retention(RUNTIME)
//@Constraint(validatedBy = EmailValidator.class)
//@Documented
//public @interface ValidEmail {
//    String message() default "Invalid email";
//    Class<?>[] groups() default {};
//    Class<? extends Payload>[] payload() default {};
//}
//
//public class EmailValidator
//        implements ConstraintValidator<ValidEmail, String> {
//
//    private Pattern pattern;
//    private Matcher matcher;
//    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
//    @Override
//    public void initialize(ValidEmail constraintAnnotation) {
//    }
//    @Override
//    public boolean isValid(String email, ConstraintValidatorContext context){
//        return (validateEmail(email));
//    }
//    private boolean validateEmail(String email) {
//        pattern = Pattern.compile(EMAIL_PATTERN);
//        matcher = pattern.matcher(email);
//        return matcher.matches();
//    }
//}