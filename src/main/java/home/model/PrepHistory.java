package home.model;

import home.entity.Subject;
import lombok.Data;

public @Data class PrepHistory {
    private int id;
    private Subject subject;
    private int result;
    private Long date;
}
