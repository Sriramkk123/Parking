package com.sriram.parking.vehicle;

import com.sriram.parking.parkingDetails.ParkingFee;
import com.sriram.parking.parkingDetails.ParkingSpot;
import com.sriram.parking.parkingDetails.Ticket;
import com.sriram.parking.payment.PaymentType;

//Vehicle which comes and goes out of parking
public class Vehicle {
	private String registrationNumber;
	private VehicleType type;
	private Ticket ticket;
	private ParkingFee parkingfree;
	private ParkingSpot spot;
	private PaymentType paymentType;
	
	public Vehicle(String registrationNumber,VehicleType type)
	{
		this.registrationNumber = registrationNumber;
		this.type = type;
		this.parkingfree = new ParkingFee(type);
	}
	
	public ParkingSpot getSpot() {
		return this.spot;
	}

	public void setSpot(ParkingSpot spot) {
		this.spot = spot;
	}

	public String getRegistrationNumber()
	{
		return this.registrationNumber;
	}
	
	public boolean assignTicket(Ticket ticket)
	{
		this.ticket = ticket;
		if(this.ticket.toString().isEmpty())
		{
			return false;
		}
		return true;
	}

	public Ticket getTicket() {
		return this.ticket;
	}

	public ParkingFee  getParkingFee() {
		return this.parkingfree;
	}
	
	public VehicleType getType()
	{
		return this.type;
	}
	
	public PaymentType getPaymentType()
	{
		return this.paymentType;
	}

	@Override
	public String toString() {
		return "Vehicle [registrationNumber=" + registrationNumber + ", type=" + type + ", ticket=" + ticket
				+ ", parkingfree=" + parkingfree + ", spot=" + spot + ", paymentType=" + paymentType + "]";
	}

	public void setPaymentType(PaymentType type) {
		this.paymentType = type;
		
	}
	
	
}
