

class TranscriptEntry {
	Student student;
	Section section;
	String grade;
	
	TranscriptEntry(){
		
	}
	TranscriptEntry(Student s, Section sec, String grade){
		this.student = s;
		this.section = sec;
		this.grade = grade;
	}
	
	public String toString() {
		return "Student: " + student.name + "\n"
				+ "Course name: " + section.courseName + "\n"
				+ "Section number: " + section.sectionNumber + "\n"
				+ "Grade: " + grade + "\n";
	}
}
