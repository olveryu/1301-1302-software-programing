// *************************
// Note: the following code will compile on nike, however you must run
//     javac *.java  from your project1 directory
// *************************

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
public class DBCommands{ 

	private String message;
	
	/**
	 * constructor of DBcommands
	 */
	public DBCommands(){
		message = "";
	}// constructor end

	/**
	 * 
	 * @param dbName
	 * @param tableName
	 * @return print out the text inside a file
	 */
	public String print(String dbName,String tableName){
		try{
			// returns pathnames for files and directory
			File table = new File(dbName,tableName);
			Scanner sc = new Scanner(table);
			// read till EOF
			String line = "";
			while (sc.hasNext()) {
				line += sc.nextLine() + "\r\n";
			}
			//close the scanner
			sc.close();
			return line;
		}catch(Exception e){
			e.getMessage();
			return "";
		}
	}

	/**
	 * get the message
	 * 
	 * @return: message
	 */
	public String getMessage() {
		return message;
	}//getter end

	/**
	 * set the message
	 * 
	 * @param: message
	 */
	public void setMessage(String message) {
		this.message = message;
	}//setter end

	/**
	 * insert the error message to the error.txt
	 * 
	 * @param line
	 * @param dbName
	 * @param error
	 */
	public void insertErrorMessage(String line,String dbName,String error){
		//insert message in the error file
		insert(getMessage() +"\r\n("+ line.trim() + ")\r\n", dbName, error);
		setMessage("");
	} // insertErrorMessage end

	/**
	 * insert the status message to the status.txt
	 * 
	 * @param line
	 * @param dbName
	 * @param status
	 */
	public void insertStatusMessage(String line,String dbName,String status){
		//format message
		//insert message in the status file
		insert(getMessage() +"\r\n("+ line.trim() + ")\r\n", dbName, status);
		setMessage("");
	} //insertStatusMessage end

	/**
	 * check the string if match idValue
	 * @param: dbName
	 * @param: tableName
	 * @param: idValue
	 * 
	 * @return: marker which is where is the string and marker = 0 means not exist
	 */
	public int stringCheck(String dbName, String tableName, String idValue){
		// returns pathnames for files and directory
		File table = new File(dbName,tableName);
		int marker = 0;
		int counter = 0;
		try{
			// declare the scanner for the table
			Scanner sc = new Scanner(table);
			// check the string that match idValue
			while (sc.hasNextLine()) {
				String line = sc.nextLine().trim();
				counter ++;
				if (line.equalsIgnoreCase(idValue)){
					marker = counter;
					sc.close();
					break;
				}
			}
			//close the scanner copy
			sc.close();
		}catch(Exception e){
			setMessage(e.getMessage());
		}
		return marker;
	}

	/**
	 * Create a unix directory named dbName.
	 * 
	 * @param: dbName
	 * @return: true or false
	 */
	public  boolean createDatabase(String dbName){
		try{
			// returns pathnames for files and directory
			File dir = new File(dbName);
			// check if the database exist
			if(dir.exists()){
				throw new Exception("Database \"" + dbName + "\" already exist.");
			}
			//create directories
			dir.mkdirs();
			setMessage("Database \""+dbName+"\" created.");
			//System.out.println(message);
			return true;
		}catch (Exception e){
			setMessage("Database \"" + dbName + "\" could not be created. " + e.getMessage());
			//System.out.println(message);
			return false;
		}
	} // end createDatabase

