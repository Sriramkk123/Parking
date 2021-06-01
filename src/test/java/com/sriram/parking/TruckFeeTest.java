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

public class TruckFeeTest {

    @Test
    public void truckFeeTest() {
        Vehicle truck = new Vehicle("KA129897",VehicleType.TRUCK); 
    	truck.assignTicket(new Ticket(LocalDateTime.now()));
    	truck.getTicket().setTimeExited(LocalDateTime.of(2021,06,01,16,0,0,0));
    	truck.setPaymentType(PaymentType.CARD);
    	assertEquals(new Payment(truck.getPaymentType()).calculateFee(truck),90L);
    }

}
