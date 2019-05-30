

import java.util.*;

public class Student {
	String id;
	String name;
	List<TranscriptEntry> grades;
	
	Student(String id, String name){
		this.id= id;
		this.name=name;
		grades = new ArrayList<TranscriptEntry>();
	}
	
	public void addTranscetionEntry(TranscriptEntry transEntry) {
		grades.add(transEntry);
		}
	
	public Transcript getTranscript() {
		return new Transcript(grades);
		
	}
	
}
