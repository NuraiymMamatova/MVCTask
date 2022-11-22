package peaksoft.enums;

public enum StudyFormat {
    ONLINE("Online"),
    OFFLINE("Offline");

    private final String displayValue;

    StudyFormat(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
