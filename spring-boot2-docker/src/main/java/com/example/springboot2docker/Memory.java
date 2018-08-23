package com.example.springboot2docker;

public class Memory {
	
	private String maxMemory;
	private String freeMemory;
	private String usedMemory;
	public Memory(String maxMemory, String freeMemory, String usedMemory) {
		super();
		this.maxMemory = maxMemory;
		this.freeMemory = freeMemory;
		this.usedMemory = usedMemory;
	}
	public String  getMaxMemory() {
		return maxMemory;
	}
	public String getFreeMemory() {
		return freeMemory;
	}
	public String getUsedMemory() {
		return usedMemory;
	}
	
	
}
