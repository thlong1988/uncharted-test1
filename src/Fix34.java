import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fix34 {

  /**
   * Return an array that contains exactly the same numbers as the given array, but rearranged so
   * that every 3 is immediately followed by a 4. Do not move the 3's, but every other number may
   * move. The array contains the same number of 3's and 4's, every 3 has a number after it that is
   * not a 3, and a 3 appears in the array before any 4.
   * <p>
   * fix34([1, 3, 1, 4]) returns [1, 3, 4, 1]
   * fix34([1, 3, 1, 4, 4, 3, 1]) returns [1, 3, 4, 1, 1,3, 4]
   * fix34([3, 2, 2, 4]) returns [3, 4, 2, 2]
   *
   * @param arrInput: array need to rearrange
   * @return an array have arranged
   * @throws Exception if the array invalid
   */
  public static int[] fix34_ver1(int[] arrInput) throws Exception {
    if (!checkArraryInputIsValid(arrInput)) {
      throw new Exception("Array is invalid");
    }
    // find all index of number 4 and number 3
    List<Integer> lstIndexOfNumber4 = new ArrayList<>();
    List<Integer> lstIndexOfNumber3 = new ArrayList<>();
    for (int i = 0; i < arrInput.length; i++) {
      if (arrInput[i] == 4) {
        lstIndexOfNumber4.add(i);
      }
      if (arrInput[i] == 3) {
        lstIndexOfNumber3.add(i);
      }
    }
    // rearranged
    for (int i = 0; i < lstIndexOfNumber3.size(); i++) {
      int indexOfNumber3 = lstIndexOfNumber3.get(i);
      int indexOfNumber4 = lstIndexOfNumber4.get(0);
      lstIndexOfNumber4.remove(0);

      // swap
      int temp = arrInput[indexOfNumber3 + 1];
      arrInput[indexOfNumber3 + 1] = 4;
      arrInput[indexOfNumber4] = temp;

    }
    return arrInput;
  }

  public static int[] fix34_ver2(int[] arrInput) throws Exception {
    if (!checkArraryInputIsValid(arrInput)) {
      throw new Exception("Array is invalid");
    }
    int markIndexOfNumber4 = 0;
    for (int i = 0; i < arrInput.length; i++) {
      if (arrInput[i] == 3) {
        for (int j = markIndexOfNumber4; j < arrInput.length; j++) {
          if (arrInput[j] == 4) {
            // mark index for next loop to find number 4
            markIndexOfNumber4 = j + 1;

            // swap
            int temp = arrInput[i + 1];
            arrInput[i + 1] = 4;
            arrInput[j] = temp;

            break;
          }
        }
      }
    }
    return arrInput;
  }

  private static boolean checkArraryInputIsValid(int[] arrInput) {
    int numberOf3 = 0, numberOf4 = 0;
    for (int i = 0; i < arrInput.length; i++) {
      if (arrInput[i] == 3) {
        numberOf3++;
        // check every 3 have a number after it that is not a 3 and 3 is not last
        if (i == arrInput.length - 1 || arrInput[i + 1] == 3) {
          return false;
        }
      }
      if (arrInput[i] == 4) {
        numberOf4++;
      }
    }
    // check array has the same number of 3's and 4's
    if (numberOf3 != numberOf4 || numberOf3 == 0) {
      return false;
    }

    // check a 3 before any 4
    boolean markFoundNumber3 = false;
    boolean markFoundNumber4 = false;
    for (int i = 0; i < arrInput.length; i++) {
      // number 4 appears first
      if (arrInput[i] == 3) {
        markFoundNumber3 = true;
        if (!markFoundNumber4) {
          break;
        }
      }
      // number 4 appears first
      if (arrInput[i] == 4) {
        markFoundNumber4 = true;
        if (!markFoundNumber3) {
          return false;
        }
      }
    }
    return true;
  }

  public static void main(String[] args) throws Exception {
    int[] arrrInput = {3, 2, 2, 4};
    System.out.println(Arrays.toString(fix34_ver1(arrrInput)));
    System.out.println(Arrays.toString(fix34_ver2(arrrInput)));
  }
}
