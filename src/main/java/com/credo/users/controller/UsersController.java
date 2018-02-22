package com.credo.users.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.credo.users.dto.UpdateMaxYearsDTO;
import com.credo.users.dto.UsersDTO;
import com.credo.users.dto.UsersPrevilegesDTO;
import com.credo.users.exceptions.UserNotFoundError;
import com.credo.users.model.UserPrevilegesResponse;
import com.credo.users.service.UserService;
import com.credo.users.util.GeneratePdfReport;

@RestController
public class UsersController {

	@Autowired
	UserService userService;

	
	@RequestMapping(value = "/updateMaxYears", headers=("content-type=multipart/*"), method = RequestMethod.POST)
	public ResponseEntity<?> updateMaxYears(Model model, @RequestParam("admin") MultipartFile excelfile) {	
		ResponseEntity<String> resultData=null;
		try {
			List<UpdateMaxYearsDTO> updateMaxYearsList = new ArrayList<UpdateMaxYearsDTO>();
			int i = 1;
			// Creates a workbook object from the uploaded excelfile
			XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
			// Creates a worksheet object representing the first sheet
			XSSFSheet worksheet = workbook.getSheetAt(0);
			// Reads the data in excel file until last row is encountered
			while (i <= worksheet.getLastRowNum()) {
				// Creates an object for the UserInfo Model
				UpdateMaxYearsDTO updateMaxYearDTO = new UpdateMaxYearsDTO();
				// Creates an object representing a single row in excel
				XSSFRow row = worksheet.getRow(i++);
				// Sets the Read data to the model class				
				//String x=row.getCell(1).getStringCellValue();
				updateMaxYearDTO.setExperiencetotrainingid(Double.valueOf(row.getCell(0).getNumericCellValue()).longValue());
				updateMaxYearDTO.setExpids(Double.valueOf(row.getCell(1).getNumericCellValue()).longValue());
				updateMaxYearDTO.setMaxyears(Double.valueOf(row.getCell(2).getNumericCellValue()).longValue());
				updateMaxYearDTO.setTrainingids(Double.valueOf(row.getCell(3).getNumericCellValue()).longValue());
				updateMaxYearDTO.setTrainingName(row.getCell(4).getStringCellValue());				
				updateMaxYearsList.add(updateMaxYearDTO);
			}			
			
			model.addAttribute("updateMaxYearsList", updateMaxYearsList);
			if (userService.updateMaxYears(updateMaxYearsList))
				resultData= new ResponseEntity<String>("Max Experience Updated Successfully....", HttpStatus.OK);
			else {
				resultData= new ResponseEntity<String>("Updating Experience Got failed....", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultData;		
	}
	
	
	
	/*@RequestMapping(value = "updateMaxYears", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<?> getUsers(@RequestBody UpdateMaxYearsDTO updateMaxYears) {
		if (userService.updateMaxYears(updateMaxYears))
			return new ResponseEntity<String>("Max Experience Updated Successfully....", HttpStatus.OK);
		else {
			return new ResponseEntity<String>("Updating Experience Got failed....", HttpStatus.OK);
		}
	}*/
	
	
	@RequestMapping(value = "/pdfreport", method = RequestMethod.POST,produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getAdminReport(@RequestBody UsersDTO reqUser) throws IOException {
        List<UserPrevilegesResponse> userPrevList = (List<UserPrevilegesResponse>) userService.getAdminUser(reqUser);
        ByteArrayInputStream bis = GeneratePdfReport.adminReport(userPrevList);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=AuditReport.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
 }
	
	
		@RequestMapping(value = "getUser", method = RequestMethod.GET, produces = "application/json")
		public List<UserPrevilegesResponse> getUsers(@RequestParam("uname") String uname, @RequestParam("action") String action) {		
			List<UserPrevilegesResponse> userPrevList = userService.getUser(uname, action);		
			return userPrevList;
		}
	
	@RequestMapping(value = "getAdminUser", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<UserPrevilegesResponse> getAdminUsers(@RequestBody UsersDTO reqUser) {		
		List<UserPrevilegesResponse> userPrevList = userService.getAdminUser(reqUser);		
		return userPrevList;
	}
	
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<?> updateUser(@RequestBody UsersPrevilegesDTO userPrevileges,HttpServletRequest request) {
		if (userService.updateUser(userPrevileges))
			return new ResponseEntity<String>(HttpStatus.OK);
		return new ResponseEntity<UserNotFoundError>(
						new UserNotFoundError("User Not found, please enter valid user...."), HttpStatus.NOT_FOUND);
	}
	
	
	@RequestMapping(value = "isPendingTrainings", method = RequestMethod.GET,produces = "application/json")
	public boolean isPendingTrainings(@RequestParam("uname") String uname,@RequestParam("action") String action) {		
		
		if(userService.isPendingTrainings(uname,action)) {			
			return true;
		}
		else {
			return false;
		}		
	}	
}
