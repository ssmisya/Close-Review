package CloseReview.paper;

import jakarta.persistence.*;

@Entity
@Table(name = "paper_info")
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", columnDefinition = "varchar(100)", nullable = false, unique = true)
    private String title;

    @Column(name = "author", columnDefinition = "varchar(100)")
    private String author;

    @Column(name = "pdfPath", columnDefinition = "varchar(100)", nullable = false)
    private String pdfPath;

    @Column(name = "email", columnDefinition = "varchar(100)", nullable = false)
    private String email;

    @Column(name = "organization", columnDefinition = "varchar(100)", nullable = false)
    private String organization;

    @Column(name = "status", columnDefinition = "varchar(100)", nullable = false)
    private String status;

    @Column(name = "enabled", columnDefinition = "varchar(100)", nullable = false)
    private String notificationResult;

    public Paper(String title, String author, String email, String organization)
    {
        super();
        this.title = title;
        this.author = author;
        this.pdfPath = "None";
        this.email = email;
        this.organization = organization;
        this.status = "Submitted";
        this.notificationResult = "None";
    }

    public Paper()
    {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getOrganization() {
        return organization;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotificationResult() {
        return notificationResult;
    }

    public void setNotificationResult(String notificationResult) {
        this.notificationResult = notificationResult;
    }

    @Override
    public String toString(){
        return "id="+this.getId()+" author="+this.getAuthor()+" title="+this.getTitle()+" email="+this.getEmail()
                +" organization="+this.getOrganization()+" PDF="+this.getPdfPath()+" Status="+this.getStatus()+" NotificationResult="+this.getNotificationResult();
    }
}
