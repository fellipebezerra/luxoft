package br.com.luxoft.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.luxoft.model.InstrumentPriceModifier;
import br.com.luxoft.repository.InstrumentPriceModifierDTO;

public class caculatorServiceImp implements Runnable{

	@Autowired
	private InstrumentPriceModifierDTO instrumentPriceModifierDTO;

	@SuppressWarnings("resource")
	@Override
	public void run() {
		while(true) {
			Calendar data = Calendar.getInstance();
			try {
				String arquivo = "C:\\files\\file.txt";
				BufferedReader br = null;
				String line = "";
				String cvsSplitBy = ",";

				br = new BufferedReader(new FileReader(arquivo));
				while ((line = br.readLine()) != null) {
					BigDecimal inValue;
					BigDecimal value;
					String[] proced = line.split(cvsSplitBy);
					System.out.println("IN: "+proced[0]);
					SimpleDateFormat simpleFormat = new SimpleDateFormat("dd-MMM-yyyy");
					Date date = (Date)simpleFormat.parse(proced[1]);
					data.setTime(date);

					if (data.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
					{
						System.out.println("SUNDAY!");
					}
					else if (data.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
					{
						System.out.println("SATURDAY!");
					}
					else
					{
						inValue = instrumentPriceModifierDTO.instrument(proced[0]);
						value = new BigDecimal(proced[2]);

						value = value.multiply(inValue);
						System.out.println("Work Days");
						System.out.println("Instrument: "+proced[0]+" Date: "+proced[1] +" New Value: "+value);
					}
				}



			} catch (FileNotFoundException e) {			
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
