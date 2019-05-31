package lesson4.labs.probC;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws ParseException {
		List<Employee> emps = new ArrayList<Employee>();
		emps.add(new Hourly(50,20));
		emps.add(new Salaried(7000));

		Commissioned comm = new Commissioned(0.10, 500);
		comm.addOrder(new Order(10, LocalDate.of(2019, Month.APRIL, 1), 100));
		comm.addOrder(new Order(50, LocalDate.of(2019, Month.APRIL, 15), 50));
		comm.addOrder(new Order(20, LocalDate.of(2019, Month.MAY, 10), 200));
		
		emps.add(comm);
		
		for(Employee emp:emps) {
			emp.calcCompensation().print();
		}
	}

}
