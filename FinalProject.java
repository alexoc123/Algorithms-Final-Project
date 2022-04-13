import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


class FinalProject {
	int count = 0;
	
	public static void main(String args[]) {
		String filename = "stops.txt";
		String timesFilename = "stop_times.txt";
		TST map = new TST();
		List<String> keysTST = new LinkedList<String>();
		createTST(filename,map);
		Scanner input = new Scanner(System.in);
		boolean exit = true;
		while(exit) {
			System.out.println("Press 1 to find the shortest route");
			System.out.println("Press 2 to find information for a stop");
			System.out.println("Press 3 to search for all trips with a given arrival time");
			System.out.println("Press 0 to exit the program");
			String user = input.nextLine();
			
			if(user.equals(null)) {
				System.out.println("Error: Please enter a valid input");
			}
			else if(user.equals("1")) {
				System.out.println("Sorry this has not been implemented yet");
			}
			else if(user.equals("2")) {
				Scanner input2 = new Scanner(System.in);
				System.out.println("Enter the full name or start of the name of the bus stop you want to find information for");
				String answer = input2.nextLine();
				answer = answer.toUpperCase();
				getKeysTST(map,answer);
				
			}
			else if(user.equals("3")) {
				Scanner input2 = new Scanner(System.in);
				System.out.println("Enter the arrival time you would like the find all trips for");
				String answer = input2.nextLine();
				getTimes(answer,timesFilename);
				
				
				
			}
			else if(user.equals("0")) {
				exit = false;
			}
			else {
				System.out.println("Error: Please enter a valid input");
				System.out.println();
			}
		}
		System.out.println("Have a great day :)");
		
		
	}
	
	public static void createTST(String filename, TST map) {
		File file = new File(filename);
		Scanner scan = null;
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scan.nextLine();
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			String[] lines = line.split(",");
			String stopID = lines[0];
			String stopCode = lines[1];
			String stopName = lines[2];
			String stopDesc = lines[3];
			String stopLat = lines[4];
			String stopLon = lines[5];
			String zoneID = lines[6];
			String stopURL = lines[7];
			String locType = lines[8];
			
			//Fixing Stop Name String
			StringBuilder sb = new StringBuilder(stopName);
			if(sb.substring(0,8).equals("FLAGSTOP")) {
				String temp = sb.substring(0,8);
				sb.delete(0, 12);
				sb.append(" " + temp);
			}
			if(sb.substring(0,2).equals("WB") || sb.substring(0,2).equals("NB") || sb.substring(0,2).equals("SB")
				|| sb.substring(0,2).equals("EB")) {
				String temp = sb.substring(0,2);
				sb.delete(0, 3);
				sb.append(" " + temp);
			}
			stopName = sb.toString();
			
			String stopInfo = "The information for stop " + stopName + " is as follows:\n"
			+"Stop ID: " + stopID + "\n"
			+"Stop Code: " + stopCode + "\n"
			+ "Stop Description: " + stopDesc + "\n"
			+ "Stop Latitude: " + stopLat + "\n"
			+ "Stop Longitude: " + stopLon + "\n"
			+ "Zone ID: " + zoneID + "\n"
			+ "Stop URL: " + stopURL + "\n"
			+ "Location Type: " + locType + "\n";
			
			map.put(stopName, stopInfo);
			
			
		}
	}
	
	public static void getKeysTST(TST map,String prefix) {
		List<String> keys = new LinkedList<String>();
		keys.addAll((Collection<? extends String>) map.keysWithPrefix(prefix));
		if(keys.size() == 0) {
			System.out.println("Error: Please enter valid bus stop");
		}
		else {
			for(int i = 0; i < keys.size(); i++) {
				String tempKey = keys.get(i);
				System.out.println(map.get(tempKey));
			}
		}
	}
	
	public static void getTimes(String user, String filename) {
		File file = new File(filename);
		Scanner scan = null;
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scan.nextLine();
		int count = 0;
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			String[] lines = line.split(",");
			String arrTime = lines[1];
			String[] arrTimes = arrTime.split(":");
			String temp = arrTimes[0];
			if(Character.isWhitespace(temp.charAt(0))) {
				arrTime = arrTime.substring(1);
			}
			String[] hours = arrTime.split(":");
			String stringHour = hours[0];
			int hour = Integer.parseInt(stringHour);
			
			if(arrTime.equals(user) && hour < 24) {
				count++;
				String tripID = lines[0];
			
				String depTime = lines[2];
				String stopID = lines[3];
				String stopSeq = lines[4];
				String stopHead = lines[5];
				String pickType = lines[6];
				String dropType = lines[7];
				String dist = "";
				if(lines.length == 8) {
					dist = "0";
				}
				else {
					dist = lines[8];
				}
				String info = 
						"Trip ID:" + tripID + "\n" 
								+ "Departure Time: " + depTime + "\n"
								+ "Stop ID: " + stopID + "\n"
								+ "Stop Sequence: " + stopSeq + "\n"
								+ "Stop Headsign: " + stopHead + "\n"
								+ "Pickup Type: " + pickType + "\n"
								+ "Drop Off Type: " + dropType + "\n"
								+ "Distance Travelled: " + dist + "\n" + "\n";
				System.out.println(info);
				}
			}
		if(count == 0) {
			System.out.println("Error: Please enter a valid arrival time");
		}
	}
	
	
	
	
	
	
	
	
	
	
}
