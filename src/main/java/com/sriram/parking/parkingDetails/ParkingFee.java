package com.sriram.parking.parkingDetails;

import com.sriram.parking.vehicle.VehicleType;

//Class for Assigning Fee based on Type of Vehicle
public class ParkingFee {
	private VehicleType type;
	private Long firstHourCharges;
	private Long secondHourCharges;
	private Long laterHourCharges;
	
	public ParkingFee(VehicleType type)
	{
		this.type = type;
		if(this.type == VehicleType.BIKE)
		{	
			this.firstHourCharges = 20L;
			this.secondHourCharges = 10L;
			this.laterHourCharges = 5L;
		}
		else if(this.type == VehicleType.CAR)
		{	
			this.firstHourCharges = 30L;
			this.secondHourCharges = 20L;
			this.laterHourCharges = 10L;
		}
		else if(this.type == VehicleType.TRUCK)
		{
			this.firstHourCharges = 40L;
			this.secondHourCharges = 30L;
			this.laterHourCharges = 20L;
		}
	}

	public Long getFirstHourCharges() {
		return firstHourCharges;
	}

	public Long getSecondHourCharges() {
		return secondHourCharges;
	}

	public Long getLaterHourCharges() {
		return laterHourCharges;
	}
	
}
