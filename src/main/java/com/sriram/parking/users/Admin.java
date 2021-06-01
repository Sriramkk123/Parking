package com.sriram.parking.users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.sriram.parking.parkingDetails.ParkingLot;
import com.sriram.parking.parkingDetails.ParkingSpot;
import com.sriram.parking.parkingDetails.SpaceCount;
import com.sriram.parking.vehicle.Vehicle;

//Admin class handles the  parking lot creation,adding parking spots,adding attendants 
public class Admin extends Account{
	private ParkingLot parkinglot;
	private ArrayList<Vehicle> paymentMapForCash;
	private static HashMap<String,ParkingSpot> parkingSpots;
	
	public Admin(String username, String password, Person person) {
		super(username, password, person);	
		this.paymentMapForCash = new ArrayList<>();
	}
	
	//Creating Parking Lot
	public ParkingLot addParking(String name,int compactCount,int mediumCount,int largeCount)
	{
		parkingSpots = new HashMap<String,ParkingSpot>();
		SpaceCount spaceCount = new SpaceCount(compactCount, mediumCount, largeCount);
		this.parkinglot = ParkingLot.getInstance(name, spaceCount);
//		System.out.println(this.parkinglot);
		return this.parkinglot;
	}
	
	//Adding a spot for particular type 
	public boolean addParkingSpot(String id,ParkingSpot spot)
	{
		//System.out.println(parkingSpots.toString());
		if(parkingSpots.size() == 0)
		{
			parkingSpots.put(id, spot);
			return true;
		}
		
		int currentCountOfParticularType = 0;
		for(Entry<String, ParkingSpot> entry : parkingSpots.entrySet())
		{
			if(entry.getValue().getParkingSpotType() == spot.getParkingSpotType())
				currentCountOfParticularType++;
		}
		
		if(currentCountOfParticularType < parkinglot.getTotalSpaceCount().getCountByType(spot.getParkingSpotType()))
		{
			parkingSpots.put(id, spot);
			return true;
		}
		return false;
	}
	
	
	//Add attendants
	public Attendant addAttendant(String username,String password,Person person)
	{
		Attendant attendant = new Attendant(username,password,person,this,this.parkinglot);
		return attendant;
	}
	
	
	public void getparkingSpaceDetails()
	{
		this.parkinglot.showNumberOfAvailableSpots();
	}
	
	
	//Notifying when payment is made via Cash Method
	public void setIsPaymentByCash(Vehicle vehicle)
	{
		System.out.println("Payment done by cash for vehicle with registration number " + vehicle.getRegistrationNumber());
		this.paymentMapForCash.add(vehicle);
	}
	
	
	public static HashMap<String,ParkingSpot> getParkingSpots()
	{
		return parkingSpots;
	}
	
	
	public ArrayList<Vehicle> getCashPaymentDetails()
	{
		return this.paymentMapForCash;
	}
}
