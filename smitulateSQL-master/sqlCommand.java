

public class sqlCommand {
	//declare the DBCommands
	DBCommands command = new DBCommands();
	final String errorMessage = "Invalid command, please check symbols and keywords.";

	/**
	 * split the string into two part,this is the front part
	 * 
	 * @param: remain
	 * @param: split
	 */
	public String front(String remain,String split){
		if(remain.toUpperCase().indexOf(split) != -1){
			//split the string into two part
			String front = remain.substring(0,remain.toUpperCase().indexOf(split)).trim();
			return front;
		}else{
			return split + " did not exist.";
		}
	}// end of front

	/**
	 * split the string into two part,this is the rear part
	 * 
	 * @param: remain
	 * @param: split
	 */
	public String rear(String remain,String split){
		if(remain.toUpperCase().indexOf(split) != -1){
			//split the string into two part
			remain = remain.substring(remain.toUpperCase().indexOf(split)+split.length()).trim();
			return remain;
		}else{
			return split + " did not exist.";
		}
	}// end of rear

	/**
	 * SQL command "CREATE"
	 * @param: remain
	 * @param: wholeLine
	 * @param: dbName
	 * @param: error
	 * @param: status
	 */
	public void create(String remain, String wholeLine, String dbName, String error, String status){
		//declare the front part as the type, the rear part as the remain
		String type = front(remain," ");
		String rear = rear(remain," ");
		//match the type DATABSE
		switch(type.toUpperCase()){
		case "DATABASE":
			if(!command.createDatabase(rear)){
				//send message when create failed
				command.insertErrorMessage(wholeLine,dbName,error);
			}else{
				//send message when created successful
				command.insertStatusMessage(wholeLine,dbName,status);
			}
			break;
		case "TABLE":
			if(remain.indexOf(".") != -1){
				//the front part
				String database = remain.substring(remain.indexOf(" "), remain.indexOf(".")).trim();
				//the rear part
				String table = remain.substring(remain.indexOf(".")+1)+".txt".trim();
				//send message when created failed
				if(!command.createTable(database, table)){
					command.insertErrorMessage(wholeLine,dbName,error);
				}else{
					//send message when created successful
					command.insertStatusMessage(wholeLine,dbName,status);
				}
			}else{
				//send message when create failed
				command.setMessage(errorMessage);
				command.insertErrorMessage(wholeLine,dbName,error);
			}
			break;
		default:
			command.setMessage(errorMessage);
			command.insertErrorMessage(wholeLine,dbName,error);
		}
	}//end of create

	/**
	 * SQL command "DROP"
	 * @param: remain
	 * @param: wholeLine
	 * @param: dbName
	 * @param: error
	 * @param: status
	 */
	public void drop(String remain, String wholeLine, String dbName, String error, String status){

		//declare the front part as the type, the rear part as the remain
		String type = front(remain," ");
		String rear = rear(remain," ");
		//match the type DATABSE
		switch(type.toUpperCase()){
		case "DATABASE":
			if(!command.dropDatabase(rear)){
				//send message when dropped failed
				command.insertErrorMessage(wholeLine,dbName,error);
			}else{
				//send message when dropped successful
				command.insertStatusMessage(wholeLine,dbName,status);
			}
			break;
		case "TABLE":
			if(remain.indexOf(".") != -1){
				//the front part
				String database = remain.substring(remain.indexOf(" "), remain.indexOf(".")).trim();
				//the rear part
				String table = remain.substring(remain.indexOf(".")+1)+".txt".trim();
				//send message when dropped failed
				if(!command.dropTable(database, table)){
					command.insertErrorMessage(wholeLine,dbName,error);
				}else{
					//send message when dropped successful
					command.insertStatusMessage(wholeLine,dbName,status);
				}
			}else{
				//send message when dropped failed
				command.setMessage(errorMessage);
				command.insertErrorMessage(wholeLine,dbName,error);
			}
			break;
		default:
			command.setMessage(errorMessage);
			command.insertErrorMessage(wholeLine,dbName,error);
		}
	}// end of drop

	/**
	 * SQL command "insert"
	 * @param remain
	 * @param wholeLine
	 * @param dbName
	 * @param error
	 * @param status
	 */
	public void insert(String remain, String wholeLine, String dbName, String error, String status){
		if((wholeLine.indexOf(".") != -1) && (wholeLine.toUpperCase().indexOf("INTO") != -1)){
			String front;
			front = front(remain, "INTO");
			remain = rear(remain, "INTO");
			//the front part
			String database = remain.substring(0,remain.indexOf(".")).trim();
			//the rear part
			String table = remain.substring(remain.indexOf(".")+1)+".txt".trim();
			//send message when insert failed
			if(!command.insert(front, database, table)){
				command.insertErrorMessage(wholeLine,dbName,error);
			}else{
				//send message when insert successful
				command.insertStatusMessage(wholeLine,dbName,status);
			}
		}else{
			//send message when insert failed
			command.setMessage(errorMessage);
			command.insertErrorMessage(wholeLine,dbName,error);
		}
	}// end of insert

