package twitter;
	import com.google.gson.JsonElement;
	import com.google.gson.JsonObject;
	import com.google.gson.JsonParser;
	import java.io.FileNotFoundException;
	import java.io.FileReader;
	import java.io.IOException;

	public class user {

	public static void main(String[] args)
	{
	   convertFileToJSON ("C:\\Users\\nishi\\TwittUser.json");
		System.out.println("json file");
	   //convertFileToJSON ("/Twitter_User/src/twitter/TwittUser.json");
	   
	}

	public static JsonObject convertFileToJSON (String fileName){

	        // Read from File to String
	        JsonObject jsonObject = new JsonObject();
	        
	        try {
	            JsonParser parser = new JsonParser();
	            JsonElement jsonElement = parser.parse(new FileReader(fileName));
	            
	            System.out.println(jsonElement);
	      
	            jsonObject = jsonElement.getAsJsonObject();
	       
	        } catch (FileNotFoundException e) {
	           
	        } catch (IOException ioe){
	        
	        }
	        return jsonObject;
	    }

	}

