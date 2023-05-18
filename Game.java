import java.util.Random;
import java.util.Scanner;

public class Game {
    private static final int game_width = 10;
    private static final int game_height = 20;
    private static int wolf_pos = game_width / 2;
    private static final int MAX_EGGS = 10;

    public static void main(String[] args) {
        boolean[][] gameBoard = new boolean[game_height][game_width];
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int score = 0;
        int eggsCaught = 0;

        System.out.println("Ну, погоди! — Волк ловит яйца");
        System.out.println("Инструкции: используйте клавиши влево и вправо для передвижения волка.");

        while (eggsCaught < MAX_EGGS) {
            // Генерация нового яйца
            int eggColumn = random.nextInt(game_width);
            gameBoard[0][eggColumn] = true;

            // Отрисовка игрового поля
            for (int i = 0; i < game_height; i++) {
                for (int j = 0; j < game_width; j++) {
                    if (gameBoard[i][j]) {
                        System.out.print("O");
                    } else if (i ==  game_height- 1 && j ==  wolf_pos) {
                        System.out.print("V");
                    } else {
                        System.out.print(".");
                    }
                }
                System.out.println();
            }

            // Передвижение волка
            System.out.print("Введите команду (L - влево, R - вправо): ");
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("L")) {
                if ( wolf_pos > 0) {
                    wolf_pos--;
                }
            } else if (command.equalsIgnoreCase("R")) {
                if ( wolf_pos < game_width - 1) {
                    wolf_pos++;
                }
            }

            // Обработка пойманных яиц
            if (gameBoard[game_height - 1][ wolf_pos]) {
                gameBoard[game_height - 1][ wolf_pos] = false;
                eggsCaught++;
                score += 10;
                System.out.println("Поймано яйцо! Ваш счет: " + score);
            }

            // Смещение яиц вниз
            for (int i = game_height - 2; i >= 0; i--) {
                for (int j = 0; j < game_width; j++) {
                    gameBoard[i + 1][j] = gameBoard[i][j];
                }
            }

            // Очистка верхнего ряда
            for (int j = 0; j < game_width; j++) {
                gameBoard[0][j] = false;
            }
        }

        System.out.println("Игра окончена! Ваш итоговый счет: " + score);
        scanner.close();
    }}
