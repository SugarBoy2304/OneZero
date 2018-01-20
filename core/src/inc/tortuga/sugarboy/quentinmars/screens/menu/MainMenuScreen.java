package inc.tortuga.sugarboy.quentinmars.screens.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import inc.tortuga.sugarboy.quentinmars.Game;
import inc.tortuga.sugarboy.quentinmars.utils.visual.ButtonStyle;
import inc.tortuga.sugarboy.quentinmars.utils.visual.FontUtils;
import inc.tortuga.sugarboy.quentinmars.utils.visual.State;
import inc.tortuga.sugarboy.quentinmars.utils.visual.StateManager;

/**
 * Created by swift on 22.10.2017.
 */

public class MainMenuScreen extends State {

    // Ссылка на переменную y - высота текста
    private int y;

    // Ссылки на ресурсы
    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private TextButton button;
    private ButtonStyle styles;

    public MainMenuScreen(final StateManager manager) {
        super(manager);

        // Создаем стадию
        stage = new Stage();
        // Генерируем стили
        styles = new ButtonStyle();

        // Подгружаем все текстуры для кнопок из атласа
        atlas = new TextureAtlas("ui/gui.atlas");
        skin = new Skin(atlas);

        // Создаем виртуальную таблицу
        table = new Table(skin);

        // Создаем кнопку Играть
        button = new TextButton("Играть", styles.style());
        // Смещение от границ в таблице
        button.pad(20F);
        // Размер самой кнопки
        button.setSize(400F, 120F);

        /* TODO Длина всего экрана / 2 - (длина кнопки / 2) */
        button.setPosition(
                Gdx.graphics.getWidth() / 2 - (button.getWidth() / 2)
                , 80);

        // Добавляем для кнопки события нажатие
        button.addListener(new InputListener() {

            // Наследуем метод нажатия
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // Включаем экран самой игры
                manager.push(new GameScreen(manager));

                // Удаляем из памяти ненужные елементы
                dispose();
                return true;
            }
        });

        // Добавляем в нашу таблицу кнопку
        table.add(button);
        // Добавляем на стадию актера в виде нашей таблице
        stage.addActor(table);

        // И задаем для движка в качестве источника входа нашу стадию
        Gdx.input.setInputProcessor(stage);
    }

    // Метод апдейт, который смещает текст на 100 единиц в секунду
    // DT - deltaTime - разница в миллисекундах между обновлениями экрана
    @Override
    public void update(float dt) {
        stage.act(dt);
        y += dt * 100;
    }

    // Отрисовка экрана
    @Override
    public void render(SpriteBatch sb) {
        sb.begin();

        // Рисуем текст
        FontUtils.sendCenter(Game.get().getFonts().main_big, sb, "Единички-нолики", Gdx.graphics.getWidth() / 2, (int) ((float) (Math.sin(y * 0.025) * 100) + 460));
        // Рисуем кнопку
        button.draw(sb, 1);

        sb.end();
    }

    @Override
    public void dispose() {
        atlas.dispose();
        skin.dispose();

    }

}
