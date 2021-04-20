package commands;

import exception.WrongArgumentException;
import utils.GroupCollection;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InfoCommand extends GeneralCommand {
    private GroupCollection groupCollection;

    public InfoCommand(GroupCollection groupCollection) {
        super("info ", "вывести информацию о коллекции");
        this.groupCollection = groupCollection;
    }

    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgumentException();
            LocalDateTime lastInitTime = groupCollection.getLastInitTime();
            String lastInitTimeString = (lastInitTime == null) ? "в данной сессии инициализации еще не происходило" :
                    lastInitTime.toLocalDate().toString() + " " + lastInitTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            LocalDateTime lastSaveTime = groupCollection.getLastSaveTime();
            String lastSaveTimeString = (lastSaveTime == null) ? "в данной сессии сохранения еще не происходило" :
                    lastSaveTime.toLocalDate().toString() + " " + lastSaveTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            System.out.println("Сведения о коллекции:");
            System.out.println(" Тип: " + groupCollection.collectionType());
            System.out.println(" Количество элементов: " + groupCollection.collectionSize());
            System.out.println(" Дата последнего сохранения: " + lastSaveTimeString);
            System.out.println(" Дата последней инициализации: " + lastInitTimeString);
            return true;
        } catch (WrongArgumentException exception) {
            System.err.println("Неправильное использование: '" + getName() + "'");
        }
        return false;
    }
}
