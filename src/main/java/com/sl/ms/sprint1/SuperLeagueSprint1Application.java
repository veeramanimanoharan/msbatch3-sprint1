package com.sl.ms.sprint1;

import java.io.IOException;
import java.nio.file.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SuperLeagueSprint1Application {

	public static void main(String[] args) throws IOException, InterruptedException, ParseException{
		SpringApplication.run(SuperLeagueSprint1Application.class, args);
		List<ItemModel> prods = new ArrayList<ItemModel>();
		System.out.println(prods);
		
		 WatchService watchService = FileSystems.getDefault().newWatchService();
	        Path path = Paths.get("C:\\Users\\veera\\Documents\\GitHub\\Inv File");
	        path.register(watchService,  StandardWatchEventKinds.ENTRY_CREATE);
	        WatchKey key;
	        List<ItemModel> tmpprods = new ArrayList<ItemModel>();
	        while ((key = watchService.take()) != null) {
	            for (WatchEvent<?> event : key.pollEvents()) {
	            	Thread.sleep(1000);
	                System.out.println("Event kind:" + event.kind() + ". File affected: " + event.context() + ".");
	                tmpprods=null;
	                tmpprods =CsvReader.csvread(path.toString()+"\\"+event.context().toString());
	            }
	            key.reset();
	           
	            prods.addAll(tmpprods);
	            System.out.println(prods);
	           
//***************************Todays******************************	            
	            List<ItemModel> todayProds= prods.stream()
	            		.filter(dtt->dtt.getDt().isEqual(LocalDate.now()))
	            		.collect(Collectors.toList());
//***************************Current Month******************************	            
	            List<ItemModel> thisMonthProds= prods.stream()
	            		.filter(dtt->dtt.getDt().getMonth()==LocalDate.now().getMonth())
	            		.collect(Collectors.toList());
	            
//***************************Todays Sale******************************
	            System.out.println("--------------------------------------------------------------------");
	             System.out.println("Todays Sale - Date"+LocalDate.now());
	             System.out.println("--------------------------------------------------------------------");
	             todayProds.stream().forEach(it->System.out.println(it.name+"             | "+it.getId()+"             | "+it.getTotal_amount()));;
				 System.out.println("End");
//	***********************Today Top 5 Sale*******************************		
				 System.out.println("--------------------------------------------------------------------");
				 System.out.println("Top 5 Sale Overall");
				 System.out.println("--------------------------------------------------------------------");
				 List<ItemModel> sortedListdec = prods.stream()
				            .sorted(Comparator.comparingInt(ItemModel::getTotal_amount).reversed())
				            .collect(Collectors.toList());
				 
				 sortedListdec.stream().limit(5).forEach(it->System.out.println(it.name+"             | "+it.getId()+"             | "+it.getTotal_amount()));
//***********************This Month Top 5 Sale*******************************				 
				 System.out.println("--------------------------------------------------------------------");
				 System.out.println("Month Top 5 Sale");
				 System.out.println("--------------------------------------------------------------------");
				 List<ItemModel> sortedListdecMonth = thisMonthProds.stream()
				            .sorted(Comparator.comparingInt(ItemModel::getTotal_amount).reversed())
				            .collect(Collectors.toList());
				 
				 sortedListdecMonth.stream().limit(5).forEach(it->System.out.println(it.name+"             | "+it.getId()+"             | "+it.getTotal_amount()));
				 
//***********************1 item total sale*******************************	
				 System.out.println("--------------------------------------------------------------------");
				 System.out.println("1 item total sale");
				 System.out.println("--------------------------------------------------------------------");
				 ItemModel oneitem = new ItemModel();
				 prods.stream()
//						 .peek(it->System.out.println(it.getName()))
						 .filter(it->it.getId()==20)
//						 .peek(it->System.out.println("veera"))
						 .forEach(it->{
							 oneitem.setName(it.getName());
				 oneitem.setId(it.getId());
				 oneitem.setTotal_amount(it.getTotal_amount()+oneitem.getTotal_amount());
						 });
//						 .collect(Collectors.toList());
				 
				 System.out.println(oneitem.name+"             | "+oneitem.getId()+"             | "+oneitem.getTotal_amount());
				 
	        }
	        
	        

	        watchService.close();
	    }
		
	
}
	


	

