package friendoo.parkingsystem.jpa.enums;


public enum TicketStatus {

    PAID,
    NOT_PAID,
    UNKNOWN;

    public boolean isPaid() {
        return this == PAID;
    }

    public boolean isNotPaid() {
        return !isPaid();
    }


}

