package sample.htmx.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Todos {

    private int id;
    private String description;
    private boolean done;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Todos() {
    }

    public Todos(
            int id,
            String description,
            boolean done,
            LocalDateTime created,
            LocalDateTime updated
    ) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.created = created;
        this.updated = updated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todos todos = (Todos) o;
        return Objects.equals(id, todos.id) && Objects.equals(description, todos.description) && Objects.equals(done, todos.done) && Objects.equals(created, todos.created) && Objects.equals(updated, todos.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, done, created, updated);
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", done=" + done +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}

