package com.sriram.parking.payment;
import java.time.Duration;
import java.time.LocalDateTime;

import com.sriram.parking.vehicle.Vehicle;

//Payment calculation class 
public class Payment {
	private PaymentType type;
	
	public Payment(PaymentType type)
	{
		this.type = type;
	}
	
	
	//Calculate Fees for a vehicle
	public synchronized Long calculateFee(Vehicle vehicle) 
	{
		LocalDateTime entryTime = null;
		LocalDateTime exitTime = null;
		entryTime = vehicle.getTicket().getTimeIssued();
		exitTime = vehicle.getTicket().getTimeExited() == null ? LocalDateTime.now() : vehicle.getTicket().getTimeExited();
		if(entryTime != null && exitTime != null){
			Long paymentAmount = 0L;
			Duration duration = Duration.between(entryTime, exitTime);
			Long difference = duration.toHours();
			if(difference == 0)
			{
				paymentAmount += vehicle.getParkingFee().getFirstHourCharges();
				return paymentAmount;
			}
			else if(difference == 1)
			{
				paymentAmount += vehicle.getParkingFee().getFirstHourCharges() + vehicle.getParkingFee().getSecondHourCharges();
				return paymentAmount;
			}
			else if(difference > 1)
			{
				paymentAmount += vehicle.getParkingFee().getFirstHourCharges() + vehicle.getParkingFee().getSecondHourCharges() + (difference - 1)*vehicle.getParkingFee().getLaterHourCharges();
				return paymentAmount;
			}
		}
		return null;
	}

	public PaymentType getType() {
		return type;
	}	
	
}
