

import java.util.*;

public class Section {
	String courseName;
	int sectionNumber;
	
	List<TranscriptEntry> gradeSheet;
	
	Section(int sectionNumber, String courseName){
		this.sectionNumber= sectionNumber;
		this.courseName=courseName;
		gradeSheet = new ArrayList<TranscriptEntry>();
	}
	
	public void addTranscetionEntry(TranscriptEntry transEntry) {
		gradeSheet.add(transEntry);
		}
	
}
