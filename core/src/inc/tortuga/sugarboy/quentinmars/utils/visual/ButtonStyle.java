package inc.tortuga.sugarboy.quentinmars.utils.visual;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import inc.tortuga.sugarboy.quentinmars.Game;

/**
 * Created by swift on 28.10.2017.
 */

public class ButtonStyle {

    // Три поля задающие стили для кнопок
    private TextButton.TextButtonStyle style; // Обычный стиль
    private TextButton.TextButtonStyle forX; // Стиль для единичек
    private TextButton.TextButtonStyle forO; // Стиль для ноликов

    private TextureAtlas atlas;
    private Skin skin;

    // Конструктор класса ButtonStyle
    public ButtonStyle() {
        atlas = new TextureAtlas("ui/gui.atlas");
        skin = new Skin(atlas);

        style = new TextButton.TextButtonStyle();
        style.up = skin.getDrawable("button.up");
        style.down = skin.getDrawable("button.down");
        style.pressedOffsetX = 1F;
        style.pressedOffsetY = -1F;
        style.font = Game.get().getFonts().main;
        style.fontColor = new Color(1F, 1F, 1F, 1);
        style.downFontColor = new Color(0.35F, 0.91F, 0.63F, 1F);

        forX = new TextButton.TextButtonStyle();
        forX.up = skin.getDrawable("button.up");
        forX.down = skin.getDrawable("button.down");
        forX.pressedOffsetX = 1F;
        forX.pressedOffsetY = -1F;
        forX.font = Game.get().getFonts().main;
        forX.fontColor = new Color(0.94F, 0.22F, 0.28F, 1F);
        forX.downFontColor = new Color(0.35F, 0.91F, 0.63F, 1F);

        forO = new TextButton.TextButtonStyle();
        forO.up = skin.getDrawable("button.up");
        forO.down = skin.getDrawable("button.down");
        forO.pressedOffsetX = 1F;
        forO.pressedOffsetY = -1F;
        forO.font = Game.get().getFonts().main;
        forO.fontColor = new Color(0.22F, 0.30F, 0.94F, 1F);
        forO.downFontColor = new Color(0.35F, 0.91F, 0.63F, 1F);
    }

    // Геттеры возвращающие тот или иной стиль

    public TextButton.TextButtonStyle forX() {
        return forX;
    }

    public TextButton.TextButtonStyle style() {
        return style;
    }

    public TextButton.TextButtonStyle forO() {
        return forO;
    }

    public void dispose() {
        atlas.dispose();
        skin.dispose();
    }
}
