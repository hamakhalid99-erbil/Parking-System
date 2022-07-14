package friendoo.parkingsystem.jpa.enums;

public enum SlotStatus {
    Full,
    Empty,
    Unknown;

    public boolean isEmpty() {
        return this == Empty;
    }

    public boolean isFull() {
        return !isEmpty();
    }
}
