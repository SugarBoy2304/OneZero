package inc.tortuga.sugarboy.quentinmars.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import inc.tortuga.sugarboy.quentinmars.Game;
import inc.tortuga.sugarboy.quentinmars.utils.logic.GameConfig;

public class DesktopLauncher {

	public static void main(String[] arg) {
		// Создаем конфиг игрового движка
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		// Задаем название для нашего окна
		config.title = "Единички-нолики";

		// Высота и ширина окна
		config.width = 1280;
		config.height = 720;

		// Создаем экземпляр нашего приложения
		new LwjglApplication(
				new Game(new GameConfig(config.width, config.height, config.title, "PC")), // Класс игры
				config // Кофиг
		);
	}
}
