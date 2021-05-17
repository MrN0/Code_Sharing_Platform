package platform.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "code_snippet")
public class CodeSnippet {

    @Id
    @GeneratedValue
    @Column(name = "uuid", nullable = false)
    private UUID id;

    @Column(name = "code", nullable = false, columnDefinition = "TEXT")
    private String code;

    @Column(name = "date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime date;

    @Column(name = "time", nullable = false, columnDefinition = "LONG DEFAULT 0")
    private long time;

    @Column(name = "views", nullable = false, columnDefinition = "LONG DEFAULT 0")
    private long views;

    public CodeSnippet() {
        super();
    }

    public CodeSnippet(String code, LocalDateTime date, long time, long views) {
        this.code = code;
        this.date = date;
        this.time = time;
        this.views = views;
    }

    public UUID getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

}
