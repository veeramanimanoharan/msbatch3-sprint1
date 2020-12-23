package com.sl.ms.sprint1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.opencsv.CSVReader;

public class CsvReader {

	public static List<ItemModel> csvread (String csvfile) throws IOException, ParseException {


		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
		List<ItemModel> prods = new ArrayList<ItemModel>();
		
		CSVReader reader;
		try {
			reader = new CSVReader(new FileReader(csvfile), ',');
		
			System.out.println(csvfile);
		
 
		// read line by line
		String[] record = null;
		record = reader.readNext();
	//
		while ((record = reader.readNext()) != null) {
//			System.out.println(record[0]);
			ItemModel pro = new ItemModel();
			pro.setId(Integer.parseInt(record[0]));
			pro.setName(record[1]);
			pro.setPrice(Double.parseDouble(record[2]));
			pro.setTotal_amount(Integer.parseInt(record[3]));
			
	        LocalDate localDate = LocalDate.parse(record[4], formatter);
			pro.setDt(localDate);
			prods.add(pro);
//		System.out.println(pro.toString());
 //			proService.save(pro);
			
			
	}
	
		
		reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prods;
	}
}
