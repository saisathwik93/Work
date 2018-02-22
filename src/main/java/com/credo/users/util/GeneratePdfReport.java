package com.credo.users.util;

import com.credo.users.model.UserPrevilegesResponse;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;

public class GeneratePdfReport {
    public static ByteArrayInputStream adminReport(List<UserPrevilegesResponse> userPrevList) {
        Document document = new Document();
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
        	Font titleFont =FontFactory.getFont(FontFactory.HELVETICA_BOLD,20);
        	titleFont.setColor(BaseColor.RED);
        	Font headFont = FontFactory.getFont(FontFactory.TIMES_BOLD);
        	Font bodyFont = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        	Paragraph preface = new Paragraph();
        	
        	
        
        	preface.add(Element.ALIGN_CENTER,new Paragraph("Audit Report",headFont));
        	
        	preface.add(new Paragraph("\n"));
        	preface.add(new Paragraph("\n"));
       

            for (UserPrevilegesResponse userPrev : userPrevList) {
        
            	 int count =1;
                preface.add(new Chunk(("User Name       "),headFont));
               preface.setTabSettings(new TabSettings(250f));
               preface.add(Chunk.TABBING);
                
                preface.add(new Chunk(userPrev.getUsername(),bodyFont));
             
                preface.add(new Paragraph(""));
                
                //Roles Col
                
                preface.add(new Chunk(("Roles           "),headFont));
               preface.setTabSettings(new TabSettings(350f));
               preface.add(Chunk.TABBING);
               
                preface.add(new Chunk(userPrev.getPrevileges(), bodyFont));
                
                // Privileges Col
                preface.add(new Paragraph(""));
                
                preface.add(new Chunk(("Privileges         "),headFont));
               preface.setTabSettings(new TabSettings(250f));
                preface.add(Chunk.TABBING);
            
                preface.add(new Chunk(userPrev.getActions() + "\n", bodyFont));
                
                //Completed Trainings Col              
                
                List<String> cts = Arrays.asList(userPrev.getCompletedtrainings());
               count=1;
                for(String completed:cts) {
                	if(count==1) {
                		preface.add(new Chunk(("Completed Trainings   	  "), headFont));
                		preface.add(new Chunk((completed+ "\n"),bodyFont));
                	}
                	
                	else {
           
               preface.setTabSettings(new TabSettings(250f));
               preface.add(Chunk.TABBING);
               preface.add(new Chunk((completed + "\n"), bodyFont));	
                	}
                	
                	count++;
                	
                }
                
                
                //waived Trainings Col
                
                List<String> wts = Arrays.asList(userPrev.getWaivedtrainings());
                
                count=1;
                for(String waived:wts) {
                	if(count==1) {
                		
                		preface.add(new Chunk(("Waived Trainings   	      "), headFont));
                		preface.add(new Chunk((waived+ "\n"),bodyFont));
                	}
                	
                	else {
                		
              
               preface.setTabSettings(new TabSettings(250f));
               preface.add(Chunk.TABBING);
              
               preface.add(new Chunk((waived+ "\n"),bodyFont));
                	}
                	count++;
                }
                
                //Pending Trainings Col
//                List<String> pts = Arrays.asList(userPrev.getPendingtrainings2());
//                String pending = String.join("\n" , pts);
//                preface.setTabSettings(new TabSettings(56f));
//                preface.add(Chunk.TABBING);
//                preface.setAlignment(Element.ALIGN_RIGHT);
//                preface.add(new Paragraph("Pending Trainings:"));
//                preface.add(new Chunk(pending));
                
                List<String> pts = Arrays.asList(userPrev.getPendingtrainings2());
                
                count=1;
                for(String pending : pts) {
                	if(count==1) {
                		preface.add(new Chunk(("Pending Trainings   	     "), headFont));
                		preface.add(new Chunk((pending+ "\n"),bodyFont));
                	}
                	else {
                	preface.setTabSettings(new TabSettings(200f));
                    preface.add(Chunk.TABBING);
                
                    
                    preface.add(new Chunk((pending+" \n"),bodyFont));
                    
                }
                	count++;
                }
                
               
                
                
                //Technical Skills Col
                List<String> technical= Arrays.asList(userPrev.getJd());
                count=1;
                for(String jd : technical) {
                	if(count==1) {
                		preface.add(new Chunk(("Job Description   	         "), headFont));
                		preface.add(new Chunk((jd+ "\n"),bodyFont));
                	}
                	else {
                	preface.setTabSettings(new TabSettings(120f));
                    preface.add(Chunk.TABBING);
                    preface.add(new Chunk((jd+ " \n"),bodyFont));
                    	
                }
                	count++;
                }
                
                
                //Years of Exp Col
                List<Long> list = Arrays.asList(userPrev.getYears()); 
                List<String> newList	 = list.stream().map(s->String.valueOf(s)).collect(Collectors.toList()); 
                count=1;
               for(String years:newList) {
            	   
            	   if(count==1) {
            		   preface.add(new Chunk(("Years of Experience   	   "), headFont));
               		preface.add(new Chunk((years+ "\n"),bodyFont));
            	   }
            	   else{
            	   preface.setTabSettings(new TabSettings(120f));
            	   preface.add(Chunk.TABBING);
            	   preface.add(new Chunk((years+ "\n"),bodyFont));
            		   
            	   }
            	  count++; 
            	}
              
                preface.add(new Paragraph("  "));
                preface.add(new Paragraph("********************************************************************************************************"));
                preface.add(new Paragraph("  "));
                preface.add(new Paragraph("  "));
                
            }
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(preface);            
            document.close();            
        } catch (DocumentException ex) {        
            Logger.getLogger(GeneratePdfReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}