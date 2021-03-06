

public class StudentSectionFactory {

	public static Section createSection(int secNum, String courseName) {
		if(courseName == null) {
			throw new IllegalArgumentException("Section can not be null");
		}
		 
		return new Section(secNum, courseName);
	}
	public static Student createStudent(String id, String name) {
		if(name == null) 
			throw new IllegalArgumentException("Student name cannot be null");
		
		return new Student(id, name);
	}
	
	public static void newTranscriptEntry(Student s, Section sect, String grade) {
		TranscriptEntry t = new TranscriptEntry(s,sect,grade);
		s.addTranscetionEntry(t);
		sect.addTranscetionEntry(t);
	}
	

}
