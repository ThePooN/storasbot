package lt.ekgame.storasbot.utils.osu;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.tillerino.osuApiModel.OsuApiBeatmap;

import lt.ekgame.storasbot.StorasDiscord;

public class OsuBeatmapCatche {
	
	private static Map<String, OsuApiBeatmap> catche = new HashMap<>();
	
	/**
	 * @param beatmapId
	 * @return OsuApiBeatmap object or null if failed 3 times
	 */
	public static OsuApiBeatmap getBeatmap(String beatmapId) {
		if (catche.containsKey(beatmapId)) {
			return catche.get(beatmapId);
		}
		else {
			OsuApiBeatmap beatmap = null;
			for (int i = 0; i < 3; i++) {
				try {
					beatmap = StorasDiscord.getOsuApi().getBeatmap(beatmapId);
					break;
				} catch (NumberFormatException | IOException e) {
					e.printStackTrace();
				}
			}
			if (beatmap != null)
				catche.put(beatmapId, beatmap);
			return beatmap;
		}
	}

}
