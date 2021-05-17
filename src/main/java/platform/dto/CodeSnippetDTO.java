package platform.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CodeSnippetDTO {

    private String code;
    private String date;
    private long time;
    private long views;
    private boolean timeLimited;
    private boolean viewsLimited;

    public CodeSnippetDTO() {
        super();
    }

    public CodeSnippetDTO(String code, long time, long views) {
        this.code = code;
        this.time = time;
        this.views = views;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    @JsonIgnore
    public boolean isTimeLimited() {
        return timeLimited;
    }

    public void setTimeLimited(boolean timeLimited) {
        this.timeLimited = timeLimited;
    }

    @JsonIgnore
    public boolean isViewsLimited() {
        return viewsLimited;
    }

    public void setViewsLimited(boolean viewsLimited) {
        this.viewsLimited = viewsLimited;
    }

    @JsonIgnore
    public boolean expired() {
        boolean timeLimitExpired = timeLimited && time == 0;
        boolean viewsLimitExpired = viewsLimited && views == 0;
        return timeLimitExpired || viewsLimitExpired;
    }

}
