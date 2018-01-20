package inc.tortuga.sugarboy.quentinmars.screens.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import inc.tortuga.sugarboy.quentinmars.Game;
import inc.tortuga.sugarboy.quentinmars.utils.visual.ButtonStyle;
import inc.tortuga.sugarboy.quentinmars.utils.visual.FontUtils;
import inc.tortuga.sugarboy.quentinmars.utils.visual.State;
import inc.tortuga.sugarboy.quentinmars.utils.visual.StateManager;

/**
 * Created by swift on 28.10.2017.
 */

public class WinGameScreen extends State {

    private Stage stage;
    private TextButton button;
    private ButtonStyle styles;

    // Символ победителя
    private char winner;

    public WinGameScreen(final StateManager manager, char winner) {
        super(manager);
        // Устанавливаемсимвол побителя
        this.winner = winner;

        // Созаем стадию, подгружаем стили
        stage = new Stage();
        styles = new ButtonStyle();

        // Создаем кнопку и делаем цвет побителя
        button = new TextButton("В главное меню", winner == '1' ? styles.forX() : styles.forO());
        // Смещение на 20 пикселей
        button.pad(20F);
        // размер 400x120
        button.setSize(400F, 120F);
        // позиция посередине экрана
        button.setPosition(Gdx.graphics.getWidth() / 2 - button.getWidth() / 2, 80);

        // добавялем обработчик событий
        button.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // Обращаемся к скрин менеджеру и устанавливаем экран главного меню
                manager.push(new MainMenuScreen(manager));
                // Выгружаем ненужные элементы
                dispose();
                return true;
            }
        });

        // Сообщаем движку о обработке данной стадии
        Gdx.input.setInputProcessor(stage);

        // Добавляем на стадию нашу кнопку
        stage.addActor(button);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();

        // Собщение о победителе
        FontUtils.sendCenter(Game.get().getFonts().main, sb, "Победили " + (winner == '1' ? "Единички" : "Нолики"), Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 80);
        // Отрисовка кнопки
        button.draw(sb, 1);

        sb.end();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
