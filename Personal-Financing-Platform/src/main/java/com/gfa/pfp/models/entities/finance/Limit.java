package com.gfa.pfp.models.entities.finance;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gfa.pfp.models.entities.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "finance_limit")
public class Limit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    Long id;

    @ManyToOne
    Category category;

    Double expenseLimit;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate start;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate end;

    @ManyToOne
    @JsonIgnore
    User user;

    public Limit(Category category, Double expenseLimit, LocalDate start, LocalDate end, User user) {
        this.category = category;
        this.expenseLimit = expenseLimit;
        this.start = start;
        this.end = end;
        setUser(user);
    }

    public Limit(Long id, Category category, Double expenseLimit, LocalDate start, LocalDate end) {
        this.id = id;
        this.category = category;
        this.expenseLimit = expenseLimit;
        this.start = start;
        this.end = end;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}