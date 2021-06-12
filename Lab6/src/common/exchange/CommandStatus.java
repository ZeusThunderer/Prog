package common.exchange;

import java.io.Serializable;

public enum CommandStatus implements Serializable {
    OK,
    NEED_GROUP,
    NEED_PERSON,
    ERROR,
    SAVE,
    EXIT
}
