package teamTime.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ENTRIES")
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Entry(String annotation, Long time, Team team) {
        this.annotation = annotation;
        this.time = time;
        this.team = team;
    }

    private String annotation;
    private Long time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Entry(String annotation, Long time) {
        this.annotation = annotation;
        this.time = time;
    }

    public Entry() {
    }
}