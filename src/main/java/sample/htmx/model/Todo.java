package sample.htmx.model;

import java.time.LocalDateTime;

public record Todo(
        Long id,
        String description,
        Boolean done,
        LocalDateTime created,
        LocalDateTime updated
) {
}
