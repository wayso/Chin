package net.miladinov.innerclasses;

public class Parcel6 {
	private void internalTracking(boolean b) {
		if (b) {
			class TrackingSlip {
				private String id;
				TrackingSlip(String s) { 
					id = s; 
				}
				String getSlip() { return id; } 
			}
			TrackingSlip ts = new TrackingSlip("slip");
			String s = ts.getSlip();
		}
	}
}