package lo.server;

import static lo.server.gui.InputAndOutput.*;
import static lo.server.utilities.Constant.*;
import static lo.server.utilities.Validation.*;
import lo.server.model.Student;
import lo.server.model.Team;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	
	/*Método responsável por inicializar o programa*/
	public static void main(String[]args) {
		new Server().server(inuput());
	}
	
	/*Responsável por receber e retornar todos os dados das turmas*/
	private static String inuput() {

		try {
			List<Team>teams = fillingClass();
			return formattingData(teams);
		}catch(Exception e) {
			showMessage(MESSAGE_CLOSE, TITLE);
		}
		return null;
	}
	
	/*Retorna os dados formatados em uma String*/
	private static String formattingData(List<Team>teams) {
		StringBuilder stringBuilder = new StringBuilder();
		
		for(Team team : teams)
			stringBuilder.append(team.toString());
		return stringBuilder.toString();
	}
	
	/*Preenche os dados da classe Team*/
	private static List<Team> fillingClass() {
		String option = "";
		List<Team> teams = new ArrayList<>();
		List<Student>students;
		do {
			try {
				String name, id;
				int year;
				id = generatorId();
				name = manipulatingString(MESSAGE_NAME_TEAM);
				year = manipulatingInt(MESSAGE_YEAR);
				students = studentData();
				teams.add(new Team(id,name,year,students));
				
			}catch(Exception e) {
				showMessage(ERROR_DATA, TITLE);
			}
			
			option = inputString(MESSAGE_CLOSE_TEAM,TITLE);
			
		}while(option != null && option.equals("1"));
		return teams;
	}
	
	/*Preenche os dados da classe Student*/
	private static List<Student> studentData() {
		String option = "";
		List<Student>students = new ArrayList<>();
		do {
			try {
				String name, matriculation;
				int age;
				matriculation = manipulatingString(MESSAGE_MATRICULATION);
				matriculation = checksMatriculation(students, matriculation);
				name = manipulatingString(MESSAGE_NAME_STUDENT);
				age = manipulatingInt(MESSAGE_AGE);
				students.add(new Student(matriculation,name, age));
			}catch(Exception e) {
				showMessage(ERROR_DATA, TITLE);
			}
			option = inputString(MESSAGE_CLOSE_STUDENT,TITLE);
		}while(option != null && option.equals("1"));
		return students;
	}
	
	/*Realiza o papel do servidor, ele recebe os dados, cria um socket,
	  espera a conexão é envia os dados*/
	public void server(String datas) {
		//Ao criar um soquete o códogo pode desparar exceções
		try {
			//Instanciando um socket em java
			@SuppressWarnings("resource")
			ServerSocket server = new ServerSocket(14000);
			System.out.println("Servidor aguardando uma conexão");
			
			while(true) {
				//O método accept() bloqueia a execução até que haja uma solicitação de conexão feita por um cliente
				Socket client = server.accept();
				OutputStream clientOutput = client.getOutputStream();
				PrintWriter output = new PrintWriter(new OutputStreamWriter(clientOutput,"utf-8"),true);
				output.println(datas);
				output.close();
				client.close();
			}
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}
}
