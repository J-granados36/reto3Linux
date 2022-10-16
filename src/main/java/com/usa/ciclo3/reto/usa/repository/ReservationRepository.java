package com.usa.ciclo3.reto.usa.repository;


import com.usa.ciclo3.reto.usa.model.Category;
import com.usa.ciclo3.reto.usa.model.Reservation;
import com.usa.ciclo3.reto.usa.repository.crud.ReservationCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepository {

    @Autowired
    private ReservationCrudRepository reservationCrudRepository;

    public List<Reservation> getAll(){
        return (List<Reservation>) reservationCrudRepository.findAll();
    }

    public Optional<Reservation> getReservation(int id){
        return reservationCrudRepository.findById(id);
    }

    public Reservation save(Reservation reservation){
        return reservationCrudRepository.save(reservation);
    }

    public void delete(Reservation reservation){
        reservationCrudRepository.delete(reservation);
    }



    public List<Object[]> getTopClients(){
        return reservationCrudRepository.countTotalReservationByClient();
    }

    public List<Reservation> getReservationPeriod(Date dateOne, Date dateTow){
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(dateOne,dateTow);
    }

    public List<Reservation> getStatusReport(String status){
        return reservationCrudRepository.findAllByStatus(status);
    }



}
