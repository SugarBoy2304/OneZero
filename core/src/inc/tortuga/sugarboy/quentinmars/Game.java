package inc.tortuga.sugarboy.quentinmars;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inc.tortuga.sugarboy.quentinmars.screens.menu.MainMenuScreen;
import inc.tortuga.sugarboy.quentinmars.utils.logic.GameConfig;
import inc.tortuga.sugarboy.quentinmars.utils.visual.Fonts;
import inc.tortuga.sugarboy.quentinmars.utils.visual.StateManager;

public class Game extends ApplicationAdapter {

	// Статическая ссылка на постоянную игры
	private static Game instance;

	// Ссылки на различные ресурсы
	private GameConfig config;
	private Fonts fonts;
	private SpriteBatch batch;
	private StateManager stateManager;

	// Конструктор класса Game
	public Game(GameConfig config) {
		instance = this;
		this.config = config;
	}

	// Геттер, возвращающий нам экземпляр игры
	public static Game get() {
		return instance;
	}

	// Геттер на менеджер скринов
	public StateManager getStateManager() {
		return stateManager;
	}

	// Геттер на конфиг
	public GameConfig getConfig() {
		return this.config;
	}

	// Гетер на экземпляр с шрифтами
	public Fonts getFonts() {
		return this.fonts;
	}

	// Метод создания скрина
	@Override
	public void create () {
		config.load();
		batch = new SpriteBatch(); // Создаем батч
		fonts = new Fonts(); // Экземпляр шрифтов
		stateManager = new StateManager(); // Менеджер скринов
		stateManager.push(new MainMenuScreen(stateManager)); // Просим у менеджера экранов показать MainMenuScreen
	}

	// Метод отрисовки скрина
	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Чистка экрана
		stateManager.update(Gdx.graphics.getDeltaTime()); // Вызов апдейта для данного скрина
		stateManager.render(batch); // Отрисвка экрана
	}

	// Удаление из кеша ненужных элементов
	@Override
	public void dispose () {
		batch.dispose();
	}
}
