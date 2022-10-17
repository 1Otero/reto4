package com.g31.jpa.service;

import com.g31.jpa.entity.Reservation;
import com.g31.jpa.repository.ReservationRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author desaextremo
 */
@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    //Metodo para consultar todos los registros (Capa de servicios)
    public List<Reservation> getReservation() {
        return reservationRepository.findAll();
    }

    //Metodo para insertar (Capa de servicios)
    public Reservation insertReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
    
    //Metodo para consultar una registo x su id (Capa de servicios)
    public Reservation getReservationById(Long id){
            return reservationRepository.findById(id).get();
    }

    //Metodo para eliminar (Capa de servicios)
    public void deleteReservation(Long id){
       reservationRepository.deleteById(id);               
    }
    
    public Reservation updateReservation(Reservation reservation){
        //valido si viene un id en la información de la peticion
        //si no viene retorno la entidad recibida como parametro
        if (reservation.getIdReservation()!=null){
            //valido si el id existe en la base de datos
            Optional<Reservation> opcional = reservationRepository.findById(reservation.getIdReservation());
            
            if (!opcional.isEmpty()){
                //logica
                Reservation reservationBD = opcional.get();
                if (reservation.getClient()!=null) reservationBD.setClient(reservation.getClient());
                if(reservation.getGame()!=null) reservationBD.setGame(reservation.getGame());
                if(reservation.getStartDate()!=null) reservationBD.setStartDate(reservation.getStartDate());
                if(reservation.getDevolutionDate()!=null) reservationBD.setDevolutionDate(reservation.getDevolutionDate());
                if(reservation.getStatus()!=null) reservationBD.setStatus(reservation.getStatus());
                
                return reservationRepository.save(reservationBD);
            }else{
                return reservation;
            }
        }
        return reservation;
        
    }
}
