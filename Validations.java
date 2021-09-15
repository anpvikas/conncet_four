public class Validations {

    /**
     * Validates the user input to check if input is a number
     * 
     * @param value user Input
     * @return true when number
     */
    public static boolean isNumeric(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Validates the user input to check is input in in range of board columns
     * 
     * @param value user input column number
     * @param min
     * @param max
     * @return
     */
    public static boolean isInRange(int value, int min, int max) {
        if (value >= min && value <= max) {
            return true;
        }
        return false;
    }

    /**
     * Validates the user input for all validations
     * 
     * @param input   user input
     * @param columns number of columns in board
     * @return
     */
    public static boolean validateUserInput(String input, int columns) {
        if (isNumeric(input)) {
            if (isInRange(Integer.parseInt(input), 1, columns)) {
                return true;
            }
        }
        return false;
    }
}
