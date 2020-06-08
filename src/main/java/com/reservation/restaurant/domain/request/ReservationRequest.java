package com.reservation.restaurant.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class ReservationRequest {

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date selectedDate;
    @Valid
    @NotNull
    private List<ReserveInfo> reserveInfo;

    public Date getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }

    public List<ReserveInfo> getReserveInfo() {
        return reserveInfo;
    }

    public void setReserveInfo(List<ReserveInfo> reserveInfo) {
        this.reserveInfo = reserveInfo;
    }

}
