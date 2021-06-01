package com.sriram.parking.parkingDetails;

import com.sriram.parking.vehicle.Vehicle;
import com.sriram.parking.vehicle.VehicleType;

//The main Parking Lot class handling parking and exit
public class ParkingLot {
	private String name;
	private int compactCount;
	private int mediumCount;
	private int largeCount;
	private SpaceCount spaceCount;
	private static ParkingLot parkinglot = null;
	
	private ParkingLot(String name,SpaceCount spaceCount)
	{
		this.name = name;
		this.spaceCount = spaceCount;
		this.compactCount = this.mediumCount = this.largeCount = 0;
	}
	
	
	public static ParkingLot getInstance(String name,SpaceCount spaceCount)
	{
		if(parkinglot == null)
		{
			parkinglot = new ParkingLot(name,spaceCount);
		}
		return parkinglot;
	}
	
	//Park the vehicle
	public synchronized boolean park(Vehicle vehicle,ParkingSpot spot)
	{
		boolean assigned = spot.assignVehicleToThisSpot(vehicle);
		if(assigned == true)
		{
			this.increaseSpotCount(vehicle.getType());
			return true;
		}
		return false;
	}

	//Exit from parking
	public synchronized boolean exit(Vehicle vehicle)
	{
		boolean exited = vehicle.getSpot().removeVehicleFromThisSpot();
		if(exited)
		{
			this.decreaseSpotcount(vehicle.getType());
			return true;
		}
		return false;
	}

	//Check if the spot is free for parking
	public synchronized boolean isFree(Vehicle vehicle)
	{
		VehicleType type = vehicle.getType();
		switch(type)
		{
			case BIKE:
			{
				return this.compactCount >= this.spaceCount.getCompactCount();
			}
			case CAR:
			{
				return this.mediumCount >= this.spaceCount.getMediumCount();
			}
			case TRUCK:
			{
				return this.largeCount >= this.spaceCount.getLargeCount();
			}
			default:
				return false;
		}
	}
	
	//Decrease the spot count for given vehicle type
	private void decreaseSpotcount(VehicleType type) 
	{
		switch(type)
		{
			case BIKE:
			{
				if(this.compactCount == 0)
					break;
				this.compactCount--;
				break;
			}
			case CAR:
			{
				if(this.mediumCount == 0)
					break;
				this.mediumCount--;
				break;
			}
			case TRUCK:
			{
				if(this.largeCount == 0)
					break;
				this.largeCount--;
				break;
			}
			default:
				break; 
		}
	}
	
	//Increase the spot count for given vehicle type
	private void increaseSpotCount(VehicleType type) 
	{
		switch(type)
		{
			case BIKE:
			{
				this.compactCount++;
				break;
			}
			case CAR:
			{
				this.mediumCount++;
				break;
			}
			case TRUCK:
			{
				this.largeCount++;
				break;
			}
			default:
				break; 
		}
	}

	public SpaceCount getTotalSpaceCount()
	{
		return this.spaceCount;
	}
	
	public void showNumberOfAvailableSpots()
	{
		int availableCompactSpots = (this.spaceCount.getCompactCount() - this.compactCount);
		int availableMediumSpots = (this.spaceCount.getMediumCount() - this.mediumCount);
		int availableLargeSpots = (this.spaceCount.getLargeCount() - this.largeCount);
		
		if(availableCompactSpots > 0 && availableMediumSpots > 0 && availableLargeSpots > 0)
		{
			System.out.println("Available Bike spaces: " + availableCompactSpots);
			System.out.println("Available Car spaces: " + availableMediumSpots);
			System.out.println("Availale Truck spaces: " + availableLargeSpots);
		}
		else if(availableCompactSpots > 0 && availableMediumSpots > 0)
		{
			System.out.println("Available Bike spaces: " + availableCompactSpots);
			System.out.println("Available Car spaces: " + availableMediumSpots);
			System.out.println("Truck spots are full");
		}
		else if(availableMediumSpots > 0 && availableLargeSpots > 0)
		{
			System.out.println("Bike spaces are full");
			System.out.println("Available Car spaces: " + availableMediumSpots);
			System.out.println("Availale Truck spaces: " + availableLargeSpots);
		}
		else if(availableCompactSpots > 0 && availableLargeSpots > 0)
		{
			System.out.println("Available Bike spaces: " + availableCompactSpots);
			System.out.println("Car spaces are full");
			System.out.println("Availale Truck spaces: " + availableLargeSpots);
		}
		else if(availableCompactSpots > 0)
		{
			System.out.println("Available Bike spaces: " + availableCompactSpots);
			System.out.println("Car spaces are full");
			System.out.println("Truck spots are full");
		}
		else if(availableMediumSpots > 0)
		{
			System.out.println("Bike spaces are full");
			System.out.println("Available Car spaces: " + availableMediumSpots);
			System.out.println("Truck spots are full");
		}
		else if( availableLargeSpots > 0)
		{
			System.out.println("Bike spaces are full");
			System.out.println("Car spaces are full");
			System.out.println("Availale Truck spaces: " + availableLargeSpots);
		}
		else
		{
			System.out.println("Parking is Full");
		}
	}

	@Override
	public String toString() {
		return "ParkingLot [name=" + name + ", compactCount=" + compactCount + ", mediumCount=" + mediumCount
				+ ", largeCount=" + largeCount + ", spaceCount=" + spaceCount + "]";
	}
	
	
}
