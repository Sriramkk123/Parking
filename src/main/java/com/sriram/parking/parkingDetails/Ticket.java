package com.sriram.parking.parkingDetails;
import java.time.LocalDateTime;
import java.util.UUID;

//Ticket class , used for assigning to each vehicle
public class Ticket {
	private String id;
	private LocalDateTime timeIssued;
	private LocalDateTime timeExited;
	
	public Ticket(LocalDateTime timeIssued)
	{
		String uuid = UUID.randomUUID().toString();
		this.id = uuid;
		this.timeIssued = timeIssued;
	}
	
	public Ticket()
	{
		String uuid = UUID.randomUUID().toString();
		this.id = uuid;
		this.timeIssued = LocalDateTime.now();
	}

	public LocalDateTime getTimeIssued() {
		return this.timeIssued;
	}

	public LocalDateTime getTimeExited() {
		return this.timeExited;
	}

	public void setTimeExited(LocalDateTime time) {
		this.timeExited = time;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", timeIssued=" + timeIssued + ", timeExited=" + timeExited + "]";
	}
	
	
}
