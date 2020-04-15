package view;

public class LoginData {

static boolean case2 = true;
	public static boolean authenticate(String username, String password) {
        // hardcoded username and password
        if (username.equals("User123") && password.equals("aa")) {
        	case2=false;
            return true;
        }
        else if (username.equals("Admin") && password.equals("aa")){
    		MonthlyView k = new MonthlyView();
    		case2 = true;
    		return true;
        }
        
        
        return false;
	
        

      

	
	
}
	public static boolean getState(){
		if (case2){
		return true;
	}
		return false;
	}
	
}