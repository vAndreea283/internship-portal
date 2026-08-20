package org.proiectre.proiectre.common;

public class GradeDto {
    private Long id;
    private Double value;
    private Long applicationId;

    public GradeDto(Long id, Double value, Long applicationId) {
        this.id = id;
        this.value = value;
        this.applicationId = applicationId;
    }

    public Long getId() { return id; }
    public Double getValue() { return value; }
    public Long getApplicationId() { return applicationId; }
}