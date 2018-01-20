package inc.tortuga.sugarboy.quentinmars.utils.visual;

/**
 * Created by swift on 22.10.2017.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import sun.rmi.runtime.Log;

import java.util.logging.Logger;

public class Fonts {

    // Перечень символов, необходимых для загрузки из файла шрифта
    private final String FONT_CHARS = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrs" +
            "tuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXY" +
            "Z0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";

    // Ссылки на шрифты
    public BitmapFont main;
    public BitmapFont main_big;


    public Fonts() {

        FreeTypeFontGenerator minecraft = new FreeTypeFontGenerator(Gdx.files.internal("hacked.ttf")); // Подгружаем файл haced.ttf из рабочей бибилотеки
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter(); // Создаем экземпляр параметров для шрифта
        parameter.characters = FONT_CHARS; // Устанавливаем список используемых символов
        parameter.size = 40; // Размер шрифта
        parameter.color = Color.WHITE; // Цвет (белый)
        parameter.shadowOffsetX = 1; // Смещение теней по X
        parameter.shadowOffsetY = 1;
        main = minecraft.generateFont(parameter); // Создаем сам шрифт

        parameter.size = 72; // Меняем значение размера на 72
        main_big = minecraft.generateFont(parameter); // И создаем точно такой же шрифт, но больше

        minecraft.dispose();
    }

    // Удаление шрифтов из памяти
    public void dispose() {
        main.dispose();
    }

}