import java.io.File;
import java.util.Scanner;

public class Database{
	String hello;

	public Database(){
		hello = "";
	}

	public void checkDatabase(String fileName){
		// *** declare the DBUSER commands file
		//declare the location of the file
		String dbName = ".";
		String error = "Error.txt";
		String status = "Status.txt";
		//declare the DBCommands
		DBCommands command = new DBCommands();
		//delcare the case commands
		sqlCommand option = new sqlCommand();
		//create new error and status table
		command.createTable(dbName, error);
		command.createTable(dbName, status);
		command.deleteAll(dbName, error);
		command.deleteAll(dbName, status);
		//set the message to null
		command.setMessage("");
		//loop through the end of the DBUSER file
		try{
			//declare the file path
			File dir = new File(dbName + "/" + fileName);
			//declare the scanner for the DBSUER to read
			Scanner in = new Scanner(dir);
			while(in.hasNextLine()){
				command.setMessage("");
				//read the whole line and store wholeLine
				String wholeLine = in.nextLine();
				String temp = wholeLine.trim();
				if(temp.length() != 0){
					//front is the front part of the line as the first command
					String front ="";
					//create a temp to store the rest of the line
					String remain = wholeLine;
					//Separate the line for the command
					front = option.front(remain," ");
					remain = option.rear(remain," ");
					switch(front.toUpperCase()){
					case "CREATE"://SQLCOMMAND CREATE
						option.create(remain, wholeLine,dbName,error,status);
						break;
					case "DROP"://SQLCOMMAND DROP
						option.drop(remain, wholeLine, dbName, error, status);
						break;
					case "INSERT"://SQLCOMMAND INSERT
						option.insert(remain, wholeLine, dbName, error, status);
						break;
					case "DELETE"://SQLCOMMAND DELETED
						option.delete(remain, wholeLine, dbName, error, status);
						break;
					case "SELECT"://SQLCOMMAND SELECT
						option.select(remain, wholeLine, dbName, error, status);
						break;
					case "UPDATE"://SQLCOMMAND UPDATE
						option.update(remain, wholeLine, dbName, error, status);
						break;
					default:
						//command error
						command.setMessage("Invild or empty command, please check symbol and keyword.");
						command.insertErrorMessage(wholeLine,dbName,error);
					}
				}
			}
			in.close();
			command.deleteAll(dbName, "temp.txt");
		}catch(Exception e){
			//error occur when open the file
			command.insertErrorMessage("Problem with output to file " + fileName + "\r\n" + e.getMessage(), dbName, error);
		}
	}
}