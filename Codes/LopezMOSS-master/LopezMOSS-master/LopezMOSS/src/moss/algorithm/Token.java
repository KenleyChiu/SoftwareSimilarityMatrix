package moss.algorithm;

import java.util.Objects;

@SuppressWarnings("unused")
public class Token {
    public enum TYPE{
        EOL, NUMBER, WORD, STRING_LITERAL, IGNORE, OTHER, NONE
    }


    private final int id;
    private final int lineNo;
    @SuppressWarnings("FieldCanBeLocal")
    private final Object value;

    @SuppressWarnings("WeakerAccess")
    public static class TokenBuilder {
        private int id;
        private int lineNo;
        private Object value;
        private TYPE type;

        /**
         *
         * @param type token type of the token to be built
         * @return returns the called builder (Stream)
         * @see TYPE
         */
        @SuppressWarnings({"SameParameterValue", "UnusedReturnValue"})
        final TokenBuilder setType(TYPE type){
            return setTypeWithValue(type, null);
        }

        /**
         * @param type token type of the token to be built
         * @param value underlying value of the token (e.g. a number for number tokens)
         * @param <T> specifies the type of <i>value</i>
         * @return returns the called builder (Stream)
         * @see TYPE
         */
        final <T> TokenBuilder setTypeWithValue(TYPE type, T value){
            this.type = type;
            this.value = value;
            //NOTE: this null check is necessary because if there is no SPECIFIC value for a particular token,
            //it is unnecessary to separate its identity from other already existing tokens of precisely the same type
            if (this.value == null)
                this.id = this.type.hashCode();
            else
                this.id = this.value.hashCode();
            return this;
        }


        /**
         * @param lineNo Line number of the token to be built
         * @return returns the called builder (Stream)
         */
        TokenBuilder setLineNo(int lineNo) {
            this.lineNo = lineNo;
            return this;
        }


        /**
         *
         * @return returns a built token with the entered values
         *
         */
        public Token createToken() {
            return new Token(id, lineNo, value);
        }
    }


    private Token(int hash, int lineNo, Object value) {
        this.id = hash;
        this.lineNo = lineNo;
        this.value = value;
    }

    /**
     * @return returns the token's line number
     */
    public final int getLineNo() {
        return lineNo;
    }

    /**
     * @return returns the auto-generated identifier of the token
     */
    public final int getId() {
        return id;
    }

    /**
     * @param other token being compared to
     * @return equality of the tokens (based on their Id)
     */
    @Override
    public final boolean equals(Object other){
        //TODO: Write motivation for using only id as the identifier of the class
        if (!(other instanceof Token)) return false;
        Token ref = (Token)other;
        return ref.id == this.id;
    }

    /**
     * The hash code is based on the hash of the token's ID.
     * This has the consequence of two non-valued tokens getting the same ID
     * @return hash code of the token
     */
    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }





}
