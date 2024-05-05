package sample.htmx.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Todo {

    private Long id;
    private String description;
    private Boolean done;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Todo() {
    }

    public Todo(
            Long id,
            String description,
            Boolean done,
            LocalDateTime created,
            LocalDateTime updated
    ) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.created = created;
        this.updated = updated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
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
        Todo todo = (Todo) o;
        return Objects.equals(id, todo.id) && Objects.equals(description, todo.description) && Objects.equals(done, todo.done) && Objects.equals(created, todo.created) && Objects.equals(updated, todo.updated);
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

