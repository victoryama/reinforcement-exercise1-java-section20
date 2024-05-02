package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		String path = "C:\\Users\\victo\\OneDrive\\Documentos\\desenvolvimento\\ws-java\\section20_reinforcement_1\\in.txt";
		System.out.println("Enter full file path:  " + path);
		System.out.print("Enter salary: ");
		double salary = sc.nextDouble();
		
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
					
			List<Employee> list = new ArrayList<>();
				
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			
			List<String> emails = list.stream()
					.filter(x -> x.getSalary() >= salary)
					.map(x -> x.getEmail())
					.sorted()
					.collect(Collectors.toList());
			
			System.out.println("Email fo people whose salary is more than " + String.format("%.2f", salary));
			emails.forEach(System.out::println);
				
			double sum = list.stream()
					.filter(x -> x.getName().charAt(0) == 'M')
					.map(x -> x.getSalary())
					.reduce(0.0,  (x, y) -> x + y);
			System.out.println("Sum of the salary from people show name starts with 'M': " + String.format("%.2f", sum));
			
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

}
