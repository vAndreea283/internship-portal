package org.proiectre.proiectre.common;

import org.proiectre.proiectre.entities.InterviewResult;

public class InterviewDto {
    private Long id;
    private String summary;
    private InterviewResult result;
    private Long applicationId;

    public InterviewDto(Long id, String summary, InterviewResult result, Long applicationId) {
        this.id = id;
        this.summary = summary;
        this.result = result;
        this.applicationId = applicationId;
    }

    public Long getId() { return id; }
    public String getSummary() { return summary; }
    public InterviewResult getResult() { return result; }
    public Long getApplicationId() { return applicationId; }
}