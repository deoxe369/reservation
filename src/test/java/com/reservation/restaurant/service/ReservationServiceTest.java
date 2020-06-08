package com.reservation.restaurant.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.reservation.restaurant.domain.request.ReservationRequest;
import com.reservation.restaurant.domain.response.ReservationResponse;
import com.reservation.restaurant.exception.InvalidInputException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;



@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceTest {

    @InjectMocks
    private ReservationService reservationService;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void calculateTotalTableSuccessWhenPeriodIsSame() throws Exception {
        String requestBody = FileUtils.readFileToString(new File(this.getClass().getResource("/mock/success_same_time.json").getFile()),"UTF-8");
        ReservationRequest request = objectMapper.readValue(requestBody,ReservationRequest.class);
        ReservationResponse response = reservationService.calculateTotalTable(request);

        Assert.assertEquals((Integer)2,response.getTotalTable());
    }

    @Test
    public void calculateTotalTableSuccessWhenPeriodIsNotSame() throws Exception {
        String requestBody = FileUtils.readFileToString(new File(this.getClass().getResource("/mock/success_different_time.json").getFile()),"UTF-8");
        ReservationRequest request = objectMapper.readValue(requestBody,ReservationRequest.class);
        ReservationResponse response = reservationService.calculateTotalTable(request);

        Assert.assertEquals((Integer)1,response.getTotalTable());
    }

    @Test
    public void calculateTotalTableSuccessWhenPeriodOverlap1() throws Exception {
        String requestBody = FileUtils.readFileToString(new File(this.getClass().getResource("/mock/success_time_overlap1.json").getFile()),"UTF-8");
        ReservationRequest request = objectMapper.readValue(requestBody,ReservationRequest.class);
        ReservationResponse response = reservationService.calculateTotalTable(request);

        Assert.assertEquals((Integer)3,response.getTotalTable());
    }

    @Test
    public void calculateTotalTableSuccessWhenPeriodOverlap2() throws Exception {
        String requestBody = FileUtils.readFileToString(new File(this.getClass().getResource("/mock/success_time_overlap2.json").getFile()),"UTF-8");
        ReservationRequest request = objectMapper.readValue(requestBody,ReservationRequest.class);
        ReservationResponse response = reservationService.calculateTotalTable(request);

        Assert.assertEquals((Integer)3,response.getTotalTable());
    }

    @Test(expected = InvalidInputException.class)
    public void calculateTotalTableFailWhenEndTimeBeforeStartTime() throws Exception {
        String requestBody = FileUtils.readFileToString(new File(this.getClass().getResource("/mock/success_time_overlap3.json").getFile()),"UTF-8");
        ReservationRequest request = objectMapper.readValue(requestBody,ReservationRequest.class);
        reservationService.calculateTotalTable(request);

    }

}