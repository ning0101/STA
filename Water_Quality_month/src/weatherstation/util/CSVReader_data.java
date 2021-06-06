package weatherstation.util;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import com.opencsv.*;

import weatherstation.aqi.datamodel.WeatherStation;
import weatherstation.aqi.datamodel.Water_Quality;
import weatherstation.datamodel.*;
public class CSVReader_data {
	
	public static void main(String[] args) {
		ArrayList<Water_Quality> metro_data = readDataCSV();
		for(int i=0; i<metro_data.size();i++) {
			System.out.println("水質測點: "+metro_data.get(i).getStation_id());
		}
	}

		public static ArrayList<Water_Quality> readDataCSV() {
		// TODO Auto-generated method stub
			ArrayList<Water_Quality> dataArray = new ArrayList<Water_Quality>();
			
			try {
				CSVReader csvreader = new CSVReader(new FileReader("data/wqx_p_117_20200729121047.csv"));
				// 頝喲����
				String[] next = csvreader.readNext();
				while ((next = csvreader.readNext()) != null)
			    {
					Water_Quality data = new Water_Quality(next[0], next[1], Double.parseDouble(next[2]), Double.parseDouble(next[3]), Double.parseDouble(next[4]), Double.parseDouble(next[5]), Double.parseDouble(next[6]), Double.parseDouble(next[7]), Double.parseDouble(next[8]), Double.parseDouble(next[9]), Double.parseDouble(next[10]), Double.parseDouble(next[11]), Double.parseDouble(next[12]), Double.parseDouble(next[13]), Double.parseDouble(next[14]), Double.parseDouble(next[15]), Double.parseDouble(next[16]), Double.parseDouble(next[17]), Double.parseDouble(next[18]), Double.parseDouble(next[19]), Double.parseDouble(next[20]), Double.parseDouble(next[21]));
					dataArray.add(data);
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



