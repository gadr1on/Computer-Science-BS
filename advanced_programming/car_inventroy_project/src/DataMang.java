import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*******************************************************
 * PROGRAMMERS: Kevin Hernandez & Kimberly Pereyra
 * UNIVERSITY: Inter-american of Puerto Rico, Bayamon
 * CLASS: Advanced Programming
 * PROFESSOR: Jaime Yeckle Sanchez
 * DATE: MAY 8, 2018
 *******************************************************/

public class DataMang {
	
	public List<String> cars, parts, carId, partId;
	public List<String> cars_prices, parts_prices, quantity1, quantity2;
	public String car,part; 
	
	public void setData() throws IOException {
		cars = addDataString("Cars.txt");
		parts = addDataString("Parts.txt");
		carId = addDataString("CarIDs.txt");
		partId = addDataString("PartIDs.txt");
		cars_prices = addDataString("Prices_Cars.txt");
		parts_prices = addDataString("Prices_Parts.txt");
		quantity1 = addDataString("Quantity.txt");
		quantity2 = addDataString("Quantity.txt");
	}
	
	public List<String> addDataString(String file) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Kevin\\Documents\\CAR_PROJECTS\\Data\\"+file));
		String str;

		List<String> list = new ArrayList<String>();
		while((str = in.readLine()) != null) {list.add(str);}
		return list;
		}

	//public String getCar() {return car;}
	
	//public String getPart() {return part;}
	
	public int getCarPrice() {return Integer.parseInt(cars_prices.get(cars.indexOf(car)));}
	
	public int getPartPrice() {return Integer.parseInt(parts_prices.get(parts.indexOf(part)));}
	
	public String getCarId() {return carId.get(cars.indexOf(car));}
	
	public String getPartId() {return partId.get(parts.indexOf(part));}
	
	public int getQuantity1() {return Integer.parseInt(quantity1.get(parts.indexOf(part)));}
	
	public int getQuantity2() {return Integer.parseInt(quantity2.get(cars.indexOf(car)));}
	
	public int sumQuantity1(String part) {int num= Integer.parseInt(quantity1.get(parts.indexOf(part))); return num++-1;}
	
	public int resQuantity1() {int num= Integer.parseInt(quantity1.get(parts.indexOf(part))); return num--;}
	
	public int sumQuantity2(String car) {int num= Integer.parseInt(quantity2.get(cars.indexOf(car))); return num++-1;}
	
	public int resQuantity2() {int num= Integer.parseInt(quantity2.get(cars.indexOf(car))); return num--;}

	public void setQuantity1(String part, String num) {quantity1.set(parts.indexOf(part), num);}
	
	public void setQuantity2(String car, String num) {quantity2.set(cars.indexOf(car), num);}
	
	// INVENTORY FUNCTIONS
	public int getQuantity(String carObject) throws IOException {
		File file = new File("C:\\Users\\Kevin\\Documents\\CAR_PROJECTS\\Data\\Inventory.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		
		String st, data = null;
		int quantity;
		while ((st = br.readLine())!=null)
			if(st.contains(carObject)) data=st;
		
		quantity = Integer.parseInt(data.replaceAll("[^0-9]+", ""));

		return quantity;
	}
	
	public String getAutopart(String carObject) throws IOException {
		File file = new File("C:\\Users\\Kevin\\Documents\\CAR_PROJECTS\\Data\\Inventory.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String st, data = null;
		while ((st = br.readLine())!=null)
			if(st.contains(carObject)) data=st;

		return data;
	}
}
