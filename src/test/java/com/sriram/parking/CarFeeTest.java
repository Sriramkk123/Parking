package com.sriram.parking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.sriram.parking.parkingDetails.Ticket;
import com.sriram.parking.payment.Payment;
import com.sriram.parking.payment.PaymentType;
import com.sriram.parking.vehicle.Vehicle;
import com.sriram.parking.vehicle.VehicleType;

public class CarFeeTest {

    @Test
    public void carFeeTest() {
        Vehicle car = new Vehicle("KA129897",VehicleType.CAR); 
    	car.assignTicket(new Ticket(LocalDateTime.now()));
    	car.getTicket().setTimeExited(LocalDateTime.of(2021,06,01,16,0,0,0));
    	car.setPaymentType(PaymentType.CARD);
    	assertEquals(new Payment(car.getPaymentType()).calculateFee(car),60L);
    }

}
