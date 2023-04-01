package model;

import ui.Main;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Controller {

	private Project[] projects;

	public Controller() {

		projects = new Project[10];
	
	}
	
	//Incomplete
	public boolean RegisterProject(String projectName, String clientName, String projectType, Calendar initialDate, Calendar finalDate , double budget)  {

		Project newProject= new Project(projectName, clientName, initialDate, finalDate, budget);

		for(int i=0;i< projects.length;i++){
			if(projects[i] == null){
				projects[i]=newProject;
				return true;
			}
		}


		return false;
	}

	public String searchProjectsAfterDate(String date) {
		Project[] projectsFound = new Project[10];
        int count = 0;
        
        Calendar searchDate = new GregorianCalendar();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
            searchDate.setTime(formatter.parse(date));
        } catch (ParseException e) {
            return "ERROR: DATE IN INCORRECT FORMAT";
        }
        
        for (int i = 0; i < projects.length; i++) {
            if (projects[i] != null && projects[i].getInitialDate().after(searchDate)) {
                projectsFound[count] = projects[i];
                count++;
            }
        }
       
        if (count == 0) {
            return "No projects found";
        }
        
        String msg = "";
        for (int i = 0; i < count; i++) {
            msg += projectsFound[i].getName() + " - " + projectsFound[i].getClientName() + "\n";
        }
        return msg;
    }
	
	
	public String searchProjectsBeforeDate(String date) {
		Project[] projectsFound = new Project[10];
        int count = 0;
        
        Calendar searchDate = new GregorianCalendar();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
            searchDate.setTime(formatter.parse(date));
         } catch (ParseException e) {
            return "ERROR: DATE IN INCORRECT FORMAT";
         }
       
         for (int i = 0; i < projects.length; i++) {
            if (projects[i] != null && projects[i].getFinalDate().before(searchDate)) {
                projectsFound[count] = projects[i];
                count++;

		
			}

		}
        if (count == 0){
		return "No projects found";
	 }

	    String msg= "";
	    for (int i= 0; i< count;i++){
		msg += projectsFound[i].getName() + "-" + projectsFound[i].getClientName()+ "\n";
	 }
	    return msg;

    }
}