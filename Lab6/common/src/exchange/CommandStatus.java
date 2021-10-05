package exchange;

import java.io.Serializable;

public enum CommandStatus implements Serializable {
    LOGGED,
    WRONG_USERNAME,
    WRONG_PASSWORD,
    OK,
    NEED_GROUP,
    NEED_PERSON,
    UPDATE,
    ERROR
}
