package so.process;

public enum PriorityEnum {
    P1("BAIXO", 1),
    P2("MEDIO", 2),
    P3("CRITICO", 3);

    private String description;
    private Integer value;

    PriorityEnum(String description, Integer value) {
        this.description = description;
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public Integer getValue() {
        return value;
    }
}

