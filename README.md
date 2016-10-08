# Java-SteamAPI
An API for getting information from steam using Java, with no external libraries!

##How to use
Instantiate the class, then add it to your project. When it's added import the SteamAPI.java file into your project by using "import steam.SteamAPI;"

##Use within a project
```java
public static void main(String[] args){
		SteamAPI steam = new SteamAPI("API-KEY-HERE");
		String[] sid = { "STEAM-ID" };
		System.out.println(steam.GetNewsForApp(440, 3, 300, SteamEnumerations.JSON));
		System.out.println(steam.GetGlobalAchievementPercentagesForApp(17740, 1, "global.map.emp_isle", SteamEnumerations.JSON));
		System.out.println(steam.GetPlayerSummaries(sid, SteamEnumerations.JSON));
		System.out.println(steam.GetFriendsList("76561198046608916", SteamEnumerations.ALL, SteamEnumerations.JSON));
		System.out.println(steam.GetPlayerAchievements("76561198046608916", 440));
		System.out.println(steam.GetUserStatesForGame("76561197960435530", 440));
		System.out.println(steam.GetOwnedGames("76561197960435530", SteamEnumerations.JSON));
		System.out.println(steam.GetRecentlyPlayedGames("76561197960435530", 3, SteamEnumerations.JSON));
		System.out.println(steam.IsPlayingSharedGame("76561197960435530", 440, SteamEnumerations.JSON));
		System.out.println(steam.GetSchemaForGame(440, SteamEnumerations.JSON));
		System.out.println(steam.GetPlayerBans("76561197960435530"));
	}
```