	/**
	 * SQL command SELECT
	 * @param remain
	 * @param wholeLine
	 * @param dbName
	 * @param error
	 * @param status
	 */
	public void select(String remain, String wholeLine, String dbName, String error, String status){
		//determine select all or just select
		if((wholeLine.toUpperCase().indexOf("FROM") != -1) && (wholeLine.indexOf(".") != -1)){
			//selete method
			String front;
			//Separate and get the rear part after from
			remain = rear(remain,"FROM");
			//Separate and get the front and rear part before and after "." 
			front = front(remain, ".");
			remain = rear(remain, ".");
			//check is there has a "WHERE COLUMN"
			if(wholeLine.toUpperCase().indexOf("WHERE COLUMN") != -1){
				//store the database part
				String database = front;
				//store the table part
				String  table = front(remain,"WHERE COLUMN")+".txt";
				//check is there has a "="
				if(remain.indexOf("=") != -1){
					//store the idValue part
					String idValue = rear(remain,"=");
					//select method 
					if(!command.select(database, table, idValue)){
						command.insertErrorMessage(wholeLine,dbName,error);
					}else{
						command.insertStatusMessage(wholeLine,dbName,status);
					}
				}else{
					command.setMessage(errorMessage);
					command.insertErrorMessage(wholeLine,dbName,error);
				}
			}else{
				//select all method
				if(!command.selectAll(front, remain+ ".txt")){
					command.insertErrorMessage(wholeLine,dbName,error);
				}else{
					command.insertStatusMessage(wholeLine,dbName,status);
				}
			}
		}else{
			command.setMessage(errorMessage);
			command.insertErrorMessage(wholeLine,dbName,error);	
		}
	}// end of select

	/**
	 * SQL command DELETE
	 * @param remain
	 * @param wholeLine
	 * @param dbName
	 * @param error
	 * @param status
	 */
	public void delete(String remain, String wholeLine, String dbName, String error, String status){
		//determine delete all or delete
		if((wholeLine.toUpperCase().indexOf("FROM") != -1) && (wholeLine.indexOf(".") != -1)){
			//delete method
			String front;
			//Separate and get the rear part after from
			remain = rear(remain,"FROM");
			//Separate and get the front and rear part before and after "." 
			front = front(remain,".");
			remain = rear(remain,".");
			//check is there has a "WHERE COLUMN"
			if(wholeLine.toUpperCase().indexOf("WHERE COLUMN") != -1){
				//store the database part
				String database = front;
				//store the table part
				String  table = front(remain,"WHERE COLUMN")+".txt";
				//check is there has a "="
				if(remain.indexOf("=") != -1){
					//store the idValue part
					String idValue = rear(remain,"=");
					//delete method 
					if(!command.delete(database, table, idValue)){
						command.insertErrorMessage(wholeLine,dbName,error);
					}else{
						command.insertStatusMessage(wholeLine,dbName,status);
					}
				}else{
					command.setMessage(errorMessage);
					command.insertErrorMessage(wholeLine,dbName,error);
				}
			}else{
				//delete all method
				if(!command.deleteAll(front, remain+ ".txt")){
					command.insertErrorMessage(wholeLine,dbName,error);
				}else{
					command.insertStatusMessage(wholeLine,dbName,status);
				}
			}
		}else{
			command.setMessage(errorMessage);
			command.insertErrorMessage(wholeLine,dbName,error);	
		}
	}// end of delete

	/**
	 * SQL command update
	 * 
	 * @param remain
	 * @param wholeLine
	 * @param dbName
	 * @param error
	 * @param status
	 */
	public void update(String remain, String wholeLine, String dbName, String error, String status){
		//check how many " = " in the line
		int equalSignCounter = 0;
		for(int i=0; i<wholeLine.length(); i++){
			String temp = wholeLine.substring(i, i+1);
			if(temp.equals("=")){
				equalSignCounter ++;
			}
		}
		if((wholeLine.toUpperCase().indexOf("WHERE COLUMN") != -1) && (wholeLine.toUpperCase().indexOf("SET COLUMN") != -1) && (equalSignCounter == 2) && (wholeLine.indexOf(".") != -1)){
			String front;
			//Separate and get the front and rear part before and after "." 
			front = front(remain,".");
			remain = rear(remain,".");
			//store the database part
			String database = front;
			//separate from SET COLUMN
			front = front(remain,"SET COLUMN");
			remain = rear(remain,"SET COLUMN");
			//store the table part
			String table = front;
			//separate from the first "="
			remain = rear(remain,"=");
			//separate from WHERE COLUMN
			front  = front(remain,"WHERE COLUMN");
			//store the value
			String newValue = front;
			//Separate from the second "="
			remain = rear(remain,"=");
			String value = remain;
			if(!command.update(database, table + ".txt", value, newValue)){
				command.insertErrorMessage(wholeLine,dbName,error);
			}else{
				command.insertStatusMessage(wholeLine,dbName,status);
			}
			//check is there has a "WHERE COLUMN"
		}else{
			command.setMessage(errorMessage);
			command.insertErrorMessage(wholeLine,dbName,error);
		}
	}// end of update

}//end of sqlCommand 

