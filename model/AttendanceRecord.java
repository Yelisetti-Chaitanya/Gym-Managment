package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AttendanceRecord implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String memberId;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

    public AttendanceRecord(String memberId) {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be null or empty.");
        }
        this.memberId = memberId;
        this.checkIn = LocalDateTime.now();
    }

    public void checkOut() {
        if (this.checkOut != null) {
            throw new IllegalStateException("Check-out has already been recorded.");
        }
        this.checkOut = LocalDateTime.now();
    }

    public String getMemberId() { return memberId; }
    public LocalDateTime getCheckIn() { return checkIn; }
    public LocalDateTime getCheckOut() { return checkOut; }
}
