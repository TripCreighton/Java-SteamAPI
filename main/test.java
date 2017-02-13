package main;

import steam.SteamAPI;
import steam.SteamEnumerations;

public class test {
	public static void main(String[] args){
		SteamAPI steam = new SteamAPI("YOUR KEY HERE");
		String[] sid = { "SID", "SID" };
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
}
