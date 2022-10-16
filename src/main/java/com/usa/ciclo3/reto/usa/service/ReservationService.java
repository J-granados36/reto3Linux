package com.usa.ciclo3.reto.usa.service;


import com.usa.ciclo3.reto.usa.model.Client;
import com.usa.ciclo3.reto.usa.model.Reservation;
import com.usa.ciclo3.reto.usa.model.dto.StatusAcount;
import com.usa.ciclo3.reto.usa.model.dto.TopClients;
import com.usa.ciclo3.reto.usa.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll(){
        return reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int id){
        return reservationRepository.getReservation(id);
    }

    public Reservation save(Reservation reservation){
        if (reservation.getIdReservation()==null){
            return reservationRepository.save(reservation);
        }else{
            Optional<Reservation> Raux=reservationRepository.getReservation(reservation.getIdReservation());
            if (Raux.isEmpty()){
                return reservationRepository.save(reservation);
            }else{
                return reservation;
            }
        }
    }

    public Reservation update(Reservation reservation){
        if (reservation.getIdReservation() != null){
            Optional<Reservation> reservation1= reservationRepository.getReservation(reservation.getIdReservation());
            if (!reservation1.isEmpty()){
                if (reservation.getStartDate()!= null){
                    reservation1.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate() != null){
                    reservation1.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getStatus() != null){
                    reservation1.get().setStatus(reservation.getStatus());
                }
                reservationRepository.save(reservation1.get());
                return reservation1.get();
            }else{
                return reservation;
            }
        }else{
            return reservation;
        }
    }

    public boolean deleteReservation(int id){
        Boolean dR = getReservation(id).map(reservation -> {
            reservationRepository.delete(reservation);
            return true;
        }).orElse(false);
        return dR;
    }

    public List<Reservation> getReservationByPeriod(String dateA,String dateB){
        SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd");
        Date a =new Date();
        Date b =new Date();
        try{
            a=parser.parse(dateA);
            b=parser.parse(dateB);
        }catch (ParseException e){
            e.printStackTrace();
        }
        if (a.before(b)){
            return reservationRepository.getReservationPeriod(a,b);
        }else{
            return new ArrayList<Reservation>();
        }
    }

    public StatusAcount getReportByStatus(){
        List<Reservation> completes=reservationRepository.getStatusReport("completed");
        List<Reservation> cancelled=reservationRepository.getStatusReport("canceled");

        StatusAcount resultado=new StatusAcount(completes.size(),cancelled.size());
        return resultado;
    }

    public List<TopClients> getTopclients(){
        List<TopClients> tc=new ArrayList<>();
        List<Object[]> result= reservationRepository.getTopClients();

        for(int i=0;i<result.size();i++){
            int total=Integer.parseInt(result.get(i)[1].toString());
            Client client= (Client) result.get(i)[0];
            TopClients topClient=new TopClients(total,client);
            tc.add(topClient);
        }
        return tc;
    }


}
