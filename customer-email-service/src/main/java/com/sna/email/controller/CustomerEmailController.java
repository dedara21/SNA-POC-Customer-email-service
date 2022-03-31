package com.sna.email.controller;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sna.email.model.CustomerToken;
import com.sna.email.utils.EmailUtil;

@RestController
@RequestMapping("/api/customer/emails")
public class CustomerEmailController {

	@Autowired
	private EmailUtil emailUtil;

	@PostMapping(consumes = "application/json", produces = "application/json")
	public String sendUpdatedRecordEmail(@RequestBody HashMap<String, Integer> map) {
		
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();  
			HSSFSheet sheet = workbook.createSheet("SNA Expiry Sheet"); 
			
			HSSFRow rowhead = sheet.createRow((short)0);  
			rowhead.createCell(0).setCellValue("S.No.");  
			rowhead.createCell(1).setCellValue("Total Records Read"); 
			rowhead.createCell(2).setCellValue("Total Number of Ambassadors");
			rowhead.createCell(3).setCellValue("Total SNA Expired"); 
			rowhead.createCell(4).setCellValue("Total Customers EXP history"); 
			
			HSSFRow row = sheet.createRow((short)1);  
			row.createCell(0).setCellValue("1");  
			row.createCell(1).setCellValue(map.get("Total Records Read"));  
			row.createCell(2).setCellValue(map.get("Total Number of Ambassadors"));
			row.createCell(3).setCellValue(map.get("Total SNA Expired"));  
			row.createCell(4).setCellValue(map.get("Total Customers EXP history"));  
			
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			workbook.write(bos);
			bos.close();
			workbook.close();
			
			DataSource fds = new ByteArrayDataSource(bos.toByteArray(), "application/vnd.ms-excel");

			emailUtil.sendMailWithAttachment("testdivya425@gmail.com", "SNA records expiry report","Followin SNA's have been expired for the year 2022. Please find attached document", fds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Email sent successfully";
	}

}
