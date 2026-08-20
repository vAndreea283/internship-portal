package org.proiectre.proiectre.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
    @Id /*cheia primara a tabelului*/
    @GeneratedValue(strategy = GenerationType.SEQUENCE) /*foloseste o secventa pentru generarea ID-ului*/
    @Column(name = "id", nullable = false) /*regula pentru baza de date*/
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Email(message = "Introdu o adresa de email valida") /*!!!*/
    @Column(name = "email", nullable = false)
    private String email;

    @Size(min = 8, max = 100, message = "Parola trebuie sa aiba intre 8 si 100 de caractere") /*!!!*/
    @Column(name = "password", nullable = false)
    private String password;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}