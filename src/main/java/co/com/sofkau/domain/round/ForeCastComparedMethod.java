package co.com.sofkau.domain.round;

import co.com.sofkau.domain.round.values.DiceFace;

import java.util.List;

public class ForeCastComparedMethod {
    private final Integer numberTimes;
    private final Integer diceFace;
    private final List<DiceFace> diceFaceList;
    private final Integer countStage;

    public ForeCastComparedMethod( Integer numberTimes, Integer diceFace, List<DiceFace> diceFaceList, Integer countStage) {

        this.numberTimes = numberTimes;
        this.diceFace = diceFace;
        this.diceFaceList = diceFaceList;
        this.countStage = countStage;
    }


    public boolean compared() {
        Integer contador = 0;
        switch (countStage) {
            case 1:
                return checkStageOne(contador);
            case 2:
                return checkStageTwo(contador);
            case 3:
                return checkStageThree(contador);
        }
        return false;
    }

    private boolean checkStageOne(Integer contador) {
        contador = Compare(contador, 3);
        if (contador >= numberTimes - 3) {
            return true;
        }
        return numberTimes == 3 ? true : false;
    }

    private boolean checkStageTwo(Integer contador) {
        contador = Compare(contador, 5);
        return contador >= numberTimes - 1 ? true : false;
    }


    private boolean checkStageThree(Integer contador) {
        contador = Compare(contador, 6);
        return contador >= numberTimes ? true : false;
    }

    private Integer Compare(Integer contador, int facesShow) {
        for (Integer i = 0; i < facesShow; i++) {
            if (diceFace.equals(diceFaceList.get(i))) {
                contador += 1;
            }
        }
        return contador;
    }



}
