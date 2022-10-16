package com.usa.ciclo3.reto.usa.web;


import com.usa.ciclo3.reto.usa.model.Reservation;
import com.usa.ciclo3.reto.usa.model.dto.StatusAcount;
import com.usa.ciclo3.reto.usa.model.dto.TopClients;
import com.usa.ciclo3.reto.usa.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Reservation")
@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")
    public List<Reservation> getReservations(){
        return reservationService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Reservation> getReservation(@PathVariable("id") int id){
        return reservationService.getReservation(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation save(@RequestBody Reservation reservation){
        return reservationService.save(reservation);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation update(@RequestBody Reservation reservation){
        return reservationService.update(reservation);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id){
        return reservationService.deleteReservation(id);
    }

    @GetMapping("/report-dates/{dateA}/{dateB}")
    public List<Reservation> getByDates(@PathVariable("dateA")String dateA,@PathVariable("dateB")String dateB){
        return reservationService.getReservationByPeriod(dateA, dateB);
    }

    @GetMapping("/report-status")

    public StatusAcount getByStatus(){
        return reservationService.getReportByStatus();
    }

    @GetMapping("/report-clients")
    public List<TopClients> getTopClients(){
        return reservationService.getTopclients();
    }



}
