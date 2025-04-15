public class ArrayValueCalculator {
    String[][] correctArray = {
            {"1", "2", "3", "4"},
            {"5", "6", "7", "8"},
            {"9", "10", "11", "12"},
            {"13", "14", "15", "16"}
    };
    String[][] wrongSizeArray = {
            {"1", "2", "3"},
            {"4", "5", "6"},
            {"7", "8", "9"}
    };
    String[][] wrongDataArray = {
            {"1", "2", "3", "4"},
            {"5", "x", "7", "8"},
            {"9", "10", "11", "12"},
            {"13", "14", "15", "16"}
    };

    public int doCalc(String[][] array) throws ArrayDataException, ArraySizeException{
        if (array.length !=4){
            throw new ArraySizeException("Incorrect number of lines. Expected 4, received:  " + array.length);
        };

        for(int i= 0; i < array.length;i++){
            if (array[i].length !=4){
                throw new ArraySizeException("Incorrect number of columns in a row "  + i + "Expected 4, received:  " + array[i].length);
            };

        };
        int sum = 0;
        for(int i= 0; i<array.length;i++){
            for(int j= 0; j < array[i].length;j++){
               try {
                   sum += Integer.parseInt(array[i][j]);
               } catch (NumberFormatException e){
                   throw new ArrayDataException("Incorrect data in cell [" + i + "][" + j + "]: '" + array[i][j] + "'");

               }

            }


        }
        return sum;
    }

}
