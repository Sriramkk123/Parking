package com.sriram.parking;

import java.time.LocalDateTime;

import com.sriram.parking.parkingDetails.ParkingLot;
import com.sriram.parking.parkingDetails.ParkingSpot;
import com.sriram.parking.parkingDetails.ParkingSpotType;
import com.sriram.parking.users.Admin;
import com.sriram.parking.users.Attendant;
import com.sriram.parking.users.Person;
import com.sriram.parking.vehicle.Vehicle;
import com.sriram.parking.vehicle.VehicleType;

//Main Class
public class App {
    
    public static void main(String[] args) {
    	String adminUsername = "Admin";
		String adminPassword = "1234";
		String attendantUsername = "Attendant";
		String attendantPassword = "1234";
		Person person = new Person(adminUsername,"admin@gmail.com","9998887776");
		Person worker = new Person(attendantUsername,"attendant@gmail.com","8887776665");
		Admin admin = new Admin(adminUsername,adminPassword,person);
		
		//Create parking lot
		ParkingLot parkinglot = admin.addParking("Parking 1", 10, 10, 10);
		if(parkinglot != null)
		{
			System.out.println("Parkinglot created");
		}
		else
		{
			System.out.println("Cannot create Parkinglot");
		}
		
		
		//Add spots
		boolean is1 = admin.addParkingSpot("1", new ParkingSpot("1",ParkingSpotType.COMPACT));
		if(is1)
		{
			System.out.println("Successfully created spot");
		}
		else
		{
			System.out.println("Could not create spot");
		}
		boolean is2 = admin.addParkingSpot("2", new ParkingSpot("2",ParkingSpotType.MEDIUM));
		if(is2)
		{
			System.out.println("Successfully created spot");
		}
		else
		{
			System.out.println("Could not create spot");
		}
		boolean is3 = admin.addParkingSpot("3", new ParkingSpot("3",ParkingSpotType.LARGE));
		if(is3)
		{
			System.out.println("Successfully created spot");
		}
		else
		{
			System.out.println("Could not create spot");
		}
		
		//Add attendant
		Attendant attendant = admin.addAttendant(attendantUsername, attendantPassword, worker);
		
		//Create Vehicles
		Vehicle vehicle = new Vehicle("KA137676",VehicleType.BIKE);
		Vehicle truck = new Vehicle("KA137686",VehicleType.TRUCK);
		
	
		admin.getparkingSpaceDetails();
		
		//Parking vehicles
		boolean isParked = attendant.parkVehicle(vehicle);
		boolean isParkedT = attendant.parkVehicle(truck);
		
		if(isParked)
		{
			System.out.println("Vehicle Parked");
		}
		else
		{
			System.out.println("Vehicle not Parked");
		}
		if(isParkedT)
		{
			System.out.println("Vehicle Parked");
		}
		else
		{
			System.out.println("Vehicle not Parked");
		}
		
		admin.getparkingSpaceDetails();
		
		//Set exit time
		vehicle.getTicket().setTimeExited(LocalDateTime.of(2021,6,1,13,22,04,05));
		truck.getTicket().setTimeExited(LocalDateTime.of(2021,6,1,13,22,04,05));

		//Calculate Fees(Hard coded for testing)
		System.out.println("Fee is: " + attendant.exitVehicle(vehicle));
		System.out.println("Fee is: " + attendant.exitVehicle(truck));
		
		//Get details of vehicles who paid via cash
		System.out.println(admin.getCashPaymentDetails().toString());
	}   
}
