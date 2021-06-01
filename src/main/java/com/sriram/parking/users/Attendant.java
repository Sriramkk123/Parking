package com.sriram.parking.users;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import com.sriram.parking.parkingDetails.ParkingLot;
import com.sriram.parking.parkingDetails.ParkingSpot;
import com.sriram.parking.parkingDetails.ParkingSpotType;
import com.sriram.parking.parkingDetails.Ticket;
import com.sriram.parking.payment.Payment;
import com.sriram.parking.payment.PaymentType;
import com.sriram.parking.vehicle.Vehicle;
import com.sriram.parking.vehicle.VehicleType;

//Attendant handles incoming and outgoing of vehicles and notifies user when payment is made via cash
public class Attendant extends Account{
	
	private ParkingLot parkinglot;
	private boolean isAmountPaidByCash;
	private Admin admin;
	public Attendant(String username, String password, Person person,Admin admin,ParkingLot parkinglot) {
		super(username, password, person);
		this.admin = admin;
		this.parkinglot = parkinglot;
	}

	//park vehicle
	public boolean parkVehicle(Vehicle vehicle)
	{
		HashMap<String,ParkingSpot> parkingSpots = Admin.getParkingSpots();
		
		if(vehicle.getType() == VehicleType.BIKE)
		{	
			for(Entry<String, ParkingSpot> entry : parkingSpots.entrySet())
			{
				if(entry.getValue().isFreeSpot() == true && entry.getValue().getParkingSpotType() == ParkingSpotType.COMPACT)
				{
					vehicle.setSpot(entry.getValue());
					vehicle.assignTicket(new Ticket(LocalDateTime.now()));
					return this.parkinglot.park(vehicle,vehicle.getSpot());
				}
			}
		}
		
		else if(vehicle.getType() == VehicleType.CAR)
		{
			for(Entry<String, ParkingSpot> entry : parkingSpots.entrySet())
			{	
				if(entry.getValue().isFreeSpot() == true && entry.getValue().getParkingSpotType() == ParkingSpotType.MEDIUM)
				{
					vehicle.setSpot(entry.getValue());
					vehicle.assignTicket(new Ticket(LocalDateTime.now()));
					return this.parkinglot.park(vehicle,vehicle.getSpot());
				}
			}
		}
		
		else if(vehicle.getType() == VehicleType.TRUCK)
		{
			for(Entry<String, ParkingSpot> entry : parkingSpots.entrySet())
			{
				if(entry.getValue().isFreeSpot() == true && entry.getValue().getParkingSpotType() == ParkingSpotType.LARGE)
				{
					vehicle.setSpot(entry.getValue());
					vehicle.assignTicket(new Ticket(LocalDateTime.now()));
					return this.parkinglot.park(vehicle,vehicle.getSpot());
				}
			}
		}
		
		return false;
	}
	
	//exit vehicle
	public Long exitVehicle(Vehicle vehicle)
	{
		boolean exitVehicle = parkinglot.exit(vehicle);
		if(exitVehicle)
		{
			Scanner sc  = new Scanner(System.in);
			System.out.println("Enter mode of payment: CASH or CARD");
			String paymentType = sc.next();
			if(paymentType.equalsIgnoreCase("CASH") || paymentType.equalsIgnoreCase("CARD"))
			{
				PaymentType typeOfPayment = paymentType.equalsIgnoreCase("CASH") ? PaymentType.CASH : PaymentType.CARD;
				vehicle.setPaymentType(typeOfPayment);
				Payment payment = new Payment(vehicle.getPaymentType());
				Long fees = payment.calculateFee(vehicle);
				
				//Notify Admin when payment method is Cash
				if(vehicle.getPaymentType() == PaymentType.CASH)
				{
					this.isAmountPaidByCash = true;
					this.notifyAdmin(vehicle);
				}
				return fees;
			}
			else
			{
				System.out.println("Wrong payment mode");
				return null;
			}
		}
		return null;
	}
	
	//Notify Admin when payment method is cash
	public void notifyAdmin(Vehicle vehicle)
	{
		if(isAmountPaidByCash)
			this.admin.setIsPaymentByCash(vehicle);
	}

	@Override
	public String toString() {
		return "Attendant [parkinglot=" + parkinglot + ", isAmountPaidByCash=" + isAmountPaidByCash + ", admin=" + admin
				+ "]";
	}
	
	
	
}
