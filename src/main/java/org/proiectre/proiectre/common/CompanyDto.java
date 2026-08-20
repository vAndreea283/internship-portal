package org.proiectre.proiectre.common;

import org.proiectre.proiectre.entities.CompanyStatus;

public class CompanyDto {
    private Long id;
    private String name;
    private String description;
    private CompanyStatus status;
    private String username;

    public CompanyDto(Long id, String name, String description, CompanyStatus status, String username) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.username = username;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public CompanyStatus getStatus() { return status; }
    public String getUsername() { return username; }
}