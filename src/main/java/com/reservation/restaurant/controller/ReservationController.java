package com.reservation.restaurant.controller;

import com.reservation.restaurant.domain.request.ReservationRequest;
import com.reservation.restaurant.domain.response.ReservationResponse;
import com.reservation.restaurant.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @RequestMapping(value = "/reservation", method = RequestMethod.POST)
    public ReservationResponse getTotalTable(@RequestBody @Valid ReservationRequest request) throws ParseException {
        return reservationService.calculateTotalTable(request);
    }
}
