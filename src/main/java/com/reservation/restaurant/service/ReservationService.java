package com.reservation.restaurant.service;

import com.reservation.restaurant.domain.request.ReservationRequest;
import com.reservation.restaurant.domain.request.ReserveInfo;
import com.reservation.restaurant.domain.response.ReservationResponse;
import com.reservation.restaurant.exception.InvalidInputException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    public ReservationResponse calculateTotalTable(ReservationRequest request) {

        validateStartTimeAndEndTime(request);

        List<ReserveInfo> selectedDateReserve = request.getReserveInfo().stream()
                .filter(x -> x.getReserveDate().equals(request.getSelectedDate())).collect(Collectors.toList());

        Integer totalTable = 0;
        for (ReserveInfo reserveInfo : selectedDateReserve) {
            Integer table = calculateReserve(reserveInfo,selectedDateReserve);
            if(table > totalTable){
                totalTable = table;
            }
        }
        return new ReservationResponse(totalTable);
    }

    private Integer calculateReserve(ReserveInfo reserveInfo, List<ReserveInfo> selectedDate) {

        Integer reserveTable = findTableFromPersons(reserveInfo.getPersons());
        Integer table = reserveTable;
        List<ReserveInfo> selectedDateReserveTemp = new ArrayList<>();
        selectedDateReserveTemp.addAll(selectedDate);
        selectedDateReserveTemp.remove(reserveInfo);

        for (ReserveInfo info : selectedDateReserveTemp) {
            if(info.getCheckOut().after(reserveInfo.getCheckIn()) && info.getCheckIn().before(reserveInfo.getCheckIn())){
                table = table + findTableFromPersons(info.getPersons());
                continue;
            }
            if(info.getCheckIn().before(reserveInfo.getCheckOut()) && info.getCheckOut().after(reserveInfo.getCheckOut())){
                table = table + findTableFromPersons(info.getPersons());
                continue;
            }
            if(info.getCheckIn().after(reserveInfo.getCheckIn()) && info.getCheckOut().before(reserveInfo.getCheckOut())){
                table = table + findTableFromPersons(info.getPersons());
                continue;
            }
            if(info.getCheckIn().equals(reserveInfo.getCheckIn()) && info.getCheckOut().equals(reserveInfo.getCheckOut())){
                table = table + findTableFromPersons(info.getPersons());
                continue;
            }
        }
        return table;
    }

    private void validateStartTimeAndEndTime(ReservationRequest request) {

        for (ReserveInfo info : request.getReserveInfo()) {
            if (info.getCheckIn().after(info.getCheckOut())) {
                throw new InvalidInputException("endTime cannot before or equal startTime");
            }
        }
    }

    private Integer findTableFromPersons(double persons){
       return (int)Math.ceil(persons/4);
    }
}
