package conference.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "paper")
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "conference_id", columnDefinition = "varchar(100)", nullable = false, unique = true)
    private String conferenceId;

    @Column(name = "author", columnDefinition = "varchar(100)")
    private String author;

    @Column(name = "pdf_path", columnDefinition = "varchar(100)", nullable = false)
    private String pdfPath;

    @Column(name = "email", columnDefinition = "varchar(100)", nullable = false)
    private String email;

    @Column(name = "organization", columnDefinition = "varchar(100)")
    private String organization;

    @Column(name = "status", columnDefinition = "varchar(100)")
    private String status;

    @Column(name = "notification_result", columnDefinition = "varchar(100)")
    private String notificationResult;
}
