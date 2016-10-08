package steam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class SteamAPI {
	private String apiKey = null;
	public SteamAPI(String apiKey){
		if(apiKey.isEmpty()){
			try { throw new Exception("You didn't provide an API Key!"); } 
			catch (Exception e) { e.printStackTrace(); }
		}
		this.apiKey = apiKey;
	}
	
	public String GetNewsForApp(int appId, int count, int maxLength, SteamEnumerations formatType){
		if(appId < 0 || count < 0 || maxLength < 0 || formatType == null) return null;
		String fType = null;
		try{
			switch(formatType){
			case JSON:
				fType = "json";
			case XML:
				fType = "xml";
			case VDF:
				fType = "vdf";
			default: break;
			}
			StringBuilder sb = new StringBuilder();
			String input = null;
			URL json = new URL("http://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/?appid=" + appId + "&count=" + count + "&maxlength=" + maxLength + "&format=" + fType);
			BufferedReader jsonBR = new BufferedReader(new InputStreamReader(json.openConnection().getInputStream(), "UTF-8"));
			while ((input = jsonBR.readLine()) != null)
				sb.append(input);
			jsonBR.close();
			return sb.toString();
		}catch(Exception e){
			System.out.println("Oops! An error occured! -> " + e.getMessage());
		}
		return null;
	}
	
	public String GetGlobalAchievementPercentagesForApp(int appId, int count, String name, SteamEnumerations formatType){
		if(appId < 0 || count < 0 || formatType == null) return null;
		String fType = null;
		try{
			switch(formatType){
			case JSON:
				fType = "json";
			case XML:
				fType = "xml";
			case VDF:
				fType = "vdf";
			default: break;
			}
			StringBuilder sb = new StringBuilder();
			String input = null;
			URL json = new URL("http://api.steampowered.com/ISteamUserStats/GetGlobalStatsForGame/v0001/?appid=" + appId + "&count=" + count + "&format=" + fType + "&name[0]=" + name);
			BufferedReader jsonBR = new BufferedReader(new InputStreamReader(json.openConnection().getInputStream(), "UTF-8"));
			while ((input = jsonBR.readLine()) != null)
				sb.append(input);
			jsonBR.close();
			return sb.toString();
		}catch(Exception e){
			System.out.println("Oops! An error occured! -> " + e.getMessage());
		}
		return null;
	}
	
	public String GetPlayerSummaries(String steamID, SteamEnumerations formatType){
		if(steamID == null) return null;
		String fType = null;
		switch(formatType){
		case JSON:
			fType = "json";
		case XML:
			fType = "xml";
		case VDF:
			fType = "vdf";
		default: break;
		}
		
		try{
			StringBuilder sb = new StringBuilder();
			String input = null;
			for(int i = 0; i <= 100; i++){
				URL json = new URL("http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key="+apiKey+"&steamids="+steamID+"&format="+fType);
				BufferedReader jsonBR = new BufferedReader(new InputStreamReader(json.openConnection().getInputStream(), "UTF-8"));
				while ((input = jsonBR.readLine()) != null)
					sb.append(input);
				jsonBR.close();
			}
			return sb.toString();
		}catch(Exception e){
			System.out.println("Oops! An error occured! -> " + e.getMessage());
		}
		return null;
	}
	
	public String GetPlayerSummaries(String[] steamID, SteamEnumerations formatType){
		if(steamID == null || steamID.length > 100) return null;
		String fType = null;
		switch(formatType){
		case JSON:
			fType = "json";
		case XML:
			fType = "xml";
		case VDF:
			fType = "vdf";
		default: break;
		}
		try{
			StringBuilder sb = new StringBuilder();
			String input = null, sids = steamID[0];
			for(int i = 1; i < steamID.length; i++){
				sids += "," + steamID[i];
			}
			URL json = new URL("http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key="+apiKey+"&steamids="+sids+"&format="+fType);
			BufferedReader jsonBR = new BufferedReader(new InputStreamReader(json.openConnection().getInputStream(), "UTF-8"));
			while ((input = jsonBR.readLine()) != null)
				sb.append(input);
			jsonBR.close();
			return sb.toString();
		}catch(Exception e){
			System.out.println("Oops! An error occured! -> " + e.getMessage());
		}
		return null;
	}
	
	public String GetFriendsList(String steamID, SteamEnumerations relationship, SteamEnumerations formatType){
		if(steamID == null ||  relationship == null || formatType == null) return null;

		String rel = null, fType = null;
		try{
			switch(formatType){
			case JSON:
				fType = "json";
			case XML:
				fType = "xml";
			case VDF:
				fType = "vdf";
			default: break;
			}
			switch(relationship){
			case ALL:
				rel = "all";
			case FRIEND:
				rel = "friend";
			default: rel = "all";
			}
			StringBuilder sb = new StringBuilder();
			String input = null;
			URL json = new URL("http://api.steampowered.com/ISteamUser/GetFriendList/v0001/?key="+apiKey+"&steamid="+steamID+"&relationship="+rel+"&format="+fType);
			BufferedReader jsonBR = new BufferedReader(new InputStreamReader(json.openConnection().getInputStream(), "UTF-8"));
			while ((input = jsonBR.readLine()) != null)
				sb.append(input);
			jsonBR.close();
			return sb.toString();
		}catch(Exception e){
			System.out.println("Oops! An error occured! -> " + e.getMessage());
		}
		
		
		return null;
	}

	public String GetPlayerAchievements(String steamID, int appId){
		if(steamID == null || appId < 0) return null;
		try{
			StringBuilder sb = new StringBuilder();
			String input = null;
			URL json = new URL("http://api.steampowered.com/ISteamUserStats/GetPlayerAchievements/v0001/?key="+apiKey+"&steamid="+steamID+"&appid="+appId);
			BufferedReader jsonBR = new BufferedReader(new InputStreamReader(json.openConnection().getInputStream(), "UTF-8"));
			while ((input = jsonBR.readLine()) != null)
				sb.append(input);
			jsonBR.close();
			return sb.toString();
		}catch(Exception e){
			System.out.println("Oops! An error occured! -> " + e.getMessage());
		}
		return null;
	}

	public String GetUserStatesForGame(String steamID, int appId){
		if(steamID == null || appId < 0) return null;
		try{
			StringBuilder sb = new StringBuilder();
			String input = null;
			URL json = new URL("http://api.steampowered.com/ISteamUserStats/GetUserStatsForGame/v0002/?key="+apiKey+"&steamid="+steamID+"&appid="+appId);
			BufferedReader jsonBR = new BufferedReader(new InputStreamReader(json.openConnection().getInputStream(), "UTF-8"));
			while ((input = jsonBR.readLine()) != null)
				sb.append(input);
			jsonBR.close();
			return sb.toString();
		}catch(Exception e){
			System.out.println("Oops! An error occured! -> " + e.getMessage());
		}
		return null;
	}

	public String GetOwnedGames(String steamID, SteamEnumerations formatType){
		if(steamID == null || formatType == null) return null;

		String fType = null;
		switch(formatType){
		case JSON:
			fType = "json";
		case XML:
			fType = "xml";
		case VDF:
			fType = "vdf";
		default: break;
		}
		
		try{
			StringBuilder sb = new StringBuilder();
			String input = null;
			URL json = new URL("http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key="+apiKey+"&steamid="+steamID+"&format="+fType);
			BufferedReader jsonBR = new BufferedReader(new InputStreamReader(json.openConnection().getInputStream(), "UTF-8"));
			while ((input = jsonBR.readLine()) != null)
				sb.append(input);
			jsonBR.close();
			return sb.toString();
		}catch(Exception e){
			System.out.println("Oops! An error occured! -> " + e.getMessage());
		}
		return null;
	}

	public String GetRecentlyPlayedGames(String steamID, int count, SteamEnumerations formatType){
		if(steamID == null || count < 0 || formatType == null) return null;

		String fType = null;
		switch(formatType){
		case JSON:
			fType = "json";
		case XML:
			fType = "xml";
		case VDF:
			fType = "vdf";
		default: break;
		}
		
		try{
			StringBuilder sb = new StringBuilder();
			String input = null;
			URL json = new URL("http://api.steampowered.com/IPlayerService/GetRecentlyPlayedGames/v0001/?key="+apiKey+"&steamid="+steamID+"&format="+fType);
			BufferedReader jsonBR = new BufferedReader(new InputStreamReader(json.openConnection().getInputStream(), "UTF-8"));
			while ((input = jsonBR.readLine()) != null)
				sb.append(input);
			jsonBR.close();
			return sb.toString();
		}catch(Exception e){
			System.out.println("Oops! An error occured! -> " + e.getMessage());
		}
		return null;
	}

	public String IsPlayingSharedGame(String steamID, int appId, SteamEnumerations formatType){
		if(steamID == null || appId < 0 || formatType == null) return null;
		String fType = null;
		switch(formatType){
		case JSON:
			fType = "json";
		case XML:
			fType = "xml";
		case VDF:
			fType = "vdf";
		default: break;
		}
		
		try{
			StringBuilder sb = new StringBuilder();
			String input = null;
			URL json = new URL("http://api.steampowered.com/IPlayerService/IsPlayingSharedGame/v0001/?key="+apiKey+"&steamid="+steamID+"&format="+fType);
			BufferedReader jsonBR = new BufferedReader(new InputStreamReader(json.openConnection().getInputStream(), "UTF-8"));
			while ((input = jsonBR.readLine()) != null)
				sb.append(input);
			jsonBR.close();
			return sb.toString();
		}catch(Exception e){
			System.out.println("Oops! An error occured! -> " + e.getMessage());
		}
		return null;
	}

	public String GetSchemaForGame(int appId, SteamEnumerations formatType){
		String fType = null;
		switch(formatType){
		case JSON:
			fType = "json";
		case XML:
			fType = "xml";
		case VDF:
			fType = "vdf";
		default: break;
		}
		
		try{
			StringBuilder sb = new StringBuilder();
			String input = null;
			URL json = new URL("http://api.steampowered.com/ISteamUserStats/GetSchemaForGame/v2/?key="+apiKey+"&appid="+appId+"&format="+fType);
			BufferedReader jsonBR = new BufferedReader(new InputStreamReader(json.openConnection().getInputStream(), "UTF-8"));
			while ((input = jsonBR.readLine()) != null)
				sb.append(input);
			jsonBR.close();
			return sb.toString();
		}catch(Exception e){
			System.out.println("Oops! An error occured! -> " + e.getMessage());
		}
		return null;
	}

	public String GetPlayerBans(String steamID){
		if(steamID == null) return null;
		try{
			StringBuilder sb = new StringBuilder();
			String input = null;
			URL json = new URL("http://api.steampowered.com/ISteamUser/GetPlayerBans/v1/?key="+apiKey+"&steamids="+steamID);
			BufferedReader jsonBR = new BufferedReader(new InputStreamReader(json.openConnection().getInputStream(), "UTF-8"));
			while ((input = jsonBR.readLine()) != null)
				sb.append(input);
			jsonBR.close();
			return sb.toString();
		}catch(Exception e){
			System.out.println("Oops! An error occured! -> " + e.getMessage());
		}
		return null;
	}
	
	public String GetPlayerBans(String[] steamID){
		if(steamID == null || steamID.length > 100) return null;
		try{
			StringBuilder sb = new StringBuilder();
			String input = null, sids = steamID[0];
			for(int i = 1; i < steamID.length; i++){
				sids += "," + steamID[i];
			}
			System.out.println(sids);
			URL json = new URL("http://api.steampowered.com/ISteamUser/GetPlayerBans/v1/?key="+apiKey+"&steamids="+sids);
			BufferedReader jsonBR = new BufferedReader(new InputStreamReader(json.openConnection().getInputStream(), "UTF-8"));
			while ((input = jsonBR.readLine()) != null)
				sb.append(input);
			jsonBR.close();
			return sb.toString();
		}catch(Exception e){
			System.out.println("Oops! An error occured! -> " + e.getMessage());
		}
		return null;
	}
}

