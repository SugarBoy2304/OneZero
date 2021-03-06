package inc.tortuga.sugarboy.quentinmars.utils.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by swift on 22.10.2017.
 */

public class GameConfig {

    private int width;
    private int height;
    private String gameName;
    private String deviceType;
    private String lang = "En";
    private float volume;
    private Preferences pref;

    public GameConfig(int width, int height, String gameName, String deviceType) {
        this.width = width;
        this.height = height;
        this.gameName = gameName;
        this.deviceType = deviceType;
    }

    public void load() {
        pref = Gdx.app.getPreferences("inc.tortuga.sugarboy.QuenitMars.pref");
        this.lang = sifString("language", "En");
        this.volume = sifFloat("volume", 0.5F);

        // TODO баловство
        Gdx.app.log("Loading..", "Test number #" + (10000000 + pref.putInteger("starters", sifInteger("starters", 0)+1).getInteger("starters")));

        pref.flush();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getGameName() {
        return gameName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
        pref.putString("language", lang);
        pref.flush();
    }




































    protected int sifInteger(String key, int val) {
        if (!pref.contains(key)) {
            pref.putInteger(key, val);
            return val;
        }
        return pref.getInteger(key);
    }
    protected float sifFloat(String key, float val) {
        if (!pref.contains(key)) {
            pref.putFloat(key, val);
            return val;
        }
        return pref.getFloat(key);
    }
    protected Long sifLong(String key, long val) {
        if (!pref.contains(key)) {
            pref.putLong(key, val);
            return val;
        }
        return pref.getLong(key);
    }
    protected String sifString(String key, String val) {
        if (!pref.contains(key)) {
            pref.putString(key, val);
            return val;
        }
        return pref.getString(key);
    }
    protected boolean sifBoolean(String key, boolean val) {
        if (!pref.contains(key)) {
            pref.putBoolean(key, val);
            return val;
        }
        return pref.getBoolean(key);
    }

}