	/**
	 * Remove the unix directory named dbName.
	 * 
	 * @param: dbName
	 * @return: true or false
	 */
	public boolean dropDatabase(String dbName){
		try{
			// returns pathnames for files and directory
			File dir = new File(dbName);
			//delete directories
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				files[i].delete();
			}
			dir.delete();
			setMessage("Database \"" + dbName + "\" dropped and all files deleted.");
			//System.out.println(message);
			return true;
		}catch(Exception e){
			setMessage("Database \"" + dbName + "\" could not be drop. " + e.getMessage());
			//System.out.println(message);
			return false;
		}
	} // end dropDatabase

	/**
	 * Create a single column table file in the dbName directory
	 * 
	 * @param: dbName
	 * @param: tableName
	 * @return: true or false
	 */
	public boolean createTable(String dbName, String tableName){
		try {
			// returns pathnames for files and directory
			File file = new File(dbName,tableName);
			//check if the file exist
			if(file.exists()){
				throw new Exception("Table \"" + tableName + "\" has already exist.");
			}
			//create files
			file.createNewFile();
			setMessage("Table \"" + tableName + "\" created.");
			//System.out.println(message);
			return true;
		}catch (Exception e) {
			setMessage("Table \"" + tableName + "\" could not be created. " + e.getMessage());
			//System.out.println(message);
			return false;
		}
	}//  end createTable

	/**
	 * Remove the table file from the dbName directory
	 * 
	 * @param: dbName
	 * @param: tableName
	 * @return: true or false
	 */
	public boolean dropTable(String dbName, String tableName){
		try {
			// returns pathnames for files and directory
			File table = new File(dbName,tableName);
			//drop files
			if(!table.exists()){
				throw new Exception("Table \"" + tableName + "\" does not exist.");
			}
			table.delete();
			setMessage("Table \"" + tableName + "\" deleted.");
			//System.out.println(message);
			return true;
		} catch (Exception e) {
			setMessage("Table \"" + tableName + "\" coule not be deleted. " + e.getMessage());
			//System.out.println(message);
			return false;
		}
	} // end dropTable

	/**
	 * Write the string into the table file as a record.
	 * 
	 * @param: insertString
	 * @param: dbName
	 * @param: tableName
	 * 
	 * return: true or false
	 */
	public boolean insert(String insertString, String dbName, String tableName){
		try{
			// returns pathnames for files and directory
			File table = new File(dbName,tableName);
			//check if the file does not exist
			if(!table.exists()){
				throw new Exception("Table \"" + tableName + "\" did not exist.");
			}
			//use bufferedWriter to include fileWriter
			FileWriter fw = new FileWriter(table,true);
			//write insertString
			fw.append(insertString + "\r\n");
			fw.flush();
			fw.close();
			setMessage("inserted \"" + insertString + "\".");
			//System.out.println(message);
			return true;
		}catch (Exception e) {
			setMessage("inserted \"" + insertString + "\" fail. " + e.getMessage());
			//System.out.println(message);
			return false;
		}
	} // end of inert

	/**
	 *  select all method to read file
	 *  
	 * @param: dbName
	 * @param: tableName
	 * 
	 * @return: true or false
	 */
	public boolean selectAll(String dbName, String tableName) {
		try{
			// returns pathnames for files and directory
			File table = new File(dbName,tableName);
			Scanner sc = new Scanner(table);
			// read till EOF
			String selectAllString = "";
			while (sc.hasNext()) {
				selectAllString += sc.nextLine() + "\r\n";
				//System.out.println(line);
			}
			//close the scanner
			sc.close();
			setMessage("selectAll for \"" + tableName + "\" reading completed.");
			selectWindowPanel.print(selectAllString+message);
			//System.out.println(message);
			return true;
		}catch(Exception e){
			setMessage("Reading failed. " + e.getMessage());
			//System.out.println(message);
			return false;
		}
	} // slectAll end


	/**
	 *  select with where method to read file
	 *  
	 * @param: dbName
	 * @param: tableName
	 * @param: idvalue
	 * 
	 * @return: true or false
	 */
	public boolean select(String dbName, String tableName, String idValue) {
		//declare the string printOut
		String selectString = "";
		try{
			File table = new File(dbName,tableName);
			Scanner sc = new Scanner(table);
			//check if the string exist
			int marker = stringCheck(dbName,tableName,idValue);
			//if the string exist
			if(marker != 0){
				selectString += "----[The Colunm]----" + "\r\n";
				selectString += idValue + " Found in the " + marker + " line.";
			}else{
				selectString += "\"" + idValue + "\" did not found.";
			}
			sc.close();
			setMessage("\r\n\"" + tableName + "\" reading completed.");
			selectWindowPanel.print(selectString + message);
			//System.out.println(message);
			return true;
		}catch(Exception e){
			setMessage("\"" + tableName +  "\" reading fail. " + selectString + e.getMessage());
			//System.out.println(message);
			return false;
		}
	} // select end 

	/**
	 * delete all record in the file
	 * 
	 * @param: dbName
	 * @param: tableName
	 * @return true or false
	 */
	public boolean deleteAll(String dbName, String tableName){
		try{
			File table = new File(dbName,tableName);
			//check if the file does not exist
			if(!table.exists()){
				throw new Exception("table \"" + tableName + " \" did not exist.");
			}
			//declared fileWriter
			FileWriter fw = new FileWriter(table);
			fw.close();
			setMessage("\"" + tableName + "\" all records deleted. ");
			//System.out.println(message);
			return true;
		}catch(Exception e){
			setMessage("\"" + tableName + "\" records could not be deleted. " + e.getMessage());
			//System.out.println(message);
			return false;
		}
	}// deleteAll end

	/**
	 * delete string in the .txt files
	 * 
	 * @param: dbName
	 * @param: tableName
	 * @param: idValue
	 * @return: true or false
	 */
	public boolean delete(String dbName, String tableName, String idValue){
		try{
			File table = new File(dbName,tableName);
			File temp = new File(dbName,"temp.txt");
			//check if the file does not exist
			if(!table.exists()){
				throw new Exception("table \"" + tableName + "\" did not exist.");
			}
			//check if the idValue exist
			int marker = stringCheck(dbName,tableName,idValue);
			if(marker == 0){
				throw new Exception("\""+idValue + "\" did not exist. Could not be deleted.");
			}
			//delete the string
			FileWriter fw = new FileWriter(table,true);
			FileWriter tempFw = new FileWriter(temp);
			Scanner sc = new Scanner(table);
			while(sc.hasNextLine()){
				String line = sc.nextLine().trim();
				if(!line.equalsIgnoreCase(idValue)){
					tempFw.append(line + "\r\n");
				}
			}
			//close the filewriter and scanner
			fw.close();
			tempFw.close();
			sc.close();
			//delete the old table file and rename the temp file to the table name
			table.delete();
			temp.renameTo(table);
			setMessage("\""+idValue +"\" has been deleted.");
			//System.out.println(message);
			return true;
		}catch(Exception e){
			setMessage(tableName + "\""+idValue +"\" could not be deleted. " + e.getMessage());
			//System.out.println(message);
			return false;
		}
	}//end of delete

	/**
	 * update the value with new value in the table
	 * 
	 * @param: dbName
	 * @param: tableName
	 * @param: value
	 * @param: newValue
	 * @return: true or false
	 */
	public boolean update(String dbName, String tableName, String value, String newValue){
		try{
			File table = new File(dbName,tableName);
			File temp = new File(dbName,"temp.txt");
			//check if the file does not exist
			if(!table.exists()){
				throw new Exception("table \"" + tableName + "\" did not exist.");
			}
			//check if the idValue exist
			int marker = stringCheck(dbName,tableName,value);
			if(marker == 0){
				throw new Exception("\""+value + "\" did not exist. Could not be updated.");
			}
			//delete the string
			FileWriter fw = new FileWriter(table,true);
			FileWriter tempFw = new FileWriter(temp);
			Scanner sc = new Scanner(table);
			while(sc.hasNextLine()){
				String line = sc.nextLine().trim();
				if(!line.equalsIgnoreCase(value)){
					tempFw.append(line + "\r\n");
				}else{
					tempFw.append(newValue + "\r\n");
				}
			}
			//close the filewriter and scanner
			fw.close();
			tempFw.close();
			sc.close();
			//delete the old table file and rename the temp file to the table name
			table.delete();
			temp.renameTo(table);
			setMessage("\""+value +"\" has been update to \"" +newValue +"\".");
			//System.out.println(message);
			return true;
		}catch(Exception e){
			setMessage(tableName + "\""+value +"\" could not be updated. " + e.getMessage());
			//System.out.println(message);
			return false;
		}

	}//end of update


} // end of class DBCommands