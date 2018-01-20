package inc.tortuga.sugarboy.quentinmars.screens.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Timer;

import inc.tortuga.sugarboy.quentinmars.Game;
import inc.tortuga.sugarboy.quentinmars.utils.visual.ButtonStyle;
import inc.tortuga.sugarboy.quentinmars.utils.visual.FontUtils;
import inc.tortuga.sugarboy.quentinmars.utils.visual.State;
import inc.tortuga.sugarboy.quentinmars.utils.visual.StateManager;

/**
 * Created by swift on 28.10.2017.
 */

public class GameScreen extends State {

    // двумерный массив нашего поля
    private TextButton place[][];

    // Остальные ссылки на экземпляры
    private ButtonStyle styles;
    private Stage stage;

    // Логическая переменная ходит ли сейчас 1
    private boolean isX;
    // Количество поставленных знаков 1 или 0
    private int count;
    // Логическая переменная на вывод сообщения о занятой ячейке
    private boolean warnBusy;
    // Размер поля
    private final int lenght = 3;

    public GameScreen(StateManager manager) {
        super(manager);

        // Создание стадии, подгрузка стилей
        stage = new Stage();
        styles = new ButtonStyle();

        // Перезапускам игру, либо начинаем ее
        restart();

        // Сообщаем движку о том, что нужно обрабатывать щелчки на данной стадии
        Gdx.input.setInputProcessor(stage);
    }

    public void restart() {
        // Устанавливаем количество поставленных символов 0
        count = 0;
        // Создаем массив длинной размера поля
        place = new TextButton[lenght][lenght];
        // Первым ходил 1
        isX = true;

        /*
        * 0 0
        * 0 1
        * 0 2
        *
        * 1 0
        * 1 1
        * 1 2
        *
        * 2 0
        * 2 1
        * 2 2
        * */

        for (int x = 0; x < lenght; x++) {
            for (int y = 0; y < lenght; y++) {

                // Создаем кнопку
                final TextButton button = new TextButton(" ", styles.style());
                // Задаем размер кнопки 100x100
                button.setSize(100F, 100F);

                // Высчитываем позицию кнопки
                button.setPosition(
                        /* TODO Ширина экрана / 2 + (-105 или 0 или +105) - длина кнопки /2 */
                        Gdx.graphics.getWidth() / 2 + ((-(lenght - 1) / 2) + x) * 105F - (button.getWidth() / 2),
                        Gdx.graphics.getHeight() / 2 + ((-(lenght - 1) / 2) + y) * 105F - (button.getHeight() / 2)
                );

                // Создаем константы из-за необходимости структуры языка
                final int _x = x;
                final int _y = y;
                // Создаем обработчик события нажатия
                button.addListener(new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float xx, float yy, int pointer, int b) {

                        // Если кнопка имеет текст не " ", то:
                        if (!button.getText().toString().equals(" ")) {
                            // Делаем warnBusy правдой (Клетка занята)
                            warnBusy = true;
                            // Запускаем таймер, который выключит в последствии надпись
                            Timer.schedule(new Timer.Task() {
                                @Override
                                public void run() {
                                    warnBusy = false;
                                }
                            }, 2F);

                            // Заверщаем обрабтку события
                            return true;
                        }

                        // Применяем констуркцию правда ? да : нет
                        // С помощью данной конструкции устанавливаем для нажатой кнопки либо 1, либо 0
                        button.setText(isX ? "1" : "O");
                        // Меням стиль кнопки (цвет текста)
                        button.setStyle(isX ? styles.forX() : styles.forO());

                        // Проверяем не победил ли игрок
                        if (checkWin(_x, _y)) {
                            // Вызываем метод победы
                            setWin();
                            // Заканчиваем обработку события
                            return true;
                        }

                        // Увеличваем значение ходов
                        count++;

                        // Уточняем остались ли ходы
                        if (count == lenght * lenght) {
                            // Вызываем таймер
                            Timer.schedule(new Timer.Task() {
                                public void run() {
                                    // Вызываем рестарт
                                    restart();
                                }
                            }, 0.5F);
                        }


                        // Меняем значение c true -> false или false -> true
                        isX = !isX;
                        return true;
                    }
                });
                place[x][y] = button;
                stage.addActor(button);
            }
        }
    }

    @Override
    public void update(float dt) {

    }

    // Отрисовка экрана
    @Override
    public void render(SpriteBatch sb) {
        sb.begin();

        // Отрисовываем текст о занятой ячейке
        if (warnBusy) {
            BitmapFont font = Game.get().getFonts().main;
            FontUtils.sendCenter(font, sb, "Данная ячейка уже занята", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 80);
        }

        // Через цикл отрисовываем все кнопки
        for (TextButton[] buttons : place) {
            for (TextButton button : buttons) {
                button.draw(sb, 1);
            }
        }

        sb.end();
    }

    public void setWin() {
        // Запуска таймер
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                // Требуем от скрин менеджера установить экран WinGameScreen с победителем 1 или 0
                manager.push(new WinGameScreen(manager, isX ? '1' : 'O'));
            }
        }, 0.5F);
    }

    public boolean checkWin(int _x, int _y) {
        String lines = getLine(_x, _y, 1, 0) + getLine(_x, _y, 0, 1) + getLine(_x, _y, 1, 1) + getLine(_x, _y, 1, -1);
        Gdx.app.log("TestWin", lines.replace(" ", "_"));
        return lines.contains("111") || lines.contains("OOO");
    }

    private String getLine(int _x, int _y, int offsetX, int offsetY) {
        String line = "";
        for (int q = -2; q < 3; q++) {
            int x = _x + q * offsetX;
            int y = _y + q * offsetY;
            if (x < 0 || y < 0 || x > lenght - 1 || y > lenght - 1) continue;
            line += place[x][y].getText();
        }
        Gdx.app.log("TestWin", line.replace(" ", "_"));
        return line + "-";
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
