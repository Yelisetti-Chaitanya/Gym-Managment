package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Schedule {
    public static class Session implements Serializable {
        private String trainerId;
        private String memberId;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private String status;

        public Session(String trainerId, String memberId, LocalDateTime startTime, LocalDateTime endTime) {
            if (trainerId == null || trainerId.trim().isEmpty()) {
                throw new IllegalArgumentException("Trainer ID cannot be null or empty.");
            }
            if (memberId == null || memberId.trim().isEmpty()) {
                throw new IllegalArgumentException("Member ID cannot be null or empty.");
            }
            if (startTime == null || endTime == null) {
                throw new IllegalArgumentException("Start time and end time cannot be null.");
            }
            if (endTime.isBefore(startTime)) {
                throw new IllegalArgumentException("End time cannot be before start time.");
            }

            this.trainerId = trainerId;
            this.memberId = memberId;
            this.startTime = startTime;
            this.endTime = endTime;
            this.status = "Scheduled";
        }

        public String getTrainerId() { return trainerId; }
        public String getMemberId() { return memberId; }
        public LocalDateTime getStartTime() { return startTime; }
        public LocalDateTime getEndTime() { return endTime; }
        public String getStatus() { return status; }
        
        public void setStatus(String status) {
            if (status == null || status.trim().isEmpty()) {
                throw new IllegalArgumentException("Status cannot be null or empty.");
            }
            this.status = status;
        }
    }
}
