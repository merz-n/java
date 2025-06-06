public class ArrayProcess {

    public int[] getElementsAfterLastFour(int[] arr){
        int lastIndex = -1;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == 4){
                lastIndex = i;
            }
        }
        if(lastIndex == -1){
            throw new RuntimeException("Not 4 in Array");
        }
        int[] result = new int[arr.length - lastIndex -1];
        System.arraycopy(arr,lastIndex+1, result,0,result.length);
        return result;
    }

    public boolean containsOnlyOneAndFour(int[] arr){
        boolean hasOne = false;
        boolean hasFour = false;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == 1){
                hasOne = true;
            } else if (arr[i] == 4) {
                hasFour = true;
            }else{
                return false;
            }
        }
        return hasOne && hasFour;

    }

}
