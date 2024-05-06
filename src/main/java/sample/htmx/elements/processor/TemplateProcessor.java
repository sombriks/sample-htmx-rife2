package sample.htmx.elements.processor;

import rife.template.Template;
import sample.htmx.model.Todo;

import java.util.List;

public class TemplateProcessor {
    public static void populateList(Template template, List<Todo> todos) {
        todos.forEach(todo -> {
            template.setValue("id", todo.getId());
            template.setValue("description", todo.getDescription());
            template.setValue("ifTrue", todo.getDone() ? "selected" : "");
            template.setValue("ifFalse", !todo.getDone() ? "selected" : "");
            template.appendBlock("todos", "todo");
        });
    }
}
