package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Builder(toBuilder = true)
@Entity
@Table(name = "invitations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Invitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonIgnoreProperties({"invitations", "events"})
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"invitations", "events"})
    private User user;

    @Column
    private StatusInvitation status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invitation that = (Invitation) o;
        return Objects.equals(id, that.id) && Objects.equals(event, that.event)
                && Objects.equals(user, that.user) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, event, user, status);
    }

    @Override
    public String toString() {
        return "Invitation{" + "id=" + id + ", event=" + event + ", user=" + user + ", invitation=" + status + '}';
    }
}
