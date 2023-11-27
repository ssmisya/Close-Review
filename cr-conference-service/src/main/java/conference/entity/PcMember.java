package conference.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pc_member")
public class PcMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "conference_id", columnDefinition = "varchar(100)", nullable = false, unique = true)
    private String conferenceId;

    @Column(name = "user_name", columnDefinition = "varchar(100)", nullable = false, unique = true)
    private String userName;

    @Column(name = "incharge_papers", columnDefinition = "varchar(200)")
    private String inchargePapers;

    @Column(name = "topic", columnDefinition = "varchar(200)")
    private String topic;

    public PcMember(String conferenceId, String userName, String inchargePapers, String topic) {
        this.conferenceId = conferenceId;
        this.userName = userName;
        this.inchargePapers = inchargePapers;
        this.topic = topic;
    }

    public PcMember() {

    }
}
