package com.reservation.restaurant.domain.response;

public class ReservationResponse {

    private Integer totalTable;

    public ReservationResponse(Integer totalTable) {
        this.totalTable = totalTable;
    }

    public Integer getTotalTable() {
        return totalTable;
    }

    public void setTotalTable(Integer totalTable) {
        this.totalTable = totalTable;
    }
}
