package com.usa.ciclo3.reto.usa.repository.crud;

import com.usa.ciclo3.reto.usa.model.Reservation;
import com.usa.ciclo3.reto.usa.model.Skate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ReservationCrudRepository extends CrudRepository<Reservation,Integer> {


    //Jpql
    //Top mas dinero
    @Query("SELECT c.client, count(c.client) from Reservation AS c group by c.client order by COUNT(c.client)desc ")
    public List<Object[]> countTotalReservationByClient();
    //cantidad en tiempo
    public List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date dateOne, Date dateTwo);
    //cantidad com vs can
    public List<Reservation> findAllByStatus(String status);




}
