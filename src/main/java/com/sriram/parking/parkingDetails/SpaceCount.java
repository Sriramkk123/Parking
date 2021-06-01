package com.sriram.parking.parkingDetails;

//This class sets the total count of the entire parking lot spaces
public class SpaceCount {
	private  int compactCount;
	private  int mediumCount;
	private  int largeCount;
	
	
	public SpaceCount(int compactCount, int mediumCount, int largeCount)
	{
		this.compactCount = compactCount;
		this.mediumCount = mediumCount;
		this.largeCount = largeCount;
	}

	public  int getCompactCount() {
		return this.compactCount;
	}

	public int getMediumCount() {
		return this.mediumCount;
	}

	public  int getLargeCount() {
		return this.largeCount;
	}
	
	public int getCountByType(ParkingSpotType type)
	{
		if(type == ParkingSpotType.COMPACT)
			return this.compactCount;
		
		else if(type == ParkingSpotType.MEDIUM)
			return this.mediumCount;
		
		else if(type == ParkingSpotType.LARGE)
			return this.largeCount;
		else
			return -1;
	}	
	
}
