package com.sriram.parking.parkingDetails;

import com.sriram.parking.vehicle.Vehicle;

//Class of the particular spot type
public class ParkingSpot {
	private String id;
	private Boolean isFree;
	private ParkingSpotType type;
	private Vehicle vehicle;
	
	public ParkingSpot(String number, ParkingSpotType type)
	{
		this.id = number;
		this.type = type;
		this.isFree = true;
	}
	
	//Add vehicle to this Parking Spot
	public boolean assignVehicleToThisSpot(Vehicle vehicle)
	{
		if(this.vehicle == null)
		{
			this.vehicle = vehicle;
			this.isFree = false;
			return true;
		}
		return false;
	}
	
	//Remove vehicle from this Parking Spot
	public boolean removeVehicleFromThisSpot()
	{
		if(this.vehicle != null)
		{
			this.vehicle = null;
			this.isFree = true;
			return true;
		}
		return false;
	}
	
	
	//Check if this spot Free for parking
	public boolean isFreeSpot()
	{
		return this.isFree;
	}
	
	public ParkingSpotType getParkingSpotType()
	{
		return this.type;
	}

	@Override
	public String toString() {
		return "ParkingSpot [id=" + id + ", isFree=" + isFree + ", type=" + type + ", vehicle=" + vehicle + "]";
	}
	
	
}
