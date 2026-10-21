package org.example.enums;

public enum JsonTokenId {
    /**
     * Id used to represent {@link JsonToken#NOT_AVAILABLE}, used in
     * cases where a token may become available when more input
     * is available: this occurs in non-blocking use cases.
     */
    ID_NOT_AVAILABLE(-1),

    /**
     * Id used to represent the case where no {@link JsonToken}
     * is available: either because {@link JsonParser} has not been
     * advanced to first token, or because no more tokens will be
     * available (end-of-input or explicit closing of parser}.
     */
    ID_NO_TOKEN(0),

    /**
     * Id used to represent {@link JsonToken#START_OBJECT}
     */
    ID_START_OBJECT(1),

    /**
     * Id used to represent {@link JsonToken#END_OBJECT}
     */
    ID_END_OBJECT(2),

    /**
     * Id used to represent {@link JsonToken#START_ARRAY}
     */
    ID_START_ARRAY(3),

    /**
     * Id used to represent {@link JsonToken#END_ARRAY}
     */
    ID_END_ARRAY(4),

    /**
     * Id used to represent {@link JsonToken#PROPERTY_NAME}
     */
    ID_PROPERTY_NAME(5),

    /**
     * Id used to represent {@link JsonToken#VALUE_STRING}
     */
    ID_STRING(6),

    /**
     * Id used to represent {@link JsonToken#VALUE_NUMBER_INT}
     */
    ID_NUMBER_INT(7),

    /**
     * Id used to represent {@link JsonToken#VALUE_NUMBER_FLOAT}
     */
    ID_NUMBER_FLOAT(8),

    /**
     * Id used to represent {@link JsonToken#VALUE_TRUE}
     */
    ID_TRUE(9),

    /**
     * Id used to represent {@link JsonToken#VALUE_FALSE}
     */
    ID_FALSE(10),
    /**
     * Id used to represent {@link JsonToken#VALUE_NULL}
     */

    ID_NULL(11),

    /**
     * Id used to represent {@link JsonToken#VALUE_EMBEDDED_OBJECT}
     */
    ID_EMBEDDED_OBJECT(12);

    public static final int Z = 1;


    private int val;

    JsonTokenId(int val) {
        this.val = val;
    }

    int getVal() {
        return this.val;
    }
}
