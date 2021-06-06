package weatherstation.util;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.*;
import weatherstation.aqi.datamodel.WeatherStation;


public class CSVReader_weatherstation {

		public static void main(String args[]) {
			ArrayList<WeatherStation> weather_array = readStationCSV();
			for(int i=0; i<weather_array.size();i++) {
				System.out.println("\n測站名稱: "+weather_array.get(i).getStation_name());
			}
		}
		
		public static ArrayList<WeatherStation> readStationCSV() {
			ArrayList<WeatherStation> dataArray = new ArrayList<WeatherStation>();
			try {
				CSVReader csvreader = new CSVReader(new FileReader("data/weather_station_metro.csv"));
				// 跳過欄位
				String[] next = csvreader.readNext();
				while ((next = csvreader.readNext()) != null)
			    {
					WeatherStation weather = new WeatherStation(next[0],next[1],Double.parseDouble(next[2]),Double.parseDouble(next[3]),Double.parseDouble(next[4]),Double.parseDouble(next[5]),next[6],Integer.parseInt(next[7]),next[8],Integer.parseInt(next[9]));
					dataArray.add(weather);
			    }
				System.out.println(dataArray.size());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return dataArray;
		}
	}



