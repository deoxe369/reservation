package com.reservation.restaurant.controller;

import com.reservation.restaurant.domain.request.ReservationRequest;
import com.reservation.restaurant.domain.response.ReservationResponse;
import com.reservation.restaurant.service.ReservationService;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReservationService reservationService;

    @Test
    public void calculateTotalTableSuccess() throws Exception {
        when(reservationService.calculateTotalTable(any(ReservationRequest.class))).thenReturn(new ReservationResponse(2));
        String requestBody = FileUtils.readFileToString(new File(this.getClass().getResource("/mock/success_same_time.json").getFile()),"UTF-8");
        mvc.perform( MockMvcRequestBuilders
                .post("/reservation")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void calculateTotalTableFailWhenRequiredFieldIsNull() throws Exception {
        String requestBody = FileUtils.readFileToString(new File(this.getClass().getResource("/mock/fail_invalid_required_field_null.json").getFile()),"UTF-8");
        mvc.perform( MockMvcRequestBuilders
                .post("/reservation")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }

    @Test
    public void calculateTotalTableFailWhenRequiredFieldIsBlank() throws Exception {
        String requestBody = FileUtils.readFileToString(new File(this.getClass().getResource("/mock/fail_invalid_required_field_blank.json").getFile()),"UTF-8");
        mvc.perform( MockMvcRequestBuilders
                .post("/reservation")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }

    @Test
    public void calculateTotalTableFailWhenInvalidDateFormat() throws Exception {
        String requestBody = FileUtils.readFileToString(new File(this.getClass().getResource("/mock/fail_invalid_date_format.json").getFile()),"UTF-8");
        mvc.perform( MockMvcRequestBuilders
                .post("/reservation")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }

    @Test
    public void calculateTotalTableFailWhenInvalidDateTimeFormat() throws Exception {
        String requestBody = FileUtils.readFileToString(new File(this.getClass().getResource("/mock/fail_invalid_date_time_format.json").getFile()),"UTF-8");
        mvc.perform( MockMvcRequestBuilders
                .post("/reservation")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }

    @Test
    public void calculateTotalTableFailWhenPersonLessThan1() throws Exception {
        String requestBody = FileUtils.readFileToString(new File(this.getClass().getResource("/mock/fail_person_less_than_1.json").getFile()),"UTF-8");
        mvc.perform( MockMvcRequestBuilders
                .post("/reservation")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }

}